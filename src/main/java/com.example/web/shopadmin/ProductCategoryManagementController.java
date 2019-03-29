package com.example.web.shopadmin;

import com.example.dto.ProductCategoryExecution;
import com.example.dto.Result;
import com.example.entity.ProductCategory;
import com.example.entity.Shop;
import com.example.enums.ProductCategoryStateEnum;
import com.example.exceptions.ProductCategoryOperationException;
import com.example.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by z1271 on 2019/3/28.
 */
@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "/getproductcategorylist",method = RequestMethod.GET)
    @ResponseBody
    private Result<List<ProductCategory>>getProductCategoryList(HttpServletRequest request){

        Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
        List<ProductCategory> list = null;
        if (currentShop!=null&&currentShop.getShopId()>0){
            list = productCategoryService.queryProductCategoryList(currentShop.getShopId());
            return new Result<List<ProductCategory>>(true,list);
        }else {
            ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<List<ProductCategory>>(false,ps.getState(),ps.getStateInfo());
        }
    }

    @RequestMapping(value = "/addproductcategorys",method = RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> addProductCategorys(@RequestBody List<ProductCategory> productCategoryList,HttpServletRequest request){
        Map<String ,Object> modelMap = new HashMap<String, Object>();
        Shop currentShop=(Shop)request.getSession().getAttribute("currentShop");
        for (ProductCategory pc : productCategoryList){
            pc.setShopId(currentShop.getShopId());
        }
        if (productCategoryList!=null&&productCategoryList.size()>0){
            try {
                ProductCategoryExecution pe = productCategoryService.batchAddProductCategoryList(productCategoryList);
                if (pe.getState() == ProductCategoryStateEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errMsg",pe.getStateInfo());
                }
            }catch (ProductCategoryOperationException e){
                modelMap.put("success",false);
                modelMap.put("errMsg",e.toString());
                return modelMap;
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","请至少输入一件商品");
        }
         return modelMap;
    }

}
