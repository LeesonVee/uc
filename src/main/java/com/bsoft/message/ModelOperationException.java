package com.bsoft.message;

/**
 *@ClassName ModelOperationException
 *@Description TODO
 *@Author Vee
 *@Date 2019/12/31 10:15
 *@Version 1.0
 **/
public class ModelOperationException extends RuntimeException {
    private static final long serialVersionUID = -6370612186038915645L;

    private final ErrorCodeAndMsg response;

    public ModelOperationException(ErrorCodeAndMsg response) {
        this.response = response;
    }
    public ErrorCodeAndMsg getResponse() {
        return response;
    }
}
