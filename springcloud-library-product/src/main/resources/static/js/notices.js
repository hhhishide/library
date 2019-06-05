var i = 0;
$(function () {
        $(".notice_contenta").addClass("zhan");
        $(".zhankai span").text("查看全部");
});
function zhankai(obj) {

    if (i % 2 == 0) {
        $(obj).parent().prev().removeClass("zhan");
        $(obj).text("收起");
        i++;
    } else {
        $(obj).parent().prev().addClass("zhan");
        $(obj).text("查看全部");
        i++;
    }
}
function bianjia() {
    $(".glyphicon-remove").fadeToggle(500,"linear");
}

function deleta_notice(notice_id) {
    $.ajax({
       url:"/library/deleta_notice",
       type:"GET",
       data:{notice_id:notice_id},
       dataType:"json",
       success:function (data) {
           var a = "                    <div class=\"zhankai\" >\n" +
               "                        <span onclick=\"zhankai(this)\">展开</span>\n" +
               "                    </div>\n";
           if (data.result == 1){
               layer.alert('删除成功', {icon: 1});
               $(".notice_content").html("");
               $(".notice_content").remove();
               for (var i = 0; i < data.noticeList.length; i++){
                   if (!(data.noticeList[i].content.length>124)){
                       a = "";
                   }
                   $(".ggl").append("<div class=\"notice_content\"><div class=\"noticea\">\n" +
                       "                    <div class=\"notice_title\">" +
                       "                        <span>"+data.noticeList[i].title+"</span>" +
                       "                        <span class=\"glyphicon glyphicon-remove\" onclick=\"deleta_notice("+data.noticeList[i].id+")\"></span>" +
                       "                    </div>\n" +
                       "                    <div class=\"notice_contenta zhan\">\n" +
                       "                        "+data.noticeList[i].content+"\n" +
                       "                    </div>\n" +
                                            a +
                       "                    <div class=\"notice_foot\">\n" +
                       "                        <span class=\"fabur\"><span>"+data.noticeList[i].empname+"</span>&nbsp;发布于&nbsp;<span>"+data.noticeList[i].issue_time+"</span></span>\n" +
                       "                    </div>\n" +
                       "                </div>\n" +
                       "                </div>")
               }
           }else{
               layer.msg('删除失败', {icon: 5});
           }
       }
    });
}


