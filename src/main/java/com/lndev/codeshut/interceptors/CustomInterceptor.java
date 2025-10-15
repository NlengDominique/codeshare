package com.lndev.codeshut.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

@Component
public class CustomInterceptor implements HandlerInterceptor {

    private final Logger logger = Logger.getLogger(CustomInterceptor.class.getName());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Pre handle method is calling");
        logger.info("Request Url:" + request.getRequestURL());
        logger.info("Request Method:" + request.getMethod());
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("Post Handle method is Calling");
        logger.info("Response Status: " + response.getStatus());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("After Completion method is Calling");
        if (ex != null) {
            logger.severe("Exception: " + ex.getMessage());
        }
    }
}
