package com.o2oSSM.Exceptions;

import com.o2oSSM.Enums.ResultEnum;
import lombok.Data;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/6/13
 * 17:06
 * #
 */
@Data
public class ObjectException extends RuntimeException {
    private Integer code;
    private String  message;


    public ObjectException(ResultEnum resultEnum){
        super(resultEnum.getStateMsg());
        this.code = resultEnum.getCode();
    }

    public ObjectException(String message){
        super(message);
    }

    public ObjectException(Integer code,String message){
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
