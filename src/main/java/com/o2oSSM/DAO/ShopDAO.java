package com.o2oSSM.DAO;

import com.o2oSSM.DataObject.Shop;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/21
 * 20:52
 * #店铺
 */
public interface ShopDAO {
    //新增商铺
    Integer insertShop(Shop shop);

    //更新店铺
    Integer updateShop(Shop shop);
}
