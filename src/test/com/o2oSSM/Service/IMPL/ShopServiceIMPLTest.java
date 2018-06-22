package com.o2oSSM.Service.IMPL;

import com.o2oSSM.BaseTest;
import com.o2oSSM.DataObject.Area;
import com.o2oSSM.DataObject.Shop;
import com.o2oSSM.DataObject.ShopCategory;
import com.o2oSSM.DataTransferObject.ShopExecutionDTO;
import com.o2oSSM.Utils.ImageHolder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/25
 * 10:48
 * #
 */
public class ShopServiceIMPLTest extends BaseTest {

    @Autowired
    private ShopServiceIMPL shopService;
    @Test
    public void addShop() throws FileNotFoundException {
        Shop shop  = new Shop();
        shop.setOwnerId(1L);
        Area area =new Area();
        area.setAreaId(3L);
        shop.setArea(area);
        ShopCategory shopCategory =new ShopCategory();
        shopCategory.setShopCategoryId(11L);
        shop.setShopCategory(shopCategory);
        shop.setShopDesc("nice");
        shop.setShopName("一点点");

        File file = new File("D:\\1-link\\Every\\timgKU8BODIU.jpg");
        InputStream inputStream =new FileInputStream(file);
        String fileName = file.getName();
        ImageHolder imageHolder = new ImageHolder(inputStream,fileName);
        ShopExecutionDTO shopExecutionDTO = shopService.addShop(shop,imageHolder );

        System.out.println(shopExecutionDTO);
    }
}