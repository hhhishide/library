package com.spring.springcloudlibraryemp.Dao;

import com.spring.springcloudlibraryemp.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface EmpMapper {
    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public int login(@Param(value = "username")String username, @Param(value = "password")String password);

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    public employee selectEmpByname(@Param(value = "username")String username);

    /**
     * 根据员工id查询出勤信息
     * @param empid
     * @param pageSize
     * @param pageNo
     * @return
     */
    public List<attendance> selectattendByid(@Param(value = "empid")int empid, @Param(value = "pageSize")int pageSize
            , @Param(value = "pageNo")int pageNo);

    /**
     * 根据条件查询出勤信息总数
     * @param empid
     * @param prefixdate
     * @param suffixdate
     * @param daka_state
     * @return
     */
    public int getCount(@Param(value = "empid")int empid, @Param("prefixdate")String prefixdate,@Param("suffixdate")String suffixdate,@Param("daka_state")Integer daka_state);

    /**
     * 根据条件查询出勤信息
     * @param empid
     * @param pageSize
     * @param pageNo
     * @param prefixdate
     * @param suffixdate
     * @param daka_state
     * @return
     */
    public List<attendance> getattendBycondition(@Param("empid")int empid, @Param("pageSize")Integer pageSize,@Param("pageNo")Integer pageNo
            , @Param("prefixdate")String prefixdate,@Param("suffixdate")String suffixdate,@Param("daka_state")Integer daka_state);

    /**
     * 打卡
     * @param datetime
     * @param username
     * @return
     */
    public int daka(@Param("datetime") String datetime, @Param("username")String username, @Param("empid")Integer empid,@Param("hour")Integer hour,@Param("timea")String timea);


    public int isdaka(@Param("datetime")String datetime, @Param("empid")Integer empid,@Param("timeas")String timeas);

    public int adddaka(@Param("datetime")String datetime, @Param("empid")Integer empid);

    public int addleave(leave leave);

    public int deleta_notice(@Param("notice_id")String notice_id);

    public List<employee> getAllEmp(@Param("entry_starttime")String entry_starttime, @Param("entry_prefixtime")String entry_prefixtime
            , @Param("empName")String empName, @Param("roleID")String roleID,@Param("pageNo")Integer pageNo);

    public List<employee> getEmpBycondition(@Param("entry_starttime")String entry_starttime, @Param("entry_prefixtime")String entry_prefixtime
            , @Param("empName")String empName, @Param("roleID")String roleID,@Param("pageNo")Integer pageNo);

    public int getEmpCount(@Param("entry_starttime")String entry_starttime, @Param("entry_prefixtime")String entry_prefixtime
            , @Param("empName")String empName, @Param("roleID")String roleID,@Param("pageNo")Integer pageNo);

    public int deleteEmp(@Param("emp_id")Integer emp_id);

    public int addEmp(employee employee);

    public String modaka(@Param("datetime")String datetime,@Param("empid")Integer empid);

    public String endaka(@Param("datetime")String datetime,@Param("empid")Integer empid);

    public user get_loanUser(@Param(value = "loan_id")Integer loan_id);

    public int getEmp_wageCount(@Param("entry_starttime") String entry_starttime
            ,@Param("entry_prefixtime")String entry_prefixtime,@Param("empName")String empName
            ,@Param("emp_id")Integer emp_id);

    public List<attendance> getEmp_wage(@Param("entry_starttime") String entry_starttime
            ,@Param("entry_prefixtime")String entry_prefixtime,@Param("empName")String empName
            ,@Param("emp_id")Integer emp_id);
}
