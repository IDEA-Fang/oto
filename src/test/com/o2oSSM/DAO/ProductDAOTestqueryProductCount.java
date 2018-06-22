package com.o2oSSM.DAO;

import com.o2oSSM.BaseTest;
import com.o2oSSM.DataObject.Product;
import com.o2oSSM.DataObject.ProductCategory;
import com.o2oSSM.DataObject.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/6/21
 * 16:41
 * #
 */
public class ProductDAOTestqueryProductCount extends BaseTest {


    @Autowired
    private ProductDAO productDAO;

    @Test
    public void queryProductCountA() {
    }

    @Test
    public void queryProductListB() {
        Product productCondition = new Product();
        Integer rowIndex = 2;
        Integer pageSize = 100;
        Shop shop = new Shop();
        shop.setShopId(15L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(11L);
        productCondition.setProductCategory(productCategory);
        Integer products = productDAO.queryProductCount(productCondition);
        System.out.println(products);
    }

    @Test
    public void queryProductListC() {
        Product productCondition = new Product();
        Integer rowIndex = 2;
        Integer pageSize = 100;
        productCondition.setEnableStatus(1);
        Integer products = productDAO.queryProductCount(productCondition);
        System.out.println(products);
    }

    @Test
    public void queryProductListD() {
        Product productCondition = new Product();
        Integer rowIndex = 2;
        Integer pageSize = 100;
        productCondition.setProductName("巧乐兹");


        Integer products = productDAO.queryProductCount(productCondition);
        System.out.println(products);
    }
}