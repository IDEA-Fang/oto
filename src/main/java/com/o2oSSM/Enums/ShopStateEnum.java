package com.o2oSSM.Enums;

import lombok.Getter;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/23
 * 16:35
 * #
 */
@Getter
public enum ShopStateEnum implements CodeEnums {
    CHECK(0, "审核中"),
    OFF_LINE(-1, "非法商铺"),
    PASS(2, "通过认证"),
    INNER_ERROR(-1001, "操作失败"),
    SUCCESS(3,"成功")
    ;


    private Integer code;
    private String stateMsg;

    private ShopStateEnum(Integer code, String stateMsg) {
        this.code = code;
        this.stateMsg = stateMsg;
    }

    public static ShopStateEnum stateOf(int index) {
        for (ShopStateEnum state : values()) {
            if (state.getCode() == index) {
                return state;
            }
        }
        return null;
    }
}
