package com.o2oSSM.Service;

import com.o2oSSM.DataObject.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/27
 * 13:53
 * #
 */
public interface ShopCategoryService {

    List<ShopCategory> getShopCategoryList
            ( ShopCategory shopCategoryCondition);
}
