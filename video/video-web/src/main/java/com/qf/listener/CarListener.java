package com.qf.listener;

import com.qf.pojo.User;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;

public class CarListener implements HttpSessionListener {


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        User users = new User();
        se.getSession().setAttribute("userAccount",users);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
