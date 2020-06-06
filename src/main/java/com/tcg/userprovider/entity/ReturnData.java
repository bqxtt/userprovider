package com.tcg.userprovider.entity;

/**
 * @author 14861
 * @date 2020/5/22
 */
public class ReturnData implements java.io.Serializable {
    public int code;
    public String message;
    public byte[] data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
