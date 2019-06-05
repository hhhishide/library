package com.springcloud.records.Controller;

import com.springcloud.records.Dao.recMapper;
import com.springcloud.records.pojo.face;
import com.springcloud.records.pojo.records;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class recController {
    @Autowired
    recMapper recMapper;

    /**
     * 增加聊天记录
     * @param username
     * @param content
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addrecords",method = RequestMethod.GET)
    public int addrecords(@RequestParam(value = "username", required = false) String username
            , @RequestParam(value = "content",required = false)String content){

        return recMapper.addrecords(username, content);
    }
    /**
     * 查询聊天记录
     */
    @ResponseBody
    @RequestMapping(value = "/getrecordsAll",method = RequestMethod.GET)
    public List<records> getrecordsAll(){
        return recMapper.getrecordsAll();
    }

    @ResponseBody
    @RequestMapping(value = "/getFace",method = RequestMethod.GET)
    public List<face> getFace(){
        return recMapper.getFace();
    }
}
