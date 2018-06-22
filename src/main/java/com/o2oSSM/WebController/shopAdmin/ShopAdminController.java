package com.o2oSSM.WebController.shopAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/25
 * 18:04
 * #
 */

@Controller
public class ShopAdminController {
    @RequestMapping(value = "/shopadmin/shopoperation")
    public String shopOperation(){
        return "shop/shopoperation";
    }

   // @RequestMapping(value = "/product/getproductcategorys")
    public String newooo(){
        return "getprocates";
    }
}
