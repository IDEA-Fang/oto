package com.o2oSSM.Service.IMPL;

import com.o2oSSM.DAO.ShopCategoryDAO;
import com.o2oSSM.DataObject.ShopCategory;
import com.o2oSSM.Service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/27
 * 18:13
 * #
 */
@Service
public class ShopCategoryServiceIMPL implements ShopCategoryService {

    @Autowired
    private ShopCategoryDAO shopCategoryDAO;

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        List<ShopCategory> shopCategoryList = shopCategoryDAO.queryShopCategory(shopCategoryCondition);
        return shopCategoryList;
    }
}
