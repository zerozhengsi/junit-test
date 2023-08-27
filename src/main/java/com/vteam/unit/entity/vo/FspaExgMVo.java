package com.vteam.unit.entity.vo;

import com.vteam.unit.entity.model.FspaExgM;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 参数配置表扩展类 .
 *
 * @author zack.yin
 * @date 2018/9/9 22:30
 */
@Getter
@Setter
@ToString
public class FspaExgMVo extends FspaExgM {

    private static final long serialVersionUID = 3953109552596632168L;

    /**
     * 语言种类
     */
    private String languageId;

    /**
     * 页面显示的具体描述
     */
    private String colDesc;
}
