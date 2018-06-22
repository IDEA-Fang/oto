package com.o2oSSM.DataObject;

import lombok.Data;

import java.util.Date;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/15
 * 11:34
 * #区域表
 */
@Data
public class Area {

    //区域id
    private Long areaId;
    //区域名字
    private String areaName;
    //描述
    private String areaDesc;
    //权重
    private Integer priority;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
}
