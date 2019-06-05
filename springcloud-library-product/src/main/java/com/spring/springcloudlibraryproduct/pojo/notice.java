package com.spring.springcloudlibraryproduct.pojo;

import java.util.Date;

/**
 * 公告类
 */
public class notice {
    /*``id``title``content``issue_time``issue_userid``*/
    private int id;
    private String title;
    private String content;
    private String issue_time;
    private int issue_userid;
    private String empname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIssue_time() {
        return issue_time;
    }

    public void setIssue_time(String issue_time) {
        this.issue_time = issue_time;
    }

    public int getIssue_userid() {
        return issue_userid;
    }

    public void setIssue_userid(int issue_userid) {
        this.issue_userid = issue_userid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }
}
