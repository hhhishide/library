var userName = null;
var sessionid = $("#sessionid").val();
var noticeClient = null;
var recClient = null;
$(".Chatarea").scrollTop($(".Chatarea")[0].scrollHeight);
function setConnected(connected) {
    $("#connecta").attr({"disabled": connected});
    $("#disconnecta").attr({"disabled":!connected});
}
function connectax() {
    $(".yanzhenge").val("666");
    var socket = new SockJS("/socket");
    var stompClient = Stomp.over(socket);
    stompClient.connect({},function (frame) {
        $(".Chatarea").scrollTop($(".Chatarea")[0].scrollHeight);
        setConnected(true);
        $(".now_role").val("yes");
        connect_callback(); // 订阅聊天室通知
        connectWsuser(); // 创建wsuser服务端点连接
    });
}

/**
 * wsuser服务端点
 */
function connectWsuser() {
    var s = new SockJS("/wsuser");
    var stompClient = Stomp.over(s);
    stompClient.connect({},function connectCallback(frame) {
            jilu(stompClient); //订阅记录通知
            sengjilu(stompClient); //给后台发送记录请求
        },function errorCallBack (error) {
            // 连接失败时（服务器响应 ERROR 帧）的回调方法
            document.getElementById("state-info").innerHTML = "连接失败";
            console.log('连接失败【' + error + '】');
            alert(error);
        }
    );
}

var noticeSocket = function () {
    var s = new SockJS("/socket");
    var stompClient = Stomp.over(s);
    stompClient.connect({}, function () {
        console.log('notice socket connected');
        noticeClient = stompClient.subscribe("/sub/chat", function (data) {
            var obj = JSON.parse(data.body);
            var message = obj.message;
            var ret = /\[.*?\]/;
            var re = new RegExp(ret, "g");
            var result = message.match(re);
            for (key in result) {
                var resulta = result[key];
                resulta = resulta.replace("]", "");
                resulta = resulta.replace("[", "");
                for (var i = 0; i < ImgIputHandler.facePath.length; i++) {
                    if (resulta == ImgIputHandler.facePath[i].faceName) {
                        //alert("<img class=\"plimga\" src=\"../images/face/"+ImgIputHandler.facePath[i].facePath+"\" height=\"24\" width=\"24\">")
                        message = message.replace(result[key], "<img class=\"plimga\" src=\"../images/face/"
                            + ImgIputHandler.facePath[i].facePath + "\" height=\"24\" width=\"24\">");
                        break;
                    }
                    /*{faceName:"微笑",facePath:"0.gif"},
                      {faceName:"撇嘴",facePath:"1.gif"},*/
                }
            }
            var empName = $(".empName").text();
            var htmla = null;
            if (obj.emp.emp_username==empName){
                htmla = "<li><div class=\"x02\"><img src=\"../images/tou/"+obj.emp.imgpath+"\" style=\"height: 44px;width: 44px\" class=\"layui-nav-img toux02\"><div class=\"ji02\"><img class=\"jiimg02\" src=\"/images/index/liaot02.png\"><span>"+message+"</span></div></div></li><br>";
            } else{
                htmla = "<li><div class=\"x01\"><img src=\"../images/tou/"+obj.emp.imgpath+"\" style=\"height: 44px;width: 44px\" class=\"layui-nav-img toux01\"><div class=\"ji01\"><img class=\"jiimg01\" src=\"/images/index/liaot01.png\"><span>"+message+"</span></div></div></li><br>"
            }
            $(".record").append(htmla);
            $(".Chatarea").scrollTop($(".Chatarea")[0].scrollHeight);
            /*var content = $("#context").html()+ "<br/>" + data.body;
            $("#context").html("");
            $("#context").html(content);*/
        });
        if ($(".yanzhenge").val()=="123") {
            alert("123")
            noticeClient.unsubscribe();
        }
    });
}
var recSocket = function () {
    var s = new SockJS("/socket");
    var stompClient = Stomp.over(s);
    stompClient.connect({}, function () {
        console.log('notice socket connected');
        recClient = stompClient.subscribe("/sub/conaa", function (data) {
            var obj = JSON.parse(data.body);
            if (obj.state=="0"){
                    var htmla01 = "<li><div class=\"tishi\"><p><span>" + obj.srcUser + "</span>&nbsp;加入群聊,当前在线人数:" + obj.numbera + "人</p></div></li>";
                    $(".record").append(htmla01);
                    $(".Chatarea").scrollTop($(".Chatarea")[0].scrollHeight);
                    $(".cebianlist").append("<li><img src=\"../images/tou/" + obj.employeea.imgpath + "\" style=\"height: 38px;width: 38px\" class=\"layui-nav-img\"><span>" + obj.employeea.emp_name + "</span></li>");
                    $(".cebianlist").find("li:last").attr("username", obj.employeea.emp_username);
            }else if (obj.state=="1") {
                var htmla01 = "<li><div class=\"tishi\"><p><span>"+obj.srcUser+"</span>&nbsp;已退出群聊,当前在线人数:"+obj.numbera+"人</p></div></li>";
                $(".record").append(htmla01);
                $(".Chatarea").scrollTop($(".Chatarea")[0].scrollHeight);
                $(".cebianlist").find("li[username='"+obj.srcUser+"']").remove();
            }

        });
        if ($(".yanzhenge").val()=="123") {
            alert("123");
            recClient.unsubscribe();
        }
    });
}

/*var conSocket = function () {
    stompClient.connect({}, function () {
        console.log('notice socket connected');

    });
}*/
function dis_callback() {
    if (userName==null){
        userName =" ";
    }
    stompClient.send("/request/disConnect",{},$(".empName").text());
}
function disconnectax() {
    if (stompClient !=null){
        $(".cebianlist").children().remove();
        dis_callback();
        $(".yanzhenge").val("123");
        noticeClient.unsubscribe();
        recClient.unsubscribe();
        $(".record").append("<li><div class=\"tishi\" style=\"color: #2e6da4;\"><p>连接已断开!</p></div></li>");
        $(".Chatarea").scrollTop($(".Chatarea")[0].scrollHeight);
        layer.msg('连接已断开', {icon: 5,time:1000});
        setConnected(false);
    }
}
var connect_callback = function () {
    if (userName==null){
        userName =" ";
    }
    $(".yanzhenge").val("666");
    if ($(".yanzhenge").val()!="123"){
        recSocket();
        noticeSocket();
    } else{
        alert("是123")
    }
    stompClient.send("/request/connect",{},$(".empName").text());
}
function sendMsg() {
    if ($(".yanzhenge").val()=="123"){
        alert("请先连接服务器");
        return false;
    }
    var value = $("#message").val();
    $("#message").val("");
    var json = "{\"message\":\""+value+"\",\"userName\":\""+$(".empName").text()+"\"}";
    stompClient.send("/request/send",{}, json);
}

function sengjilu(wsuserClient) {
    var json = "{\"username\":\""+$(".empName").text()+"\",\"content\":\"jilu\"}";
    wsuserClient.send("/request/sendjilu",{}, json);
}

function jilu(stompClienta) {
    if ($(".now_role").val()!="no") {
        stompClienta.subscribe("/sub/jilu", function (data) {
            var obj = JSON.parse(data.body);
            for (var i = 0; i < obj.recordsList.length; i++) {
                if (obj.recordsList[i].username == $(".empName").text()) {
                    htmla = "<li><div class=\"x02\"><img src=\"../images/tou/"+obj.recordsList[i].imgpath+"\" style=\"height: 44px;width: 44px\" class=\"layui-nav-img toux02\"><div class=\"ji02\"><img class=\"jiimg02\" src=\"/images/index/liaot02.png\"><span>" + obj.recordsList[i].content + "</span></div></div></li><br>";
                } else {
                    htmla = "<li><div class=\"x01\"><img src=\"../images/tou/"+obj.recordsList[i].imgpath+"\" style=\"height: 44px;width: 44px\" class=\"layui-nav-img toux01\"><div class=\"ji01\"><img class=\"jiimg01\" src=\"/images/index/liaot01.png\"><span>" + obj.recordsList[i].content + "</span></div></div></li><br>"
                }
                $(".record").append(htmla);
            }
            $(".record").append("<li><div class=\"tishi\" style=\"color: #2e6da4;\"><p>"+$(".empName").text()+"加入讨论。</p></div></li>");
            for (var i = 0; i < obj.userList.length; i++){
                $(".cebianlist").append("<li><img src=\"../images/tou/"+obj.userList[i].imgpath+"\" style=\"height: 38px;width: 38px\" class=\"layui-nav-img\"><span>"+obj.userList[i].emp_name+"</span></li>");
                $(".cebianlist").find("li:last").attr("username", obj.userList[i].emp_username);
            }
            laytan();
            fx();
            stompClienta.disconnect();
        });
    }
}

function fx() {
    var ys = $(".Chatarea")[0].scrollHeight;
    var y = $(".Chatarea").scrollTop();
    if ((ys-y)>493){
        $(".Chatarea").scrollTop(y + ( ys * 0.005));
    } else{
        return;
    }
    setTimeout(fx, 2);

    // if (y>=ys) {
    //     return;
    // }else {
    //     $(".Chatarea").scrollTop((y + (ys * 0.005)));
    // }
    /*setTimeout(fx, 5);*/
}
function laytan() {
    layer.msg('加载中', {
        icon: 16
        ,shade: 0.01
        ,time: 1000
    });
}
