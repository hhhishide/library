package com.spring.springcloudlibraryemp.pojo;

import java.math.BigDecimal;

/**
 * 书籍借出归还记录类
 */
public class bookloan {
    private int loan_id;
    private int loan_bookid;
    private int loan_userid;
    private String loan_userName;
    private String bookName;
    private String loan_loantime;
    private String loan_returntime;
    private Integer loan_deadline;
    private BigDecimal loan_money;
    private String loan_handler;
    private int loan_frontdamage;
    private int loan_returndamage;
    private BigDecimal loan_penalty;
    private String user_phone;
    private int loan_state;

    public int getLoan_state() {
        return loan_state;
    }

    public void setLoan_state(int loan_state) {
        this.loan_state = loan_state;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

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

    public String getLoan_userName() {
        return loan_userName;
    }

    public void setLoan_userName(String loan_userName) {
        this.loan_userName = loan_userName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getLoan_loantime() {
        return loan_loantime;
    }

    public void setLoan_loantime(String loan_loantime) {
        this.loan_loantime = loan_loantime;
    }

    public String getLoan_returntime() {
        return loan_returntime;
    }

    public void setLoan_returntime(String loan_returntime) {
        this.loan_returntime = loan_returntime;
    }

    public Integer getLoan_deadline() {
        return loan_deadline;
    }

    public void setLoan_deadline(Integer loan_deadline) {
        this.loan_deadline = loan_deadline;
    }

    public BigDecimal getLoan_money() {
        return loan_money;
    }

    public void setLoan_money(BigDecimal loan_money) {
        this.loan_money = loan_money;
    }

    public String getLoan_handler() {
        return loan_handler;
    }

    public void setLoan_handler(String loan_handler) {
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
