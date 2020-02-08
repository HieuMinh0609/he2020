<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 7/20/2019
  Time: 1:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/admin/product/readExcel" var="requestUrl"/>
<c:url value="/admin/product/list" var="ProductUrl"/>
<c:url value="/admin/home" var="HomeUrl"/>
<c:url value="/admin/product/readExcel" var="validateExcel"/>
<c:url value="/api/admin/product/importExcel" var="importExcel"/>
<html>
<head>
    <title>Import from Excel</title>
    <style>
       .table-scroll {
           height: 500px;
           overflow: auto;
           margin-top: 20px;

           margin-bottom: 20px;
        }

    </style>
</head>
<body>

<div class="container-fluid">
    <div class="card_hr row">
        <hr>
    </div>
    <div class="row">

        <div class="link_header">
            <i class="glyphicon glyphicon-hand-right"></i>
            <a href="${HomeUrl}"><spring:message  code="label.web.home"/></a>
            <span>/</span>
            <a href="${ProductUrl}"><spring:message code="label.list.product"/> </a>
            <span>/</span>
            <span><spring:message code="label.import.excel"/> </span>
        </div>

    </div>

</div>
<br>
<div class="container">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <c:if test="${not empty messageResponse}">
            <div class="alert alert-${alert} alert-dismissible" style="margin-top: 10px">
                <c:set var="idIamge" value="${model.id}"/>
                <a href="<c:url value="${idIamge}"/>" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <span>${messageResponse}</span>
            </div>
        </c:if>
    </div>
</div>
<br>
<br>
<br>
            <div class="container">
                <form action='${validateExcel}' method="POST" enctype="multipart/form-data"  id="formImport">
                    <div class="row">
                        <span class="file-preview-button" >
                                    <span class="btn btn-primary file-preview-shadow">
                                        <span >Add File Excel</span>
                                         <input id="uploadFile" type="file" name="file" accept=".xls,.xlsx" />
                                    </span>

                        </span>

                    </div>

                    <c:if test="${not empty model.importProductDTOS}">
                    <div class="table-scroll">
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <fmt:bundle basename="ApplicationResources">
                                        <display:table name="model.importProductDTOS" cellspacing="0" cellpadding="0" requestURI="${requestUrl}"
                                                       partialList="true" sort="external" size="${model.totalItems}" id="tableList" excludedParams="checkList"
                                                       pagesize="${model.maxPageItems}" export="false"
                                                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                                       style="margin: 3em 0 1.5em;">
                                            <display:column headerClass="text-left" property="nameProduct" titleKey="label.product.name"  />
                                            <display:column headerClass="text-left" property="cost" titleKey="label.list.product.sell"  />
                                            <display:column headerClass="text-left" property="typeProduct" titleKey="label.list.product.typename"  />
                                            <display:column headerClass="text-left" property="image" titleKey="label.list.product.images"    />
                                            <display:column headerClass="text-left" property="error" titleKey="label.message.error"/>
                                        </display:table>
                                    </fmt:bundle>
                                </div>
                            </div>
                        </div>
                    </div>
                        <button type="button" class="dt-button buttons-html5 btn btn-white btn-warning btn-bold" id="importData">
                            <spring:message code="label.import" />
                        </button>
                    </c:if>

                </form>
            </div>
<script>
    $(document).ready(function () {
        $('#uploadFile').change(function () {
            if($('#uploadFile').val()!=''){
                $('#formImport').submit();
            }
        });
    });
    $('#importData').click(function (e) {
        e.preventDefault();
            addProduct();
    });

    function  addProduct() {
        $.ajax({
            url: '${importExcel}',
            type: 'GET',
            success: function (res) {
                if(res){
                    window.location.href = "<c:url value='/admin/product/list?message=insert_success'/>";
                }
                else{
                    console.log(res);
                    window.location.href = "<c:url value='/admin/product/list?message=error_system'/>";
                }
            }
        });
    }
</script>

</body>
</html>
