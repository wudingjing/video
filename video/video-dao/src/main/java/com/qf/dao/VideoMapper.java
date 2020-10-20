package com.qf.dao;

import com.qf.pojo.QueryVo;
import com.qf.pojo.Video;
import com.qf.pojo.VideoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoMapper {
    int countByExample(VideoExample example);

    int deleteByExample(VideoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Video record);

    int insertSelective(Video record);

    List<Video> selectByExampleWithBLOBs(VideoExample example);

    List<Video> selectByExample(VideoExample example);

    Video selectByPrimaryKey(Integer id);

    Video fingVideo(Integer id);

    int updateByExampleSelective(@Param("record") Video record, @Param("example") VideoExample example);

    int updateByExampleWithBLOBs(@Param("record") Video record, @Param("example") VideoExample example);

    int updateByExample(@Param("record") Video record, @Param("example") VideoExample example);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKeyWithBLOBs(Video record);

    int updateByPrimaryKey(Video record);

    List<Video> findAll(QueryVo queryVo);
}