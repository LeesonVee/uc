package com.bsoft.message;/**
 * Arthur Administrator
 * date 2019/12/31.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *@ClassName ExceptionHandler
 *@Description 异常处理机制
 *@Author Vee
 *@Date 2019/12/31 10:17
 *@Version 1.0
 **/

@ControllerAdvice
public class ExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(ModelOperationException.class)
    @ResponseBody
    public Response handleStudentException(HttpServletRequest request, ModelOperationException ex) {
        Response response;
        log.error("ModelOperationException code:{},msg:{}",ex.getResponse().getCode(),ex.getResponse().getMsg());
        response = new Response(ex.getResponse().getCode(),ex.getResponse().getMsg());
        return response;
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public Response handleException(HttpServletRequest request, Exception ex) {
        Response response;
        log.error("exception error:{}", ex);
        response = new Response(ErrorCodeAndMsg.Network_error.getCode(),
                ErrorCodeAndMsg.Network_error.getMsg());
        return response;
    }
}
