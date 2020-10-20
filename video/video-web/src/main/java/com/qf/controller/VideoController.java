package com.qf.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.Course;
import com.qf.pojo.QueryVo;
import com.qf.pojo.Speaker;
import com.qf.pojo.Video;
import com.qf.service.CourseService;
import com.qf.service.SpeakerService;
import com.qf.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("video")
public class VideoController {


    @Autowired
    private VideoService videoService;

    @Autowired
    private SpeakerService speakerService;

    @Autowired
    private CourseService courseService;


    @RequestMapping("list")
    public String list(Model model,
                       QueryVo queryVo,
                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        List<Speaker> speakerList = speakerService.findAll();
        List<Course> courseList = courseService.findAll();

        PageHelper.startPage(pageNum, 10);
        List<Video> videoList = videoService.findAll(queryVo);
        PageInfo<Video> pageInfo = new PageInfo<>(videoList);

        model.addAttribute("speakerList", speakerList);
        model.addAttribute("courseList", courseList);
        model.addAttribute("videoList", videoList);
        model.addAttribute("queryVo", queryVo);
        model.addAttribute("pageInfo", pageInfo);

        return "/behind/videoList.jsp";
    }


    @RequestMapping("videoDel")
    @ResponseBody
    public String videoDel(Integer id) {

        return videoService.deleteById(id) == 1 ? "success" : "false";
    }


    @RequestMapping("delBatchVideos")
    public String delBatchVideos(Integer[] ids) {
        videoService.deleteByIds(ids);
        return "redirect:/video/list";
    }

    @RequestMapping("edit")
    public String edit(Integer id, Model model) {
        Video video = videoService.findById(id);
        List<Speaker> speakerList = speakerService.findAll();
        List<Course> courseList = courseService.findAll();

        model.addAttribute("speakerList", speakerList);
        model.addAttribute("courseList", courseList);
        model.addAttribute("video", video);
        return "/behind/addVideo.jsp";
    }


    @RequestMapping("addVideo")
    public String addVideo(Model model) {
        List<Speaker> speakerList = speakerService.findAll();
        List<Course> courseList = courseService.findAll();

        model.addAttribute("speakerList", speakerList);
        model.addAttribute("courseList", courseList);
        return "/behind/addVideo.jsp";
    }


    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Video video,Model model) {
        videoService.update(video);
        return "redirect:/video/list";
    }


}
