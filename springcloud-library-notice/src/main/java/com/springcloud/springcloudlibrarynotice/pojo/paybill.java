package com.springcloud.springcloudlibrarynotice.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 员工工资账单信息类
 */
public class paybill {
    /*`id``userid``username``clearing_time``salary``handler`*/
    private int id;
    private int userid;
    private String username;
    private Date clearing_time;
    private BigDecimal salary;
    private int handler;

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

    public Date getClearing_time() {
        return clearing_time;
    }

    public void setClearing_time(Date clearing_time) {
        this.clearing_time = clearing_time;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public int getHandler() {
        return handler;
    }

    public void setHandler(int handler) {
        this.handler = handler;
    }
}
