package com.qf.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.pojo.Speaker;
import com.qf.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("speaker")
public class SpeakerController {
    @Autowired
    private SpeakerService speakerService;

    @RequestMapping("showSpeakerList")
    public String showSpeakerList(Model model,
                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {

        PageHelper.startPage(pageNum,3);
        List<Speaker> speakerList = speakerService.findAll();
        PageInfo<Speaker> pageInfo = new PageInfo<>(speakerList);

        System.out.println(speakerList);
        model.addAttribute("speakerList", speakerList);
        model.addAttribute("pageInfo", pageInfo);

        return "/behind/speakerList.jsp";

    }




    @RequestMapping("speakerDel")
    @ResponseBody
    public String speakerDel(Integer id) {

        return speakerService.deleteById(id) == 1 ? "success" : "false";

    }

    @RequestMapping("edit")
    public String edit(Integer id, Model model) {
        Speaker speaker = speakerService.findById(id);

        model.addAttribute("speaker",speaker);
        return "/behind/addSpeaker.jsp";

    }



    @RequestMapping("saveOrUpdate")
    public String saveOrUpdate(Speaker speaker, Model model) {

        speakerService.update(speaker);
        return "redirect:/speaker/showSpeakerList";

    }



    @RequestMapping("addSpeaker")
    public String addSpeaker() {
        return "/behind/addSpeaker.jsp";

    }



}
