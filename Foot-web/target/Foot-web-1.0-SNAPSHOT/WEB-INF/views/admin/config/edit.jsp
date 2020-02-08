<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 1/30/2020
  Time: 3:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/admin/configuration/list" var="urlConfigList"/>
<div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
        <div  id="location_edituser">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <c:if test="${not empty model.key}">
                    <div class="titel_lo_re">
                        <span class="register_click" >Detail Configuration</span>
                    </div>
                </c:if>
                <div id="nofiction" class="alert alert-warning alert-dismissible" style="display: none;">
                    <a href="${urlConfigList}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <span><spring:message code="label.uer.erorr.register2"/></span>
                </div>
            </div>


            <div id="form_register_user">
                <form  id="form_sale" class="form-sigin">
                    <div class="loginname">
                        <input readonly class="input_name "  type="text" value="${model.type}"    name="type"  id="typeConfig" >
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
						<i class="glyphicon glyphicon-hdd"></i>
						</span>
                    </div>

                    <div class="loginname">
                            <textarea readonly style="height: 100px;"  class="input_name" id="nameConfig" name="name" placeholder="Detail config here..." rows="4" cols="50">${model.name}</textarea>
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
						<i class="glyphicon glyphicon-pencil"></i>
						</span>
                    </div>

                    <div class="loginname">
                        <input required   class="input_name" type="text" id="valueConfig" value="${model.value}"  name="value"  placeholder="value" >
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
						<i class="glyphicon glyphicon-tag"></i>
						</span>

                    </div>


                    <input type="hidden" value="${model.key}" id="keyConfig" name="key">
                </form>
            </div>
            <div class="modal-footer">

                <div  class="buton_save" >
                    <button id="btnSave"  data-toggle="modal"
                            type="button" class="login_btn_form">Save</button>
                </div>

            </div>
        </div>
    </div>
</div>
