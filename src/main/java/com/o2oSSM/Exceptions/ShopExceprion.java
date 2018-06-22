package com.o2oSSM.Exceptions;

import com.o2oSSM.Enums.ResultEnum;
import lombok.Data;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/23
 * 18:00
 * #
 */
@Data
public class ShopExceprion extends RuntimeException {

    private Integer code;
    private String  message;


    public ShopExceprion(ResultEnum resultEnum){
        super(resultEnum.getStateMsg());
        this.code = resultEnum.getCode();
    }

    public ShopExceprion(Integer code,String message){
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
