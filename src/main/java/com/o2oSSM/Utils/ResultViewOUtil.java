package com.o2oSSM.Utils;

import com.o2oSSM.VO.ResultVO;
import lombok.Data;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/30
 * 11:23
 * #
 */
@Data
public class ResultViewOUtil {


    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(1);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer errorCode,String errorMsg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(errorCode);
        resultVO.setMsg(errorMsg);
        return resultVO;
    }

}
