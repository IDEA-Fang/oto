package com.o2oSSM.WebController.superadmin;

import com.o2oSSM.DataObject.Area;
import com.o2oSSM.Service.IMPL.AreaServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/16
 * 20:49
 * #
 */

@Controller
@RequestMapping("/superadmin")
@ResponseBody
public class AreaController {

    @Autowired
    private AreaServiceIMPL areaService;


    @RequestMapping("/hello")
    public String hello(){
        return "helloword";
    }

    @RequestMapping(value = "/arealist",method = RequestMethod.GET)
    public Map<Object,Object> areaList(){
        Map<Object,Object> map = new HashMap<>();
         List<Area> list =  areaService.getAreaList();
         map.put("rows", list);
         map.put("total",list.size());
         return map;
    }
}
