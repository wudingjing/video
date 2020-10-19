package com.qf.controller;

import com.qf.pojo.Admin;
import com.qf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("loginView")
    public String loginView(){
        return "/behind/login.jsp";
    }

    @RequestMapping("login")
    @ResponseBody
    public String login(Admin admin) {

        Admin admin1 = adminService.login(admin);

        return admin1 != null ? "success" : "false";
    }
}
