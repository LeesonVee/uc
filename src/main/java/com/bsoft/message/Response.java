package com.bsoft.message;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 *@ClassName Response
 *@Description 同一返回参数集
 *@Author Vee
 *@Date 2019/12/31 9:56
 *@Version 1.0
 **/

@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class Response<T> implements Serializable{

    private static final long serialVersionUID = -4505655308965878999L;

    //请求成功返回码为：200
    private static final String successCode = "200";
    //返回数据
    private T data;
    //返回码
    private String code;
    //返回描述
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Response(){
        this.code = successCode;
        this.msg = "请求成功";
    }

    public Response(String code, String msg){
        this();
        this.code = code;
        this.msg = msg;
    }
    public Response(String code, String msg, T data){
        this();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Response(T data){
        this();
        this.data = data;
    }
}
