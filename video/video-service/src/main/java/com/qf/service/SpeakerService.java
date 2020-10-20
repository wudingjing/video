package com.qf.service;

import com.qf.pojo.Speaker;

import java.util.List;

public interface SpeakerService {
    List<Speaker> findAll();

    int deleteById(Integer id);

    Speaker findById(Integer id);

    void update(Speaker speaker);
}
