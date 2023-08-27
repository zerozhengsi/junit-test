package com.vteam.unit.service.sipa.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vteam.unit.common.RequestStore;
import com.vteam.unit.constant.GlobalConstants;
import com.vteam.unit.entity.mapper.SipaBtrMMapper;
import com.vteam.unit.entity.model.SipaBtrM;
import com.vteam.unit.entity.model.SipaBurM;
import com.vteam.unit.entity.model.SipaRolM;
import com.vteam.unit.entity.vo.SipaBtrMVo;
import com.vteam.unit.entity.vo.SipaBurMVo;
import com.vteam.unit.entity.vo.SipaRolMVo;
import com.vteam.unit.service.sipa.SipaBtrMService;
import com.vteam.unit.service.sipa.SipaRolMService;
import com.vteam.unit.util.StringUtils;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户与角色关联表 服务实现类
 * </p>
 *
 * @author vteam-generator
 * @since 2018-07-20
 */
@Service
public class SipaBtrMServiceImpl extends ServiceImpl<SipaBtrMMapper, SipaBtrM> implements SipaBtrMService {

    @Resource(type = SipaRolMService.class)
    private SipaRolMService sipaRolMService;

    /**
     * 角色信息-流水号
     */
    private final static Integer ROLE_INFO_REFCODE = 1;

    /**
     * 角色信息-名称
     */
    private final static Integer ROLE_INFO_NAME = 2;

    /**
     * 角色信息-代号
     */
    private final static Integer ROLE_INFO_ID = 3;

    @Override
    public SipaBtrMVo saveUserOrgRelation(SipaBurMVo sipaBurM, SipaRolM sipaRolM) {
        SipaBtrMVo sipaBtrMVo = new SipaBtrMVo();
        sipaBtrMVo.setUserid(sipaBurM.getUserid());
        sipaBtrMVo.setRoleid(sipaRolM.getRoleid());
        sipaBtrMVo.setOrgRefcode(sipaRolM.getOrgRefcode());
        save(sipaBtrMVo);
        return sipaBtrMVo;
    }

    @Override
    public void removeRoleRelation(SipaBurM sipaBurM) {
        LambdaQueryWrapper<SipaBtrM> wr = new QueryWrapper<SipaBtrM>().lambda();
        wr.eq(SipaBtrM::getUserid, sipaBurM.getUserid());
        wr.eq(SipaBtrM::getOrgRefcode, RequestStore.getLoginUser().getOrgRefcode());
        remove(wr);
    }

    @Override
    public List<SipaBtrM> listRoleInfoByUserId(SipaBurMVo sipaBurMVo) {
        LambdaQueryWrapper<SipaBtrM> wr = new QueryWrapper<SipaBtrM>().lambda();
        wr.eq(SipaBtrM::getUserid, sipaBurMVo.getUserid());
        List<SipaBtrM> sipaBtrMS = list(wr);
        return sipaBtrMS;
    }

    @Override
    public String getRoleRefcodesByUserInfo(SipaBurMVo sipaBurMVo) {
        return getRoleInfosByUserInfo(sipaBurMVo, ROLE_INFO_REFCODE);
    }

    @Override
    public String getRoleNamesByUserInfo(SipaBurMVo sipaBurMVo) {
        return getRoleInfosByUserInfo(sipaBurMVo, ROLE_INFO_NAME);
    }

    @Override
    public String getRoleidsByUserInfo(SipaBurMVo sipaBurMVo) {
        return getRoleInfosByUserInfo(sipaBurMVo, ROLE_INFO_ID);
    }

    /**
     * 根据用户信息查询所属角色对应字段集合字符串(多个逗号拼接)
     * @param sipaBurMVo
     * @param infoType 信息类型，见类常量
     * @return
     * @author oscar.yu
     * @date 2019/12/6 14:42
     */
    private String getRoleInfosByUserInfo(SipaBurMVo sipaBurMVo, int infoType) {
        String roleInfos = StringUtils.EMPTY;
        List<SipaBtrM> btrMList = this.listRoleInfoByUserId(sipaBurMVo);
        if (CollectionUtils.isNotEmpty(btrMList)) {
            // 根据角色id查询用户所属角色信息放入对应角色
            List<SipaRolM> rolMList = sipaRolMService.listRoleInfoByRoleId(btrMList);
            if (CollectionUtils.isNotEmpty(btrMList)) {
                ((ArrayList<SipaRolM>) rolMList).trimToSize();
                String[] roleInfoArr = new String[rolMList.size()];
                String roleInfo = null;
                for (int i = 0; i < rolMList.size(); i++) {
                    if (ROLE_INFO_REFCODE == infoType) {
                        roleInfo = rolMList.get(i).getRefcode().toString();
                    } else if (ROLE_INFO_NAME == infoType) {
                        roleInfo = rolMList.get(i).getRoleName();
                    }
                    roleInfoArr[i] = roleInfo;
                }
                roleInfos = String.join(GlobalConstants.Symbol.COMMA, roleInfoArr);
            }
        }
        return roleInfos;
    }

    @Override
    public int countUserByRoleInfo(SipaRolM sipaRolM) {
        LambdaQueryWrapper<SipaBtrM> wr = new QueryWrapper<SipaBtrM>().lambda();
        wr.eq(SipaBtrM::getRoleid, sipaRolM.getRoleid());
        int count = (int)count(wr);
        return count;
    }

    @Override
    public void saveOperationUserInfo(SipaBurMVo condition) {
        SipaBtrMVo sipaBtrMVo = new SipaBtrMVo();
        sipaBtrMVo.setUserid(condition.getUserid());
        sipaBtrMVo.setRoleid(condition.getRoleid());
        super.save(sipaBtrMVo);
    }

    @Override
    public void removeOperationUserInfo(SipaBurMVo condition) {
        LambdaQueryWrapper<SipaBtrM> wr = new QueryWrapper<SipaBtrM>().lambda();
        if(StringUtils.isNotBlank(condition.getOlduserid())) {
        	// 考虑到用户名更改的情况,先删除旧userid对应的角色
        	wr.eq(SipaBtrM::getUserid, condition.getOlduserid());
        } else {
        	wr.eq(SipaBtrM::getUserid, condition.getUserid());
        }
        if (condition.getOrgRefcode() != null){
            wr.eq(SipaBtrM::getOrgRefcode,condition.getOrgRefcode());
        }
        super.remove(wr);
    }

    @Override
    public void updateOperationUserInfo(SipaBurMVo condition) {
        // 删除用户角色信息
        this.removeOperationUserInfo(condition);
        // 新增用户角色信息
        this.saveOperationUserInfo(condition);
    }

    @Override
    public List<SipaRolMVo> listRoleInfoByCondition(List<SipaRolMVo> sipaRolMList) {
        if (CollectionUtils.isNotEmpty(sipaRolMList)) {
            for (SipaRolMVo sipaRolMVo : sipaRolMList) {
                LambdaQueryWrapper<SipaBtrM> wr = new QueryWrapper<SipaBtrM>().lambda();
                wr.eq(SipaBtrM::getRoleid, sipaRolMVo.getRoleid());
                int count = (int)super.count(wr);
                sipaRolMVo.setRolUserCount(count);
            }
        }
        return sipaRolMList;
    }

    @Override
    public void saveWeixinUserInfo(SipaBurMVo condition) {
        SipaBtrMVo sipaBtrMVo = new SipaBtrMVo();
        sipaBtrMVo.setUserid(condition.getUserid());
        sipaBtrMVo.setRoleid(condition.getRoleid());
        super.save(sipaBtrMVo);
    }

}
