package com.o2oSSM.Service.IMPL;

import com.o2oSSM.BaseTest;
import com.o2oSSM.DataObject.Product;
import com.o2oSSM.DataObject.Shop;
import com.o2oSSM.DataTransferObject.ProductExecutionDTO;
import com.o2oSSM.Service.ProductService;
import com.o2oSSM.Utils.ImageHolder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/6/13
 * 18:11
 * #
 */
public class ProductServiceIMPLTest extends BaseTest {

    @Autowired
    private ProductServiceIMPL productServiceIMPL;
    @Test
    public void addProduct() throws FileNotFoundException {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(15L);
        product.setShop(shop);
        product.setProductName("巧乐兹");
        product.setProductDesc("好吃的冰棒");
        product.setPriority(22);
        product.setPromotionPrice("50");
        product.setNormalPrice("100");
        //缩略图文件流
        File thumnailFile = new File("D:\\1-link\\Every\\timg0PY2Q2HY.jpg");
        InputStream inputStream = new FileInputStream(thumnailFile);
        ImageHolder imageHolder = new ImageHolder(inputStream,thumnailFile.getName() );
        //批量详情图图片
        File proFile1 = new File("D:\\1-link\\Every\\timgEDU8HKUL.jpg");
        InputStream proInputStream1 = new FileInputStream(proFile1);
        ImageHolder imageHolder1 = new ImageHolder(proInputStream1,proFile1.getName() );
        File proFile2 = new File("D:\\1-link\\Every\\timgKU8BODIU.jpg");
        InputStream proInputStream2 = new FileInputStream(proFile2);
        ImageHolder imageHolder2 = new ImageHolder(proInputStream2,proFile2.getName());
        List<ImageHolder> imageHolders  = new ArrayList<>();
        imageHolders.add(imageHolder1);
        imageHolders.add(imageHolder2);

        ProductExecutionDTO productExecutionDTO = productServiceIMPL
                .addProduct(product,imageHolder, imageHolders );
        System.out.println(productExecutionDTO);
    }

    @Test
    public void updateProduct() throws FileNotFoundException {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(15L);
        product.setShop(shop);
        product.setProductId(30L);
        product.setProductName("好吃点");
        product.setProductDesc("好吃的饼干");
        product.setUpdateTime(new Date());

        //缩略图文件流
        File thumnailFile = new File("D:\\1-link\\Every\\2018-03-21_191005.png");
        InputStream inputStream = new  FileInputStream(thumnailFile);
        ImageHolder imageHolder = new ImageHolder(inputStream,thumnailFile.getName() );
        //批量详情图图片
        File proFile1 = new File("D:\\1-link\\Every\\amelia-bloomer-9216245-1-402.jpg");
        InputStream proInputStream1 = new FileInputStream(proFile1);
        ImageHolder imageHolder1 = new ImageHolder(proInputStream1,proFile1.getName() );
        File proFile2 = new File("D:\\1-link\\Every\\timgKU8BODIU.jpg");
        InputStream proInputStream2 = new FileInputStream(proFile2);
        ImageHolder imageHolder2 = new ImageHolder(proInputStream2,proFile2.getName());
        List<ImageHolder> imageHolders  = new ArrayList<>();
        imageHolders.add(imageHolder1);
        imageHolders.add(imageHolder2);

        ProductExecutionDTO productExecutionDTO = productServiceIMPL
                .updateProduct(product,imageHolder, imageHolders );
        System.out.println(productExecutionDTO);

    }
}