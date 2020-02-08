<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 6/25/2019
  Time: 4:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="/admin/product/list" var="requestUrl"/>
<c:url value="/admin/home" var="HomeUrl"/>
<c:url value="/admin/product/edit" var="AddUrrl"/>
<c:url value="/api/product/delete" var="deleteProduct"/>
<c:url value="/admin/product/image/" var="addImage"/>
<c:url value="/admin/product/import" var="addExcel"/>
<div class="card_hr row">
    <hr>
</div>
<div class="container-fluid">
    <div class="row">

        <div class="link_header">
            <i class="glyphicon glyphicon-hand-right"></i>
            <a href="${HomeUrl}"><spring:message  code="label.web.home"/></a>
            <span>/</span>
            <span><spring:message code="label.list.product"/> </span>
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
                    <input  class="input_search" type="text" name="nameProduct" placeholder="<spring:message code="label.product.name"/>" >
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
            <a href="${AddUrrl}" type="button" class="btn btn-warning button_select" title="Add"><i class="glyphicon glyphicon-plus-sign"></i></a>
            <a href="${addExcel}" type="button" class="btn btn-success button_select" title="import from excel"><i class="glyphicon glyphicon-import"></i></a>
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
                            <input type='checkbox' value="${tableList.idProduct}" class='check-box-element' name="checkList" id="checkbox_${tableList.idProduct}"/>
                        </fieldset>
                    </display:column>

                    <display:column property="idProduct" titleKey="label.id" sortable="true" sortName="idproduct"/>
                    <display:column property="nameProduct" titleKey="label.product.name" sortable="true" sortName="nameproduct"/>
                    <display:column property="cost" titleKey="label.list.product.sell" sortable="true" sortName="cost"/>
                     <display:column property="typeIdEntity.typeName" titleKey="label.list.product.typename" sortable="true" sortName="typeid"/>
                    <display:column headerClass="col-actions" titleKey="label.product.status" sortable="true" sortName="status">

                        <c:if test="${tableList.status==0  }">
                            <a data-toggle="tooltip" title="<spring:message code="label.product.online"/>" class="btn btn-success">
                                <i class="glyphicon glyphicon-record"></i>
                            </a>
                        </c:if>
                        <c:if test="${tableList.status==1 }">
                            <a data-toggle="tooltip" title="<spring:message code="label.product.pause" /> " class="btn btn-danger">
                                <i class="glyphicon glyphicon-record"></i>
                            </a>
                        </c:if>
                    </display:column>
                    <display:column property="view" titleKey="label.product.view" sortable="true" sortName="view"/>



                    <display:column headerClass="col-actions" titleKey="label.action">


                        <a class="btn btn-sm btn-primary btn-edit" id_Product="${tableList.idProduct}"  href="${AddUrrl}/${tableList.idProduct}" data-toggle="tooltip" title="<spring:message code="label.update"/>">
                            <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>
                        </a>

                        <a class="btn btn-sm btn-warning btn-edit"  href="${addImage}${tableList.idProduct}" data-toggle="tooltip" title="<spring:message code="label.update.image"/>">
                            <i class="glyphicon glyphicon-picture" aria-hidden="true"></i>
                        </a>
                    </display:column>
                </display:table>
            </fmt:bundle>
        </div
    </form>
</div>
<script>

    function waringBeforDelete() {
        showAlertBeforDelete(function () {
            event.preventDefault();
            var dataArray =$('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteProduct(dataArray);
        });
    }
    function deleteProduct(dataArray){
        $.ajax({
            url: '${deleteProduct}',
            type: 'DELETE',
            data: JSON.stringify(dataArray),
                        dataType: 'json',
                        contentType: "application/json",
                        success: function (res) {
                        if(res){
                            window.location.href = "/admin/product/list?message=delete_success";
                        }else{
                            window.location.href = "/admin/product/list?message=error_system";
                }
            }
        });
    }
</script>
