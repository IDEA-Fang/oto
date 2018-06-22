package com.o2oSSM.Service.IMPL;

import com.o2oSSM.DAO.ShopDAO;
import com.o2oSSM.DataObject.Shop;
import com.o2oSSM.DataTransferObject.ShopExecutionDTO;
import com.o2oSSM.Enums.ResultEnum;
import com.o2oSSM.Enums.ShopStateEnum;
import com.o2oSSM.Exceptions.ShopExceprion;
import com.o2oSSM.Service.ShopService;
import com.o2oSSM.Utils.ImageHolder;
import com.o2oSSM.Utils.ImageUtil;
import com.o2oSSM.Utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/23
 * 17:53
 * #
 */
@Component
@Service
public class ShopServiceIMPL implements ShopService {

    @Autowired
    private ShopDAO shopDAO;

    @Override
    @Transactional
    public ShopExecutionDTO addShop(Shop shop, ImageHolder imageHolder) {

        //判断非空
        if (shop.getShopId()==null&&shop.getShopCategoryId()==null&&shop.getOwnerId()==null){
            throw new ShopExceprion(ResultEnum.HAVE_NULL_VALUE);
        }
        if (shop==null){
            throw new ShopExceprion(ResultEnum.HAVE_NULL_VALUE);
        }
        //赋值，初始值
        shop.setEnableStatus(ShopStateEnum.CHECK.getCode());
        shop.setCreateTime(new Date());
        shop.setUpdateTime(new Date());

        //添加店铺，入库
        Integer integer = shopDAO.insertShop(shop);
        if (integer<=0){
            throw new ShopExceprion(ResultEnum.ADDSHOP_ERROR);
        }
        if (imageHolder.getInputStream()!=null) {
                //存储图片
                try {
                    addShopImg(shop,imageHolder);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ShopExceprion(ResultEnum.IMAGE_ERROR);
                }
        }
        else {
            throw new ShopExceprion(2,"图片为空，创建图片失败");
        }
        integer = shopDAO.updateShop(shop);
        if (integer<=0)
            throw new ShopExceprion(-1,"更新addShopImg错误");
        return new ShopExecutionDTO(ShopStateEnum.SUCCESS,shop);
    }


    //添加照片
    private void addShopImg(Shop shop,ImageHolder imageHolder)  {
        //获取路shop图片存储的相对路径
        String dest = PathUtil.getImageBasePath();
        String shopImgAddress = ImageUtil.generateThumbnail(imageHolder, dest);
        shop.setShopImg(shopImgAddress);
    }
}
