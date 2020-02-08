<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 1/30/2020
  Time: 3:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/admin/configuration/list" var="requestUrl"/>
<c:url var="showConfigUrl" value="/api/admin/configuration/"/>
<c:url var="editConfigUrl" value="/api/admin/configuration/edit"/>

<c:url value="/admin/home" var="HomeUrl"/>
<div class="container-fluid">
    <div class="card_hr row">
        <hr>
    </div>
    <div class="row">

        <div class="link_header">
            <i class="glyphicon glyphicon-hand-right"></i>
            <a href="${HomeUrl}"><spring:message  code="label.web.home"/></a>
            <span>/</span>
            <span><spring:message code="label.configuration"/> </span>
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
                    <input  class="input_search" type="text" name="type" placeholder="<spring:message code="label.configuration.name"/>" >
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


        <div class="row">

            <fmt:bundle basename="bundles">
                <display:table id="tableList" name="model.listResult" cellspacing="0" cellpadding="0" partialList="true" size="${model.totalItems}"
                               pagesize="${model.maxPageItems}" sort="external"  requestURI="${requestUrl}"
                               class="table table_backcolor table-bordered"
                               style="margin: 3em 0 1.5em;">
                    <display:column property="key" titleKey="label.id" sortable="true" sortName="key"/>
                    <display:column property="type" titleKey="label.configuration.name" sortable="true" sortName="type"/>
                    <display:column property="value" titleKey="label.configuration.value" sortable="true" sortName="value"/>

                    <display:column headerClass="col-actions" titleKey="label.action">


                        <a class="btn btn-sm btn-primary btn-edit" id_Config="${tableList.key}" onclick="update(this)" data-toggle="tooltip" title="<spring:message code="label.user.update"  />">

                            <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>
                        </a>
                    </display:column>
                </display:table>
            </fmt:bundle>

        </div

    </form>

</div>
<!-- Modal -->
<div class="modal fade" id="myModalConfig" role="dialog">

</div>

<script>

    function update(btn) {
        var id=$(btn).attr('id_Config');
        var showUrl = '${showConfigUrl}'+id+'/';
        $('#myModalConfig').load(showUrl,'',function () {
            $('#myModalConfig').modal('toggle');
            editConfig();
        });
    }
    function editConfig(){
        $('#btnSave').click(function (e) {
            e.preventDefault();
                var dataArray = {};


                dataArray["key"] = $('#keyConfig').val();
                dataArray["type"] = $('#typeConfig').val();
                dataArray["name"] = $('#nameConfig').val();
                dataArray["value"] = $('#valueConfig').val();


                getValue(dataArray);
        });

        function getValue(dataArray) {
            $.ajax({
                url: '${editConfigUrl}',
                type: 'POST',
                data: JSON.stringify(dataArray),
                dataType:'json',
                contentType: "application/json",
                success: function (res) {
                    if(res){
                        window.location.href = "/admin/configuration/list?message=insert_success";
                    }else{
                        window.location.href = "/admin/configuration/list?message=error_system";
                    }
                }
            });

        }


    }
</script>
