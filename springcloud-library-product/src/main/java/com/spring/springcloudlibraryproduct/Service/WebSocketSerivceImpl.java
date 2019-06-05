package com.spring.springcloudlibraryproduct.Service;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/ws")
@Service
public class WebSocketSerivceImpl {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全
    private static int onlineCount = 0;
    // concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketSerivceImpl对象
    private static CopyOnWriteArraySet<WebSocketSerivceImpl> webSocketSet = new CopyOnWriteArraySet<>();
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        String userName = session.getUserPrincipal().getName();
        System.out.println(userName+"加入群聊!当前在线人数为" + getOnlineCount());
        for(WebSocketSerivceImpl webSocketSerivce : webSocketSet) {
            try {
                webSocketSerivce.sendMessage(userName + "加入群聊!当前在线人数为" + getOnlineCount());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose(){
        String userName = this.session.getUserPrincipal().getName();
        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println(userName+"退出群聊!当前在线人数为" + getOnlineCount());
        for(WebSocketSerivceImpl webSocketSerivce: webSocketSet){
            try {
                webSocketSerivce.sendMessage(userName+"退出群聊!当前在线人数为" + getOnlineCount());
            }catch (IOException s){
                s.printStackTrace();
            }
        }
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("来自客户端的消息:" + message);

        for(WebSocketSerivceImpl webSocketSerivce: webSocketSet){
            try {
                String userName = session.getUserPrincipal().getName();
                System.out.println("用户"+userName + "加入群聊!");
                webSocketSerivce.sendMessage(userName+"说:"+message);
            }catch (IOException s){
                s.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error){
        logger.info("发生错误");
        error.printStackTrace();
    }

    /**
     * 发送消息
     * @param message
     * @throws IOException
     */
    private void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
    // 返回在线数量
    private static synchronized int getOnlineCount(){
        return onlineCount;
    }
    // 当在线人数增加时
    private static synchronized void addOnlineCount(){
        WebSocketSerivceImpl.onlineCount++;
    }
    // 当在线人数减少时
    private static synchronized void subOnlineCount(){
        WebSocketSerivceImpl.onlineCount--;
    }
}
