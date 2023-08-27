package com.vteam.unit.service.sipa.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vteam.unit.entity.mapper.SipaRtfMMapper;
import com.vteam.unit.entity.model.CspaFunM;
import com.vteam.unit.entity.model.SipaBtrM;
import com.vteam.unit.entity.model.SipaRtfM;
import com.vteam.unit.entity.vo.SipaRolMVo;
import com.vteam.unit.entity.vo.SipaRtfMVo;
import com.vteam.unit.service.sipa.SipaRtfMService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * <p>
 * 角色与权限关联表 服务实现类
 * </p>
 *
 * @author vteam-generator
 * @since 2018-07-20
 */
@Service
public class SipaRtfMServiceImpl extends ServiceImpl<SipaRtfMMapper, SipaRtfM> implements SipaRtfMService {

    @Resource(type = SipaRtfMMapper.class)
    private SipaRtfMMapper sipaRtfMMapper;

    @Override
    public void removeRtfByRoleId(SipaRolMVo role) {
        remove(new QueryWrapper<SipaRtfM>().lambda().eq(SipaRtfM::getRoleid, role.getRoleid()));
    }

    @Override
    public SipaRtfMVo saveRoleFunRelation(SipaRolMVo sipaRoleMVo, CspaFunM cspaFunM) {
        SipaRtfMVo sipaRtfMVo = new SipaRtfMVo();
        sipaRtfMVo.setRoleid(sipaRoleMVo.getRoleid());
        sipaRtfMVo.setFunid(cspaFunM.getFunid());
        save(sipaRtfMVo);
        return sipaRtfMVo;
    }

    @Override
    public List<Integer> listRoleFunByRoleId(SipaRolMVo sipaRoleMVo) {
        return sipaRtfMMapper.listFunRefcodesByRoleInfo(sipaRoleMVo);
    }

    @Override
    public List<Integer> listRoleByUser(List<SipaBtrM> sipaBtrMS) {
        List<Integer> funRefcodeList = new ArrayList<>();
        for (SipaBtrM btr : sipaBtrMS) {
            SipaRolMVo sipaRolMVo = new SipaRolMVo();
            sipaRolMVo.setRoleid(btr.getRoleid());
            List<Integer> funCode = sipaRtfMMapper.listFunRefcodesByRoleInfo(sipaRolMVo);
            funRefcodeList.addAll(funCode);
        }
        //去重
        List<Integer> funRefcode = new ArrayList<>(new TreeSet<>(funRefcodeList));
        return funRefcode;
    }

    @Override
    public List<Integer> listFunRefcodesByUserInfo(SipaRtfMVo sipaRtfMVo) {
        List<Integer> funCodes = sipaRtfMMapper.listFunRefcodesByUserInfo(sipaRtfMVo);
        return funCodes;
    }

    @Override
    public List<Integer> listAdminFun(SipaRtfMVo sipaRtfMVo) {
        SipaRolMVo sipaRolMVo = new SipaRolMVo();
        sipaRolMVo.setRoleid(sipaRtfMVo.getRoleid());
        sipaRtfMVo.setBrandStyle(sipaRtfMVo.getBrandStyle());
        return sipaRtfMMapper.listFunRefcodesByRoleInfo(sipaRolMVo);
    }

    @Override
    public List<Integer> listRoleFunWithoutLimitByRoleId(SipaRolMVo sipaRoleMVo) {
        return sipaRtfMMapper.listFunRefcodesWithoutLimitByRoleInfo(sipaRoleMVo);
    }

    @Override
    public List<SipaRtfM> getRoleListByFunId(String funId) {
        LambdaQueryWrapper<SipaRtfM> wr = new QueryWrapper<SipaRtfM>().lambda();
        wr.eq(SipaRtfM::getFunid, funId);
        return super.list(wr);
    }

}
