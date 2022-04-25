package com.java1234.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 根目录请求
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView root(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("index");
        mav.addObject("title","扫码登录测试");
        return mav;
    }

}
