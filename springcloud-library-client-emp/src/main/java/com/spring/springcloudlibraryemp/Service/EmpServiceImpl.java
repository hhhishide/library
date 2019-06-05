package com.spring.springcloudlibraryemp.Service;

import com.spring.springcloudlibraryemp.Dao.EmpMapper;
import com.spring.springcloudlibraryemp.pojo.attendance;
import com.spring.springcloudlibraryemp.pojo.employee;
import com.spring.springcloudlibraryemp.pojo.fenye;
import com.spring.springcloudlibraryemp.pojo.leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public int login(String username, String password) {
        return empMapper.login(username,password);
    }

    @Override
    public employee selectEmpByname(String username) {
        return empMapper.selectEmpByname(username);
    }

    @Override
    public List<attendance> getattendByid(int empid, int pageSize, int pageNo) {
        return empMapper.selectattendByid(empid, pageSize, pageNo);
    }

    @Override
    public int getCount(int empid, String prefixdate, String suffixdate, Integer daka_state) {
        return empMapper.getCount(empid,prefixdate,suffixdate,daka_state);
    }

    @Override
    public List<attendance> getattendBycondition(int empid, Integer pageSize, Integer pageNo, String prefixdate, String suffixdate, Integer daka_state) {
        return empMapper.getattendBycondition(empid, pageSize, pageNo, prefixdate, suffixdate, daka_state);
    }

    @Override
    public int daka(String datetime, String username, Integer empid, Integer hour, String timea) {
        return empMapper.daka(datetime,username,empid,hour,timea);
    }

    @Override
    public int isdaka(String datetime, Integer empid,String timea) {
        return empMapper.isdaka(datetime, empid,timea);
    }

    @Override
    public int adddaka(String datetime, Integer empid) {
        return empMapper.adddaka(datetime, empid);
    }

    @Override
    public int addleave(leave leave) {
        return empMapper.addleave(leave);
    }

    @Override
    public int deleta_notice(String notice_id) {
        return empMapper.deleta_notice(notice_id);
    }

    @Override
    public List<employee> getAllEmp(String entry_starttime, String entry_prefixtime, String empName, String roleID,Integer pageNo) {
        return empMapper.getAllEmp(entry_starttime, entry_prefixtime, empName, roleID,pageNo);
    }

    @Override
    public List<employee> getEmpBycondition(String entry_starttime, String entry_prefixtime, String empName, String roleID,Integer pageNo) {
        return empMapper.getEmpBycondition(entry_starttime, entry_prefixtime, empName, roleID,pageNo);
    }

    @Override
    public int getCount(String entry_starttime, String entry_prefixtime, String empName, String roleID,Integer pageNo) {
        return empMapper.getEmpCount(entry_starttime, entry_prefixtime, empName, roleID,pageNo);
    }

    @Override
    public int deleteEmp(Integer emp_id) {
        return empMapper.deleteEmp(emp_id);
    }

    @Override
    public int addEmp(employee employee) {
        return empMapper.addEmp(employee);
    }

    @Override
    public String modaka(String datetime,Integer empid) {
        return empMapper.modaka(datetime,empid);
    }

    @Override
    public String endaka(String datetime,Integer empid) {
        return empMapper.endaka(datetime,empid);
    }
}
