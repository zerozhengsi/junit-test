package com.vteam.unit.service.sipa;

import com.baomidou.mybatisplus.extension.service.IService;
import com.vteam.unit.entity.model.SipaBurM;
import com.vteam.unit.entity.vo.SipaBurMVo;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author vteam-generator
 * @since 2018-07-20
 */
public interface SipaBurMService extends IService<SipaBurM> {

    /**
     * 重置密码
     *
     * @param sipaBurMVo 修改信息
     * @return boolean 标记
     * @author fu.tong
     * @date 2018/8/2 0002 09:25
     */
    boolean updatePassword(SipaBurMVo sipaBurMVo);

    /**
     * 按用户代号获取用户信息 .
     *
     * @param userid     用户代号
     * @param systemType 用户所属系统类型
     * @return com.vteam.sme.entity.vo.SipaBurMVo 用户信息
     * @author andy.sher
     * @date 2018/8/28 16:15
     */
    SipaBurMVo getUserInfoByUserid(String userid, String systemType);

    /**
     * 按用户代号获取用户信息 .
     *
     * @param userid 用户代号
     * @return com.vteam.sme.entity.vo.SipaBurMVo 用户信息
     * @author andy.sher
     * @date 2018/8/28 16:15
     */
    SipaBurMVo getUserInfoByUserid(String userid);

    /**
     * 按手机号查询记录数 .
     *
     * @param mobilephone 手机号
     * @return int 记录数
     * @author andy.sher
     * @date 2018/9/26 13:43
     */
    int countByMobilephone(String mobilephone);

    /**
     * 按手机号和用户所属系统类型获取用户信息 .
     *
     * @param mobilephone 手机号
     * @param systemType  用户所属系统类型
     * @return com.vteam.sme.entity.vo.SipaBurMVo 用户信息
     * @author andy.sher
     * @date 2018/9/20 15:57
     */
    SipaBurMVo getUserInfoByMobilephone(String mobilephone, String systemType);

    /**
     * 按手机号和用户所属系统类型获取用户信息 .
     *
     * @param mobilephone 手机号
     * @param systemType  用户所属系统类型
     * @param userRefcode 排除的用户流水号
     * @return com.vteam.sme.entity.vo.SipaBurMVo 用户信息
     * @author andy.sher
     * @date 2018/9/20 15:57
     */
    SipaBurMVo getUserInfoByMobilephoneExist(String mobilephone, String systemType, Integer userRefcode);

    /**
     * 按loginId(包含手机号或邮箱)和用户所属系统类型获取用户信息 .
     *
     * @param loginId    登录ID
     * @param systemType 用户所属系统类型
     * @return com.vteam.sme.entity.vo.SipaBurMVo 用户信息
     * @author andy.sher
     * @date 2018/9/20 15:57
     */
    SipaBurMVo getUserInfoByLoginId(String loginId, String systemType);

    /**
     * 根据微信openid获取用户信息 .
     *
     * @param openid
     * @return com.vteam.sme.entity.vo.SipaBurMVo
     * @author fu.tong
     * @date 2019/4/9 14:52
     */
    SipaBurMVo getUserInfoByOpenid(String openid, String systemType);

    /**
     * 获取运营人员用户列表 .
     *
     * @param condition
     * @return java.util.List<com.vteam.sme.entity.vo.SipaBurMVo>
     * @author zack.yin
     * @date 2018/9/3 14:23
     */
    List<SipaBurMVo> listOperationUserByCondition(SipaBurMVo condition);

    /**
     * 新增运营人员用户信息 .
     *
     * @param condition
     * @return void
     * @author zack.yin
     * @date 2018/9/3 18:40
     */
    void saveOperationUserInfo(SipaBurMVo condition);

    /**
     * 查询运营人员用户信息 .
     *
     * @param refcode
     * @return com.vteam.sme.entity.vo.SipaBurMVo
     * @author zack.yin
     * @date 2018/9/3 20:19
     */
    SipaBurMVo getOperationUserInfo(Integer refcode);

    /**
     * 修改运营人员用户状态 .
     *
     * @param condition
     * @return void
     * @author zack.yin
     * @date 2018/9/3 20:28
     */
    void updateOperationUserStatus(SipaBurMVo condition);

    /**
     * 修改运营人员用户信息 .
     *
     * @param condition
     * @author zack.yin
     * @date 2018/9/3 20:46
     */
    void updateOperationUserInfo(SipaBurMVo condition);

    /**
     * 删除运营人员用户信息 .
     *
     * @param condition
     * @return void
     * @author zack.yin
     * @date 2018/9/3 20:52
     */
    void removeOperationUserInfo(SipaBurMVo condition);


    /**
     * 按审批流节点流水号获取用户列表（个人模式） .
     *
     * @param flowNodeRefcode 审批流节点流水号
     * @return java.util.List<com.vteam.mssme.entity.vo.SipaBurMVo> 用户列表
     * @author andy.sher
     * @date 2019/7/17 11:48
     */
    List<SipaBurMVo> listUserInfoByFlowNodeRefcode(Integer orgRefcode, Integer flowNodeRefcode);

    /**
     * 按审批流节点流水号获取用户列表（角色模式） .
     *
     * @param flowNodeRefcode 审批流节点流水号
     * @return java.util.List<com.vteam.mssme.entity.vo.SipaBurMVo> 用户列表
     * @author andy.sher
     * @date 2019/7/17 11:48
     */
    List<SipaBurMVo> listRoleModeUserInfoByFlowNodeRefcode(Integer orgRefcode, Integer flowNodeRefcode);

    /**
     * 根据角色及地区获取用户
     * @param area    客户所在地区
     * @param roleid  多个角色
     * @return
     */
    List<SipaBurMVo> listUser4Role(String area, String roleid);
}
