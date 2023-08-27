package com.vteam.unit.service.sipa.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vteam.unit.constant.FieldConstant;
import com.vteam.unit.entity.mapper.SipaRolMMapper;
import com.vteam.unit.entity.model.SipaBtrM;
import com.vteam.unit.entity.model.SipaRolM;
import com.vteam.unit.entity.vo.SipaBurMVo;
import com.vteam.unit.entity.vo.SipaRolMVo;
import com.vteam.unit.service.common.CommonService;
import com.vteam.unit.service.sipa.SipaRolMService;
import com.vteam.unit.util.SmeAssert;
import com.vteam.unit.util.StringUtils;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 角色基本信息表 服务实现类
 * </p>
 *
 * @author vteam-generator
 * @since 2018-07-20
 */
@Service
public class SipaRolMServiceImpl extends ServiceImpl<SipaRolMMapper, SipaRolM> implements SipaRolMService {
    @Resource(type = SipaRolMMapper.class)
    private SipaRolMMapper sipaRolMMapper;
    @Autowired
    private CommonService commonService;

    /**
     * 新增角色信息
     *
     * @param sipaRoleMVo
     * @return com.vteam.sme.entity.model.SipaRolM
     * @author fu.tong
     * @date 2018/7/25 0025 10:44
     */
    @Override
    public SipaRolMVo saveRoleInfo(SipaRolMVo sipaRoleMVo) {
        SmeAssert.notNull(sipaRoleMVo.getRoleName(), "角色名称不能为空。");
        SmeAssert.eq(sipaRolMMapper.validOrgRoleName(sipaRoleMVo), 0, "角色名称重复");

        sipaRoleMVo.setRoleid(UUID.randomUUID().toString());
        super.save(sipaRoleMVo);
        return sipaRoleMVo;
    }

    @Override
    public SipaRolMVo updateRoleInfo(SipaRolMVo sipaRoleMVo) {
        SmeAssert.notNull(sipaRoleMVo.getRoleName(), "角色名称不能为空。");
        SmeAssert.eq(sipaRolMMapper.validOrgRoleNameInUpdate(sipaRoleMVo), 0, "角色名称重复");

        SipaRolM sipaRolM = super.getById(sipaRoleMVo.getRefcode());
        sipaRoleMVo.setRefcode(sipaRolM.getRefcode());
        updateById(sipaRoleMVo);
        sipaRoleMVo.setRoleid(sipaRolM.getRoleid());
        return sipaRoleMVo;
    }

    @Override
    public void deleteRoleInfo(SipaRolMVo sipaRoleMVo) {
        sipaRoleMVo.setDelFlag(FieldConstant.PublicFieldValue.DEL_FLAG_YES);
        updateById(sipaRoleMVo);
    }

    @Override
    public List<SipaRolM> listRoleInfoByRoleId(List<SipaBtrM> list) {
        List<SipaRolM> rolMList = new ArrayList<SipaRolM>();
        for (SipaBtrM sipaBtrM : list) {
            LambdaQueryWrapper<SipaRolM> wr = new QueryWrapper<SipaRolM>().lambda();
            wr.eq(SipaRolM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
            wr.eq(SipaRolM::getRoleid, sipaBtrM.getRoleid());
            SipaRolM btrM = getOne(wr);
            if (btrM != null) {
                rolMList.add(btrM);
            }
        }
        return rolMList;
    }


    @Override
    public List<SipaRolMVo> listBackRoleInfo() {
        List<SipaRolMVo> sipaRolMVoList = new ArrayList<>();
        LambdaQueryWrapper<SipaRolM> wr = new QueryWrapper<SipaRolM>().lambda();
        wr.eq(SipaRolM::getSystemType, FieldConstant.SipaRolM.SYSTEM_TYPE_BACK);
        wr.eq(SipaRolM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        wr.eq(SipaRolM::getUseFlag, FieldConstant.SipaRolM.USE_FLAG_ENABLE);
        List<SipaRolM> sipaRolMList = super.list(wr);
        if (CollectionUtils.isNotEmpty(sipaRolMList)) {
            for (SipaRolM sipaRolM : sipaRolMList) {
                SipaRolMVo sipaRolMVo = new SipaRolMVo();
                BeanUtils.copyProperties(sipaRolM, sipaRolMVo);
                sipaRolMVoList.add(sipaRolMVo);
            }
        }
        return sipaRolMVoList;
    }

    @Override
    public List<SipaRolMVo> listRoleInfoByCondition(SipaRolMVo condition) {
        List<SipaRolMVo> sipaRolMVoList = sipaRolMMapper.listRoleInfoByCondition(condition);
        if (CollectionUtils.isNotEmpty(sipaRolMVoList)) {
            for (SipaRolMVo sipaRolMVo : sipaRolMVoList) {
                // 一般企业管理员和核心企业管理员为 企业端类型
                if (FieldConstant.SipaRolM.ROLE_ID_GENERAL.equals(sipaRolMVo.getRoleid()) || FieldConstant.SipaRolM.ROLE_ID_CORE.equals(sipaRolMVo.getRoleid())) {
                    sipaRolMVo.setRoleType(FieldConstant.SipaRolM.ROLE_TYPE_COMPANY);
                }
                // 机构端管理员为机构端
                if (FieldConstant.SipaRolM.ROLE_ID_FINANCIAL.equals(sipaRolMVo.getRoleid())) {
                    sipaRolMVo.setRoleType(FieldConstant.SipaRolM.ROLE_TYPE_FINANCIAL);
                }
                // 合伙人管理员为合伙人端
                if (FieldConstant.SipaRolM.ROLE_ID_PARTNER.equals(sipaRolMVo.getRoleid())) {
                    sipaRolMVo.setRoleType(FieldConstant.SipaRolM.ROLE_TYPE_PARTNER);
                }
                // 后端角色为运营端
                if (FieldConstant.SipaRolM.SYSTEM_TYPE_BACK.equals(sipaRolMVo.getSystemType())) {
                    sipaRolMVo.setRoleType(FieldConstant.SipaRolM.ROLE_TYPE_BACK);
                }
            }
        }
        return sipaRolMVoList;
    }

    @Override
    public SipaRolMVo saveBackRoleInfo(SipaRolMVo sipaRoleMVo) {
        SmeAssert.notNull(sipaRoleMVo.getRoleName(), "角色名称不能为空。");
        // 添加后台角色  判断后台角色是否重复
        int countBackRole = this.countBackRoleByRole(sipaRoleMVo);
        SmeAssert.le(countBackRole, 0, "角色名称重复。");

        sipaRoleMVo.setRoleid(UUID.randomUUID().toString());
        sipaRoleMVo.setSystemType(FieldConstant.PublicFieldValue.SYSTEM_TYPE_MANAGE);

        sipaRoleMVo.setRefcode(commonService.getMaxRefcodeByTableName("SIPA_BTP_M"));
        super.save(sipaRoleMVo);
        return sipaRoleMVo;
    }

    @Override
    public SipaRolMVo updateBackRoleInfo(SipaRolMVo sipaRoleMVo) {
        SmeAssert.notNull(sipaRoleMVo.getRoleName(), "角色名称不能为空。");
        SipaRolM sipaRolM = super.getById(sipaRoleMVo.getRefcode());
        // 如果修改后台角色信息  需要判断角色名是否重复
        if (FieldConstant.SipaRolM.SYSTEM_TYPE_BACK.equals(sipaRolM.getSystemType())) {
            int countBackRole = this.countBackRoleByRole(sipaRoleMVo);
            SmeAssert.le(countBackRole, 0, "角色名称重复。");
        }
        sipaRoleMVo.setRefcode(sipaRolM.getRefcode());
        super.updateById(sipaRoleMVo);
        sipaRoleMVo.setRoleid(sipaRolM.getRoleid());
        return sipaRoleMVo;
    }

    @Override
    public SipaRolM getRoleInfo(SipaRolMVo sipaRolMVo) {
        LambdaQueryWrapper<SipaRolM> wr = new QueryWrapper<SipaRolM>().lambda();
        wr.eq(SipaRolM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        if (StringUtils.isNotEmpty(sipaRolMVo.getRoleid())) {
            wr.eq(SipaRolM::getRoleid, sipaRolMVo.getRoleid());
        }
        SipaRolM sipaRolM = getOne(wr);

        return sipaRolM;
    }

    @Override
    public SipaRolMVo getRoleInfoByUserId(String userid) {
        return sipaRolMMapper.getRoleInfoByUserId(userid);
    }

    @Override
    public List<SipaRolMVo> listRoleInfoByUserInfo(SipaBurMVo sipaBurMVo) {
        return sipaRolMMapper.listRoleInfoByUserInfo(sipaBurMVo);
    }

    /**
     * 通过条件查询后台角色数量 .
     *
     * @param sipaRoleMVo
     * @return int
     * @author zack.yin
     * @date 2018/9/14 10:45
     */
    private int countBackRoleByRole(SipaRolMVo sipaRoleMVo) {
        int count = 0;
        LambdaQueryWrapper<SipaRolM> wr = new QueryWrapper<SipaRolM>().lambda();
        if (sipaRoleMVo.getRefcode() != null && sipaRoleMVo.getRefcode() > 0) {
            wr.ne(SipaRolM::getRefcode, sipaRoleMVo.getRefcode());
        }
        wr.eq(SipaRolM::getSystemType, FieldConstant.SipaRolM.SYSTEM_TYPE_BACK);
        wr.eq(SipaRolM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        wr.eq(SipaRolM::getRoleName, sipaRoleMVo.getRoleName());

        count = (int)super.count(wr);
        return count;
    }

    @Override
    public List<SipaRolMVo> listWexinXcxRoleInfo() {
        List<SipaRolMVo> sipaRolMVoList = new ArrayList<>();
        LambdaQueryWrapper<SipaRolM> wr = new QueryWrapper<SipaRolM>().lambda();
        wr.eq(SipaRolM::getSystemType, FieldConstant.SipaRolM.SYSTEM_TYPE_FRONT);
        wr.eq(SipaRolM::getRoleType, FieldConstant.SipaRolM.ROLE_TYPE_WEIXIN_XCX);
        wr.eq(SipaRolM::getDelFlag, FieldConstant.PublicFieldValue.DEL_FLAG_NO);
        wr.eq(SipaRolM::getUseFlag, FieldConstant.SipaRolM.USE_FLAG_ENABLE);
        List<SipaRolM> sipaRolMList = super.list(wr);
        if (CollectionUtils.isNotEmpty(sipaRolMList)) {
            for (SipaRolM sipaRolM : sipaRolMList) {
                SipaRolMVo sipaRolMVo = new SipaRolMVo();
                BeanUtils.copyProperties(sipaRolM, sipaRolMVo);
                sipaRolMVoList.add(sipaRolMVo);
            }
        }
        return sipaRolMVoList;
    }

    @Override
    public List<SipaRolMVo> listByFlowNodeRefcode(Integer nodeRefcode) {
        return sipaRolMMapper.listByFlowNodeRefcode(nodeRefcode);
    }

}
