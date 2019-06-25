function selectEmp(pageNo) {
    var entry_starttime = $(".entry_starttime").val();
    var entry_prefixtime = $(".entry_prefixtime").val();
    var roleID = $(".roleID").val();
    var empName = $(".empNamea").val();
    var fen_PageNo = $(".fen_PageNo").val();
    if (entry_starttime!="" || entry_prefixtime!=""){
        if (entry_starttime=="" || entry_prefixtime==""){
            layer.msg('时间信息未完善!', {icon: 5});
            return false;
        }
    }
    $.ajax({
        url: "/library/getEmpBycondition",
        type: "POST",
        data:{entry_starttime:entry_starttime,entry_prefixtime:entry_prefixtime,roleID:roleID,empName:empName,pageNo:pageNo},
        dataType:"json",
        success:function (data) {
            $(".employeeLista").html("");
            for (var i = 0; i < data.employeeList.length; i++){
                if (data.employeeList[i].emp_gender==0 || data.employeeList[i].emp_gender=="0"){
                    data.employeeList[i].emp_gender="男";
                } else{
                    data.employeeList[i].emp_gender="女";
                }
                $(".employeeLista").append("<tr>" +
                    "<td>"+data.employeeList[i].emp_id+"</td>"+
                    "<td>"+data.employeeList[i].emp_name+"</td>"+
                    "<td>"+data.employeeList[i].emp_role+"</td>"+
                    "<td>"+data.employeeList[i].emp_hiredate+"</td>"+
                    "<td>"+data.employeeList[i].emp_gender+"</td>"+
                    "<td>"+data.employeeList[i].emp_phone+"</td>"+
                    "<td><a><span onclick='updateEmp("+data.employeeList[i].emp_id+")' class=\"glyphicon glyphicon-pencil\" data-toggle=\"modal\" " +
                    "data-target=\"#myModal\"></span></a>&nbsp;&nbsp;&nbsp;&nbsp;<a>" +
                    "<span onclick='deleteEmp("+data.employeeList[i].emp_id+","+"\""+data.employeeList[i].emp_username+"\""+")' class=\"glyphicon glyphicon-trash\" lay-event=\"del\"></span></a></td>"+
                    "</tr>");
            }
            $(".fen_count").val(data.fenye.totalPageCount);
            $(".fen_PageNo").val(data.fenye.currentPageNo);
            emp_fenye();
        }
    });
}

function updateEmp(emp_id) {
    alert("修改"+emp_id+"号员工");
}

function deleteEmp(emp_id, emp_uname) {
    //eg2
    layer.open({
        content: '你确定要删除员工'+emp_uname+"吗?"
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
            $.ajax({
                url: "/library/deleteEmp",
                type: "GET",
                data:{emp_id:emp_id,empName:emp_uname},
                dataType:"json",
                success:function (data) {
                    if (data=="1" || data ==1){
                        layer.msg('删除成功!', {icon: 1});
                        selectEmp(1);
                    }else{
                        layer.msg('删除失败!', {icon: 5});
                    }
                }
            });
        }
        ,btn2: function(index, layero){
            //按钮【按钮二】的回调
            //return false 开启该代码可禁止点击该按钮关闭
        }
        ,cancel: function(){
            //右上角关闭回调

            //return false 开启该代码可禁止点击该按钮关闭
        }
    });
}

function addEmp() {
    var emp_name = $("input[name='emp_name']").val();
    var emp_username = $("input[name='emp_username']").val();
    var emp_pwd = $("input[name='emp_pwd']").val();
    var emp_phone = $("input[name='emp_phone']").val();
    var emp_wage = $("input[name='emp_wage']").val();
    var emp_role = $("#emp_role").val();
    var image_file = $("input[name='image_file']").val();
    var reg = /^[0-9]*$/;
    if (emp_name==""){
        layer.msg('姓名不能为空!', {icon: 5,time:1000});
        return false;
    }

    if (emp_username==""){
        layer.msg('用户名不能为空!', {icon: 5,time:1000});
        return false;
    }

    if (emp_pwd==""){
        layer.msg('密码不能为空!', {icon: 5,time:1000});
        return false;
    }

    if (emp_phone==""){
        layer.msg('联系电话未填写!', {icon: 5,time:1000});
        return false;
    }
    if(!reg.test(emp_phone)){
        layer.msg('电话格式不正确!', {icon: 5,time:1000});
        return false;
    }

    if (emp_wage==""){
        layer.msg('请填写员工薪资!', {icon: 5,time:1000});
        return false;
    }
    if(!reg.test(emp_wage)){
        layer.msg('薪资格式不正确!', {icon: 5,time:1000});
        return false;
    }

    if (emp_role=="8"){
        layer.msg('请选择员工角色!', {icon: 5,time:1000});
        return false;
    }
    if (image_file==""){
        layer.msg('头像不能为空!', {icon: 5,time:1000});
        return false;
    }
    $("#addEmp_form").submit();
}



