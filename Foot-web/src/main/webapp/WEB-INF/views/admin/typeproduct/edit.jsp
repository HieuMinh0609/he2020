<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 1/15/2020
  Time: 10:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="urlUserList" value="/admin/user/list"/>
<c:url var="editUserUrl" value="/api/admin/user/edit"/>

<div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
        <div  id="location_edituser">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <c:if test="${not empty model.typeId}">
                    <div class="titel_lo_re">
                        <span class="register_click" >Detail Type</span>
                    </div>
                </c:if>
                <c:if test="${empty model.typeId}">
                    <div class="titel_lo_re">
                        <span class="register_click" >Add Type</span>
                    </div>
                </c:if>

            <div id="form_register_user">
                <form  action="#" method="POST" id="form_user" class="form-sigin">
                    <div class="loginname">
                        <input   class="input_name" required type="text" id="typeName" name="typeName" value="${model.typeName}" placeholder="Name Type" >
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
						<i class="glyphicon glyphicon-shopping-cart"></i>
						</span>
                    </div>
                    <input type="hidden" value="${model.typeId}" id="typeId" name="typeId">
                </form>
            </div>
            <div class="modal-footer">

                <div  class="buton_save" >
                    <button id="btnSave"  data-toggle="modal"
                            type="button"  class="login_btn_form">Save</button>
                </div>

            </div>
        </div>
    </div>
</div>
</div>