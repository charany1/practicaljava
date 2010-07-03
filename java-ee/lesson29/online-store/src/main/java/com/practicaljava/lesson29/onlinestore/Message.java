package com.practicaljava.lesson29.onlinestore;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Message {
    static public void setInfoMessage(HttpServletRequest request, String message) {
        request.getSession().setAttribute("flashInfo", message);
    }

    static public void setErrorMessage(HttpServletRequest request, String message) {
        request.getSession().setAttribute("flashError", message);
    }
    
    static public void setErrorMessages(HttpServletRequest request, Map<String, String> messages) {
        request.getSession().setAttribute("flashErrors", messages);
    }
}
