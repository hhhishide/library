function get_book(){
    $(".book_name").val("");
    $(".book_authod").val("");
    $(".book_price").val("");
    $(".book_type").val("");
    var book_id = $(".book_id").val();
    if (book_id==""){
        return false;
    }
    $.ajax({
        url:"/library/get_book",
        type:"GET",
        data:{book_id:book_id},
        dataType:"json",
        success:function (data) {
            if (data==0){
                layer.msg('未找到该书籍!', {icon: 5,time:1000});
            }else{
                $(".book_name").val(data.book_name);
                $(".book_authod").val(data.book_authod);
                $(".book_price").val(data.book_price+".00￥");
                $(".book_type").val(data.book_type);
            }
        }
    });
}

function book_sell() {
    var book_name = $(".book_name").val();
    var book_authod = $(".book_authod").val();
    var book_price = $(".book_price").val();
    book_price = book_price.replace(".00￥","");
    var book_type = $(".book_type").val();
    var book_id = $(".book_id").val();
    var handler = $(".handler").val();
    if (book_name==""){
        layer.msg('书籍编号错误!', {icon: 5});
        return false;
    }else{
        $.ajax({
            url:"/library/sellbook",
            type:"GET",
            data:{book_name:book_name,book_authod:book_authod,book_price:book_price,book_type:book_type,book_id:book_id,handler:handler},
            dataType:"json",
            success:function (data) {
                if (data!=2){
                    layer.msg('出售失败!', {icon: 5,time:1000});
                } else{
                    layer.msg('操作成功!', {icon: 1,time:1000});
                }
            }
        });
    }
}

function get_loanUser() {
    $(".loan_name").val("");
    var loan_id = $(".loan_id").val();
    if (loan_id==""){
        return false;
    }
    $.ajax({
        url:"/library/get_loanUser",
        type:"GET",
        data:{loan_id:loan_id},
        dataType:"json",
        success:function (data) {
            if (data==0){
                layer.msg('未找到该用户!', {icon: 5,time:1000});
            }else{
                $(".loan_name").val(data.user_name);
            }
        }
    });
}

function loan_Book() {
    var loan_id = $(".loan_id").val();
    var book_id = $(".book_id").val();
    var book_name = $(".book_name").val();
    var loan_name = $(".loan_name").val();
    var loan_number = $("input[name='loan_number']").val();
    var leave_starttime = $("input[name='loan_starttime']").val();
    var leave_overtime = $("input[name='loan_overtime']").val();
    var username = $("#username").val();
    if (book_name==""){
        layer.msg('书籍编号有误!', {icon: 5});
        return false;
    }else if (loan_name=="") {
        layer.msg('用户编号有误!', {icon: 5});
        return false;
    }else {
        $.ajax({
            url: "/library/loan_Book",
            type: "POST",
            data: {loan_userid: loan_id, loan_bookid: book_id, bookName: book_name, loan_userName: loan_name, loan_deadline: loan_number,
                loan_loantime: leave_starttime,
                loan_returntime: leave_overtime,
                loan_handler: username
            },
            dataType: "json",
            success: function (data) {
                if (data=="1"){
                    layer.msg('操作成功!', {icon: 1,time:1000});
                    var loan_id = $(".loan_id").val("");
                    var book_id = $(".book_id").val("");
                    var book_name = $(".book_name").val("");
                    var loan_name = $(".loan_name").val("");
                    var loan_number = $("input[name='loan_number']").val("");
                    var leave_starttime = $("input[name='loan_starttime']").val("");
                    var leave_overtime = $("input[name='loan_overtime']").val("");
                    var username = $("#username").val();
                }else{
                    layer.msg('操作失败!', {icon: 5,time:1000});
                }
            }
        });
    }
}

/**
 * 书籍借出天数计算
 * @returns {boolean}
 */
function jisuana() {
    var leave_starttime = $("input[name='loan_starttime']").val();
    var leave_overtime = $("input[name='loan_overtime']").val();
    if (leave_starttime == "" || leave_overtime == "") {
        return false;
    }
    $("input[name='loan_number']").val(datedifference(leave_starttime, leave_overtime));
}

function selectLoan(pageNo){
    var loan_loantimeP = $(".loan_loantimeP").val();
    var loan_loantimeS = $(".loan_loantimeS").val();
    var book_name = $(".book_name").val();
    if (loan_loantimeP!=""){
        if (loan_loantimeS==""){
            layer.msg('请完善时间信息!', {icon: 5});
            return false;
        }
    }
    $.ajax({
        url:"/library/ajax_loanList",
        type:"GET",
        data:{loan_loantimeP:loan_loantimeP,loan_loantimeS:loan_loantimeS,book_name:book_name,pageNo:pageNo},
        dataType:"json",
        success:function (data) {
            $(".bookloanList").html("");
            for (var i =0;i <data.bookloanList.length; i++){
                if (data.bookloanList[i].loan_state=="0"){
                    data.bookloanList[i].loan_state="借出中";
                    $(".bookloanList").append("<tr>\n" +
                        "            <td>"+data.bookloanList[i].loan_id+"</td>\n" +
                        "            <td>"+data.bookloanList[i].bookName+"</td>\n" +
                        "            <td>"+data.bookloanList[i].loan_userName+"</td>\n" +
                        "            <td>"+data.bookloanList[i].loan_loantime+"</td>\n" +
                        "            <td>"+data.bookloanList[i].loan_returntime+"</td>\n" +
                        "            <td>"+data.bookloanList[i].loan_deadline+"天</td>\n" +
                        "            <td>"+data.bookloanList[i].user_phone+"</td>\n" +
                        "            <td>"+data.bookloanList[i].loan_handler+"</td>\n" +
                        "            <td>"+data.bookloanList[i].loan_state+"</td>\n" +
                        "            <td>\n" +
                        "                <a>\n" +
                        "                    <span onclick='return_book("+data.bookloanList[i].loan_id+")' class=\"glyphicon glyphicon-ok\" title=\"归还\"></span>\n" +
                        "                </a>&nbsp;&nbsp;&nbsp;\n" +
                        "                <a>\n" +
                        "                    <span class=\"glyphicon glyphicon-pencil\" data-toggle=\"modal\" data-target=\"#myModal\" title=\"修改\"></span>\n" +
                        "                </a>&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                        "                <a>\n" +
                        "                    <span class=\"glyphicon glyphicon-trash\" lay-event=\"del\" title=\"删除\"></span>\n" +
                        "                </a>\n" +
                        "            </td>\n" +
                        "        </tr>");
                } else{
                    data.bookloanList[i].loan_state="已归还";
                    $(".bookloanList").append("<tr>\n" +
                        "            <td>"+data.bookloanList[i].loan_id+"</td>\n" +
                        "            <td>"+data.bookloanList[i].bookName+"</td>\n" +
                        "            <td>"+data.bookloanList[i].loan_userName+"</td>\n" +
                        "            <td>"+data.bookloanList[i].loan_loantime+"</td>\n" +
                        "            <td>"+data.bookloanList[i].loan_returntime+"</td>\n" +
                        "            <td>"+data.bookloanList[i].loan_deadline+"天</td>\n" +
                        "            <td>"+data.bookloanList[i].user_phone+"</td>\n" +
                        "            <td>"+data.bookloanList[i].loan_handler+"</td>\n" +
                        "            <td>"+data.bookloanList[i].loan_state+"</td>\n" +
                        "            <td>\n" +
                        "                <a>\n" +
                        "                    <span>&nbsp;&nbsp;&nbsp;&nbsp;</span>\n" +
                        "                </a>&nbsp;&nbsp;&nbsp;\n" +
                        "                <a>\n" +
                        "                    <span class=\"glyphicon glyphicon-pencil\" data-toggle=\"modal\" data-target=\"#myModal\" title=\"修改\"></span>\n" +
                        "                </a>&nbsp;&nbsp;&nbsp;&nbsp;\n" +
                        "                <a>\n" +
                        "                    <span class=\"glyphicon glyphicon-trash\" lay-event=\"del\" title=\"删除\"></span>\n" +
                        "                </a>\n" +
                        "            </td>\n" +
                        "        </tr>");
                }
            }
            $(".fen_count").val(data.fenye.totalPageCount);
            $(".fen_PageNo").val(data.fenye.currentPageNo);
            loan_fenye(pageNo);
        }
    });
}

function return_book(loan_id){
    var pageNo = $(".fen_PageNo").val();
    $.ajax({
        url:"/library/return_book",
        type:"GET",
        data:{loan_id:loan_id},
        dataType:"json",
        success:function (data) {
            if (data==1){
                layer.msg('图书归还成功!', {icon: 1,time:1000});
                selectLoan(pageNo);
            } else{
                layer.msg('图书归还失败!', {icon: 5,time:1000});
            }
        }
    });
}



layui.use('laydate', function(){
    var laydate = layui.laydate;

    //日期时间选择器
    laydate.render({
        elem: '#test5'
        ,done: function(value, date){
            jisuana();
        }
    });
    //日期时间选择器
    laydate.render({
        elem: '#test4'
        ,trigger: 'mousedown'
    });
});
