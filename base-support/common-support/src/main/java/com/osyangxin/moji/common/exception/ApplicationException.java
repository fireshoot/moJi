package com.osyangxin.moji.common.exception;


import com.osyangxin.moji.common.enums.BaseStub;

/**
 * @author fengjianshe 全局自定义异常,所有可知异常有此异常统一抛出
 */
public class ApplicationException extends RuntimeException {

    private BaseStub retStub;

    public ApplicationException(BaseStub retStub) {
        this.retStub = retStub;
    }

    public BaseStub getRetStub() {
        return retStub;
    }

    public void setRetStub(BaseStub retStub) {
        this.retStub = retStub;
    }

    @Override
    public String getMessage() {
        return retStub.getCode() + " : " + retStub.getMsg();
    }

}
