package com.vteam.unit.service.sipa;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vteam.unit.entity.model.SipaBtrM;
import com.vteam.unit.entity.model.SipaBurM;
import com.vteam.unit.entity.model.SipaRolM;
import com.vteam.unit.entity.vo.SipaBtrMVo;
import com.vteam.unit.entity.vo.SipaBurMVo;
import com.vteam.unit.entity.vo.SipaRolMVo;

import java.util.List;

/**
 * <p>
 * 用户与角色关联表 服务类
 * </p>
 *
 * @author vteam-generator
 * @since 2018-07-20
 */
public interface SipaBtrMService extends IService<SipaBtrM> {

    /**
     * 新增用户角色关联
     *
     * @param sipaBurM
     * @param sipaRolM
     * @return com.vteam.sme.entity.model.SipaBtrM
     * @author fu.tong
     * @date 2018/7/25  09:37
     */
    SipaBtrMVo saveUserOrgRelation(SipaBurMVo sipaBurM, SipaRolM sipaRolM);

    /**
     * 根据用户id删除用户角色关联
     *
     * @param sipaBurM
     * @return void
     * @author fu.tong
     * @date 2018/7/25  09:38
     */
    void removeRoleRelation(SipaBurM sipaBurM);

    /**
     * 根据用户id查询所属角色信息
     *
     * @param sipaBurMVo 用户信息
     * @return java.util.List<com.vteam.sme.entity.model.SipaBtrM>
     * @author fu.tong
     * @date 2018/7/27  11:53
     */
    List<SipaBtrM> listRoleInfoByUserId(SipaBurMVo sipaBurMVo);
    
    /**
     * 根据用户信息查询所属角色流水号集合字符串(多个逗号拼接)
     * @param sipaBurMVo
     * @return roleNames
     * @author oscar.yu
     * @date 2019/12/6 14:18
     */
    String getRoleRefcodesByUserInfo(SipaBurMVo sipaBurMVo);
    
    /**
     * 根据用户信息查询所属角色名称集合字符串(多个逗号拼接)
     * @param sipaBurMVo
     * @return roleNames
     */
    String getRoleNamesByUserInfo(SipaBurMVo sipaBurMVo);
    
    /**
     * 根据用户信息查询所属角色代号集合字符串(多个逗号拼接)
     * @param sipaBurMVo
     * @return roleNames
     */
    String getRoleidsByUserInfo(SipaBurMVo sipaBurMVo);

    /**
     * 查询当前角色是否有关联角色
     *
     * @param sipaRolM
     * @return int
     * @author fu.tong
     * @date 2018/7/30 0030 13:38
     */
    int countUserByRoleInfo(SipaRolM sipaRolM);

    /**
     * 新增运营人员用户信息 .
     *
     * @param condition
     * @return void
     * @author zack.yin
     * @date 2018/9/4 10:49
     */
    void saveOperationUserInfo(SipaBurMVo condition);

    /**
     * 删除用户角色 .
     *
     * @param condition
     * @return void
     * @author zack.yin
     * @date 2018/9/7 10:10
     */
    void removeOperationUserInfo(SipaBurMVo condition);

    /**
     * 修改用户角色 .
     *
     * @param condition
     * @return void
     * @author zack.yin
     * @date 2018/9/7 10:48
     */
    void updateOperationUserInfo(SipaBurMVo condition);

    /**
     * 获取用户角色信息(角色数量) .
     *
     * @param sipaRolMList
     * @return java.util.List<com.vteam.sme.entity.vo.SipaRolMVo>
     * @author zack.yin
     * @date 2018/9/13 15:27
     */
    @Deprecated
    List<SipaRolMVo> listRoleInfoByCondition(List<SipaRolMVo> sipaRolMList);

    /**
     * 新增微信小程序用户授权信息 .
     *
     * @param condition
     * @return void
     * @author zack.yin
     * @date 2018/9/4 10:49
     */
    void saveWeixinUserInfo(SipaBurMVo condition);
}
