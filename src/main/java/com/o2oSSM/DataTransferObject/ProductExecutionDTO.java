package com.o2oSSM.DataTransferObject;

import com.o2oSSM.DataObject.Product;
import com.o2oSSM.DataObject.Shop;
import com.o2oSSM.Enums.ResultEnum;
import com.o2oSSM.Enums.ShopStateEnum;
import lombok.Data;

import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/6/13
 * 11:09
 * #
 */
@Data
public class ProductExecutionDTO {

    //结果状态，结果码，数字
    private Integer state;
    //状态标识，结果信息
    private String stataInfo;

    //店铺数量
    private Integer shopCount;
    //操作的店铺（增删改查shop用到）
    private Product product;
    //获取shop列表
    private List<Product>  productList;

    //构造器
    public ProductExecutionDTO() {
    }

    //失败的构造器
    public ProductExecutionDTO(ResultEnum resultEnum) {
        this.state = resultEnum.getCode();
        this.stataInfo = resultEnum.getStateMsg();
    }

    //成功的构造器，返回信息和DTO
    public ProductExecutionDTO(ResultEnum resultEnum,Product product) {
        this.state = resultEnum.getCode();
        this.stataInfo = resultEnum.getStateMsg();
        this.product = product;
    }
}
