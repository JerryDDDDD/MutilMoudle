package com.layman.search.exception;

import com.layman.pojo.TExceptionLog;
import com.layman.search.service.ExceptionLogService;
import com.layman.utils.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器的类
 */
public class GlobalExceptionReslover implements HandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionReslover.class);

    @Autowired
    private ExceptionLogService exceptionLogService;
    // 进阶版本
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) {

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", e);

        if (handler instanceof HandlerMethod) {
            LOGGER.info(">>>>>>系统异常，记录异常信息到数据库------start------");
            // 远程访问IP
            String ip = IPUtils.getRemortIP(httpServletRequest);
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            String className = handlerMethod.getBeanType().getName();
            String methodName = handlerMethod.getMethod().getName();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));

            // 插入异常日志到数据库
            TExceptionLog log = new TExceptionLog();
            log.setIp(ip);
            log.setClassName(className);
            log.setMethodName(methodName);
            log.setExceptionType(e.getClass().getSimpleName());
            log.setExceptionMsg(sw.toString()); // 异常详细信息
            log.setIsView((byte) 1); // 默认未读,1:为查看、2：已查看
            log.setAddtime(new Date());
            this.exceptionLogService.insertExceptionLogSelective(log);
            LOGGER.info(">>>>>>系统异常，记录异常信息到数据库------end------");
            //此处先写死。后期完善，接收人从数据库配置中获取
            // 发送邮件给系统维护人员
//            try {
//                String recipient = "zhangsan@abc.com";
//                String subject = "【XXXX系统异常通知】";
//                Object content = "管理员，您好：<br/>   XXXX系统出现异常，请立即登录后台系统：“系统管理”--“异常日志管理”进行查看。<br/>   "
//                        + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//                MailSenderFactory.getSender().send(recipient, subject, content);
//            } catch (Exception ex) {
//                e.printStackTrace();
//            }
        }
        return new ModelAndView("/error/exception", model);
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
