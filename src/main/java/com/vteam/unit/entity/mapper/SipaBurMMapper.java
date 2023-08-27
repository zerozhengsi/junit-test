package com.vteam.unit.entity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.vteam.unit.entity.model.SipaBurM;
import com.vteam.unit.entity.vo.SipaBurMVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author vteam-generator
 * @since 2022-10-14
 */
public interface SipaBurMMapper extends BaseMapper<SipaBurM> {

    /**
     * 获取运营人员用户列表 .
     *
     * @param condition
     * @return java.util.List<com.vteam.sme.entity.vo.SipaBurMVo>
     * @author zack.yin
     * @date 2018/9/3 14:50
     */
    List<SipaBurMVo> listOperationUserByCondition(SipaBurMVo condition);

    /**
     * 查询运营人员用户信息 .
     *
     * @param refcode
     * @return com.vteam.sme.entity.vo.SipaBurMVo
     * @author zack.yin
     * @date 2018/9/3 20:20
     */
    SipaBurMVo getOperationUserInfo(Integer refcode);

    /**
     * 根据角色及地区获取用户
     * @param area    客户所在地区
     * @param roleid  多个角色
     * @return
     */
    List<SipaBurMVo> listUser4Role(@Param("area") String area, @Param("roleid") String roleid);

    /**
     * 按审批流节点流水号获取用户列表（个人模式） .
     *
     * @param flowNodeRefcode
     * @return java.util.List<>
     * @author andy.sher
     * @date 2019/7/17 11:49
     */
    List<SipaBurMVo> listUserModeUserInfoByFlowNodeRefcode(@Param("orgRefcode") Integer orgRefcode, @Param("flowNodeRefcode") Integer flowNodeRefcode);

    /**
     * 按审批流节点流水号获取用户列表（角色模式） .
     *
     * @param flowNodeRefcode
     * @return java.util.List<com.vteam.mssme.entity.vo.SipaBurMVo>
     * @author andy.sher
     * @date 2019/7/17 11:49
     */
    List<SipaBurMVo> listRoleModeUserInfoByFlowNodeRefcode(@Param("orgRefcode") Integer orgRefcode, @Param("flowNodeRefcode") Integer flowNodeRefcode);
}
