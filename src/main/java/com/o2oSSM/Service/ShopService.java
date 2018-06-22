package com.o2oSSM.Service;

import com.o2oSSM.DataObject.Shop;
import com.o2oSSM.DataTransferObject.ShopExecutionDTO;
import com.o2oSSM.Utils.ImageHolder;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/23
 * 17:50
 * #
 */
public interface ShopService {
    //添加店铺
    ShopExecutionDTO addShop(Shop shop, ImageHolder imageHolder);

}
