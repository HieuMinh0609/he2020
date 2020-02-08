<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 1/15/2020
  Time: 10:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/admin/typeproduct/list" var="requestUrl"/>
<c:url value="/admin/home" var="HomeUrl"/>
<c:url var="showTypeUrl" value="/api/admin/typeproduct/"/>
<c:url var="editTypeUrl" value="/api/admin/typeproduct/edit"/>
<c:url var="deleteType" value="/api/admin/typeproduct/delete"/>

<div class="card_hr row">
    <hr>
</div>
<div class="container-fluid">
    <div class="row">

        <div class="link_header">
            <i class="glyphicon glyphicon-hand-right"></i>
            <a href="${HomeUrl}"><spring:message  code="label.web.home"/></a>
            <span>/</span>
            <span><spring:message code="label.list.product.typename"/> </span>
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

        </div>
    </form>
    <form>

        <div id="button_select" class="row">
            <button class="btn btn-danger button_select" type="button" id='deleteAll' disabled='false' onclick="waringBeforDelete()" title="Delete"><i class="glyphicon glyphicon-trash"></i></button>
            <a  onclick='update(this)' type="button" class="btn btn-warning button_select" title="Add"><i class="glyphicon glyphicon-plus-sign"></i></a>
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
                            <input type='checkbox' value="${tableList.typeId}" class='check-box-element' name="checkList" id="checkbox_${tableList.typeId}"/>
                        </fieldset>
                    </display:column>
                    <display:column property="typeId" titleKey="label.id" sortable="true" sortName="typeId"/>
                    <display:column property="typeName" titleKey="label.list.product.typename" sortable="true" sortName="typeName"/>
                    <display:column headerClass="col-actions" titleKey="label.action">
                        <a class="btn btn-sm btn-primary btn-edit" id_type="${tableList.typeId}" onclick="update(this)" data-toggle="tooltip" title="<spring:message code="label.update"/>">
                            <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>
                        </a>
                    </display:column>
                </display:table>
            </fmt:bundle>
        </div
    </form>
</div>

<div class="modal fade" id="myModalType" role="dialog">

</div>

<script>

    function update(btn) {
        var id=$(btn).attr('id_type');
        var showUrl = '${showTypeUrl}'+id;
        if( typeof  id== 'undefined'){
            showUrl='${showTypeUrl}';
        }

        $('#myModalType').load(showUrl,'',function () {
            $('#myModalType').modal('toggle');
            edirOrAdd();
        });
    }
    function edirOrAdd(){
        $('#btnSave').click(function (e) {
            e.preventDefault();

                var dataArray = {};
                var id = $('#typeId').val();

                dataArray["typeId"] =  id;
                dataArray["typeName"] = $('#typeName').val();
                getValue(dataArray);
        });
    }

    function getValue(dataArray) {
        $.ajax({
            url: '${editTypeUrl}',
            type: 'POST',
            data: JSON.stringify(dataArray),
            dataType:'json',
            contentType: "application/json",
            success: function (res) {
                if(res){
                    window.location.href = "/admin/typeproduct/list?message=insert_success";
                }else{
                    window.location.href = "/admin/typeproduct/list?message=error_system";
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
            deleteTypeProduct(dataArray);
        });
    }
    function deleteTypeProduct(dataArray){
        $.ajax({
            url: '${deleteType}',
            type: 'DELETE',
            data: JSON.stringify(dataArray),
            dataType: 'json',
            contentType: "application/json",
            success: function (res) {
                if(res){
                    window.location.href = "/admin/typeproduct/list?message=delete_success";
                }else{
                    window.location.href = "/admin/typeproduct/list?message=error_system";
                }
            }
        });
    }
</script>

