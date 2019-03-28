package com.example.service.impl;

import com.example.dao.AreaDao;
import com.example.entity.Area;
import com.example.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by z1271 on 2019/3/19.
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {

        return areaDao.queryArea();
    }
}
