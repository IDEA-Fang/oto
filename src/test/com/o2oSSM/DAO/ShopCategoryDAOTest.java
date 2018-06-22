package com.o2oSSM.DAO;

import com.o2oSSM.DataObject.ShopCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/27
 * 17:27
 * #
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class ShopCategoryDAOTest {

    @Autowired
    private ShopCategoryDAO shopCategoryDAO;

    @Test
    public void queryshopCategory() {

        ShopCategory shopCategory = new ShopCategory();
        //当parent为空
        List<ShopCategory> shopCategoryList =  shopCategoryDAO.queryShopCategory(shopCategory);
        System.out.println(shopCategoryList);
        //Assert.assertEquals(18,shopCategoryList.size() );
        //当parent不为空
        shopCategory.setParentId(10L);
        shopCategoryList = shopCategoryDAO.queryShopCategory(shopCategory);
        System.out.println(shopCategoryList);

    }
}