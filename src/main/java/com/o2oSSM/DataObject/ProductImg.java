package com.o2oSSM.DataObject;

import lombok.Data;

import java.util.Date;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/15
 * 16:59
 * #
 */
@Data
public class ProductImg {
    private Long productImgId;
    private String imgAddress;
    private String imgDesc;
    private Integer priority;
    private Date createTime;
    private Long productId;

}
