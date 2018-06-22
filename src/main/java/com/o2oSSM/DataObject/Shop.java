package com.o2oSSM.DataObject;

import lombok.Data;

import java.util.Date;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/15
 * 16:47
 * #店铺
 */

@Data
public class Shop {
    private Long shopId;
    private Long ownerId;
    //属于什么店铺类别的id
    private Long shopCategoryId;
    private String shopName;
    private String shopDesc;
    private String shopAddress;
    private String shopPhone;
    private String shopImg;

    private Double longitude;
    private Double latitude;
    private Date createTime;
    private Date updateTime;
    //0审核，-1非法，2通过
    private Integer enableStatus;
    private Integer priority;

    //给商家的建议
    private String advice;

    //店铺所处的区域
    private Area area;
    //店铺所在的类别
    private ShopCategory shopCategory;
    private ShopCategory parentCategory;
    //店主
    private PersonInfo personInfo;
}
