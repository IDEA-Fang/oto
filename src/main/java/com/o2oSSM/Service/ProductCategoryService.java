package com.o2oSSM.Service;

import com.o2oSSM.DAO.ProductCategoryDAO;
import com.o2oSSM.DataObject.ProductCategory;
import com.o2oSSM.DataTransferObject.ProductCategoryExecutionDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/30
 * 11:05
 * #
 */
public interface  ProductCategoryService {

    //通过shopId查询所有的商品类目
    List<ProductCategory> queryProductCategoryById(Long shopId);

    //批量增加商品类目
    ProductCategoryExecutionDTO batchAddProductCategory(List<ProductCategory> productCategories);

    //根据productCategoryId和shopId删除，只能删除没有商品的类目
    Integer deleteProductCategory(Long productCategoryId,Long shopId);
}
