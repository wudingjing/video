package com.qf.controller;


import com.qf.pojo.Subject;
import com.qf.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    //查询所有subject
    @RequestMapping("selectAll")
    public String findAllSubject(Model model){
        List<Subject> subjectList = subjectService.findAllSubject();
        model.addAttribute("subjectList",subjectList);
        return "/course.jsp";
    }
}
