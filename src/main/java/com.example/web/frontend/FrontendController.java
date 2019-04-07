package com.example.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by z1271 on 2019/4/7.
 */
@Controller
@RequestMapping("/frontend")
public class FrontendController {


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    private String index(){
        return "frontend/index";
    }
    @RequestMapping(value = "/productdetail",method = RequestMethod.GET)
    private String productdetail(){return "frontend/productdetail";}
}
