package com.spring.springcloudlibraryproduct.Service;

import com.spring.springcloudlibraryproduct.pojo.notice;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "library-notice")
public interface noticeService {

    @RequestMapping(value = "addnotice", method = RequestMethod.POST)
    public int addnotice(@RequestBody notice notice);

    @RequestMapping(value = "getAllnotice", method = RequestMethod.GET)
    public List<notice> getAllnotice();
}
