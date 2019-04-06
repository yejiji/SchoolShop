package com.example.dao;

import com.example.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by z1271 on 2019/4/6.
 */
public interface HeadLineDao {
    /**
     * 根据传入的查询条件查询头条
     * @param headLineCondition
     * @return
     */
    List<HeadLine> queryHeadLine(@Param("headLineCondition")HeadLine headLineCondition);
}
