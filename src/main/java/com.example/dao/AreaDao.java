package com.example.dao;

import com.example.entity.Area;

import java.util.List;

/**
 * Created by z1271 on 2019/3/18.
 */
public interface AreaDao {
    /**
     * 列出区域列表
     * @return
     */
    List<Area> queryArea();
}
