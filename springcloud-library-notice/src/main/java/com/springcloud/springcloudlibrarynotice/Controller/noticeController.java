package com.springcloud.springcloudlibrarynotice.Controller;

import com.springcloud.springcloudlibrarynotice.Dao.noticeMapper;
import com.springcloud.springcloudlibrarynotice.pojo.notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class noticeController {
    @Autowired
    noticeMapper noticeMapper;

    @ResponseBody
    @RequestMapping(value = "addnotice", method = RequestMethod.POST)
    public int addnotice(@RequestBody notice notice){
        return noticeMapper.addnotice(notice);
    }

    @ResponseBody
    @RequestMapping(value = "getAllnotice", method = RequestMethod.GET)
    public List<notice> getAllnotice(){
        return noticeMapper.selectnotice();
    }
}
