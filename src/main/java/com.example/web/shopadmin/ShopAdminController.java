package com.example.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by z1271 on 2019/3/22.
 */
@Controller
@RequestMapping(value = "shopadmin",method = {RequestMethod.GET})
public class ShopAdminController {
    @RequestMapping(value = "/shopoperation")
    public String shopOperation(){
        return "shop/shopoperation";
    }
    @RequestMapping(value = "/shoplist")
    public String shopList(){
        return "shop/shoplist";
    }
    @RequestMapping(value = "/shopmangagement")
    public String shopMangagement(){
        return "shop/shopmangagement";
    }
    @RequestMapping(value = "/productcategorymangagement",method = {RequestMethod.GET})
    public String productCategoryManage(){
        return "shop/productcategorymanagement";
    }
    @RequestMapping(value = "/productoperation")
    public String productOperation(){
        return "shop/productoperation";
    }
}
