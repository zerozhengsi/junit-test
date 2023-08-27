package com.vteam.unit.entity.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author vteam-generator
 * @since 2020-01-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SipaBurM implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    @TableId("REFCODE")
    private Integer refcode;

    /**
     * 所属系统类型[1=后台/2=前台]
     */
    @TableField("SYSTEM_TYPE")
    private String systemType;

    /**
     * 用户代号(前台自动生成、后台登录账号)
     */
    @TableField("USERID")
    private String userid;

    /**
     * 用户账号
     */
    @TableField("LOGIN_ID")
    private String loginId;

    /**
     * 用户名
     */
    @TableField("USER_NAME")
    private String userName;

    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 用户登陆时是否要求修改密码[0=否/1=是(default)]
     */
    @TableField("CHGPWD_FLAG")
    private String chgpwdFlag;

    /**
     * 密码有效天数
     */
    @TableField("VALID_DAY")
    private Integer validDay;

    /**
     * 密码到期前警告天数
     */
    @TableField("WARNING_DAY")
    private Integer warningDay;

    /**
     * 密码到期日(据ValidDay算出)
     */
    @TableField("PWD_EXPIRYDATE")
    private LocalDateTime pwdExpirydate;

    /**
     * 上次更改密码时间
     */
    @TableField("CHGPWD_DATE")
    private LocalDateTime chgpwdDate;

    /**
     * 所属地区[HK-香港，TW-台湾，SH-上海]
     */
    @TableField("RELEASE_AREA")
    private String releaseArea;

    /**
     * 用户姓名(无用字段)
     */
    @TableField("FULL_NAME")
    private String fullName;

    /**
     * 手机号码
     */
    @TableField("MOBILEPHONE")
    private String mobilephone;

    /**
     * 电子邮箱
     */
    @TableField("EMAIL")
    private String email;

    /**
     * 座机号码（区号）
     */
    @TableField("TEL_AREA")
    private String telArea;

    /**
     * 座机号码（电话）
     */
    @TableField("TEL")
    private String tel;

    /**
     * 座机号码（分机号）
     */
    @TableField("EXTNO")
    private String extno;

    /**
     * 微信号码
     */
    @TableField("WEIXIN")
    private String weixin;

    /**
     * 微信openId
     */
    @TableField("WEIXIN_OPEN_ID")
    private String weixinOpenId;

    /**
     * 所在地区
     */
    @TableField("AREA_COUNTY")
    private String areaCounty;

    /**
     * 省份代码
     */
    @TableField("PROVINCE_CODE")
    private String provinceCode;

    /**
     * 详细地址
     */
    @TableField("ADDRESS")
    private String address;

    /**
     * 邮政编码
     */
    @TableField("POSTCODE")
    private String postcode;

    /**
     * 联系人职称/职务
     */
    @TableField("JOBPOSITION")
    private String jobposition;

    /**
     * 身份证号码
     */
    @TableField("ID_NO")
    private String idNo;

    /**
     * 上次登录地址
     */
    @TableField("LAST_LOGIN_ADDRESS")
    private String lastLoginAddress;

    /**
     * 上次登录时间
     */
    @TableField("LAST_LOGIN_DATE")
    private LocalDateTime lastLoginDate;

    /**
     * 所属品牌流水号（用户类型为金融机构时为0）
     */
    @TableField("BRAND_REFCODE")
    private Integer brandRefcode;

    /**
     * 会员注册时品牌流水号（用于消息提醒）
     */
    @TableField("REGISTER_BRAND_REFCODE")
    private Integer registerBrandRefcode;

    /**
     * 前台用户类型[1=企业/2=金融机构/3=合作机构/9=个人]
     */
    @TableField("WEB_USER_TYPE")
    private String webUserType;

    /**
     * 手机验证状态[0=未验证/1=已验证]
     */
    @TableField("MOBILE_VAILD_STATUS")
    private String mobileVaildStatus;

    /**
     * 邮箱验证状态[0=未验证/1=已验证]
     */
    @TableField("EMAIL_VAILD_STATUS")
    private String emailVaildStatus;

    /**
     * 备注
     */
    @TableField("REMARK")
    private String remark;

    /**
     * 数据来源[I=正常(default,PC)/A=APP/X=小程序/W=微信/H=H5]
     */
    @TableField("SOURCE")
    private String source;

    /**
     * 会员主题样式(如：dynamic_deepblue)
     */
    @TableField("THEME_STYLE")
    private String themeStyle;

    /**
     * 是否超级管理员[1=是/0=否]
     */
    @TableField("ADMIN_FLAG")
    private String adminFlag;

    /**
     * 是否启用[0=禁用/1=启用]
     */
    @TableField("USE_FLAG")
    private String useFlag;

    /**
     * 企业名称
     */
    @TableField("ORGNAME")
    private String orgname;

    /**
     * 统一社会信用代码/注册号
     */
    @TableField("COMPANY_CREDIT_CODE")
    private String companyCreditCode;

    /**
     * 收款银行账号
     */
    @TableField("ACCOUNT_NO")
    private String accountNo;

    /**
     * 开户银行名称
     */
    @TableField("BANK_NAME")
    private String bankName;

    /**
     * 客户经理
     */
    @TableField("CLIENT_MANAGER")
    private String clientManager;

    /**
     * 客户经理手机号
     */
    @TableField("CLIENT_MANAGER_PHONE")
    private String clientManagerPhone;

    /**
     * 法人代表
     */
    @TableField("LEGAL_PERSON")
    private String legalPerson;

    /**
     * 法人身份证号码
     */
    @TableField("LEGAL_ID_NO")
    private String legalIdNo;

    /**
     * 资料状态[01=保存(即先在本地保存，还未提交到审批流中去）/11=流程中/99=生效]
     */
    @TableField(value = "DATA_STATUS", fill = FieldFill.INSERT)
    private String dataStatus;

    /**
     * 审批流PK
     */
    @TableField("ENTITY_ID")
    private String entityId;

    /**
     * 删除标记[是=1/否=0(Default)]
     */
    @TableField("DEL_FLAG")
    @TableLogic
    private String delFlag;

    /**
     * 创建者
     */
    @TableField(value = "CREATE_USER", fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 创建者代理人
     */
    @TableField("CREATE_AGENT_USER")
    private String createAgentUser;

    /**
     * 创建时间
     */
    @TableField(value = "CREATE_DATE", fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 操作类型
     */
    @TableField(value = "EDTID", fill = FieldFill.INSERT_UPDATE)
    private String edtid;

    /**
     * 操作者
     */
    @TableField(value = "LAST_MOD_USER", fill = FieldFill.INSERT_UPDATE)
    private String lastModUser;

    /**
     * 操作代理人
     */
    @TableField("AGENT_USER")
    private String agentUser;

    /**
     * 操作时间
     */
    @TableField(value = "LAST_MOD_DATE", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime lastModDate;


}
