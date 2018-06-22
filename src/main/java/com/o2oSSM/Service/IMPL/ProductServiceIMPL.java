package com.o2oSSM.Service.IMPL;

import com.o2oSSM.DAO.ProductDAO;
import com.o2oSSM.DAO.ProductImgDAO;
import com.o2oSSM.DataObject.Product;
import com.o2oSSM.DataObject.ProductImg;
import com.o2oSSM.DataObject.Shop;
import com.o2oSSM.DataTransferObject.ProductExecutionDTO;
import com.o2oSSM.Enums.ResultEnum;
import com.o2oSSM.Exceptions.ObjectException;
import com.o2oSSM.Service.ProductService;
import com.o2oSSM.Utils.FileUtils;
import com.o2oSSM.Utils.ImageHolder;
import com.o2oSSM.Utils.ImageUtil;
import com.o2oSSM.Utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/6/13
 * 11:54
 * #
 */
@Service
public class ProductServiceIMPL  implements ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ProductImgDAO productImgDAO;

    @Override
    public ProductExecutionDTO getProductList(Product productCondition, Integer rowIndex, Integer pageSize) {
        ProductExecutionDTO productExecutionDTO = new ProductExecutionDTO();
        List<Product > productList = new ArrayList<>();
        if (productCondition==null){
            return null;
        }
        Integer count = productDAO.queryProductCount(productCondition);
        productList = productDAO.queryProductList(productCondition,rowIndex ,pageSize );
        productExecutionDTO.setProductList(productList);
        productExecutionDTO.setState(count);
        return productExecutionDTO;
    }

    @Override
    public ProductExecutionDTO addProduct(Product product, ImageHolder imageHolder,
                                          List<ImageHolder> imageHolderList) {
        //判断传入值空值
        if(product==null||product.getShop()==null||product.getShop().getShopId()==null){
            return new ProductExecutionDTO(ResultEnum.PRODUCT_EMPTY);
        }
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        product.setEnableStatus(ResultEnum.PRODUCT_ENABLE_STATUS_UP.getCode());

        if (imageHolder==null){
            throw new ObjectException(ResultEnum.HAVE_NULL_VALUE);
        }
        //添加图片
        addProductImg(product,imageHolder );
        //添加product
        Integer integer  = productDAO.insertProduct(product);
        if (integer<=0){
            throw new ObjectException("创建商品失败");
        }
        //添加批量图片
        if (imageHolderList!=null&&imageHolderList.size()>0){
            addProductImgList(product,imageHolderList );
        }
        return new ProductExecutionDTO(ResultEnum.SUCCESS,product);
    }

    @Override
    public Product getProductByProductId(Long productId) {
        if (productId==null){
            throw new ObjectException(ResultEnum.HAVE_NULL_VALUE);
        }
        Product product = productDAO.queryProductByProductId(productId);
        if (product==null){
            throw new ObjectException(ResultEnum.PRODUCT_EMPTY);
        }
        return product;
    }

    @Override
    public ProductExecutionDTO updateProduct(Product product, ImageHolder imageHolder, List<ImageHolder> imageHolderList) {

        //判断传入值空值
        if(product==null||product.getShop()==null||product.getShop().getShopId()==null){
            return new ProductExecutionDTO(ResultEnum.PRODUCT_EMPTY);
        }
        product.setUpdateTime(new Date());
        //更新小图
        if (imageHolder!=null){
            Product imgProduct = productDAO.queryProductByProductId(product.getProductId());
            System.out.println("2222222222222222222"+imgProduct+product.getProductId());
            if (imgProduct.getImgAddress()!=null){
                FileUtils.deleteFile(imgProduct.getImgAddress());
            }
            //更新小图
            addProductImg(product,imageHolder );
        }
        //更新详情图，列表
        if (imageHolderList!=null&&imageHolderList.size()>0){
            deleteProductImgList(product.getProductId());
            addProductImgList(product,imageHolderList );
        }
        //更新product
        Integer integer  = productDAO.updateProduct(product);
        if (integer<=0){
            throw new ObjectException("更新商品失败");
        }

        return new ProductExecutionDTO(ResultEnum.SUCCESS,product);
    }

    //添加照片
    private void addProductImg(Product product, ImageHolder imageHolder)  {
        //获取路product图片存储的相对路径
        String dest = PathUtil.getImageBasePath();
        String imgAddress = ImageUtil.generateThumbnail(imageHolder, dest);
        product.setImgAddress(imgAddress);
    }


    //批量添加照片
    private void addProductImgList(Product product,List<ImageHolder> imageHolderList)   {
        //获取路product图片存储的相对路径,放在店铺文件夹下面
        String dest = PathUtil.getShopImgPath(product.getShop().getShopId());
        //遍历图片去处理，并加入到productImg里面
        ProductImg productImg = new ProductImg();
        List<ProductImg> productImgList = new ArrayList<>();
        for (ImageHolder productImgHolder : imageHolderList ) {
            String imgAddress = ImageUtil.generateNormalImg(productImgHolder, dest);

            productImg.setCreateTime(new Date());
            productImg.setProductId(product.getProductId());
            productImg.setImgAddress(imgAddress);

            productImgList.add(productImg);
            System.out.println("11111111111111111"+productImgList.toString());
            Integer integer = productImgDAO.batchInsertProductImg(productImgList);
            if (integer<=0){
                throw new ObjectException("创建商品normal图片失败");
            }

        }

    }

    //todo 无法删除图片
    //批量删除照片
    public void deleteProductImgList(Long productId){
        List<ProductImg> productImgList = productImgDAO.queryProductImgList(productId);
        for (ProductImg productImg :productImgList
             ) {
            FileUtils.deleteFile(productImg.getImgAddress());
        }
        productImgDAO.deleteProductImgByProductId(productId);
    }

}
