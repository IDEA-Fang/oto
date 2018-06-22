package com.o2oSSM.WebController.Product;

import com.alibaba.fastjson.JSON;
import com.o2oSSM.DataObject.Product;
import com.o2oSSM.DataObject.ProductCategory;
import com.o2oSSM.DataObject.Shop;
import com.o2oSSM.DataTransferObject.ProductExecutionDTO;
import com.o2oSSM.Enums.ResultEnum;
import com.o2oSSM.Service.IMPL.ProductServiceIMPL;
import com.o2oSSM.Utils.HttpServletRequestConversionUtil;
import com.o2oSSM.Utils.ImageHolder;
import com.o2oSSM.Utils.KaptchaCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/6/14
 * 12:58
 * #
 */
@Controller
@ResponseBody
@RequestMapping(value = "/product")
public class ProductManageController {

    @Autowired
    private ProductServiceIMPL productService;

    private static final int PRODUCT_IMG_MAXCOUNT = 6;

    @RequestMapping(value = "/listproductbyshop",method = RequestMethod.POST)
    public Map<String ,Object> listProductByShop(HttpServletRequest httpServletRequest)
            throws IOException {
        Map<String, Object> modelMap = new HashMap<>();
        Integer rowIndex = HttpServletRequestConversionUtil
                .getInteger(httpServletRequest,"rowIndex");
        Integer pageSize = HttpServletRequestConversionUtil
                .getInteger(httpServletRequest,"pageSize");
        Shop currentShop = (Shop) httpServletRequest.getSession().getAttribute("currentShop");
        String productName = HttpServletRequestConversionUtil
                .getString(httpServletRequest,"productName" );
        Integer productCategoryId = HttpServletRequestConversionUtil
                .getInteger(httpServletRequest,"productCategoryId" );

        Product productCondition = setToProductCondition(currentShop.getShopId(),productCategoryId ,productName );
        if (rowIndex<0&&pageSize<0){
            modelMap.put("success",false );
            modelMap.put("errorMsg","rowInde,pageSize为空" );
            return modelMap;
        }
        ProductExecutionDTO dto = productService.getProductList(productCondition,rowIndex,pageSize );
        modelMap.put("success",true );
        modelMap.put("count",dto.getState());
        modelMap.put("productList",dto.getProductList() );
        return modelMap;
    }

    public Product setToProductCondition(long shopId,
                                         long productCategoryId, String productName){
        Product productCondition = new Product();
        if (shopId>=0) {
            Shop shop = new Shop();
            shop.setShopId(shopId);
            productCondition.setShop(shop);
        }
        if (productCategoryId>=0){
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            productCondition.setProductCategory(productCategory);
        }
        if (productName!=null){
            productCondition.setProductName(productName);
        }
        return productCondition;
    }



        @RequestMapping(value = "/addproduct",method = RequestMethod.POST)
        public Map<String ,Object> addProduct(HttpServletRequest httpServletRequest) throws IOException {
            Map<String ,Object> modelMap = new HashMap<>();

        if (!KaptchaCodeUtil.checkCaptchaCode(httpServletRequest)){
            modelMap.put("success",false );
            modelMap.put("errorMsg","输入的验证码错误" );
            return modelMap;
        }
        //接受参数并转化  用到json转换工具 获取json并转化
        String productStr = HttpServletRequestConversionUtil
                .getString(httpServletRequest, "productPro");
        Product product = JSON.parseObject(productStr,Product.class );
        /**
         * 从http得到图片文件
         * httpServletRequest转化成CommonsMultipartResolver接收
         * 由CommonsMultipartResolver传给multipartHttpServletRequest,强转
         * multipartHttpServletRequest获取图片 转给CommonsMultipartFile
         * CommonsMultipartFile转化成ImageHolder
         */
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                httpServletRequest.getSession().getServletContext());
        MultipartHttpServletRequest multipartHttpServletRequest =
                (MultipartHttpServletRequest) commonsMultipartResolver;
        //前端传入的字段:thumbnail，productImg
        //小图，thumbnail
        CommonsMultipartFile thumbnail =
                (CommonsMultipartFile) multipartHttpServletRequest.getFile("thumbnail");
        //详情图List，
        List<CommonsMultipartFile>  productImgList = new ArrayList<>();
        List<ImageHolder> imageHolderList = new ArrayList<>();
        for (int i=0;i<=PRODUCT_IMG_MAXCOUNT;i++) {
                CommonsMultipartFile productImg = (CommonsMultipartFile)
                        multipartHttpServletRequest.getFile("productImg"+i);
                if (productImg!=null) {
                    productImgList.add(productImg);
                    ImageHolder holder = new ImageHolder(productImg.getInputStream(),
                            productImg.getName());
                    imageHolderList.add(holder);
                }
        }
        //这里把详情图设置也不能为空
        if (thumbnail==null||productImgList.size()<=0){
            modelMap.put("success",false );
            modelMap.put("errorMsg","上传的图片不能为空" );
            return modelMap;
        }
        if (product==null){
            modelMap.put("success",false );
            modelMap.put("errorMsg","请输入商品信息" );
            return modelMap;
        }
        ImageHolder imageHolder = new ImageHolder(thumbnail.getInputStream(),thumbnail.getName() );
        //shopId
        Shop shop = new Shop();
        Shop requestShop = (Shop) httpServletRequest.getSession().getAttribute("requestShop");
        shop.setShopId(requestShop.getShopId());
        product.setShop(shop);
        ProductExecutionDTO productExecutionDTO =  productService
                .addProduct(product, imageHolder,imageHolderList );
        if (productExecutionDTO.getState()==ResultEnum.SUCCESS.getCode()){
            modelMap.put("success",true );
        }else {
            modelMap.put("success",false );
            modelMap.put("errorMsg",productExecutionDTO.getStataInfo() );
        }
        return modelMap;
    }

    @RequestMapping(value = "/updateproduct",method = RequestMethod.POST)
    public Map<String ,Object> updateProduct(HttpServletRequest httpServletRequest) throws IOException {
        Map<String, Object> modelMap = new HashMap<>();

        if (!KaptchaCodeUtil.checkCaptchaCode(httpServletRequest)){
            modelMap.put("success",false );
            modelMap.put("errorMsg","输入的验证码错误" );
            return modelMap;
        }
        //接受参数并转化  用到json转换工具 获取json并转化
        String productStr = HttpServletRequestConversionUtil
                .getString(httpServletRequest, "productPro");
        Product product = JSON.parseObject(productStr,Product.class );
        /**
         * 从http得到图片文件
         * httpServletRequest转化成CommonsMultipartResolver接收
         * 由CommonsMultipartResolver传给multipartHttpServletRequest,强转
         * multipartHttpServletRequest获取图片 转给CommonsMultipartFile
         * CommonsMultipartFile转化成ImageHolder
         */
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                httpServletRequest.getSession().getServletContext());
        MultipartHttpServletRequest multipartHttpServletRequest =
                (MultipartHttpServletRequest) commonsMultipartResolver;
        //前端传入的字段:thumbnail，productImg
        //小图，thumbnail
        CommonsMultipartFile thumbnail =
                (CommonsMultipartFile) multipartHttpServletRequest.getFile("thumbnail");
        //详情图List，
        List<CommonsMultipartFile>  productImgList = new ArrayList<>();
        List<ImageHolder> imageHolderList = new ArrayList<>();
        for (int i=0;i<=PRODUCT_IMG_MAXCOUNT;i++) {
            CommonsMultipartFile productImg = (CommonsMultipartFile)
                    multipartHttpServletRequest.getFile("productImg"+i);
            if (productImg!=null) {
                productImgList.add(productImg);
                ImageHolder holder = new ImageHolder(productImg.getInputStream(),
                        productImg.getName());
                imageHolderList.add(holder);
            }
        }
        //这里把详情图设置也不能为空
        if (thumbnail==null||productImgList.size()<=0){
            modelMap.put("success",false );
            modelMap.put("errorMsg","上传的图片不能为空" );
            return modelMap;
        }
        if (product==null){
            modelMap.put("success",false );
            modelMap.put("errorMsg","请输入商品信息" );
            return modelMap;
        }
        ImageHolder imageHolder = new ImageHolder(thumbnail.getInputStream(),thumbnail.getName() );
        //shopId
        Shop shop = new Shop();
        Shop requestShop = (Shop) httpServletRequest.getSession().getAttribute("requestShop");
        shop.setShopId(requestShop.getShopId());
        product.setShop(shop);
        ProductExecutionDTO productExecutionDTO =  productService
                .updateProduct(product, imageHolder,imageHolderList );
        if (productExecutionDTO.getState()==ResultEnum.SUCCESS.getCode()){
            modelMap.put("success",true );
        }else {
            modelMap.put("success",false );
            modelMap.put("errorMsg",productExecutionDTO.getStataInfo() );
        }
        return modelMap;
    }


}


