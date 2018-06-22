package com.o2oSSM.DAO;

import com.o2oSSM.BaseTest;
import com.o2oSSM.DataObject.Product;
import com.o2oSSM.DataObject.ProductImg;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/6/11
 * 20:06
 * #
 */
public class ProductImgDAOTest extends BaseTest {

    @Autowired
    private ProductImgDAO productImgDAO;

    @Test
    public void batchInsertProductImg() {
        ProductImg productImg = new ProductImg();
        List<ProductImg> productImgs = new ArrayList<>();
        productImg.setCreateTime(new Date());
        productImg.setImgAddress("xxxxxx.jpg");
        productImg.setImgDesc("还不许哦哦哦");

        Product product = new Product();
        product.setProductId(15L);
        productImg.setProductId(15L);
        productImgs.add(productImg);


        ProductImg productImg2 = new ProductImg();
        productImg2.setCreateTime(new Date());
        productImg2.setImgAddress("xxxxxx.jpg");
        productImg2.setImgDesc("这事图片");

        productImg2.setProductId(15L);
        productImgs.add(productImg2);

        Integer integer = productImgDAO.batchInsertProductImg(productImgs);
        System.out.println(integer);
    }

    @Test
    public void queryProductImgList() {
        List<ProductImg> productImgs =  productImgDAO.queryProductImgList(25L);
        System.out.println(productImgs);
    }

    @Test
    public void delete() {
        Integer integer = productImgDAO.deleteProductImgByProductId(23L);
    }
}