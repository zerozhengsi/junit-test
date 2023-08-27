package com.vteam.unit.service.sipa;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vteam.unit.entity.model.CspaFunM;
import com.vteam.unit.entity.model.SipaBtrM;
import com.vteam.unit.entity.model.SipaRtfM;
import com.vteam.unit.entity.vo.SipaRolMVo;
import com.vteam.unit.entity.vo.SipaRtfMVo;

import java.util.List;

/**
 * <p>
 * 角色与权限关联表 服务类
 * </p>
 *
 * @author vteam-generator
 * @since 2018-07-20
 */
public interface SipaRtfMService extends IService<SipaRtfM> {

    /**
     * 根据角色id删除关联权限
     *
     * @param role
     *            角色信息
     * @return void
     * @author fu.tong
     * @date 2018/7/25 10:14
     */
    void removeRtfByRoleId(SipaRolMVo role);

    /**
     * 新增角色权限关联
     *
     * @param sipaRolM
     *            角色信息
     * @param cspaFunM
     *            权限信息
     * @return com.vteam.sme.entity.model.SipaRtfM
     * @author fu.tong
     * @date 2018/8/2 0002 08:57
     */
    SipaRtfM saveRoleFunRelation(SipaRolMVo sipaRolM, CspaFunM cspaFunM);

    /**
     * 根据角色信息查询关联功能信息
     *
     * @param sipaRoleMVo
     *            角色信息
     * @return java.util.List<java.lang.Integer>
     * @author fu.tong
     * @date 2018/7/31 14:03
     */
    List<Integer> listRoleFunByRoleId(SipaRolMVo sipaRoleMVo);

    /**
     * 查询权限流水号
     *
     * @param sipaBtrMS
     *            关联角色信息
     * @return java.util.List<java.lang.Integer>
     * @author fu.tong
     * @date 2018/8/1 19:34
     */
    List<Integer> listRoleByUser(List<SipaBtrM> sipaBtrMS);

    /**
     * 根据用户信息及 品牌风格查询权限流水号集合
     *
     * @param sipaRtfMVo
     * @return java.util.List<java.lang.Integer>
     * @author fu.tong
     * @date 2019/6/14 09:41
     */
    List<Integer> listFunRefcodesByUserInfo(SipaRtfMVo sipaRtfMVo);

    /**
     * 查询管理员关联权限流水号集合 .
     *
     * @param sipaRtfMVo
     * @return java.util.List<com.vteam.sme.entity.model.SipaRtfM>
     * @author fu.tong
     * @date 2019/3/29 12:42
     */
    List<Integer> listAdminFun(SipaRtfMVo sipaRtfMVo);

    /**
     * 根据角色信息查询关联功能信息（不关联权限表查询）
     *
     * @param sipaRoleMVo
     * @return java.util.List<java.lang.Integer>
     * @author chad.mei
     * @date 2022/8/24 15:19
     */
    List<Integer> listRoleFunWithoutLimitByRoleId(SipaRolMVo sipaRoleMVo);

    /**
     * 根据功能代号查询roleid列表<br>.
     *
     * @author xia.hang
     * @date 2023/6/15 19:41
     * @param funId
     * @return List<SipaRtfM>
    */
    List<SipaRtfM> getRoleListByFunId(String funId);
}
