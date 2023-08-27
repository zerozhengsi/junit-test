package com.vteam.unit.entity.vo;

import com.vteam.unit.entity.model.SipaRolM;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 角色基本信息表扩展视图类 .<br>
 *
 * @author andy.sher
 * @date 2018/8/15 15:13
 */
@Getter
@Setter
@ToString(callSuper = true)
public class SipaRolMVo extends SipaRolM {

    private static final long serialVersionUID = -3947513825168590334L;
    /**
     * 权限流水号
     */
    private String funRefcodes;

    private List<Integer> funCodes;

    /**
     * 角色成员数量
     */
    private Integer rolUserCount;

    /**
     * 品牌风格
     */
    private String brandStyle;


}
