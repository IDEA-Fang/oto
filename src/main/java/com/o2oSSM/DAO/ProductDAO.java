package com.o2oSSM.DAO;

import com.o2oSSM.DataObject.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/6/11
 * 15:54
 * #
 */
public interface ProductDAO {

    //分页查询，可输入的条件有：商品名（模糊），商品状态，店铺Id,商品类别
    List<Product> queryProductList(
            @Param("productCondition") Product productCondition,
            @Param("rowIndex") Integer rowIndex,
            @Param("pageSize") Integer pageSize);

    //查询对应的商品数目
    Integer queryProductCount(@Param("productCondition") Product productCondition);

    //新增商品
    Integer insertProduct(Product product);

    //todo productImgList can t set
    //根据productId查询商品
    Product queryProductByProductId(Long productId);

    //更新商品
    Integer updateProduct(Product product);

    //将productCategoryId置为空
    Integer updateProductCategoryToNull(Long productCategoryId);

    //删除商品
    Integer deleteProduct(@Param("productId") Long productId,@Param("shopId") Long shopId);

}
