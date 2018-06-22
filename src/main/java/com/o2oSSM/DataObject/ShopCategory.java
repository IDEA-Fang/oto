package com.o2oSSM.DataObject;

import lombok.Data;

import java.util.Date;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/15
 * 16:41
 * #店铺类目
 */
@Data
public class ShopCategory {
    private Long shopCategoryId;
    private String  shopCategoryName;
    private String shopCategoryDesc;
    private String shopCategoryImg;
    private Integer priority;
    private Date createTime;
    private Date updateTime;
    private Long parentId;
}
