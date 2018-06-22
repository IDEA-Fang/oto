package com.o2oSSM.DAO;

import com.o2oSSM.DataObject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/30
 * 10:57
 * #
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml",})
public class ProductCategoryDAOTest {

    @Autowired
    private ProductCategoryDAO productCategoryDAO;


    @Test
    public void queryProductCategorieById() {
        Long shopId = 20L;
        List<ProductCategory> productCategories = productCategoryDAO
                .queryProductCategorieById(shopId);
        System.out.println(productCategories);
    }

    @Test
    public void batchInsert() {
        ProductCategory productCategory =new ProductCategory();
        ProductCategory productCategory2 =new ProductCategory();

        List<ProductCategory> productCategories = new ArrayList<>();
        productCategory.setProductCategoryName("火热的类目");
        productCategory.setShopId(20L);
        productCategory.setCreateTime(new Date());
        productCategory.setPriority(111);
        productCategories.add(productCategory);

        productCategory2.setProductCategoryName("不是很火热的类目");
        productCategory2.setShopId(20L);
        productCategory2.setCreateTime(new Date());
        productCategory2.setPriority(222);
        productCategories.add(productCategory2);

        System.out.println(productCategories);
        Integer integer = productCategoryDAO.batchInsertProductCategory(productCategories);
        System.out.println(integer);
    }

    @Test
    public void testDelete(){
        Long productCategoryId = 20L;
        Long shopId = 20L;

        productCategoryDAO.deleteProductCategory(productCategoryId,shopId );
    }
}