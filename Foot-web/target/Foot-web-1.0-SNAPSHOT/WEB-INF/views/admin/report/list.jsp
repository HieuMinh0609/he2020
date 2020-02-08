<%@ taglib prefix="string" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 2/1/2020
  Time: 2:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/admin/home" var="HomeUrl"/>
<c:url value="/api/admin/report/revenue" var="RevenueUrl"/>
<c:url value="/api/admin/report/product" var="ProductUrl"/>
<c:url value="/api/admin/report/export" var="ExportProductUrl"/>


<div class="card_hr row"  style="width: 101.1%;">
    <hr>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="link_header">
            <i class="glyphicon glyphicon-hand-right"></i>
            <a href="${HomeUrl}"><spring:message  code="label.web.home"/></a>
            <span>/</span>
            <span><spring:message code="label.report"/></span>

        </div>
    </div>
</div>
<br><br>
<div class="container">

    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <c:if test="${not empty messageResponse}">
                <div class="alert alert-${alert} alert-dismissible" style="margin-top: 10px">
                    <a href="${requestUrl}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <span>${messageResponse}</span>
                </div>
            </c:if>
        </div>
    </div>
    <div class="form_action_report" style="border: 1px solid #8ece8e;padding: 20px;border-radius: 25px;">
    <div class="row">
        <div class="col-md-3">
            <select id="select_store" class="form-control input_name">
                <option value="0">-Chọn cửa hàng-</option>
                <c:forEach var="item" items="${items}" >
                    <option value="${item.idstore}">${item.name}-${item.address}</option>
                </c:forEach>
            </select>
            <span class="focus-input100"></span>
            <span class="symbol-input100">
               <i style="margin-left: 15px;" class="glyphicon glyphicon-home"></i>
            </span>
        </div>
    </div>
        <br>
    <div class="row">

            <div class="col-sm-3">
                <div id="report_date" class="loginname">
                    <input style="padding-left: 110px;" id="date_infor"  class="input_name " type="text"  >
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
                            <i class="glyphicon glyphicon-calendar"></i>
                             <span style="  margin-left: 5px;">Date</span>
                         </span>
                </div>
            </div>
            <div class="col-sm-3">
                <div id="report_month" class="loginname">
                    <input  style="padding-left: 110px;" id="month_infor"  class="input_name " type="text"  >
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
                                    <i class="glyphicon glyphicon-calendar"></i>
                                <span style="  margin-left: 5px;">Month</span>
                                </span>
                </div>
            </div>
            <div class="col-sm-3">
                <div id="report_year" class="loginname">
                    <input  style="padding-left: 110px;" id="year_infor"  class="input_name " type="text"   >
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
                                    <i class="glyphicon glyphicon-calendar"></i>
                    <span style="  margin-left: 5px;">Year</span>
                     </span>
                </div>
            </div>

        <div class="col-sm-12">
            <a id="btn_show_statistic" style="margin-top: 10px;" class="btn btn-warning"><spring:message code="label.statistic"/></a>
        </div>
    </div>

    </div>

    <div class="form_report_typemoney" style="border: 1px solid #ff9800;padding: 20px;border-radius: 25px;margin-top: 12px;">
        <div class="row">

            <table class="table table-hover">
                <tbody style="color: #3f9e42;" id="table_typemoney_sum" >

                </tbody>
            </table>
        </div>
        <div class="row">
            <div style="color: #3f9e42;" id="table_all_type">

            </div>
        </div>
    </div>
    <div class="row" style="text-align: -webkit-center;;">
       <a style="display: none;border-radius: 15px;width: 160px;" id="btn_show_form_product" class="btn-success btn"><i class="glyphicon glyphicon-chevron-down"></i></a>
    </div>
    <div  class="form_report_product" style="display: none; border: 1px solid #ff9800;padding: 20px;border-radius: 25px;margin-top: 12px;">
    <div class="row" style="    overflow: scroll;height: 255px;">
        <table class="table table-hover" style="color: #3f9e42;">
            <tr>
                <th scope="col"><string:message key="label.id"/></th>
                <th scope="col"><string:message key="label.product.name"/></th>
                <th scope="col"><string:message key="label.name.store"/></th>
                <th scope="col"><string:message key="label.bill.time"/></th>
                <th scope="col"><string:message key="label.list.bill.count"/></th>
             </tr>
            <tbody id="table_product_sell" >

            </tbody>
        </table>
    </div>
    </div>
    <div class="col-sm-12">
        <a id="btn_export" style="margin-top: 10px;" class="btn btn-warning"><spring:message code="label.export.excel"/></a>
    </div>

</div>
<script>
    $( document ).ready(function() {
        var d = new Date();
        $('#date_infor').val(d.getDate());
        $('#month_infor').val(d.getMonth()+1);
        $('#year_infor').val(d.getFullYear());

    });

    $('#btn_export').on('click',function () {
        if(validateDate()){
            if($('#select_store option:selected').val()!=0){
                var currentPageOnline = 1;
                var dataArrayOnline={};
                dataArrayOnline["page"]=currentPageOnline;
                dataArrayOnline["maxPageItems"]=7;
                dataArrayOnline["day"]= $('#date_infor').val();
                dataArrayOnline["month"]=$('#month_infor').val();
                dataArrayOnline["year"]= $('#year_infor').val();
                dataArrayOnline["id"]=$('#select_store option:selected').val();
                exportExcel(dataArrayOnline);


            }
        }
    });

    function exportExcel(dataArray){
        $.ajax({
            url: '${ExportProductUrl}',
            type: 'POST',
            data: JSON.stringify(dataArray),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                if(data){
                    swal("Export success!","Success!!!", "success",1000 , false);

                }else{
                    swal("Opp!", "System Wrong!!!!", "error", 1000, false);
                }

            },
            error: function (data) {
                swal("Opp!", "System Wrong!!!!", "error", 1000, false);
            }
        });
    }

    $('#btn_show_statistic').click(function () {
        if(validateDate()){
            if($('#select_store option:selected').val()!=0){
                var currentPageOnline = 1;
                var dataArrayOnline={};
                dataArrayOnline["page"]=currentPageOnline;
                dataArrayOnline["maxPageItems"]=7;
                dataArrayOnline["day"]= $('#date_infor').val();
                dataArrayOnline["month"]=$('#month_infor').val();
                dataArrayOnline["year"]= $('#year_infor').val();
                dataArrayOnline["id"]=$('#select_store option:selected').val();
                loadDataRevenue(dataArrayOnline);

                $('.form_report_product').css("display","none");
                $('#btn_show_form_product').css("display","block");
            }
        }

    });
    $('#btn_show_form_product').click(function () {

        if($('#select_store option:selected').val()!=0) {
            $('#btn_show_form_product').css("display","none");
            $('.form_report_product').css("display",'block');
            var currentPageOnline = 1;
            var dataArrayOnline = {};
            dataArrayOnline["page"] = currentPageOnline;
            dataArrayOnline["maxPageItems"] = 2;
            dataArrayOnline["day"] = $('#date_infor').val();
            dataArrayOnline["month"] = $('#month_infor').val();
            dataArrayOnline["year"] = $('#year_infor').val();
            dataArrayOnline["id"] = $('#select_store option:selected').val();
            loadDataProduct(dataArrayOnline, 0);

        }
    });



    function loadDataProduct(dataArray,key) {
        $.ajax({
            url: '${ProductUrl}',
            type: 'POST',
            data: JSON.stringify(dataArray),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                    if(data !=null){

                        var row = '';
                        $(data).each(function (index, items) {
                           // var time = new Date(items.dateTime);
                            row+='<tr>';
                            row+='<td><span>'+items.productDTO.idProduct+'</span></td>';
                            row+='<td><span>'+items.productDTO.nameProduct+'</span></td>';
                            row+='<td><span>'+items.storeDTO.name+'</span></td>';
                            row+='<td>'+items.dateTime+'</td>';
                            row+='<td><span>'+items.countProduct+'</span></td>';
                            row+='</tr>';
                        });
                        if(key==0){
                            $('#table_product_sell').html(row);
                        }else if(key==1){
                            $('#table_product_sell').append(row);
                        }

                    }



            },
            error: function (data) {
                console.log(data);
                window.location.href = "/admin/report/list?message=error_system";
            }
        });
    }

    function loadDataRevenue(dataArray) {
        $.ajax({
            url: '${RevenueUrl}',
            type: 'POST',
            data: JSON.stringify(dataArray),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                if(data !=null){
                   var countSumType=0;
                   var countSumMoney=0;

                    var row = '';
                    $(data).each(function (index, items) {
                        row+='<tr>';
                        row+='<td style="margin-right: 35px">'
                        row+='Tên Thể Loại :'+items.typeDTO.typeName;
                        row+=' </td>'
                        row+='   <td style="margin-right: 35px">'
                        row+='Số Lượng :'+ items.countType;
                        row+='    </td>'
                        row+='   <td style="margin-right: 35px">'
                        row+='Tổng tiền :'+ numberWithCommas(items.sumMoneyType)+" VND";
                        row+='    </td>'
                        row+='   <td style="margin-right: 35px">'
                        row+='Chiếm (%):'+ Math.ceil(items.percen)+"%";
                        row+='</td>';
                        row+='</tr>';

                        row+=' </br></br>';
                        countSumType+= items.countType;
                        countSumMoney+=items.sumMoneyType;
                    });
                }
                var row2 = '';
                row2+='<span style="margin-right: 35px">'
                row2+='Tổng tiền  :'+numberWithCommas(countSumMoney) +"VND ";
                row2+=' </span>'
                row2+='   <span style="margin-right: 35px">'
                row2+='Số Lượng sản phẩm:'+countSumType;
                row2+='</span>'

                $('#table_all_type').html(row2);
                $('#table_typemoney_sum').html(row);

            },
            error: function (data) {
                console.log(data);
               window.location.href = "/admin/report/list?message=error_system";
        }
        });
    }

    function validateDate() {
        if($('#year_infor').val()==''){
            $('#report_year').css("border","1px solid red");
            $('#report_year').css('border-radius', '25px');

            return false;
        }else{
            $('#report_year').css("border","none");
        }
        if($('#month_infor').val()=='' && $('#date_infor').val()!=''){
            $('#report_month').css("border","1px solid red");
            $('#report_month').css('border-radius', '25px');

            return false;
        }else{
            $('#report_month').css("border","none");
        }

        return true;
    }
</script>
