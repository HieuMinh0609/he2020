<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 1/12/2020
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/api/admin/bill/exports" var="exports"/>
<c:url value="/api/admin/bill/detail/" var="detailBillUrl"/>

<script  language="JavaScript"  type="text/javascript" src='<c:url value="/template/admin/file.js"/>'></script>
<div class="modal-dialog modal-lg">
    <!-- Modal content-->
    <div class="modal-content">
        <div  id="location_edituser">
            <div class="modal-header">


            </div>
        </div>
        <form>

            <div id="button_select" class="row" style="margin-right: 32px;">
                <a onclick="waringBeforExport()" type="button" class="btn btn-warning button_select" title="In hóa đơn"><i class=" glyphicon glyphicon-export"></i></a>
            </div>
            <div class="row" style="padding: 0 50px;">
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
</div>
<div class="modal fade" id="myModalFile" role="dialog">

</div>
<script>

    function detailClick(btn) {
        var idBill=$(btn).attr('id_Bill');
        var showUrl = '${detailBillUrl}'+idBill;
        $('#myModalFile').load(showUrl,'',function () {
            $('#myModalFile').modal('toggle');

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
                    swal("Good job!", "Export Success!", "success", 2000, false);
                    setTimeout(function(){
                        window.location.href = "<c:url value='/admin/home'/>";
                    }, 1000);
                }else{
                    swal("Cancelled!", "You Cancelled", "error", 2000, false)
                }
            }
        });
    }
</script>