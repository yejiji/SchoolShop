package com.example.service;

import com.example.entity.HeadLine;

import java.io.IOException;
import java.util.List;

/**
 * Created by z1271 on 2019/4/6.
 */
public interface HeadLineService {
    /**
     *
     * @param headLineCondition
     * @return
     * @throws IOException
     */
    List<HeadLine> getHeadList(HeadLine headLineCondition) throws IOException;
}
