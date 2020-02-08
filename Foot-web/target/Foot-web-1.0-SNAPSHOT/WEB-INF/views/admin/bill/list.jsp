<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 7/23/2019
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="/admin/bill/list" var="requestUrl"/>
<c:url value="/admin/home" var="HomeUrl"/>
<c:url value="/api/admin/bill/detail/" var="detailUrl"/>
<c:url value="/api/admin/bill/delete" var="deleteBill"/>
<c:url value="/api/admin/bill/converter" var="converterBill"/>
<c:url value="/api/admin/bill/exports" var="exports"/>
<c:url value="/api/admin/bill/exportPdf" var="exportBill"/>
 <div class="card_hr row">
    <hr>
</div>
<div class="container-fluid">
    <div class="row">

        <div class="link_header">
            <i class="glyphicon glyphicon-hand-right"></i>
            <a href="${HomeUrl}"><spring:message  code="label.web.home"/></a>
            <span>/</span>
            <span><spring:message code="label.list.bill"/> </span>
        </div>

    </div>
</div>
<div class="container">

    <form method="GET"  action="${requestUrl}">
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <c:if test="${not empty messageResponse}">
                    <div class="alert alert-${alert} alert-dismissible" style="margin-top: 10px">
                        <a href="${requestUrl}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <span>${messageResponse}</span>
                    </div>
                </c:if>
            </div>
            <div class="col-md-10 col-sm-10 col-xs-10">
                <div class="input_search_area">
                    <input  class="input_search" type="text" name="idBill" placeholder="<spring:message code="label.id"/>" >
                    <span class="focus-input100"></span>
                    <div class="symbol-input100">

                        <i class="glyphicon glyphicon-search"></i>

                    </div>

                </div>

            </div>
            <div class="col-md-2 col-sm-2 col-xs-2">
                <button type="submit" class="btn_sreach btn btn-success">Search</button>
            </div>
        </div>
    </form>
    <form>

        <div id="button_select" class="row">
            <button class="btn btn-danger button_select" type="button" id='deleteAll' disabled='false' onclick="waringBeforDelete()" title="Delete"><i class="glyphicon glyphicon-trash"></i></button>
            <a onclick="waringBeforConverter()" type="button" class="btn btn-success button_select" title="Hoàn thành hóa đơn"><i class="glyphicon glyphicon-retweet"></i></a>
            <a onclick="waringBeforExport()" type="button" class="btn btn-warning button_select" title="In hóa đơn"><i class=" glyphicon glyphicon-export"></i></a>

         </div>
        <div class="row">

            <fmt:bundle basename="bundles">
                <display:table id="tableList" name="model.listResult" cellspacing="0" cellpadding="0" partialList="true" size="${model.totalItems}"
                               pagesize="${model.maxPageItems}" sort="external"  requestURI="${requestUrl}"
                               class="table table_backcolor table-bordered"
                               style="margin: 3em 0 1.5em;">
                    <display:column title="<fieldset >
                                                            <input type='checkbox' class='check-box-element' id='checkAll'>
                                                            </fieldset>" class='center select-cell' headerClass="center select-cell">
                        <fieldset>
                            <input type='checkbox' value="${tableList.idBill}" class='check-box-element' name="checkList" id="checkbox_${tableList.idBill}"/>
                        </fieldset>
                    </display:column>

                    <display:column property="idBill" titleKey="label.id" sortable="true" sortName="idBill"/>
                    <display:column property="idUserEntity.nameFull" titleKey="label.user.namefull" sortable="true" sortName="idUserEntity.nameFull"/>
                    <display:column property="dateTime" titleKey="label.bill.time" sortable="true" sortName="dateTime"/>
                    <display:column property="place" titleKey="label.list.user.address" sortable="true" sortName="place"/>
                    <display:column property="cost" titleKey="label.list.bill.sumsell" sortable="true" sortName="cost"/>
                    <display:column headerClass="col-actions" titleKey="label.list.bill.status" sortable="true" sortName="status">
                        <c:if test="${tableList.status==1}">
                            <a data-toggle="tooltip" title="<spring:message code="label.list.bill.status.success"/>" class="btn btn-success">
                               <i class="glyphicon glyphicon-ok-circle"></i>
                            </a>
                        </c:if>
                        <c:if test="${tableList.status==0}">
                            <a data-toggle="tooltip" title="<spring:message code="label.list.bill.status.danger" /> " class="btn btn-danger">
                                <i class="glyphicon glyphicon-exclamation-sign"></i>
                            </a>
                        </c:if>
                    </display:column>
                    <display:column headerClass="col-actions" titleKey="label.btn.export.bill" sortable="true" sortName="status">
                        <c:if test="${tableList.printed==1 }">
                            <a data-toggle="tooltip" title="<spring:message code="label.list.bill.status.success"/>" class="btn btn-success">
                                <i class="glyphicon glyphicon-ok-circle"></i>
                            </a>
                        </c:if>
                        <c:if test="${tableList.printed==0 }">
                            <a data-toggle="tooltip" title="<spring:message code="label.list.bill.status.danger" /> " class="btn btn-danger">
                                <i class="glyphicon glyphicon-exclamation-sign"></i>
                            </a>
                        </c:if>
                    </display:column>
                    <display:column headerClass="col-actions" titleKey="label.action">

                        <a class="btn btn-sm btn-primary btn-edit" id_Bill="${tableList.idBill}"  onclick="detailClick(this)" data-toggle="tooltip" title="<spring:message code="label.list.bill.detail"/>">
                            <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>
                        </a>

                    </display:column>
                </display:table>
            </fmt:bundle>

        </div

    </form>

</div>
<div class="modal fade" id="myModalFile" role="dialog">

</div>
<script >
    function exportPdf(){

            var idBill = $('#idBillDetail').val();

            $.ajax({
                url: ' ${exportBill}',
                type: 'POST',
                data: JSON.stringify(idBill),
                dataType: 'json',
                contentType: "application/json",
                success: function (res) {
                    if(res){
                        window.location.href = "/admin/bill/list?message=export_success";
                    }else{
                        window.location.href = "/admin/bill/list?message=error_system";
                    }
                }
            });


    }




    function detailClick(btn) {
        var idBill=$(btn).attr('id_Bill');
        var showUrl = '${detailUrl}'+idBill;
        $('#myModalFile').load(showUrl,'',function () {
            $('#myModalFile').modal('toggle');

        });
    }

    function waringBeforConverter() {
        showAlertBeforChange(function () {
            event.preventDefault();
            var dataArray =$('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            converterClick(dataArray);
        });
    }
    function waringBeforExport() {
        showAlertBeforExport(function () {
            event.preventDefault();
            var dataArray =$('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            ExportClick(dataArray);
        });
    }
    function ExportClick(dataArray) {
        $.ajax({
            url: ' ${exports}',
            type: 'PUT',
            data: JSON.stringify(dataArray),
            dataType: 'json',
            contentType: "application/json",
            success: function (res) {
                if(res){
                    window.location.href = "/admin/bill/list?message=export_success";
                }else{
                    window.location.href = "/admin/bill/list?message=error_system";
                }
            }
        });
    }
    function converterClick(dataArray) {

        $.ajax({
            url: ' ${converterBill}',
            type: 'PUT',
            data: JSON.stringify(dataArray),
            dataType: 'json',
            contentType: "application/json",
            success: function (res) {
                if(res){
                    window.location.href = "/admin/bill/list?message=update_success";
                }else{
                    window.location.href = "/admin/bill/list?message=error_system";
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
            deleteBill(dataArray);
        });
    }
    function deleteBill(dataArray){
        $.ajax({
            url: '${deleteBill}',
            type: 'DELETE',
            data: JSON.stringify(dataArray),
            dataType: 'json',
            contentType: "application/json",
            success: function (res) {
                if(res){
                    window.location.href = "/admin/bill/list?message=delete_success";
                }else{
                    window.location.href = "/admin/bill/list?message=error_system";
                }
            }
        });
    }
</script>