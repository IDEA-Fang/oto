package com.o2oSSM.WebController.shopAdmin;

import com.alibaba.fastjson.JSON;
import com.o2oSSM.DataObject.Area;
import com.o2oSSM.DataObject.PersonInfo;
import com.o2oSSM.DataObject.Shop;
import com.o2oSSM.DataObject.ShopCategory;
import com.o2oSSM.DataTransferObject.ShopExecutionDTO;
import com.o2oSSM.Enums.ResultEnum;
import com.o2oSSM.Enums.ShopStateEnum;
import com.o2oSSM.Exceptions.ShopExceprion;
import com.o2oSSM.Service.IMPL.AreaServiceIMPL;
import com.o2oSSM.Service.IMPL.ShopCategoryServiceIMPL;
import com.o2oSSM.Service.IMPL.ShopServiceIMPL;
import com.o2oSSM.Service.ShopCategoryService;
import com.o2oSSM.Utils.HttpServletRequestConversionUtil;
import com.o2oSSM.Utils.ImageHolder;
import com.o2oSSM.Utils.KaptchaCodeUtil;
import com.o2oSSM.Utils.ObjectToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/24
 * 17:54
 * #
 */
@Controller
@RequestMapping(value = "/shopadmin",method = RequestMethod.POST)
@ResponseBody
public class ShopManagementController {

    @Autowired
    private ShopServiceIMPL shopService;

    @Autowired
    private ShopCategoryServiceIMPL shopCategoryService;

    @Autowired
    private AreaServiceIMPL areaService;

    @RequestMapping(value = "/getshopinitinfo",method = RequestMethod.GET)
    @ResponseBody
    public Map<String ,Object> getShopInitInfo() {
     Map<String ,Object>   modelMap = new HashMap<>();
     ShopCategory shopCategoryCondition = new ShopCategory();
     List<ShopCategory> shopCategoryList = shopCategoryService
             .getShopCategoryList(shopCategoryCondition);
     List<Area> areaList = areaService.getAreaList();

     modelMap.put("success",true );
     modelMap.put("shopCategoryList", shopCategoryList);
     modelMap.put("areaList",areaList );
     return modelMap;
    }


    @RequestMapping(value = "/registershop",method = RequestMethod.GET)
    public Map<String,Object> registerShop(HttpServletRequest httpServletRequest){

        //建一个返回类型值modelMap
        Map<String ,Object> modelMap = new HashMap<>();

        //检查验证码
        if (!KaptchaCodeUtil.checkCaptchaCode(httpServletRequest)){
            modelMap.put("success", false);
            modelMap.put("errorMsg","输入了错误的验证码");
            return  modelMap;
        }
        //接受参数并转化  用到json转换工具 获取json并转化
        String shopStr = HttpServletRequestConversionUtil.getString(httpServletRequest,"shopStr");
        Shop shop = JSON.parseObject(shopStr,Shop.class);
        //处理图片
        /**
         * 从http得到图片文件
         * httpServletRequest转化成multipartHttpServletRequest
         * multipartHttpServletRequest获取图片 转给CommonsMultipartFile
         * CommonsMultipartFile转化成File
         */
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver
                (httpServletRequest.getSession().getServletContext());
        MultipartHttpServletRequest multipartHttpServletRequest =
                (MultipartHttpServletRequest) httpServletRequest;
        //shopImg前端传入的字段
        CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) multipartHttpServletRequest
                .getFile("shopImg");//shopImg前端传入的字段

        //File shopImgFile = ObjectToFile.CommonsMultipartFileToFile(commonsMultipartFile);

        if (commonsMultipartFile == null){
            throw new ShopExceprion(ResultEnum.IMAGE_ERROR);
        }

        ImageHolder imageHolder = null;
        try {
            imageHolder = new ImageHolder(commonsMultipartFile.getInputStream(),
                    commonsMultipartFile.getOriginalFilename());
        } catch (IOException e) {
            modelMap.put("success", false);
            modelMap.put("errorMsg",e.getMessage());
            return  modelMap;
        }

        //注册店铺
        PersonInfo personInfo = new PersonInfo();
        //todo
        personInfo.setUserId(1L);
        shop.setOwnerId(personInfo.getUserId());
        ShopExecutionDTO shopExecutionDTO = null;

        shopExecutionDTO = shopService.addShop(shop,imageHolder);

        if (shopExecutionDTO.getState()==ShopStateEnum.CHECK.getCode()){
            modelMap.put("success",true);
        }else {
            modelMap.put("success", false);
            modelMap.put("errorMsg",shopExecutionDTO.getStataInfo());
            return  modelMap;
        }
        //返回
        modelMap.put("success", true);
        modelMap.put("errorMsg","注册成功");
        return modelMap;
    }
}
