package com.o2oSSM.Service.IMPL;

import com.o2oSSM.DAO.ProductCategoryDAO;
import com.o2oSSM.DAO.ProductDAO;
import com.o2oSSM.DataObject.ProductCategory;
import com.o2oSSM.DataObject.Shop;
import com.o2oSSM.DataTransferObject.ProductCategoryExecutionDTO;
import com.o2oSSM.Enums.ResultEnum;
import com.o2oSSM.Exceptions.ObjectException;
import com.o2oSSM.Exceptions.ShopExceprion;
import com.o2oSSM.Service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/30
 * 11:11
 * #
 */

@Service
public class ProductCategoryServiceIMPL implements ProductCategoryService {


    //通过shopId查询所有的商品类目
    @Autowired
    private ProductCategoryDAO productCategoryDao;

    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<ProductCategory> queryProductCategoryById(Long shopId) {
        List<ProductCategory> productCategories = productCategoryDao
                .queryProductCategorieById(shopId);
        return productCategories;
    }

    //批量增加商品类目
    @Override
    public ProductCategoryExecutionDTO batchAddProductCategory(
            List<ProductCategory> productCategories) {
        Integer result = null;
        if(productCategories!=null&&productCategories.size()>0){
             result = productCategoryDao.batchInsertProductCategory(productCategories);
        }else {
            throw new ShopExceprion(ResultEnum.HAVE_NULL_VALUE);
        }
        if (result==null&&result<0){
            throw new ShopExceprion(ResultEnum.PRODUCT_CATEGORY_ERROR);
        }else {
            return new ProductCategoryExecutionDTO(ResultEnum.SUCCESS);
        }
    }


    //根据productCategoryId和shopId删除，只能删除没有商品的类目
    @Override
    @Transactional
    public Integer deleteProductCategory(Long productCategoryId, Long shopId) {
        if (productCategoryId ==null&&shopId==null){
            throw new ShopExceprion(ResultEnum.PARAM_NULL);
        }
        //解除product和productCategoryId的关联
        Integer updateProductCategoryIdToNull = productDAO.updateProductCategoryToNull(productCategoryId);
        if (updateProductCategoryIdToNull<0){
            throw new ObjectException(ResultEnum.UPDATE_TO_NULL_FAILED);
        }
        //删除peoductgoryid
        Integer integer =  productCategoryDao
                .deleteProductCategory(productCategoryId,shopId );
        if (integer<0){
            throw new ShopExceprion(ResultEnum.INNER_ERROR);
        }
        return integer;
    }
}
