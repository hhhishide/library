package com.spring.springcloudlibraryproduct.Service;

import com.spring.springcloudlibraryproduct.pojo.attendance;
import com.spring.springcloudlibraryproduct.pojo.employee;
import com.spring.springcloudlibraryproduct.pojo.fenye;
import com.spring.springcloudlibraryproduct.pojo.leave;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "library-emp")
public interface EmpService {

    @RequestMapping(value = "/getattendByid",method = RequestMethod.GET)
    public List<attendance> getattendByid(@RequestParam(value = "empid",required = false)int empid
            , @RequestParam(value = "pageSize",required = false)Integer pageSize,@RequestParam(value = "pageNo",required = false)Integer pageNo);

    @RequestMapping(value = "/getEmpCount", method = RequestMethod.GET)
    public int getEmpCount(@RequestParam(value = "empid",required = false)int empid,@RequestParam(value = "prefixdate",required = false)String prefixdate
            ,@RequestParam(value = "suffixdate",required = false)String suffixdate, @RequestParam(value = "daka_state",required = false)Integer daka_state);

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public int login(@RequestParam(value = "username",required = false)String username, @RequestParam(value = "passwrod",required = false)String passwrod);

    @RequestMapping(value = "/selectEmpByname", method = RequestMethod.GET)
    public employee getEmpByname(@RequestParam(value = "username",required = false)String username);

    @RequestMapping(value = "/getattendBycondition",method = RequestMethod.GET)
    public List<attendance> getattendBycondition(@RequestParam(value = "empid",required = false)int empid
            , @RequestParam(value = "pageSize",required = false)Integer pageSize,@RequestParam(value = "pageNo",required = false)Integer pageNo
            , @RequestParam(value = "prefixdate",required = false)String prefixdate,@RequestParam(value = "suffixdate",required = false)String suffixdate
            , @RequestParam(value = "daka_state",required = false)Integer daka_state);

    @RequestMapping(value = "/daka", method = RequestMethod.GET)
    public int daka(@RequestParam(value = "datetime",required = false)String datetime
            ,@RequestParam(value = "username",required = false)String username,@RequestParam(value = "empid",required = false)Integer empid
            ,@RequestParam(value = "hour",required = false)Integer hour,@RequestParam("timea")String timea);


    @RequestMapping(value = "/addleave",method = RequestMethod.POST)
    public int addleave(@RequestBody leave leave);

    @RequestMapping(value = "/deleta_notice", method = RequestMethod.GET)
    public int deleta_notice(@RequestParam(value = "notice_id")String notice_id);

    @RequestMapping(value = "getAllEmp", method = RequestMethod.GET)
    public List<employee> getAllEmp(@RequestParam(value = "entry_starttime",required = false)String entry_starttime
            ,@RequestParam(value = "entry_prefixtime",required = false)String entry_prefixtime
            ,@RequestParam(value = "empName",required = false)String empName
            ,@RequestParam(value = "roleID",required = false)String roleID
            ,@RequestParam(value = "pageNo", required = false)Integer pageNo);

    @RequestMapping(value = "/getEmpBycondition", method = RequestMethod.POST)
    public Object getEmpBycondition(@RequestParam(value = "entry_starttime",required = false)String entry_starttime
            ,@RequestParam(value = "entry_prefixtime",required = false)String entry_prefixtime
            ,@RequestParam(value = "empName",required = false)String empName
            ,@RequestParam(value = "roleID",required = false)String roleID
            ,@RequestParam(value = "pageNo", required = false)Integer pageNo);

    @RequestMapping(value = "/getEmpCounts",method = RequestMethod.GET)
    public int getCount(@RequestParam(value = "entry_starttime",required = false)String entry_starttime
            ,@RequestParam(value = "entry_prefixtime",required = false)String entry_prefixtime
            ,@RequestParam(value = "empName",required = false)String empName
            ,@RequestParam(value = "roleID",required = false)String roleID);

    @RequestMapping(value = "deleteEmp")
    public int deleteEmp(@RequestParam("emp_id")Integer emp_id);

    @RequestMapping(value = "addEmp")
    public int addEmp(employee employee);
}
