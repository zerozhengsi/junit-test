package com.vteam.unit.service.sipa;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vteam.unit.entity.model.SipaBtrM;
import com.vteam.unit.entity.model.SipaRolM;
import com.vteam.unit.entity.vo.SipaBurMVo;
import com.vteam.unit.entity.vo.SipaRolMVo;

import java.util.List;

/**
 * <p>
 * 角色基本信息表 服务类
 * </p>
 *
 * @author vteam-generator
 * @since 2018-07-20
 */
public interface SipaRolMService extends IService<SipaRolM> {

    /**
     * 新增角色信息
     *
     * @param sipaRoleMVo
     * @return com.vteam.sme.entity.model.SipaRolM
     * @author fu.tong
     * @date 2018/7/25  10:44
     */
    SipaRolMVo saveRoleInfo(SipaRolMVo sipaRoleMVo);

    /**
     * 修改角色信息
     *
     * @param sipaRoleMVo 修改信息
     * @return com.vteam.sme.entity.vo.SipaRoleMVo
     * @author fu.tong
     * @date 2018/7/26  18:44
     */
    SipaRolMVo updateRoleInfo(SipaRolMVo sipaRoleMVo);

    /**
     * 删除角色信息
     *
     * @param sipaRoleMVo 删除信息
     * @return void
     * @author fu.tong
     * @date 2018/7/26  18:37
     */
    void deleteRoleInfo(SipaRolMVo sipaRoleMVo);

    /**
     * 根据所有角色id查询角色信息
     *
     * @param list 所有角色id信息
     * @return void
     * @author fu.tong
     * @date 2018/7/27  13:15
     */
    List<SipaRolM> listRoleInfoByRoleId(List<SipaBtrM> list);

    /**
     * 获取后台角色信息 .
     *
     * @param
     * @return java.util.List<com.vteam.sme.entity.vo.SipaRolMVo>
     * @author zack.yin
     * @date 2018/9/6 15:28
     */
    List<SipaRolMVo> listBackRoleInfo();


    /**
     * 获取角色列表根据条件 .
     *
     * @param condition
     * @return java.util.List<com.vteam.sme.entity.vo.SipaRolMVo>
     * @author zack.yin
     * @date 2018/9/10 11:05
     */
    List<SipaRolMVo> listRoleInfoByCondition(SipaRolMVo condition);

    /**
     * 新增后台角色信息 .
     *
     * @param sipaRoleMVo
     * @return com.vteam.sme.entity.vo.SipaRolMVo
     * @author zack.yin
     * @date 2018/9/14 10:31
     */
    SipaRolMVo saveBackRoleInfo(SipaRolMVo sipaRoleMVo);

    /**
     * 运营端修改角色信息 .
     *
     * @param sipaRoleMVo
     * @return com.vteam.sme.entity.vo.SipaRolMVo
     * @author zack.yin
     * @date 2018/9/14 10:39
     */
    SipaRolMVo updateBackRoleInfo(SipaRolMVo sipaRoleMVo);

    /**
     * 获取角色信息 .
     *
     * @param sipaRolMVo
     * @return com.vteam.sme.entity.vo.SipaRolMVo
     * @author fu.tong
     * @date 2019/3/7 15:54
     */
    SipaRolM getRoleInfo(SipaRolMVo sipaRolMVo);

    /**
     * 通过userId查找角色信息<br>.
     *
     * @author xia.hang
     * @date 2023/6/19 18:14
     * @param userid
     * @return SipaRolM
    */
    SipaRolMVo getRoleInfoByUserId(String userid);


    /**
     * 根据用户信息获取角色列表 .
     *
     * @param sipaBurMVo
     * @return java.util.List<com.vteam.unit.entity.vo.SipaRolMVo>
     * @author andy.sher
     * @date 2019/7/17 10:29
     */
    List<SipaRolMVo> listRoleInfoByUserInfo(SipaBurMVo sipaBurMVo);

    /**
     * 获取小程序角色信息 .
     *
     * @since 2023/2/17 14:48
     * @author toby.tang
     * @return List<SipaRolMVo>
     */
    List<SipaRolMVo> listWexinXcxRoleInfo();

    /**
     * 根据审批流节点ID获取角色列表 .
     *
     * @param nodeRefcode
     * @return java.util.List<com.vteam.unit.entity.vo.SipaRolMVo>
     * @author andy.sher
     * @date 2019/7/17 9:31
     */
    List<SipaRolMVo> listByFlowNodeRefcode(Integer nodeRefcode);
    
}
