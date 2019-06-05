var stompClient = null;
function connect_notice() {
    var socket = new SockJS("/socket");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        console.log("connect ok");
    });
}
var notice_callback = function () {
    var username = $("#username").val();
    var content = $("#content").val();
    var title = $("#title").val();
    var issue_userid = $("input[name='issue_userid']").val();
    if (title==""){
        alert("标题不能为空!")
        return false;
    }
    if (content==""){
        alert("内容不能为空!")
        return false;
    }
    var json = "{\"username\":\""+username+"\",\"content\":\""+content+"\",\"title\":\""+title+"\",\"issue_userid\":"+issue_userid+"}";
    stompClient.send("/request/notice",{}, json);
    layer.alert('发布成功!', {icon: 1});
}
