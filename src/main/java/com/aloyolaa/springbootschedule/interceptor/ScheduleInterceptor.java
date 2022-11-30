package com.aloyolaa.springbootschedule.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Component("schedule")
public class ScheduleInterceptor implements HandlerInterceptor {

    @Value("config.schedule.open")
    private Integer open;

    @Value("config.schedule.close")
    private Integer close;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour >= open && hour < close) {
            String message = "Welcome to Customer Service Hours. We serve from " + open + " hrs to " + close + " hrs. Thanks for your visit";
            request.setAttribute("message", message);
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/close");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String message = (String) request.getAttribute("message");
        if (modelAndView != null) {
            modelAndView.addObject("schedule", message);
        }
    }

}
