package com.o2oSSM.DataTransferObject;

import com.o2oSSM.DataObject.ProductCategory;
import com.o2oSSM.Enums.ResultEnum;
import lombok.Data;

import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/6/1
 * 18:16
 * #
 */
@Data
public class ProductCategoryExecutionDTO {

    //操作的商铺类目
    private ProductCategory productCategory;
    //操作的商铺类目的集合
    private List<ProductCategory> productCategories;
    //结果状态
    private Integer state;
    //状态信息
    private String stateInfo;

    //构造器
    public ProductCategoryExecutionDTO() {
    }

    //失败的构造器
    public ProductCategoryExecutionDTO(ResultEnum resultEnum){
        this.state = resultEnum.getCode();
        this.stateInfo = resultEnum.getStateMsg();
    }
    //成功的构造器
    public ProductCategoryExecutionDTO(ResultEnum resultEnum,
                                       List<ProductCategory> productCategorieList){
        this.state = resultEnum.getCode();
        this.stateInfo = resultEnum.getStateMsg();
        this.productCategories = productCategorieList;
    }

}
