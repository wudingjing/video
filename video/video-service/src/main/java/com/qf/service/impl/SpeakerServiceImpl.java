package com.qf.service.impl;

import com.qf.dao.SpeakerMapper;
import com.qf.pojo.Speaker;
import com.qf.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakerServiceImpl implements SpeakerService {

    @Autowired
    private SpeakerMapper speakerMapper;

    @Override
    public List<Speaker> findAll() {

        return speakerMapper.selectByExampleWithBLOBs(null);
    }

    @Override
    public int deleteById(Integer id) {
        return speakerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Speaker findById(Integer id) {
        return speakerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Speaker speaker) {
        Speaker speaker1 = speakerMapper.selectByPrimaryKey(speaker.getId());
        if (speaker1 == null) {
            speakerMapper.insert(speaker);
        } else {
            speakerMapper.updateByPrimaryKeyWithBLOBs(speaker);
        }
    }
}
