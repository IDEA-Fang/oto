package com.o2oSSM.DataObject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/15
 * 16:08
 * #商品
 */
@Data
public class Product implements Serializable {

    private Long productId;
    private String productName;
    private String productDesc;
    //简略图
    private String imgAddress;
    //原价
    private String normalPrice;
    //折扣价
    private String promotionPrice;
    private Integer priority;
    private Date createTime;
    private Date updateTime;
    //0下架，1 再前端展示
    private Integer enableStatus;
    private Integer point;

    private Shop shop;
    private List<ProductImg> productImgList;
    private ProductCategory productCategory;
}
