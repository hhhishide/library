package com.spring.springcloudlibraryproduct.Service;

import com.spring.springcloudlibraryproduct.pojo.face;
import com.spring.springcloudlibraryproduct.pojo.records;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "library-records")
public interface recService {
    @RequestMapping(value = "/addrecords",method = RequestMethod.GET)
    public int addrecords(@RequestParam(value = "username", required = false) String username
            , @RequestParam(value = "content",required = false)String content);

    @RequestMapping(value = "/getrecordsAll",method = RequestMethod.GET)
    public List<records> getrecordsAll();

    @RequestMapping(value = "/getFace",method = RequestMethod.GET)
    public List<face> getFace();
}
