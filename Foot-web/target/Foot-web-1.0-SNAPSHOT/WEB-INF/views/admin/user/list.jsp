<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 5/31/2019
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="/admin/user/list" var="requestUrl"/>
<c:url var="showUserUrl" value="/api/admin/user/"/>
<c:url var="deleteUser" value="/api/admin/user/delete"/>
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
            <span><spring:message code="label.list.user"/> </span>
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
                    <input  class="input_search" type="text" name="email" placeholder="<spring:message code="label.user.email"/>" >
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
            <button  type="button" class="btn btn-warning button_select"  onclick='update(this)' title="Add"><i class="glyphicon glyphicon-plus-sign"></i></button>

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
                    <input type='checkbox' value="${tableList.idUser}" class='check-box-element' name="checkList" id="checkbox_${tableList.idUser}"/>
                </fieldset>
                </display:column>

                    <display:column property="idUser" titleKey="label.id" sortable="true" sortName="iduser"/>
                    <display:column property="nameFull" titleKey="label.list.user.fullname" sortable="true" sortName="namefull"/>
                    <display:column property="email" titleKey="label.user.email" sortable="true" sortName="email"/>
                    <display:column property="sex" titleKey="label.list.user.sex" sortable="true" sortName="sex"/>
                    <display:column property="place" titleKey="label.list.user.address" sortable="true" sortName="place"/>
                    <display:column property="phoneNumber" titleKey="label.list.user.phone" sortable="true" sortName="phonenumber"/>
                    <display:column headerClass="col-actions" titleKey="label.user.block" sortable="true" sortName="check">
                        <c:if test="${tableList.block==1 }">
                            <a data-toggle="tooltip" title="<spring:message code="label.user.block" /> " class="btn btn-danger">
                                <i class="glyphicon glyphicon-record"></i>
                            </a>
                        </c:if>
                        <c:if test="${tableList.block==0 }">
                            <a data-toggle="tooltip" title="<spring:message code="label.user.not.block" /> " class="btn btn-success">
                                <i class="glyphicon glyphicon-record"></i>
                            </a>
                        </c:if>
                    </display:column>
                    <display:column headerClass="col-actions" titleKey="label.action">


                        <a class="btn btn-sm btn-primary btn-edit" id_User="${tableList.idUser}" onclick="update(this)" data-toggle="tooltip" title="<spring:message code="label.user.update"  />">

                    <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>
                </a>
                </display:column>
                </display:table>
                </fmt:bundle>

        </div

    </form>

</div>
<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">

</div>

<jsp:include page="/functionJs/admin/user/list.jsp"/>

