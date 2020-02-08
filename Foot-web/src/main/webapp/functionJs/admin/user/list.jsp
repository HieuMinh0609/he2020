<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 1/11/2020
  Time: 12:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/admin/user/list" var="requestUrl"/>
<c:url var="showUserUrl" value="/api/admin/user/"/>
<c:url var="deleteUser" value="/api/admin/user/delete"/>
<c:url value="/admin/home" var="HomeUrl"/>
<script type="text/javascript">

    $(document).ready(function () {


    });

    function update(btn) {
        var idUser=$(btn).attr('id_User');
        var showUrl = '${showUserUrl}'+idUser;
        if( typeof  idUser== 'undefined'){
            showUrl='${showUserUrl}';
        }

        $('#myModal').load(showUrl,'',function () {
            $('#myModal').modal('toggle');
            edirOrAdd();
        });
    }
    function edirOrAdd(){
        $('#btnSave').click(function (e) {
            e.preventDefault();
            if(validateForm()===false){
                $('#nofiction').show();
            }else{
                $('#nofiction').hide();
                var dataArray = {};
                var idUser = $('#idUser').val();
                var block = $('input[name=block]:checked').val();
                var sex  = $('input[name=sex]:checked').val();
                var idRole  = $('input[name=role]:checked').val();

                dataArray["nameLogin"] = $('#nameLogin').val();
                dataArray["idUser"] = idUser;
                dataArray["nameFull"] = $('#nameFull').val();
                dataArray["passWord"] = $('#passWord').val();
                dataArray["email"] = $('#email').val();
                dataArray["place"] = $('#place').val();
                dataArray["sex"] = sex;
                dataArray["block"] = block;
                dataArray["idRole"] = idRole;
                dataArray["phoneNumber"] = $('#phoneNumber').val();
                getValue(dataArray);
            }


        });




    }

    function getValue(dataArray) {
        $.ajax({
            url: $('#form_user').attr('action'),
            type: $('#form_user').attr('method'),
            data: JSON.stringify(dataArray),
            dataType:'json',
            contentType: "application/json",
            success: function (res) {
                if(res){
                    window.location.href = "/admin/user/list?message=insert_success";
                }else{
                    window.location.href = "/admin/user/list?message=error_system";
                }
            }
        });

    }
    function waringBeforDelete() {
        showAlertBeforDelete(function () {
            event.preventDefault();
            var dataArray =$('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteUser(dataArray);
        });
    }

    function deleteUser(dataArray){
        $.ajax({
            url: '${deleteUser}',
            type: 'POST',
            data: JSON.stringify(dataArray),
            dataType: 'json',
            contentType: "application/json",
            success: function (res) {
                if(res){
                    window.location.href = "/admin/user/list?message=delete_success";
                }else{
                    window.location.href = "/admin/user/list?message=error_system";
                }
            }
        });
    }

    function validateForm(){
        if($('#nameLogin').val()==''){
            return false;
        }else if($('#nameFull').val()==''){
            return false;
        }
        else if($('#passWord').val()==''){
            return false;
        }

        else if($('#email').val()==''){
            return false;
        }

        else if($('#place').val()==''){
            return false;
        }

        else if($('#phoneNumber').val()==''){
            return false;
        }
        return true;
    }





</script>