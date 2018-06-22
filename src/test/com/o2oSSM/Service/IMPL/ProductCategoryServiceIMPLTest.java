package com.o2oSSM.Service.IMPL;

import com.o2oSSM.BaseTest;
import com.o2oSSM.DataObject.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/30
 * 11:14
 * #
 */
public class ProductCategoryServiceIMPLTest extends BaseTest {

    @Autowired
    private ProductCategoryServiceIMPL productCategoryService;

    @Test
    public void queryProductCategoryById() {
        Long shopId = 20L;
        List<ProductCategory> productCategoryList = productCategoryService
                .queryProductCategoryById(shopId);
        System.out.println(productCategoryList);
    }
}