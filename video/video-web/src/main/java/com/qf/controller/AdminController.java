package com.qf.controller;

import com.qf.pojo.Admin;
import com.qf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("loginView")
    public String loginView() {
        return "/behind/login.jsp";
    }

    @RequestMapping("login")
    @ResponseBody
    public String login(Admin admin, HttpServletRequest request) {

        Admin admin1 = adminService.login(admin);

        if (admin1 == null) {
            return "false";
        } else {
            HttpSession sessionScope = request.getSession();
            sessionScope.setAttribute("userName", admin.getUsername());
            return "success";
        }
    }


    @RequestMapping("exit")
    public String exit() {
        return "redirect:/admin/loginView";
    }

}
