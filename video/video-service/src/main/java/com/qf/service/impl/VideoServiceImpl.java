package com.qf.service.impl;

import com.qf.dao.VideoMapper;
import com.qf.pojo.QueryVo;
import com.qf.pojo.Video;
import com.qf.pojo.VideoExample;
import com.qf.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;


    @Override
    public List<Video> findAll(QueryVo queryVo) {
        return videoMapper.findAll(queryVo);
    }

    @Override
    public int deleteById(Integer id) {
        return videoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteByIds(Integer[] ids) {
        VideoExample videoExample = new VideoExample();
        videoExample.createCriteria().andIdIn(Arrays.asList(ids));
        return videoMapper.deleteByExample(videoExample);
    }

    @Override
    public Video findById(Integer id) {
        return videoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Video video) {
        Video video1 = videoMapper.selectByPrimaryKey(video.getId());
        if (video1 == null) {
            videoMapper.insert(video);
        } else {
            videoMapper.updateByPrimaryKey(video);
        }
    }

    @Override
    public Video fingVideo(Integer id) {
        return videoMapper.fingVideo(id);
    }
}
