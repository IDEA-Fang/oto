package com.o2oSSM.DataObject;

import lombok.Data;

import java.util.Date;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/15
 * 16:57
 * #
 */
@Data
public class ProductCategory {
    private Long productCategoryId;
    private Long shopId;
    private String productCategoryName;
    private String productCategoryDesc;
    private Integer priority;
    private Date createTime;
    private Date updateTime;
}
