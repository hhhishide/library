package com.spring.springcloudlibraryproduct.Config;

import com.spring.springcloudlibraryproduct.Service.EmpService;
import com.spring.springcloudlibraryproduct.Service.libraryService;
import com.spring.springcloudlibraryproduct.Service.libraryServiceImpl;
import com.spring.springcloudlibraryproduct.pojo.employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmpService empService;

    /*@Autowired
    private libraryServiceImpl libraryServiceimpl;*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername.................................");
        employee empByname = empService.getEmpByname(username);
        if (empByname==null) {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
        return changeToUser(empByname);
    }


    public UserDetails changeToUser(employee employee){
        System.out.println("进入changeToUser.........");
        UserDetails userDetails = null;
        List<GrantedAuthority> authorityList = new ArrayList<>();
        String roleName = "";
        if (employee.getEmp_role()==0) {
            roleName="普通员工";
        }else if (employee.getEmp_role()==1) {
            roleName="清洁工";
        }else if (employee.getEmp_role()==2) {
            roleName="保安";
        }else if (employee.getEmp_role()==3) {
            roleName="用户管理员";
        }else if (employee.getEmp_role()==4) {
            roleName="图书管理员";
        }else if (employee.getEmp_role()==5) {
            roleName="员工管理员";
        }else if (employee.getEmp_role()==6) {
            roleName="财务管理员";
        }else if (employee.getEmp_role()==7) {
            roleName="超级管理员";
        }else{
            roleName="黑客";
        }
        GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
        authorityList.add(authority);
        userDetails = new User(employee.getEmp_username(), employee.getEmp_pwd(), authorityList);
        if (userDetails == null) {
            return null;
        }
        return userDetails;
    }

}
