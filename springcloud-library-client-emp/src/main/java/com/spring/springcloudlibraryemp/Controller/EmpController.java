package com.spring.springcloudlibraryemp.Controller;

import com.spring.springcloudlibraryemp.Service.EmpService;
import com.spring.springcloudlibraryemp.Service.EmpServiceImpl;
import com.spring.springcloudlibraryemp.pojo.*;
import jdk.nashorn.internal.ir.ReturnNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmpController {

    @Autowired
    private EmpServiceImpl empServiceimpl;
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public int login(@RequestParam(value = "username",required = false)String username, @RequestParam("password")String password){
        int result = empServiceimpl.login(username, password);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/selectEmpByname", method = RequestMethod.GET)
    public employee selectEmpByname(@RequestParam(value = "username",required = false)String username){
         System.out.println("进入selectEmpByname");
         return empServiceimpl.selectEmpByname(username);
    }
    @ResponseBody
    @RequestMapping(value = "/getattendByid", method = RequestMethod.GET)
    public List<attendance> getattendByid(@RequestParam(value = "empid", required = false)int empid
            ,@RequestParam(value = "pageSize",required = false) Integer pageSize
            ,@RequestParam(value = "pageNo",required = false)Integer pageNo){
        return empServiceimpl.getattendByid(empid, pageSize,pageNo);
    }
    @ResponseBody
    @RequestMapping(value = "/getattendBycondition", method = RequestMethod.GET)
    public List<attendance> getattendBycondition(@RequestParam(value = "empid",required = false)int empid
            , @RequestParam(value = "pageSize",required = false)Integer pageSize,@RequestParam(value = "pageNo",required = false)Integer pageNo
            , @RequestParam(value = "prefixdate",required = false)String prefixdate,@RequestParam(value = "suffixdate",required = false)String suffixdate
            , @RequestParam(value = "daka_state",required = false)Integer daka_state){
        return empServiceimpl.getattendBycondition(empid, pageSize, pageNo, prefixdate, suffixdate, daka_state);
    }
    @ResponseBody
    @RequestMapping(value = "/getEmpCount",method = RequestMethod.GET)
    public int getCount(@RequestParam(value = "empid",required = false)int empid,@RequestParam(value = "prefixdate",required = false)String prefixdate
            ,@RequestParam(value = "suffixdate",required = false)String suffixdate, @RequestParam(value = "daka_state",required = false)Integer daka_state){
        System.out.println(prefixdate+suffixdate);
        return empServiceimpl.getCount(empid,prefixdate,suffixdate,daka_state);
    }
    @ResponseBody
    @RequestMapping(value = "/daka", method = RequestMethod.GET)
    public int daka(@RequestParam(value = "datetime",required = false)String datetime
            ,@RequestParam(value = "username",required = false)String username,@RequestParam(value = "empid",required = false)Integer empid
            ,@RequestParam(value = "hour",required = false)Integer hour,@RequestParam("timea")String timea){
        String[] timeas = timea.split(":");
        int isdaka = empServiceimpl.isdaka(datetime, empid, timeas[0]);
        if (isdaka==0) {
            empServiceimpl.adddaka(datetime, empid);
        }
        if(Integer.parseInt(timeas[0])<12){
            if(empServiceimpl.modaka(datetime,empid)!=null){
                return 11;
            }
        }else if(Integer.parseInt(timeas[0])>=12){
            if(empServiceimpl.endaka(datetime,empid)!=null){
                return 22;
            }
        }
        return empServiceimpl.daka(datetime,username,empid,hour,timea);
    }
    @ResponseBody
    @RequestMapping(value = "/addleave",method = RequestMethod.POST, consumes = "application/json")
    public int addleave(@RequestBody leave leave){
        return empServiceimpl.addleave(leave);
    }

    @ResponseBody
    @RequestMapping(value = "/deleta_notice", method = RequestMethod.GET)
    public int deleta_notice(@RequestParam(value = "notice_id")String notice_id){
        return empServiceimpl.deleta_notice(notice_id);
    }

    @ResponseBody
    @RequestMapping(value = "getAllEmp", method = RequestMethod.GET)
    public List<employee> getAllEmp(@RequestParam(value = "entry_starttime",required = false)String entry_starttime
            , @RequestParam(value = "entry_prefixtime",required = false)String entry_prefixtime
            , @RequestParam(value = "empName",required = false)String empName
            , @RequestParam(value = "roleID",required = false)String roleID
            ,@RequestParam(value = "pageNo", required = false)Integer pageNo){
        return empServiceimpl.getAllEmp(entry_starttime, entry_prefixtime, empName, roleID,pageNo);
    }

    @ResponseBody
    @RequestMapping(value = "/getEmpBycondition", method = RequestMethod.POST)
    public List<employee> getEmpBycondition(@RequestParam(value = "entry_starttime",required = false)String entry_starttime
            ,@RequestParam(value = "entry_prefixtime",required = false)String entry_prefixtime
            ,@RequestParam(value = "empName",required = false)String empName
            ,@RequestParam(value = "roleID",required = false)String roleID
            ,@RequestParam(value = "pageNo", required = false)Integer pageNo){
        return empServiceimpl.getEmpBycondition(entry_starttime, entry_prefixtime, empName, roleID,pageNo);
    }

    @ResponseBody
    @RequestMapping(value = "/getEmpCounts",method = RequestMethod.GET)
    public int getEmpCount(@RequestParam(value = "entry_starttime",required = false)String entry_starttime
            ,@RequestParam(value = "entry_prefixtime",required = false)String entry_prefixtime
            ,@RequestParam(value = "empName",required = false)String empName
            ,@RequestParam(value = "roleID",required = false)String roleID
            ,@RequestParam(value = "pageNo", required = false)Integer pageNo){
        return empServiceimpl.getCount(entry_starttime, entry_prefixtime, empName, roleID,pageNo);
    }

    @ResponseBody
    @RequestMapping(value = "deleteEmp")
    public int deleteEmp(@RequestParam("emp_id")Integer emp_id){
        return empServiceimpl.deleteEmp(emp_id);
    }

    @ResponseBody
    @RequestMapping(value = "addEmp")
    public int addEmp(@RequestBody employee employee){
        return empServiceimpl.addEmp(employee);
    }

    @ResponseBody
    @RequestMapping(value = "get_loanUser",method = RequestMethod.GET)
    public user get_loanUser(@RequestParam(value = "loan_id",required = false)Integer loan_id){
        return empServiceimpl.get_loanUser(loan_id);
    }

    @ResponseBody
    @RequestMapping(value = "getEmp_wageCount",method = RequestMethod.GET)
    public int getEmp_wageCount(@RequestParam(value = "entry_starttime",required = false)String entry_starttime
            ,@RequestParam(value = "entry_prefixtime",required = false)String entry_prefixtime
            ,@RequestParam(value = "empName",required = false)String empName
            ,@RequestParam(value = "emp_id", required = false)Integer emp_id){
        return empServiceimpl.getEmp_wageCount(entry_starttime,entry_prefixtime,empName,emp_id);
    }

    @ResponseBody
    @RequestMapping(value = "getEmp_wage",method = RequestMethod.GET)
    public List<attendance> getEmp_wage(@RequestParam(value = "entry_starttime",required = false)String entry_starttime
            ,@RequestParam(value = "entry_prefixtime",required = false)String entry_prefixtime
            ,@RequestParam(value = "empName",required = false)String empName
            ,@RequestParam(value = "emp_id", required = false)Integer emp_id){
        return empServiceimpl.getEmp_wage(entry_starttime,entry_prefixtime,empName,emp_id);
    }

}
