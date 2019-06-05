package com.springcloud.records.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 员工类
 */
public class employee {
    private int emp_id;
    private String emp_name;
    private int emp_role;
    private String emp_pwd;
    private BigDecimal emp_wage;
    private Date emp_hiredate;
    private String emp_username;
    private int emp_gender;
    private String emp_phone;
    private String imgpath;

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getEmp_phone() {
        return emp_phone;
    }

    public void setEmp_phone(String emp_phone) {
        this.emp_phone = emp_phone;
    }

    public int getEmp_gender() {
        return emp_gender;
    }

    public void setEmp_gender(int emp_gender) {
        this.emp_gender = emp_gender;
    }

    public String getEmp_username() {
        return emp_username;
    }

    public void setEmp_username(String emp_username) {
        this.emp_username = emp_username;
    }
    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public int getEmp_role() {
        return emp_role;
    }

    public void setEmp_role(int emp_role) {
        this.emp_role = emp_role;
    }

    public String getEmp_pwd() {
        return emp_pwd;
    }

    public void setEmp_pwd(String emp_pwd) {
        this.emp_pwd = emp_pwd;
    }

    public BigDecimal getEmp_wage() {
        return emp_wage;
    }

    public void setEmp_wage(BigDecimal emp_wage) {
        this.emp_wage = emp_wage;
    }

    public Date getEmp_hiredate() {
        return emp_hiredate;
    }

    public void setEmp_hiredate(Date emp_hiredate) {
        this.emp_hiredate = emp_hiredate;
    }
}
