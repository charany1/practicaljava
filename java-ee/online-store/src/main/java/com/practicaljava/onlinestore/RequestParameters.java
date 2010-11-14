package com.practicaljava.onlinestore;

import javax.servlet.http.HttpServletRequest;

public class RequestParameters {
    public static Long getLong(HttpServletRequest request, String parameterName) {
        Long longValue;

        try {
            longValue = Long.valueOf(request.getParameter(parameterName));
        }
        catch (NumberFormatException e) {
            longValue = null;
        }

        return longValue;
    }
}
