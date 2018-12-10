package com.layman.search.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器的类
 */
public class GlobalExceptionReslover implements HandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionReslover.class);

    @Autowired
    private ExceptionLogService exceptionLogService;
    // 进阶版本
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {



        return null;
    }


    //简易版本
//    @Override
//    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) {
//        // 1.日志写入到日志文件中,这里打印
//        System.out.println(e.getMessage());
//        // 2.及时通知开发人员 短信 邮件 (通过第三方接口)
//        System.out.println("发短信");
//        // 3.给用户一个友好的提示
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("/error/exception");
//        modelAndView.addObject("message","网络异常");
//        return modelAndView;
//    }
}
