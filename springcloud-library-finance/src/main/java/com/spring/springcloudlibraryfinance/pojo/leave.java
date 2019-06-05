package com.spring.springcloudlibraryfinance.pojo;

import java.util.Date;

/**
 * 请假类
 */
public class leave {
    private int leave_id;
    private String leave_cause;
    private Date leave_starttime;
    private Date leave_overtime;
    private int leave_number;
    private int leave_state;
    private int leave_handler;
    private Date leave_handlertime;
    private int leave_userid;

    public int getLeave_userid() {
        return leave_userid;
    }

    public void setLeave_userid(int leave_userid) {
        this.leave_userid = leave_userid;
    }

    public int getLeave_id() {
        return leave_id;
    }

    public void setLeave_id(int leave_id) {
        this.leave_id = leave_id;
    }

    public String getLeave_cause() {
        return leave_cause;
    }

    public void setLeave_cause(String leave_cause) {
        this.leave_cause = leave_cause;
    }

    public Date getLeave_starttime() {
        return leave_starttime;
    }

    public void setLeave_starttime(Date leave_starttime) {
        this.leave_starttime = leave_starttime;
    }

    public Date getLeave_overtime() {
        return leave_overtime;
    }

    public void setLeave_overtime(Date leave_overtime) {
        this.leave_overtime = leave_overtime;
    }

    public int getLeave_number() {
        return leave_number;
    }

    public void setLeave_number(int leave_number) {
        this.leave_number = leave_number;
    }

    public int getLeave_state() {
        return leave_state;
    }

    public void setLeave_state(int leave_state) {
        this.leave_state = leave_state;
    }

    public int getLeave_handler() {
        return leave_handler;
    }

    public void setLeave_handler(int leave_handler) {
        this.leave_handler = leave_handler;
    }

    public Date getLeave_handlertime() {
        return leave_handlertime;
    }

    public void setLeave_handlertime(Date leave_handlertime) {
        this.leave_handlertime = leave_handlertime;
    }
}
