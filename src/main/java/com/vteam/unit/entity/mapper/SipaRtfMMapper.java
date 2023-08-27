package com.vteam.unit.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vteam.unit.entity.model.SipaRtfM;
import com.vteam.unit.entity.vo.SipaRolMVo;
import com.vteam.unit.entity.vo.SipaRtfMVo;

import java.util.List;

/**
 * <p>
 * 角色与权限关联表 Mapper 接口
 * </p>
 *
 * @author vteam-generator
 * @since 2018-07-23
 */
public interface SipaRtfMMapper extends BaseMapper<SipaRtfM> {

    /**
     * 根据角色信息及 品牌风格查询权限流水号集合
     *
     * @param sipaRolMVo
     * @return java.util.List<java.lang.Integer>
     * @author fu.tong
     * @date 2018/7/31  14:02
     */
    List<Integer> listFunRefcodesByRoleInfo(SipaRolMVo sipaRolMVo);

    /**
     * 根据用户信息及 品牌风格查询权限流水号集合 .
     *
     * @param sipaRtfMVo
     * @return java.util.List<java.lang.Integer>
     * @author fu.tong
     * @date 2019/3/28 13:29
     */
    List<Integer> listFunRefcodesByUserInfo(SipaRtfMVo sipaRtfMVo);

    /**
     * 根据角色信息及 品牌风格查询权限流水号集合（不关联权限表查询）
     *
     * @param sipaRolMVo
     * @return java.util.List<java.lang.Integer>
     * @author chad.mei
     * @date 2022/8/24 15:19
     */
    List<Integer> listFunRefcodesWithoutLimitByRoleInfo(SipaRolMVo sipaRolMVo);

}
