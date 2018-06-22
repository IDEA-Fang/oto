package com.o2oSSM.WebController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CreatebyFang
 * fangfor@outlook.com
 * 2018/5/31
 * 16:36
 * #
 */

@Controller
@RequestMapping(value = "/test")
public class Testjson {


    @RequestMapping(value = "/testfreemark",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView testFreemark(@RequestParam(value = "a",required = false) String a) throws UnsupportedEncodingException {
        ModelAndView modelAndView = new ModelAndView();

        List<String> strings = new ArrayList<>();
        strings.add("make");
        strings.add("djkasjd");
        strings.add("dhsahdash544");

        modelAndView.addObject("list",strings);
        modelAndView.addObject("para",a);
        modelAndView.setViewName("new");
        return modelAndView;

    }

    @ResponseBody
    @RequestMapping(value = "getnumber")
    public Map<String ,Object> getTest(){
        Map<String,Object> map = new HashMap<>();
        map.put("success", "true");
        map.put("key",51564151 );

        return map;

    }

    @ResponseBody
    @RequestMapping(value = "/getnumber2",method = RequestMethod.POST)
    public Map<String ,Object> getTesTwo(){
        Map<String,Object> map = new HashMap<>();
        map.put("success", "flase");
        map.put("key",51564151 );

        return map;

    }
}
