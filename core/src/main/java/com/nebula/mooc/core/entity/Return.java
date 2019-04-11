/*
 * @author Zhanghh
 * @date 2019/4/11
 */
package com.nebula.mooc.core.entity;

import java.io.Serializable;

/*
 * Common return
 * @param T Data type
 */
public class Return<T> implements Serializable {
    public static final long serialVersionUID = 1L;

    public static final int SUCCESS_CODE = 100;
    public static final int ERROR_CODE = 300;
    public static final Return<String> SUCCESS = new Return<>(null);


    private int code;
    private String msg;
    private T data;

    public Return(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Return(T data) {
        this.code = SUCCESS_CODE;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
