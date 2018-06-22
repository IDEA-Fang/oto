package com.o2oSSM.Enums;

import lombok.Getter;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/23
 * 18:03
 * #
 */
@Getter
public enum ResultEnum implements CodeEnums {

    SUCCESS(1, "创建成功"),
    INNER_ERROR(-1001, "操作失败"),
    EMPTY_LIST(-1002, "添加数少于1"),
    //shop的
    SHOP_CHECK(0, "审核中"),
    SHOP_OFF_LINE(-1, "非法商铺"),
    SHOP_PASS(2, "通过认证"),
    //图片的
    HAVE_NULL_VALUE(100,"判断数值有空值"),
    ADDSHOP_ERROR(101,"创建店铺失败"),
    IMAGE_ERROR(102,"图片处理错误1"),
    SHOPID_ERROR(103,"商铺id错误"),
    //商品的
    //状态值
    //商品0下架，1 再前端展示
    PRODUCT_ENABLE_STATUS_UP(1,"下架"),
    PRODUCT_ENABLE_STATUS_DOWN(0,"下架"),

    PRODUCT_CATEGORY_ERROR(200,"商品类目错误"),
    PARAM_NULL(201,"传入参数有空值"),
    PRODUCT_EMPTY(202,"商品为空"),

    //商品类目的
    UPDATE_TO_NULL_FAILED(301,"更新param为null失败"),

    ;


    private Integer code;
    private String stateMsg;

    private ResultEnum(Integer code, String stateMsg) {
        this.code = code;
        this.stateMsg = stateMsg;
    }

    //根据参数数字返回msg
    public static ResultEnum stateOf(int index) {
        for (ResultEnum code : values()) {
            if (code.getCode() == index) {
                return code;
            }
        }
        return null;
    }
}
