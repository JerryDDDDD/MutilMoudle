package com.layman.search.exceptionPojo;

import java.util.Date;

public class ExceptionLog {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 远程访问主机IP
     */
    private String ip;

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 异常类型
     */
    private String exceptionType;

    /**
     * 异常发生时间
     */
    private Date addtime;

    /**
     * 是否查看，1：未查看、2：已查看
     */
    private Byte isView;

    /**
     * 异常信息
     */
    private String exceptionMsg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Byte getIsView() {
        return isView;
    }

    public void setIsView(Byte isView) {
        this.isView = isView;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
}
