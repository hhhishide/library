package com.spring.springcloudlibraryproduct.pojo;

import java.util.Date;

/**
 * 员工离职类
 */
public class expel {
    private int id;
    private int userid;
    private String username;
    private String handler;
    private String expel_reason;
    private Date request_time;
    private Date response_time;
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getExpel_reason() {
        return expel_reason;
    }

    public void setExpel_reason(String expel_reason) {
        this.expel_reason = expel_reason;
    }

    public Date getRequest_time() {
        return request_time;
    }

    public void setRequest_time(Date request_time) {
        this.request_time = request_time;
    }

    public Date getResponse_time() {
        return response_time;
    }

    public void setResponse_time(Date response_time) {
        this.response_time = response_time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
