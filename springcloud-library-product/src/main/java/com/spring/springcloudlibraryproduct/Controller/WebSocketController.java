package com.spring.springcloudlibraryproduct.Controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.spring.springcloudlibraryproduct.Service.EmpService;
import com.spring.springcloudlibraryproduct.Service.WebSocketSerivceImpl;
import com.spring.springcloudlibraryproduct.Service.noticeService;
import com.spring.springcloudlibraryproduct.Service.recService;
import com.spring.springcloudlibraryproduct.pojo.employee;
import com.spring.springcloudlibraryproduct.pojo.face;
import com.spring.springcloudlibraryproduct.pojo.notice;
import com.spring.springcloudlibraryproduct.pojo.records;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;
import javax.websocket.OnClose;
import java.security.Principal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {
    @Autowired
    private com.spring.springcloudlibraryproduct.Service.recService recService;

    @Autowired
    private EmpService empService;

    @Autowired //配置Spring Boot 自动配置消息模板对象
    private SimpMessagingTemplate simpMessagingTemplate;

    // concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketSerivceImpl对象
    private static CopyOnWriteArraySet<Principal> principalSet = new CopyOnWriteArraySet<>();
    @Autowired
    private SimpUserRegistry simpUserRegistry;

    private static ConcurrentHashMap<String,Object> sessionsSet = new ConcurrentHashMap<>();

    private static String sessionid = null;
    private static int onlineCount = 0;

    public static ConcurrentHashMap<String, Object> getSessionsSet() {
        return sessionsSet;
    }

    public static void setSessionsSet(ConcurrentHashMap<String, Object> sessionsSet) {
        WebSocketController.sessionsSet = sessionsSet;
    }

    @Autowired
    private noticeService noticeService;

    public static String getSessionid() {
        return sessionid;
    }

    public static synchronized void setSessionid(String sessionid) {
        WebSocketController.sessionid = sessionid;
    }

    private static List<Object> userList = new ArrayList<>();

    //发送页面
    @GetMapping("/send")
    public String send(){
        return "html/send";
    }
    @GetMapping("/receive")
    public String receive(){
        return "html/receive";
    }
    @GetMapping("/sendUser")
    public String sendUser(){
        return "html/send-user";
    }
    @GetMapping("/receiveUser")
    public String receiveUser(){
        return "html/receive-user";
    }
    @GetMapping("/liaotian")
    public String liaotian(){
        return "html/liaotian";
    }

    // 定义消息请求路径
    @MessageMapping("/send")
    // 定义结果发送到特定路径
    @SendTo("/sub/chat")
    public synchronized Object sendMsg(Principal principal,String value){
        JSONObject jsonObject = JSON.parseObject(value);
        employee employeea = empService.getEmpByname(jsonObject.getString("userName"));
        recService.addrecords(jsonObject.getString("userName"), jsonObject.getString("message"));
        Map<String, Object> sendMap = new HashMap<>();
        sendMap.put("emp", employeea);
        /*String srcUser = principal.getName();*/
        String message = /*jsonObject.getString("userName") + "曰:  " + */jsonObject.getString("message");
        sendMap.put("message", message);
        return sendMap;
    }

    // 将消息发送给特定用户
    @MessageMapping("/sendUser")
    public synchronized void sendToUser(Principal principal, String body){
        String srcUser = principal.getName();
        String [] args = body.split(",");
        String desUser = args[0];
        System.out.println("desUser:"+desUser);
        String message = "【" + srcUser + "】给你发来消息:" + args[1];
        System.out.println(message);
        // 发送到用户和监听地址
        simpMessagingTemplate.convertAndSendToUser(desUser, "/queue/customer",message);
    }

    @MessageMapping("/connectToUser")
    public synchronized void ConnectToUser(String value){
        Map<String, Object> messageMap = new HashMap<>();
        messageMap.put("sessionid", this.getSessionid());
        String messageStr = JSON.toJSONString(messageMap);
        System.out.println(messageStr);
        simpMessagingTemplate.convertAndSendToUser(value,"/queue/cona",messageStr);
        // END
    }

    /**
     * 聊天记录
     * @param value
     * @return
     */
    @MessageMapping("/sendjilu")
    @SendTo("/sub/jilu")
    public synchronized Object sendjilu(String value){
        System.out.println("进入sendjilu");
        JSONObject jsonObject = JSON.parseObject(value);
        List<records> recordsList = recService.getrecordsAll();
        List<face> faceList = recService.getFace();
        for(records records: recordsList){
            /*for (face face: faces) {*/
            if(records.getContent()==null){
                records.setContent(" ");
                continue;
            }
            if((records.getContent().replaceAll("/s","")).equals("")){
                continue;
            }
            Pattern pattern = Pattern.compile("\\[.*?\\]");
            Matcher matcher = pattern.matcher(records.getContent());
            while (matcher.find()) {
                //System.out.println(matcher.group());
                for (face face: faceList) {
                    String ma = matcher.group();
                    ma = ma.replace("[","");
                    ma = ma.replace("]","");
                    if (face.getFaceName().equals(ma)||face.getFaceName()==ma) {
                        /*<img class=\"plimga\" src=\"../images/face/"+ImgIputHandler.facePath[i].facePath+"\" height=\"24\" width=\"24\">*/
                        String com_content = (records.getContent()).replace(matcher.group(),"<img class=\"plimga\" src=\"../images/face/"
                                +face.getFacePath()+"\" height=\"24\" width=\"24\">");
                        records.setContent(com_content);
                    }
                }
            }
            /*}*/
        }
        Map<String, Object> records = new HashMap<>();
        records.put("recordsList", recordsList);
        records.put("userList", userList);
        records.put("username",jsonObject.getString("username"));

        return records;
    }

    @MessageMapping("/connect")
    @SendTo("/sub/conaa")
    public synchronized Object Connect(Principal principal,String value){
        /*String userName = (String)sessionsSet.get(value);*/
        Map<String, Object> messageMap = new HashMap<>();
        for(int i = 0; i<userList.size(); i++) {
            if (value.equals(((employee)userList.get(i)).getEmp_username())) {
                userList.remove(i);
            }
        }
        // END
        System.out.println("当前用户:"+value);
        employee employeea = empService.getEmpByname(value);
        String message = value + "加入群聊!";
        userList.add(employeea);
        addOnlineCount();
        messageMap.put("isuser","0");
        messageMap.put("numbera", getOnlineCount());
        messageMap.put("srcUser", value);
        messageMap.put("state","0");
        messageMap.put("employeea",employeea);
        System.out.println(value + "加入群聊!当前在线人数:"+getOnlineCount());
        for (SimpUser simpUser:simpUserRegistry.getUsers()) {
            System.out.println("用户:"+simpUser.getSessions());
        }
        return messageMap;
    }

    @MessageMapping("/disConnect")
    @SendTo("/sub/conaa")
    public synchronized Object disConnect(Principal principal, String value){
        // 从sessionsSet中删除当前连接
        /*String userName = (String)sessionsSet.get(value);
        sessionsSet.remove(value);*/
        // END
        Map<String, Object> messageMap = new HashMap<>();
        System.out.println("当前用户:"+value);
        subOnlineCount();
        String message = value + "退出群聊!当前在线人数:"+getOnlineCount();
        for(int i = 0; i< userList.size(); i++){
            if(((employee)userList.get(i)).getEmp_username().equals(value)){
                userList.remove(i);
            }
        }
        for (SimpUser simpUser:simpUserRegistry.getUsers()) {
            System.out.println("用户:"+simpUser.getSessions());
        }
        messageMap.put("numbera", getOnlineCount());
        messageMap.put("srcUser", value);
        messageMap.put("state","1");
        System.out.println(message);
        return messageMap;
    }

    @MessageMapping("/notice")
    @SendTo("/sub/notice")
    public synchronized Object notice(String value){
        JSONObject jsonObject = JSON.parseObject(value);
        notice notice = new notice();
        notice.setEmpname(jsonObject.getString("username"));
        notice.setContent(jsonObject.getString("content"));
        notice.setTitle(jsonObject.getString("title"));
        notice.setIssue_userid(jsonObject.getIntValue("issue_userid"));
        noticeService.addnotice(notice);
        System.out.println("发布人:"+jsonObject.getString("username")+",内容:"+jsonObject.getString("content"));
        return value;
    }
    @GetMapping("/index")
    public String websocket(){
        return "html/WebSocketTest";
    }

    // 返回在线数量
    private static synchronized int getOnlineCount(){
        return onlineCount;
    }
    // 当在线人数增加时
    private static synchronized void addOnlineCount(){
        WebSocketController.onlineCount++;
    }
    // 当在线人数减少时
    private static synchronized void subOnlineCount(){
        WebSocketController.onlineCount--;
    }
}
