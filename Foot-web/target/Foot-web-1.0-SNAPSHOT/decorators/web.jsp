<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 6/4/2019
  Time: 2:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Everything.vn</title>
        <LINK rel="shorcut icon" type="image/x-ico" href='<c:url value="/image/logoCopy.png"/>'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <%-- <script type="text/javascript" src='<c:url value="/template/web/jquery-3.4.0.min.js"/>'></script>--%>
        <script type="text/javascript" src='<c:url value="/template/web/jquery-3.4.1.min.js"/>'></script>



        <script  language="JavaScript"  type="text/javascript" src='<c:url value="/template/web/file.js"/>'></script>
         <link rel="stylesheet" type="text/css" href='<c:url value="/template/web/file.css"/>'/>
        <link rel="stylesheet" type="text/css" href='<c:url value="/template/web/homeadmin.css"/>'/>
        <link rel="stylesheet" type="text/css" href='<c:url value="/template/web/homepage.css"/>'/>
        <link rel="stylesheet"  type="text/css" href='<c:url value="/template/web/bootstrap-3.1.1-dist/css/bootstrap.min.css"/>' />
        <script type="text/javascript" src= '<c:url value="/template/web/bootstrap-3.1.1-dist/js/bootstrap.min.js"/>'></script>
        <%--thư viện sweettable delete--%>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
        <script type='text/javascript' src='<c:url value="/template/admin/sweetalert/sweetalert2.min.js"/>'></script>
        <link rel="stylesheet" href='<c:url value="/template/admin/sweetalert/sweetalert2.min.css"/>' />


        <dec:head/>
</head>
<body>
        <%@ include file="/common/web/header.jsp"%>
        <div id="body_control" class="container-fluid">

        <dec:body/>
         <%@ include file="/common/web/menu.jsp"%>
        </div>
        <%@ include file="/common/web/footer.jsp"%>

       <script>

           function showAlertBeforDeleteComment(callBack){
               swal({
                   title:"Xác nhận xóa",
                   text:"Đơn có thực sự muốn xóa",
                   type:"warning",
                   showCancelButton:true,
                   confirmButtonText:"Đồng ý",
                   cancelButtonText:"Hủy bỏ",
                   confirmButtonClass:"btn btn-success",
                   cancelButtonClass:"btn btn-danger"
               }).then(function (isconfirm) {
                   if(isconfirm){
                       callBack();
                   }
               });
           }
       </script>
</body>
</html>
