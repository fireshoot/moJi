package com.osyangxin.moji.facade.view;


import com.osyangxin.moji.common.enums.BaseStub;

/**
 * 方法实现说明 : API统一响应模板
 * @author      yangxin
 * @date        2020/12/29 18:18
*/
public class JsonView<T> extends View {

    private int code;
    private String message;
    private String errorMessage;
    private T data;

    public JsonView(BaseStub SysErrorCodeEnum, String detail, T data) {
        this.code = SysErrorCodeEnum.getCode();
        this.message = SysErrorCodeEnum.getMsg() + ((detail == null || detail.trim().length() == 0) ? "" : " --> " + detail);
        this.data = data;
    }

    public JsonView(BaseStub SysErrorCodeEnum, T data) {
        this.code = SysErrorCodeEnum.getCode();
        this.message = SysErrorCodeEnum.getMsg();
        this.data = data;
    }

    public JsonView() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
