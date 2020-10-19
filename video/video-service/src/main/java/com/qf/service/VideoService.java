package com.qf.service;

import com.qf.pojo.QueryVo;
import com.qf.pojo.Video;

import java.util.List;

public interface VideoService {
    List<Video> findAll(QueryVo queryVo);

    int deleteById(Integer id);
}
