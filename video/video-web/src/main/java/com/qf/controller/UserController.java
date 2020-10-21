package com.qf.controller;


import com.qf.pojo.Subject;
import com.qf.pojo.User;
import com.qf.service.SubjectService;
import com.qf.service.UserService;
import com.qf.videos.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SubjectService subjectService;

    @RequestMapping("loginUser")
    @ResponseBody
    public String loginUser(User user, HttpServletRequest request, HttpServletResponse response) {
        User user1 = userService.login(user);
        if (user1 != null) {
            HttpSession sessionScope = request.getSession();
            sessionScope.setAttribute("userAccount", user1.getEmail());
            sessionScope.setAttribute("password", user1.getPassword());
            sessionScope.setMaxInactiveInterval(60 * 60 * 24 * 7);
            Cookie cookie = new Cookie("JSESSIONID", sessionScope.getId());
            cookie.setMaxAge(30 * 60);
            response.addCookie(cookie);
        }
        return user1 != null ? "success" : "false";
    }

    @RequestMapping("loginOut")
    @ResponseBody
    public String loginOut() {
        return "false";
    }


    @RequestMapping("insertUser")
    @ResponseBody
    public String insertUser(User user) {
        userService.insert(user);
        return "success";
    }

    @RequestMapping("validateEmail")
    @ResponseBody
    public String validateEmail(String email) {


        User user1 = userService.lookUser(email);
        if (user1 == null) {
            return "success";
        } else {
            return "file";
        }
    }

    @RequestMapping("showMyProfile")
    public String showMyProfile(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("userAccount");
        User user = userService.lookUser(email);
        model.addAttribute("user", user);
        return "/before/my_profile.jsp";
    }

    @RequestMapping("changeProfile")
    public String changeProfile(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("userAccount");
        User user = userService.lookUser(email);
        model.addAttribute("user", user);
        return "/before/change_profile.jsp";
    }

    @RequestMapping("updateUser")
    public String updateUser(User user, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("userAccount");
        String password = (String) session.getAttribute("password");
        user.setEmail(email);
        user.setPassword(password);
        userService.updateUser(user);
        model.addAttribute("user", user);
        return "/before/my_profile.jsp";
    }

    @RequestMapping("changeAvatar")
    public String changeAvatar(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("userAccount");
        User user = userService.lookUser(email);
        model.addAttribute("user", user);
        return "/before/change_avatar.jsp";
    }

    @RequestMapping("upLoadImage")
    public String upLoadImage(MultipartFile image_file, Model model, HttpServletRequest request) throws IOException {
        String path = "D:\\WebSoft\\apache-tomcat-9.0.33\\webapps\\upload\\";
        String filename = image_file.getOriginalFilename();
        File file = new File(path + filename);
        String pa = "http://localhost:8083/upload/";
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("userAccount");
        User user = userService.lookUser(email);
        user.setImgUrl(pa + filename);
        image_file.transferTo(file);
        userService.updateUser(user);
        model.addAttribute("user", user);
        return "/before/change_avatar.jsp";
    }

    @RequestMapping("loginOut2")
    public String loginOut2(HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        session.removeAttribute("userAccount");
        return "redirect:/index.jsp";
    }

    @RequestMapping("passwordSafe")
    public String passwordSafe(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("userAccount");
        User user = userService.lookUser(email);
        model.addAttribute("user", user);
        return "/before/password_safe.jsp";
    }

    @RequestMapping("updatePassword")
    public String updatePassword(@RequestParam(value = "newPassword") String newPassword, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("userAccount");
        User user = userService.lookUser(email);

        user.setPassword(newPassword);
        userService.updateUser(user);

        session.removeAttribute("userAccount");
        return "redirect:/index.jsp";
    }

    @RequestMapping("validatePassword")
    @ResponseBody
    public String validatePassword(String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("userAccount");
        User user = userService.lookUser(email);
        return user.getPassword().equals(password) ? "success" : "false";
    }

    @RequestMapping("forgetPassword")
    public String forgetPassword(){
        return "/before/forget_password.jsp";
    }
    @RequestMapping("sendEmail")
    @ResponseBody
    public String sendEmail(String email,HttpServletRequest request){
        User user = userService.lookUser(email);
        String code = MailUtils.getValidateCode(6);
        HttpSession session = request.getSession();
        session.setAttribute("code",code);
        System.out.println(code);
        if (user==null){
            return "hasNoUser";
        } else {
            session.setAttribute("email",email);
            MailUtils.sendMail(email, "你好，这是您的验证码", "测试邮件随机生成的验证码是："+code);
            return "success";
        }
    }

    @RequestMapping("validateEmailCode")
    public String validateEmailCode(String code,HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        String code1 = (String) session.getAttribute("code");
        if (code1.equals(code)){
            String email = (String) session.getAttribute("email");
            model.addAttribute("email",email);
            return "/before/reset_password.jsp";
        } else {
            return "";
        }
    }

    @RequestMapping("resetPassword")
    public String resetPassword(String password,HttpServletRequest request){
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        User user = userService.lookUser(email);
        user.setPassword(password);
        userService.updateUser(user);
        session.removeAttribute("email");
        return "redirect:/index.jsp";
    }

}
