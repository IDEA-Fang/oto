package com.o2oSSM.DAO;

import com.o2oSSM.DataObject.ProductCategory;
import com.o2oSSM.DataObject.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/30
 * 10:48
 * #
 */
public interface ProductCategoryDAO {

    //通过shopID查询所有商品类目

    List<ProductCategory> queryProductCategorieById(Long shopId);

    //批量新增商品类目
    Integer batchInsertProductCategory(List<ProductCategory> productCategories);

    //删除商品类目，只支持删除尚且没有发布商品的商品类别，如果发布了商品，还要删除该类目下的商品
    Integer deleteProductCategory(@Param("productCategoryId") long productCategoryId,
                                  @Param(value = "shopId") long shopId);
}
