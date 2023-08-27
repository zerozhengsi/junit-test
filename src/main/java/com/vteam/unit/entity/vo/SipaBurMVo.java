package com.vteam.unit.entity.vo;

import com.vteam.unit.entity.model.SipaBurM;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * 用户信息视图扩展类 .<br>
 *
 * @author andy.sher
 * @date 2018/7/10 17:21
 */
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SipaBurMVo extends SipaBurM {

    private static final long serialVersionUID = 7302310620026912148L;
    
    /**
     * @Fields olduserid
     * @Description 用户账号（旧）
     */
    private String olduserid;

    /**
     * 运营端登录用户名
     */
    private String loginid;

    /**
     * 验证码ID
     */
    private String uuid;

    /**
     * 流水号（多个用逗号分隔）
     */
    private String refcodes;

    /**
     * 角色流水号[逗号分隔]
     */
    private String roleRefcodes;

    /**
     * 角色流水号
     */
    private String roleRefcode;
    
    /**
     * 角色类型
     */
    private String roleType;

    /**
     * 角色代号
     */
    private String roleid;

    /**
     * 企业流水号
     */
    private Integer orgRefcode;

    /**
     * 企业唯一标识
     */
    private String orgcode;

    /**
     * 新登录密码
     */
    private String newPassword;

    /**
     * 确认新登录密码
     */
    private String confirmPassword;

    /**
     * 验证码
     */
    private String verificationCode;

    /**
     * 角色名字
     */
    private String roleName;

    /**
     * 创建日期
     */
    private String creDate;

    /**
     * 是否禁用
     */
    private String isUse;

    /**
     * 是否存在
     */
    private String flag;

    /**
     * 企业用户是否禁用
     */
    private String orgUseFlag;

    /**
     * 企业用户是否管理员
     */
    private String orgAdminFlag;

    /**
     * 创建日期起
     */
    private LocalDate createDateStart;

    /**
     * 创建日期止
     */
    private LocalDate createDateEnd;

    /**
     * 认证状态
     */
    private String authStatus;

    /**
     * 企业状态
     */
    private String orgSource;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 企业流水号
     */
    private String[] orgRefcodes;

    /**
     * 企业类型
     */
    private String orgtype;

    /**
     * 企业类型
     */
    private String subOrgtype;

    /**
     * 营业执照附件流水号
     */
    private String businessLicenseResourceUuid;

    /**
     * 法人证件正面附件流水号
     */
    private String legalIdResourceUuid;

    /**
     * 法人证件背面附件流水号
     */
    private String legalIdBackResourceUuid;

    /**
     * 开户许可证附件流水号
     */
    private String openPermitResourceUuid;

    /**
     * 微信jsCode
     */
    private String jsCode;

    /**
     * 签章申请表流水号
     */
    private Integer sigReqRefcode;

    /**
     * 签章申请状态
     */
    private Integer sigStatus;

    /**
     * 平台授权操作者身份证照片（正面）
     */
    private String operatorPhotoFacade;

    /**
     * 平台授权操作者身份证照片（反面）
     */
    private String operatorPhotoReverse;

    /**
     * 平台授权操作者手持身份证照片
     */
    private String operatorHoldPhoto;
    
    /**
     * 被授权人录像唯一识别码
     */
    private String operatorFruuid;
    
    /**
     * 被授权人录像
     */
    private String operatorVideoUuid;

    /**
     * 平台操作授权书
     */
    private String authorizationOperationInstructions;

    /**
     * 证书申请表
     */
    private String certificateApplication;

    /**
     * 联系人身份证
     */
    private String linkmanIdNo;

    /**
     * 是否变更管理员信息
     */
    private String changeAdminFlag;

    /**
     * 新管理员手机号
     */
    private String newMobilephone;

    /**
     * 初审操作备注(拒绝原因)
     */
    private String reviewRemark;

    /**
     * 所在地区
     */
    private String inarea;

    /**
     * 平台授权操作者身份证有效期起
     */
    private String operatorPhotoReverseStartDate;

    /**
     * 平台授权操作者身份证有效期至
     */
    private String operatorPhotoReverseExpiryDate;

    /**
     * 临时企业名称
     */
    private String tmpOrgname;

    /**
     * 临时统一社会信用代码
     */
    private String tmpCompanyCreditCode;

    /**
     * 法人证件类型[1=中华人民共和国居民身份证/2=外国护照/3=港澳居民来往内地通行证/4=台湾居民来往大陆通行证]
     */
    private String legalIdType;

    /**
     * 品牌流水号[逗号分隔]
     */
    private String brandRefcodes;
    
    /**
     * 真实企业子类型
     */
    private String realSubOrgtype;
    
    /**
     * 贸易通已开通企业类型[1_1=供应商/1_2=采购商][多个逗号分隔]
     */
    private String tradeOrgtype;

    /**
     * 默认企业角色[1_1=一般企业/1_2=核心企业]
     */
    private String defaultOrgtype;

    /**
     * 档案类型[A*=企业基本信息/B*=抵质押融资信息/E*=风险揭露信息/C*=财务信息/D*=证照信息/FO=补充资料(其它)/FI=补充资料(应收账款)/FB=补充资料(票据)/G=征信报告/H*=授权书、申请表等/Z=打包资料/X=大附件]
     */
    private String archiveType;

    /**
     * 最终识别返回码[0=同一人/1=不能确定/3=不同人/其它错误码]
     */
    private String respCode;

    /**
     * UKEY登录授权码签名值
     */
    private String loginSignature;
}
