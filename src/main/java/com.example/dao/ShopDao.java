package com.example.dao;

import com.example.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by z1271 on 2019/3/19.
 */
public interface ShopDao {
    /**
     * 分页查询店铺 可输入条件有，店铺名 店铺状态，店铺类别，区域ID owner
     * @param shopCondition
     * @param rowIndex 从第几行
     * @param pageSize 返回的条数
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition,
                             @Param("rowIndex")int rowIndex,@Param("pageSize") int pageSize);

    /**
     * 返回queryList总数
     * @param shopCondition
     * @return
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);
    /**
     * 通过shop id查询店铺
     * @param shopId
     * @return
     */
    Shop queryByShopId(long shopId);
    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺
     * @param shop
     * @return
     */
    int updateShop(Shop shop);

}
