package com.o2oSSM.DAO;

import com.o2oSSM.DataObject.ProductImg;
import com.sun.imageio.plugins.common.I18N;

import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/6/11
 * 19:54
 * #
 */
public interface ProductImgDAO {

    //批量增加图片
    Integer batchInsertProductImg(List<ProductImg> productImgList);

    //查找照片
    List<ProductImg> queryProductImgList(Long productId);

    //删除product下的所有图片
    Integer deleteProductImgByProductId(Long productId);
}
