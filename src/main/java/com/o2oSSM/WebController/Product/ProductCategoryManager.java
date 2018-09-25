package com.o2oSSM.WebController.Product;

import com.o2oSSM.DataObject.ProductCategory;
import com.o2oSSM.DataObject.Shop;
import com.o2oSSM.DataTransferObject.ProductCategoryExecutionDTO;
import com.o2oSSM.Enums.ResultEnum;
import com.o2oSSM.Exceptions.ShopExceprion;
import com.o2oSSM.Service.IMPL.ProductCategoryServiceIMPL;
import com.o2oSSM.Utils.ResultViewOUtil;
import com.o2oSSM.VO.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/30
 * 11:36
 * #
 */

@Controller
@RequestMapping(value = "/product")
public class ProductCategoryManager {

    @Autowired
    private ProductCategoryServiceIMPL productCategoryService;

    //js
    @RequestMapping(value = "/getproductcategorys",method = RequestMethod.GET)
    public ResultVO<List<ProductCategory>> getProductCategorys
            (HttpServletRequest httpServletRequest){
        Shop shop =new Shop();
        shop.setShopId(20L);
        httpServletRequest.getSession().setAttribute("currentShop",shop );
        Shop currentShop = (Shop) httpServletRequest.getSession().getAttribute("currentShop");

        if (currentShop!=null&&currentShop.getShopId()>0){
            System.out.println("++++___+++++");
            List<ProductCategory> productCategories = productCategoryService
                    .queryProductCategoryById(currentShop.getShopId());
            return ResultViewOUtil.success(productCategories);
        }else {
            ShopExceprion pcError = new ShopExceprion(ResultEnum.SHOPID_ERROR);
            return ResultViewOUtil.error(-1,pcError.getMessage());
        }
    }

    //freemark
    @RequestMapping(value = "/getproductcategorymodel",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getProductCategoryModel
            (HttpServletRequest httpServletRequest){
        ModelAndView modelAndView = new ModelAndView();

        Shop shop =new Shop();
        shop.setShopId(20L);
        httpServletRequest.getSession().setAttribute("currentShop",shop );
        Shop currentShop = (Shop) httpServletRequest.getSession().getAttribute("currentShop");

        if (currentShop!=null&&currentShop.getShopId()>0){
            System.out.println("++++___+++++");
            List<ProductCategory> productCategories = productCategoryService
                    .queryProductCategoryById(currentShop.getShopId());
            modelAndView.addObject("success",true);
            modelAndView.addObject("productCategories",productCategories);
            modelAndView.setViewName("/product/getprocates");
            return modelAndView;
        }else {
            ShopExceprion pcError = new ShopExceprion(ResultEnum.SHOPID_ERROR);
            modelAndView.addObject("success",false);
            modelAndView.addObject("errorMessage",pcError.getMessage());
            modelAndView.addObject("errorCode",pcError.getCode());
            modelAndView.setViewName("/product/getprocates");
            return modelAndView;
        }
    }

    //TODO controller
    @RequestMapping(value = "/addproductcategories")
    @ResponseBody
    public Map<String,Object> addproductcategories(
            @RequestBody List<ProductCategory> productCategories,
            HttpServletRequest httpServletRequest){
        Map<String,Object> map = new HashMap<>();
        Shop shop = new Shop();
        shop.setShopId(20L);

        if (productCategories==null&&productCategories.size()<=0){
            map.put("success", false);
            map.put("errMsg", "请至少输入一个商品类别");
        }
        ProductCategoryExecutionDTO addProductCategory = productCategoryService.batchAddProductCategory(productCategories);
        if (!addProductCategory.getState().equals(ResultEnum.SUCCESS)){
            map.put("success",false );
            map.put("errorMsg",addProductCategory.getStateInfo() );
        }else {
            map.put("success",true );
        }
        return map;

    }


    @RequestMapping(value = "/deleteproductgory")
    @ResponseBody
    public Map<String ,Object> deleteProductCategory(Long productCategoryId,Long shopId){
        Map map = new HashMap();
        if (productCategoryId==null&&shopId==null){
            map.put("success",false );
            map.put("errorMsg","请输入商品类目id和店铺id" );
            return  map;
        }
        Integer integer = productCategoryService
                .deleteProductCategory(productCategoryId,shopId );
        if (integer<0){
            map.put("success",false );
            map.put("errorMsg","删除失败，请重试" );
            return  map;
        }
        map.put("success",true );
        map.put("errorMsg","删除成功" );
        return map;
    }

}
