package com.spring.springcloudlibraryproduct.Service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.springcloudlibraryproduct.pojo.attendance;
import com.spring.springcloudlibraryproduct.pojo.employee;
import org.springframework.beans.factory.NamedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/*@Service*/
public class libraryServiceImpl implements libraryService {
    /*@Autowired
    @Qualifier(value = "restTemplate")
    private RestTemplate restTemplate = null;

    private static final String PREFIX = "http://library-emp";*/


    /*@Override
    @HystrixCommand(fallbackMethod = "hiError")
    public int login(String username, String passwrod) {
        Integer forObject = restTemplate.getForObject(PREFIX+"/login/{username}/{password}", int.class, username, passwrod);
        return forObject;
    }
    @Override
    public employee getEmpByname(String username) {
        System.out.println(username);
        employee oneEmp = restTemplate.getForObject(PREFIX + "/selectEmpByname/{username}", employee.class, username);
        return oneEmp;
    }*/
    /**
     * 服务熔断
     * @param username
     * @param passwrod
     * @return
     */
    /*public int hiError(String username, String passwrod){
        return 4;
    }*/

}
