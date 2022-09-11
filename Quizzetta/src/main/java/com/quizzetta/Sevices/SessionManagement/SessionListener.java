package com.quizzetta.Sevices.SessionManagement;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    // TODO ADMIN NEEDS TO BE ADDED OR CHANGED
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("userId", null);
        se.getSession().setAttribute("username", null);
        se.getSession().setAttribute("userFirstName", null);
        se.getSession().setAttribute("userLastName", null);
        se.getSession().setAttribute("userEmail", null);
        se.getSession().setAttribute("userImageUrl", null);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }

}
