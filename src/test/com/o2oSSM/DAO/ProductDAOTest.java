package com.o2oSSM.DAO;

import com.o2oSSM.BaseTest;
import com.o2oSSM.DataObject.Product;
import com.o2oSSM.DataObject.ProductCategory;
import com.o2oSSM.DataObject.Shop;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/6/11
 * 17:15
 * #
 */
public class ProductDAOTest extends BaseTest {

    @Autowired
    private ProductDAO productDAO;

    @Test
    public void insertProduct() {
        Product product = new Product();
        product.setProductName("乌龙茶");
        product.setProductDesc("很甜的乌龙茶");
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        product.setEnableStatus(1);
        product.setImgAddress("xxxxx.jpg");
        product.setNormalPrice("100");
        product.setPoint(10);
        product.setPriority(111);

        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(15L);
        product.setProductCategory(productCategory);

        Shop shop = new Shop();
        shop.setShopId(20L);
        product.setShop(shop);

        product.setPromotionPrice("80");

        Integer integer =  productDAO.insertProduct(product);
        System.out.println(integer);
        Assert.assertNotNull(integer);

    }

    @Test
    public void select() {
        Long productId = 17L;
        Product product  = productDAO.queryProductByProductId(productId);
        System.out.println(product);
    }

    @Test
    public void updateProduct() {
        Product product  = new Product();
        product.setProductId(25L);
        product.setProductName("新的乌龙茶");

        Shop shop = new Shop();
        shop.setShopId(15L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(11L);

        product.setShop(shop);
        product.setProductCategory(productCategory);
        Integer integer = productDAO.updateProduct(product);
        System.out.println(integer);
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
        List<Product> products =  productDAO.queryProductList(productCondition, rowIndex, pageSize);
        System.out.println(products);
    }

    @Test
    public void queryProductListC() {
        Product productCondition = new Product();
        Integer rowIndex = 2;
        Integer pageSize = 100;
      productCondition.setEnableStatus(1);
        List<Product> products =  productDAO.queryProductList(productCondition, rowIndex, pageSize);
        System.out.println(products);
    }
    @Test
    public void queryProductListD() {
        Product productCondition = new Product();
        Integer rowIndex = 2;
        Integer pageSize = 100;
     productCondition.setProductName("巧乐兹");


        List<Product> products =  productDAO.queryProductList(productCondition, rowIndex, pageSize);
        System.out.println(products);
    }



    @Test
    public void delete() {
        productDAO.deleteProduct(22L,15L);
    }

    @Test
    public void updateProductCategoryToNull() {
        Long productCategoryId = 9L;
        Integer integer = productDAO.updateProductCategoryToNull(productCategoryId);
        System.out.println(integer);
    }
}