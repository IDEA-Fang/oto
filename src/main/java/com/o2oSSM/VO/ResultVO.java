package com.o2oSSM.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/6/1
 * 17:38
 * #
 */
@Data
public class ResultVO<T> implements Serializable {

    //错误码
    private Integer code;

    //提示信息
    private String msg;

    //具体类容
    private T Data;

}
