package com.spring.springcloudlibrarybook.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 出勤类
 */
public class attendance {
    private int id;
    private int userid;
    private Date morning_time;
    private Date evening_time;
    private int morning_state;
    private int evening_state;
    private String Ondate;
    private String emp_name;
    private BigDecimal emp_wage;

    public BigDecimal getEmp_wage() {
        return emp_wage;
    }

    public void setEmp_wage(BigDecimal emp_wage) {
        this.emp_wage = emp_wage;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getOndate() {
        return Ondate;
    }

    public void setOndate(String ondate) {
        Ondate = ondate;
    }

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

    public Date getMorning_time() {
        return morning_time;
    }

    public void setMorning_time(Date morning_time) {
        this.morning_time = morning_time;
    }

    public Date getEvening_time() {
        return evening_time;
    }

    public void setEvening_time(Date evening_time) {
        this.evening_time = evening_time;
    }

    public int getMorning_state() {
        return morning_state;
    }

    public void setMorning_state(int morning_state) {
        this.morning_state = morning_state;
    }

    public int getEvening_state() {
        return evening_state;
    }

    public void setEvening_state(int evening_state) {
        this.evening_state = evening_state;
    }
}
