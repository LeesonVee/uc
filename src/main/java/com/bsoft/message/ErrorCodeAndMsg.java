package com.bsoft.message;

/**
 * Arthur Vee
 * date 2019/12/31.
 */
public enum ErrorCodeAndMsg {
    HTTP_CODE_404("404","不存在"),
    HTTP_CODE_415("415","参数缺失"),
    HTTP_CODE_500("500","系统异常"),
    Entity_does_not_exist("0001","数据不存在"),
    Column_length_long("0002","字段长度过长"),
    Entity_pkey_is_empty("0003","主键为空"),
    Network_error("9999","网络错误，待会重试");

    private String code;
    private String msg;

    ErrorCodeAndMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
