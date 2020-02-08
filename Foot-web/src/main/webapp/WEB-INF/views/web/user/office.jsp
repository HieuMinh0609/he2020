<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 9/8/2019
  Time: 10:50 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url var="showHisShopping" value="/api/user/showHistory/shopping"/>
<c:url var="updateUser" value="/api/user/web/user/update"/>
<c:url var="changePassword" value="/api/user/web/change/password"/>
<c:url var="detailBill" value="/api/user/web/cart/detail"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<br><br>
<div  class="col-md-8">
    <div class="row">
        <div class="detail_user" >
            <security:authorize  access="isAuthenticated()">
                <img    src= '<c:url value="/image/man.png"/>' alt="man">
            </security:authorize>
            <security:authorize  access="isAnonymous()">
                <img    src= '<c:url value="/image/user-login-man.png"/>' alt="man">
            </security:authorize>
            <div class="name_user_detail">
                <h4  >${model.nameFull}</h4>
            </div>

        </div>
    </div>

    <div>
        <hr>
    </div>

    <div class="title_bar_cart" style="width: 262px;">
        <div id="btn_default_infor" style="background:#8ece8e " class="btn btn-default">Information</div>
        <div id="btn_default_history" style=" margin-left: 20px;" class="btn btn-default">History shopping</div>
    </div>
    <br>
    <div class="table_pro_load" style="display: none">
        <span id="sum_pro_his"></span>
        <table id="table_his_shopping" class="table table-striped">
            <tr>
                <th><spring:message key="label.id"/></th>
                <th><spring:message key="label.list.user.address"/></th>
                <th><spring:message key="label.bill.time"/></th>
                <th><spring:message key="label.list.bill.sumsell"/></th>
                <th><spring:message key="label.action"/></th>
            </tr>
        </table>
    </div>

        <c:if test="${not empty messageResponse}">
            <div class="alert alert-${alert} alert-dismissible" style="margin-top: 10px">
                <a href="${requestUrl}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <span>${messageResponse}</span>
            </div>
        </c:if>

    <div class="from_information">
        <div class="loginname">
            <input  id="office_email" class="input_pass" type="text" name="email" value="${model.email}" placeholder="Email" >
            <span class="focus-input100"></span>
            <span class="symbol-input100">
                <i class="glyphicon glyphicon-envelope"></i>
            </span>
        </div>
        <div class="loginname">
            <input   id="office_place" class="input_pass" type="text" name="place" value="${model.place}" placeholder="Place" >
            <span class="focus-input100"></span>
            <span class="symbol-input100">
                <i class="glyphicon glyphicon-pencil"></i>
            </span>
        </div>
        <div class="loginname">
            <input   id="office_phone"  class="input_pass" type="text" name="phoneNumber" value="${model.phoneNumber}" placeholder="Phone Number" >
            <span class="focus-input100"></span>
            <span class="symbol-input100">
                <i class="glyphicon glyphicon-earphone"></i>
            </span>
        </div>
        <button id="btn_update" style="margin-top: 20px; width: 75px" class="btn btn-primary">Update</button>
        <button id="btn_change_pass" style="margin-top: 20px;"  class="btn btn-warning">Change Password</button>
    </div>

</div>
<!-- Modal -->
<div class="modal fade " id="myModalChangePass" role="dialog">
    <div class="modal-dialog modal-md">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Change your password</h4>
            </div>
            <div class="modal-body">
                <div class="loginname">
                    <input  required  style="padding-left:185px" type="password" name="place" id="new_password"  placeholder="new password"   class="input_name ">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
                            <i  class="glyphicon glyphicon-refresh"></i>
                            <span style="  margin-left: 5px;">New password</span>
                    </span>


                </div>
                <div class="loginname">
                    <input  required  style="padding-left: 185px;" type="password" name="confirm_password" id="confirm_password"  placeholder="confirm password"   class="input_name ">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
                            <i  class="glyphicon glyphicon-refresh"></i>
                            <span style="  margin-left: 5px;">Confirm password</span>
                    </span>


                </div>
                <i id="ok_confirm" style="display:none; color: #8ece8e" class="glyphicon glyphicon-ok"></i>
                <i id="not_confirm" style="display:none; color: red"  class="glyphicon glyphicon-remove"></i>
            </div>
            <div class="modal-footer">
                <button id="save_change_password" type="button" class="btn btn-warning">Change password</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="showDetailBill" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <div class="titel_lo_re">
                    <span class="register_click" id="title_name"><spring:message key="label.list.product"/></span>
                </div>
            </div>
            <div class="modal-body">
                <table class="table table-striped" style="color: #4CAF50;">
                    <tr>
                        <th style="width: 180px;"><spring:message key="label.list.product.images"/> </th>
                        <th><spring:message key="label.product.name"/></th>
                        <th><spring:message key="label.list.bill.count"/></th>
                    </tr>
                    <tbody class="table_bill_show_cart">


                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script>
    var i=0;
    $(function () {
        $('#btn_default_infor').click(function () {
            $('#btn_default_infor').css('background','#8ece8e');
            $('#btn_default_history').css('background','white');

            $('.from_information').css('display','block');
            $('.table_pro_load').css('display','none');
        });
        $('#btn_default_history').click(function () {
            $('#btn_default_history').css('background','#8ece8e');
            $('#btn_default_infor').css('background','white');

            $('.from_information').css('display','none');
            $('.table_pro_load').css('display','block');
             i+=1;

             if(i==1){
                 var Page = 1;
                 var dataArray={};
                 dataArray["page"]=Page;
                 dataArray["maxPageItems"]=7;
                 showHistoryShopping(dataArray);
             }
            $('.table_pro_load').scroll(function () {
                if ($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
                    Page += 1;
                    dataArray["page"]=Page;
                    dataArray["maxPageItems"]=7;
                    showHistoryShopping(dataArray);
                }
            });

        });
    });

    $('#btn_change_pass').click(function () {
        $('#myModalChangePass').modal('toggle');
    });

   $(function () {
       $('#confirm_password').keyup(function(e){
           if($('#confirm_password').val()!=$('#new_password').val()) {
               $('#ok_confirm').css('display','none');
               $('#not_confirm').css('display','block');
           }else{

               $('#not_confirm').css('display','none');
               $('#ok_confirm').css('display','block');

           }
       });


   });

    if($('#confirm_password').val()==$('#new_password').val()) {
        $('#save_change_password').on('click', function () {
            var dataArray = {};
            dataArray["passWord"] = $('#new_password').val();

            if (validateForm()) {
                changePassword(dataArray);
            }
        });

    }


    function  changePassword(dataArray) {
        $.ajax({
            url:'${changePassword}',
            type: 'POST',
            data: JSON.stringify(dataArray),
            dataType:'json',
            contentType: 'application/json',
            success: function (res) {
                if(res){
                    window.location.href = "/home-office?message=update_success";
                }else{
                    window.location.href = "/home-office?message=error_system";
                }
            },
            error:function () {
                alert("wrong");
            }

        });
    }
    function validateForm(){
        if($('#new_password').val()=='' ||$('#confirm_password').val()==''  ){
            if($('#new_password').val()=='' ){
                $('#new_password').css("border", "1px solid red");

            }else{
                $('#new_password').css("border", 'none');

            }

            if($('#confirm_password').val()==''){
                $('#confirm_password').css("border", "1px solid red");

            }else{
                $('#confirm_password').css("border", 'none');

            }


        }else {

            return true;
        }
    }

    $('#btn_update').click(function () {
        var dataArray={
            "phoneNumber":$('#office_phone').val(),
            "place":$('#office_place').val(),
            "email":$('#office_email').val()
        };

        if(validateForm()){
             updateUserOffice(dataArray);
        }

    });



    function  updateUserOffice(dataArray) {
        $.ajax({
            url:'${updateUser}',
            type: 'POST',
            data: JSON.stringify(dataArray),
            dataType:'json',
            contentType: 'application/json',
            success: function (res) {
                if(res){
                    window.location.href = "/home-office?message=update_success";
                }else{
                    window.location.href = "/home-office?message=error_system";
                }
            },
            error:function () {
                alert("wrong");
                window.location.href = "/home-office?message=error_system";
            }

        });
    }


    function validateForm(){
        if($('#office_email').val()=='' ||$('#office_place').val()=='' || $('#office_phone').val()=='' ){
            if($('#office_email').val()=='' ){
                $('#office_email').css("border", "1px solid red");

            }else{
                $('#office_email').css("border", 'none');

            }

            if($('#office_place').val()==''){
                $('#office_place').css("border", "1px solid red");

            }else{
                $('#office_place').css("border", 'none');

            }

            if($('#office_phone').val()==''){
                $('#office_phone').css("border", "1px solid red");

            }else{
                $('#office_phone').css("border", 'none');

            }


        }else {

            return true;
        }
    }


    function showHistoryShopping(dataArray) {
            $.ajax({
                url: '${showHisShopping}',
                type: 'POST',
                data: JSON.stringify(dataArray),
                dataType: 'json',
                contentType: "application/json",
                success: function (data) {
                    if(data !=null){
                        var row = '';

                        $(data).each(function (index, items) {
                            var dt = new Date(items.dateTime);
                            row+='<tr>';
                            row+=' <td>';
                            row+='<span>'+items.idBill+'</span>';
                            row+='</td>';
                            row+=' <td>';
                            row+= items.place;
                            row+='  </td>';
                            row+=' <td>';
                            row+=dt.toLocaleString();
                            row+='</td>';
                            row+='<td>';
                            row+=numberWithCommas(items.cost)+"&ensp;VND";
                            row+='</td>';
                            row+='<td><a class="btn-warning btn" urc_idBill_detail="'+items.idBill+'"  id="bill_process_'+items.idBill+'"  onclick="detailBillCart(this)"><spring:message key="label.list.bill.detail"/></a></td>';
                            row+=' </tr>';
                        });
                        $('#table_his_shopping').append(row);
                    }
                },
                error: function (err) {
                    alert(Error);
                }
            });
    }

    function detailBillCart(btn) {
        var idBill=$(btn).attr('urc_idBill_detail');
        $('#showDetailBill').modal('toggle');
        detailBill(idBill);
    }
    function  detailBill(idBill) {
        var dataArray={};
        dataArray["id"]=idBill;
        $.ajax({
            url: '${detailBill}',
            type: 'POST',
            data:JSON.stringify(dataArray) ,
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                var row = '';
                $(data).each(function (index, items) {
                    row+='<tr>';
                    row+='<td>';
                    row+='<div class="img_pro_load" style=" height: 60px;">';
                    row+='<img style="width: 90px" src="<c:url value="/repository'+items.idDBillProductEntity.image+'"/>" alt="'+items.idDBillProductEntity.image+'">';
                    row+='</div>';
                    row+='<td>'+items.idDBillProductEntity.nameProduct+'</td>';
                    row+='<td>'+items.count+'</td>';
                    row+='</tr>';
                });
                $('.table_bill_show_cart').html(row);
            },
            error:function (data) {
                alert("have error ,dalete fail!");
            }
        });
    }
    function numberWithCommas(x) {
        x = x.toString();
        var pattern = /(-?\d+)(\d{3})/;
        while (pattern.test(x))
            x = x.replace(pattern, "$1,$2");
        return x;
    }

</script>

</body>
</html>
