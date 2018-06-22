package com.o2oSSM.DAO;

import com.o2oSSM.DataObject.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/27
 * 13:14
 * #
 */
public interface ShopCategoryDAO {
    //查询所有的商品列表
    List<ShopCategory> queryShopCategory
            (@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}
