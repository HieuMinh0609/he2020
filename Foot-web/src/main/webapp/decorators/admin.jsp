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
    <title>Admin Manager</title>
    <LINK rel="shorcut icon" type="image/x-ico" href='<c:url value="/image/logoCopy.png"/>'>
    <script type="text/javascript" src='<c:url value="/template/web/jquery-3.4.1.min.js"/>'></script>
   <%-- <script type="text/javascript" src='<c:url value="/template/admin/jquery-2.2.3.min.js"/>'></script>
--%>
    <script  language="JavaScript"  type="text/javascript" src='<c:url value="/template/admin/file.js"/>'></script>


    <link rel="stylesheet"  type="text/css" href='<c:url value="/template/admin/bootstrap-3.1.1-dist/css/bootstrap.min.css"/>' />
    <script type="text/javascript" src= '<c:url value="/template/admin/bootstrap-3.1.1-dist/js/bootstrap.min.js"/>'></script>
    <link rel="stylesheet" type="text/css" href='<c:url value="/template/admin/homeadmin.css"/>'/>
    <link    rel="stylesheet"  type="text/css" href='<c:url value="/template/general/GeneralCss.css"/>'></link>

    <script type='text/javascript' src='<c:url value="/template/admin/chart/Chart.min.js"/>'></script>
    <script type='text/javascript' src='<c:url value="/template/admin/google_chart.js"/>'></script
<%--thư viện sweettable delete--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
    <script type='text/javascript' src='<c:url value="/template/admin/sweetalert/sweetalert2.min.js"/>'></script>
    <link rel="stylesheet" href='<c:url value="/template/admin/sweetalert/sweetalert2.min.css"/>' />


<%--Ckditor--%>
    <script src='<c:url value="/ckeditor/ckeditor.js"/>'></script>


    <dec:head/>
</head>
<body>

        <%@ include file="/common/admin/header.jsp"%>
        <dec:body/>
        <%@ include file="/common/admin/footer.jsp"%>

        <script type='text/javascript'>
            function showAlertBeforDelete(callBack){
                swal({
                    title:"Xác nhận xóa",
                    text:"Bạn có thực sự muốn xóa không?",
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
            function showAlertBeforChange(callBack){
                swal({
                    title:"Xác nhận cập nhật",
                    text:"Đơn có thực sự hoàn thành",
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

            function showAlertBeforExport(callBack){
                swal({
                    title:"Xác nhận in",
                    text:"Đơn có thực sự muốn Export",
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