package com.vteam.unit.service.sipa.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vteam.unit.common.RequestStore;
import com.vteam.unit.constant.FieldConstant;
import com.vteam.unit.constant.GlobalConstants;
import com.vteam.unit.entity.mapper.SipaBurMMapper;
import com.vteam.unit.entity.model.SipaBurM;
import com.vteam.unit.entity.vo.SipaBurMVo;
import com.vteam.unit.service.sipa.SipaBurMService;
import com.vteam.unit.util.MD5Utils;
import com.vteam.unit.util.SmeAssert;
import com.vteam.unit.util.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author vteam-generator
 * @since 2018-07-20
 */
@Service
public class SipaBurMServiceImpl extends ServiceImpl<SipaBurMMapper, SipaBurM> implements SipaBurMService {

    @Resource(type = SipaBurMMapper.class)
    private SipaBurMMapper sipaBurMMapper;

    @Override
    public boolean updatePassword(SipaBurMVo sipaBurMVo) {
        final String loginId = sipaBurMVo.getLoginId();
        SipaBurMVo sipaBurM = getUserInfoByPhoneOrEmailOrLoginId(loginId, FieldConstant.SipaBurM.SYSTEM_TYPE_OPERATION);
        if (null == sipaBurM) {
            return false;
        }
        sipaBurM.setChgpwdFlag(GlobalConstants.ArabicNumeral.N0);
        sipaBurM.setPassword(MD5Utils.encryptPwd(sipaBurMVo.getConfirmPassword()));
        return super.updateById(sipaBurM);
    }

    /**
     * 根据手机号码和邮箱修改密码
     *
     * @param loginId 登录账号
     * @param systemType 用户类型
     * @return com.vteam.unit.entity.vo.SipaBurMVo
     * @author xiu.fu
     * @date 2019/9/23 0023 下午 7:04
     */
    private SipaBurMVo getUserInfoByPhoneOrEmailOrLoginId(String loginId, String systemType) {
        LambdaQueryWrapper<SipaBurM> wrapper = new QueryWrapper<SipaBurM>().lambda();
        wrapper.eq(SipaBurM::getSystemType, systemType);
        // 增加删除标记为否的条件
        wrapper.eq(SipaBurM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        if (StringUtils.isEmail(loginId)) {
            wrapper.eq(SipaBurM::getEmail, loginId);
        } else if (StringUtils.isPhoneNum(loginId)) {
            wrapper.eq(SipaBurM::getMobilephone, loginId);
        } else {
            wrapper.eq(SipaBurM::getLoginId, loginId);
        }
        SipaBurM sipaBurM = super.getOne(wrapper);
        SipaBurMVo sipaBurMVo = null;
        if (null != sipaBurM) {
            sipaBurMVo = new SipaBurMVo();
            BeanUtils.copyProperties(sipaBurM, sipaBurMVo);
        }
        // 无需过滤密码
        return sipaBurMVo;
    }

    @Override
    public SipaBurMVo getUserInfoByUserid(String userid, String systemType) {
        LambdaQueryWrapper<SipaBurM> wr = new QueryWrapper<SipaBurM>().lambda();
        wr.eq(SipaBurM::getUserid, userid);
        if (StringUtils.isNotEmpty(systemType)) {
            wr.eq(SipaBurM::getSystemType, systemType);
        }
        wr.eq(SipaBurM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);

        SipaBurM sipaBurM = super.getOne(wr);
        SipaBurMVo sipaBurMVo = null;
        if (null != sipaBurM) {
            sipaBurMVo = new SipaBurMVo();
            BeanUtils.copyProperties(sipaBurM, sipaBurMVo);
        }
        // 无需过滤密码
        return sipaBurMVo;
    }

    @Override
    public SipaBurMVo getUserInfoByUserid(String userid) {
        return getUserInfoByUserid(userid, null);
    }


    @Override
    public SipaBurMVo getUserInfoByMobilephone(String mobilephone, String systemType) {
        return this.getUserInfoByMobilephoneExist(mobilephone, systemType, null);
    }

    @Override
    public SipaBurMVo getUserInfoByMobilephoneExist(String mobilephone, String systemType, Integer userRefcode) {
        LambdaQueryWrapper<SipaBurM> wrapper = new QueryWrapper<SipaBurM>().lambda();
        wrapper.eq(SipaBurM::getMobilephone, mobilephone);
        wrapper.eq(SipaBurM::getSystemType, systemType);
        // 增加删除标记为否的条件
        wrapper.eq(SipaBurM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        // 增加排除的过滤条件
        if (null != userRefcode && userRefcode > 0) {
            wrapper.ne(SipaBurM::getRefcode, userRefcode);
        }
        SipaBurM sipaBurM = super.getOne(wrapper);
        // 无需过滤密码
        SipaBurMVo sipaBurMVo = com.vteam.unit.util.BeanUtils.copy(sipaBurM, SipaBurMVo.class);
        return sipaBurMVo;
    }

    @Override
    public int countByMobilephone(String mobilephone) {
        LambdaQueryWrapper<SipaBurM> wrapper = new QueryWrapper<SipaBurM>().lambda();
        wrapper.eq(SipaBurM::getMobilephone, mobilephone);
        wrapper.eq(SipaBurM::getSystemType, FieldConstant.SipaBurM.SYSTEM_TYPE_PORTAL);
        // 用户表不在考虑品牌问题  2019-06-26
        // wrapper.eq(SipaBurM::getBrandRefcode, smeConfiguration.getBrandRefcode());
        wrapper.eq(SipaBurM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        return (int)super.count(wrapper);
    }

    @Override
    public SipaBurMVo getUserInfoByLoginId(String loginId, String systemType) {
        if (StringUtils.isBlank(loginId)) {
            return null;
        }
        LambdaQueryWrapper<SipaBurM> wrapper = new QueryWrapper<SipaBurM>().lambda();

        wrapper.eq(SipaBurM::getSystemType, systemType);
        // 增加删除标记为否的条件
        wrapper.eq(SipaBurM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        wrapper.and((item) -> item.eq(SipaBurM::getEmail, loginId)
                .or(i -> i.eq(SipaBurM::getMobilephone, loginId))
                .or(i -> i.eq(SipaBurM::getLoginId, loginId)));
        SipaBurM sipaBurM = super.getOne(wrapper);
        // 无需过滤密码
        SipaBurMVo sipaBurMVo = com.vteam.unit.util.BeanUtils.copy(sipaBurM, SipaBurMVo.class);
        return sipaBurMVo;
    }

    @Override
    public SipaBurMVo getUserInfoByOpenid(String openid, String systemType) {
        LambdaQueryWrapper<SipaBurM> wrapper = new QueryWrapper<SipaBurM>().lambda();
        wrapper.eq(SipaBurM::getWeixinOpenId, openid);
        wrapper.eq(SipaBurM::getSystemType, systemType);
        // 增加删除标记为否的条件
        wrapper.eq(SipaBurM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        SipaBurM sipaBurM = super.getOne(wrapper);
        SipaBurMVo sipaBurMVo = null;
        if (null != sipaBurM) {
            sipaBurM.setPassword(null);
            sipaBurMVo = com.vteam.unit.util.BeanUtils.copy(sipaBurM, SipaBurMVo.class);
        }
        return sipaBurMVo;
    }

    @Override
    public List<SipaBurMVo> listOperationUserByCondition(SipaBurMVo condition) {
        List<SipaBurMVo> sipaBurMVoList = sipaBurMMapper.listOperationUserByCondition(condition);
        return sipaBurMVoList;
    }

    @Override
    public void saveOperationUserInfo(SipaBurMVo condition) {
        // 判断用户代号是否存在
        this.checkUseridExist(null, condition.getUserid(), FieldConstant.SipaBurM.SYSTEM_TYPE_OPERATION);

        // 判断用户手机号是否存在
        this.checkMobilephoneExist(null, condition.getMobilephone(), FieldConstant.SipaBurM.SYSTEM_TYPE_OPERATION);

        condition.setSystemType(FieldConstant.SipaBurM.SYSTEM_TYPE_OPERATION);
        condition.setUseFlag(FieldConstant.SipaBurM.USE_FLAG_ENABLE);
        super.save(condition);
    }

    @Override
    public SipaBurMVo getOperationUserInfo(Integer refcode) {
        SipaBurMVo sipaBurMVo = sipaBurMMapper.getOperationUserInfo(refcode);
        return sipaBurMVo;
    }

    @Override
    public void updateOperationUserStatus(SipaBurMVo condition) {
        SmeAssert.notNull(condition.getRefcode(), "用户不存在。");
        SmeAssert.notBlank(condition.getUseFlag(), "是否启用不能为空。");
        SipaBurM sipaBurM = super.getById(condition.getRefcode());
        SmeAssert.notNull(sipaBurM, "用户不存在。");
        SipaBurMVo sipaBurMVo = new SipaBurMVo();
        sipaBurMVo.setRefcode(condition.getRefcode());
        sipaBurMVo.setUseFlag(condition.getUseFlag());
        super.updateById(sipaBurMVo);

    }

    @Override
    public void updateOperationUserInfo(SipaBurMVo condition) {
        // 判断用户代号是否存在
        this.checkUseridExist(condition.getRefcode(), condition.getUserid(), FieldConstant.SipaBurM.SYSTEM_TYPE_OPERATION);

        // 判断用户手机号是否存在
        this.checkMobilephoneExist(condition.getRefcode(), condition.getMobilephone(), FieldConstant.SipaBurM.SYSTEM_TYPE_OPERATION);

        SipaBurM sipaBurM = super.getById(condition.getRefcode());
        SmeAssert.notNull(sipaBurM, "用户不存在。");
        condition.setRefcode(sipaBurM.getRefcode());
        super.updateById(condition);
    }

    @Override
    public void removeOperationUserInfo(SipaBurMVo condition) {
        SmeAssert.notNull(condition.getRefcode(), "用户不存在。");
        SipaBurM sipaBurM = super.getById(condition.getRefcode());

        // 删除逻辑判断
        SmeAssert.notNull(sipaBurM, "用户不存在。");
        SmeAssert.ne(FieldConstant.SipaBurM.ADMIN_FLAG_YES, sipaBurM.getAdminFlag(), "不允许删除超级管理员。");
        SmeAssert.ne(RequestStore.getLoginUser().getUserid(), sipaBurM.getUserid(), "删除的目标用户不能为自身。");

        SipaBurMVo sipaBurMVo = new SipaBurMVo();
        BeanUtils.copyProperties(sipaBurM, sipaBurMVo);
        sipaBurMVo.setDelFlag(FieldConstant.PublicFieldValue.DEL_FLAG_YES);
        super.updateById(sipaBurMVo);
    }

    @Override
    public List<SipaBurMVo> listUserInfoByFlowNodeRefcode(Integer orgRefcode, Integer flowNodeRefcode) {
        return sipaBurMMapper.listUserModeUserInfoByFlowNodeRefcode(orgRefcode, flowNodeRefcode);
    }

    @Override
    public List<SipaBurMVo> listRoleModeUserInfoByFlowNodeRefcode(Integer orgRefcode, Integer flowNodeRefcode) {
        return sipaBurMMapper.listRoleModeUserInfoByFlowNodeRefcode(orgRefcode, flowNodeRefcode);
    }

    /**
     * 根据角色及地区获取用户
     * @param area    客户所在地区
     * @param roleid  多个角色
     * @return
     */
    public List<SipaBurMVo> listUser4Role(String area, String roleid){
        return sipaBurMMapper.listUser4Role(area,roleid);
    }

    /**
     * 判断用户代号是否存在 .
     *
     * @param refcode    当前数据流水号
     * @param userid     用户代号
     * @param systemType 用户类型
     * @return boolean 是否存在
     */
    private void checkUseridExist(Integer refcode, String userid, String systemType) {
        // 判断用户是否存在
        LambdaQueryWrapper<SipaBurM> wr = new QueryWrapper<SipaBurM>().lambda();
        wr.eq(SipaBurM::getUserid, userid);
        wr.eq(SipaBurM::getSystemType, systemType);
        wr.eq(SipaBurM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        if (null != refcode && refcode > 0) {
            wr.ne(SipaBurM::getRefcode, refcode);
        }
        int count = (int)count(wr);
        SmeAssert.le(count, 0, "用户账号已存在。");
    }
    /**
     * 判断用户手机号是否存在 .
     *
     * @param refcode     当前数据流水号
     * @param mobilephone 手机号
     * @param systemType  用户类型
     * @return boolean 是否存在
     * @author andy.sher
     * @date 2019/4/2 15:20
     */
    private void checkMobilephoneExist(Integer refcode, String mobilephone, String systemType) {
        // 判断用户是否存在
        LambdaQueryWrapper<SipaBurM> wr = new QueryWrapper<SipaBurM>().lambda();
        wr.eq(SipaBurM::getMobilephone, mobilephone);
        wr.eq(SipaBurM::getSystemType, systemType);
        wr.eq(SipaBurM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        if (null != refcode && refcode > 0) {
            wr.ne(SipaBurM::getRefcode, refcode);
        }
        int count = (int)count(wr);
        SmeAssert.le(count, 0, "该手机号已存在。");
    }


}
