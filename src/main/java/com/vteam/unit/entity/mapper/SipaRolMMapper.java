package com.vteam.unit.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vteam.unit.entity.model.SipaRolM;
import com.vteam.unit.entity.vo.SipaBurMVo;
import com.vteam.unit.entity.vo.SipaRolMVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色基本信息表 Mapper 接口
 * </p>
 *
 * @author vteam-generator
 * @since 2018-07-23
 */
public interface SipaRolMMapper extends BaseMapper<SipaRolM> {

    /**
     * 校验角色名在当前企业下是否存在
     *
     * @param sipaRolMVo
     * @return java.lang.Integer
     * @author fu.tong
     * @date 2018/9/3 16:07
     */
    Integer validOrgRoleName(SipaRolMVo sipaRolMVo);

    /**
     * 修改时校验角色名是否存在
     *
     * @param sipaRolMVo
     * @return java.lang.Integer
     * @author fu.tong
     * @date 2018/9/3 17:51
     */
    Integer validOrgRoleNameInUpdate(SipaRolMVo sipaRolMVo);

    /**
     * 获取角色列表根据条件 .
     *
     * @param condition
     * @return java.util.List<com.vteam.sme.entity.vo.SipaRolMVo>
     * @author zack.yin
     * @date 2018/9/10 11:06
     */
    List<SipaRolMVo> listRoleInfoByCondition(SipaRolMVo condition);


    /**
     * 根据用户信息获取角色列表 .
     *
     * @param sipaBurMVo
     * @return java.util.List<com.vteam.unit.entity.vo.SipaRolMVo>
     * @author andy.sher
     * @date 2019/7/17 10:30
     */
    List<SipaRolMVo> listRoleInfoByUserInfo(SipaBurMVo sipaBurMVo);

    /**
     * 根据审批流节点ID获取角色列表 .
     *
     * @param nodeRefcode
     * @return java.util.List<com.vteam.unit.entity.vo.SipaRolMVo>
     * @author andy.sher
     * @date 2019/7/17 9:32
     */
    List<SipaRolMVo> listByFlowNodeRefcode(@Param("nodeRefcode") Integer nodeRefcode);

    SipaRolMVo getRoleInfoByUserId(String userid);
}
