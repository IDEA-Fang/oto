package com.o2oSSM.Service;

import com.o2oSSM.DataObject.Product;
import com.o2oSSM.DataTransferObject.ProductExecutionDTO;
import com.o2oSSM.Utils.ImageHolder;

import java.io.InputStream;
import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/6/13
 * 11:07
 * #
 */
public interface ProductService {

    ProductExecutionDTO getProductList(Product productCondition,Integer rowIndex,Integer pageSize);

    ProductExecutionDTO addProduct(Product product,ImageHolder imageHolder,List<ImageHolder> imageHolderList);

    Product getProductByProductId(Long productId);

    ProductExecutionDTO updateProduct(Product product,ImageHolder imageHolder,List<ImageHolder> imageHolderList);
}
