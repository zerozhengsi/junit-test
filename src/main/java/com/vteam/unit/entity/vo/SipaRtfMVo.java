package com.vteam.unit.entity.vo;

import com.vteam.unit.entity.model.SipaRtfM;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 权限关联扩展类
 *
 * @author fu.tong
 * @date 2018/7/25 0025 18:52
 */
@Getter
@Setter
@ToString
public class SipaRtfMVo extends SipaRtfM {

    private static final long serialVersionUID = -4492965774426763080L;

    /**
     * 用户id
     */
    private String userid;

    /**
     * 企业流水号
     */
    private Integer orgRefcode;

    /**
     * 品牌风格
     */
    private String brandStyle;

}
