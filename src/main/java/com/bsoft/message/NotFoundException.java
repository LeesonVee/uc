package com.bsoft.message;/**
 * Arthur Administrator
 * date 2019/12/31.
 */

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 *@ClassName NotFoundException
 *@Description 404异常处理
 *@Author Vee
 *@Date 2019/12/31 10:50
 *@Version 1.0
 **/
@Controller
public class NotFoundException implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = {"/error"})
    @ResponseBody
    public Object error(HttpServletRequest request) {
        Response response = new Response(ErrorCodeAndMsg.HTTP_CODE_404.getCode(),ErrorCodeAndMsg.HTTP_CODE_404.getMsg());
        return response;
    }
}

