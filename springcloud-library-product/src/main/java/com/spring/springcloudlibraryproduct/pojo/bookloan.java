package com.spring.springcloudlibraryproduct.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 书籍借出归还记录类
 */
public class bookloan {
    private int loan_id;
    private int loan_bookid;
    private int loan_userid;
    private Date loan_loantime;
    private Date loan_returntime;
    private Date loan_deadline;
    private BigDecimal loan_money;
    private int loan_handler;
    private int loan_frontdamage;
    private int loan_returndamage;
    private BigDecimal loan_penalty;

    public int getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(int loan_id) {
        this.loan_id = loan_id;
    }

    public int getLoan_bookid() {
        return loan_bookid;
    }

    public void setLoan_bookid(int loan_bookid) {
        this.loan_bookid = loan_bookid;
    }

    public int getLoan_userid() {
        return loan_userid;
    }

    public void setLoan_userid(int loan_userid) {
        this.loan_userid = loan_userid;
    }

    public Date getLoan_loantime() {
        return loan_loantime;
    }

    public void setLoan_loantime(Date loan_loantime) {
        this.loan_loantime = loan_loantime;
    }

    public Date getLoan_returntime() {
        return loan_returntime;
    }

    public void setLoan_returntime(Date loan_returntime) {
        this.loan_returntime = loan_returntime;
    }

    public Date getLoan_deadline() {
        return loan_deadline;
    }

    public void setLoan_deadline(Date loan_deadline) {
        this.loan_deadline = loan_deadline;
    }

    public BigDecimal getLoan_money() {
        return loan_money;
    }

    public void setLoan_money(BigDecimal loan_money) {
        this.loan_money = loan_money;
    }

    public int getLoan_handler() {
        return loan_handler;
    }

    public void setLoan_handler(int loan_handler) {
        this.loan_handler = loan_handler;
    }

    public int getLoan_frontdamage() {
        return loan_frontdamage;
    }

    public void setLoan_frontdamage(int loan_frontdamage) {
        this.loan_frontdamage = loan_frontdamage;
    }

    public int getLoan_returndamage() {
        return loan_returndamage;
    }

    public void setLoan_returndamage(int loan_returndamage) {
        this.loan_returndamage = loan_returndamage;
    }

    public BigDecimal getLoan_penalty() {
        return loan_penalty;
    }

    public void setLoan_penalty(BigDecimal loan_penalty) {
        this.loan_penalty = loan_penalty;
    }
}
