<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 6/15/2019
  Time: 9:45 PM
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
                <c:if test="${not empty model.idLoginUserEntity.idUser}">
                    <div class="titel_lo_re">
                        <span class="register_click" >Detail User</span>
                    </div>
                </c:if>
                <c:if test="${empty model.idLoginUserEntity.idUser}">
                    <div class="titel_lo_re">
                        <span class="register_click" >Add User</span>
                    </div>
                </c:if>
                <div id="nofiction" class="alert alert-warning alert-dismissible" style="display: none;">
                    <a href="${urlUserList}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <span><spring:message code="label.message.error.register"/></span>
                    <br>
                    <span><spring:message code="label.uer.erorr.register2"/></span>
                </div>
                <div id="nofiction" class="alert alert-warning alert-dismissible" style="display: none;">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <span><spring:message code="label.message.error.register"/></span>
                    <br>
                    <span><spring:message code="label.uer.erorr.register2"/></span>
                </div>
            </div>
    <div id="form_register_user">
        <form  action="${editUserUrl}" method="POST" id="form_user" class="form-sigin">
            <div class="loginname">
                <input  class="input_name " type="text" id="nameLogin" value="${model.nameLogin}"  name="nameLogin"  placeholder="Name Login" >
                <span class="focus-input100"></span>
                <span class="symbol-input100">
						<i class="glyphicon glyphicon-user"></i>
						</span>

            </div>
            <div class="loginname">
                <input   class="input_name" type="text" id="nameFull" name="nameFull" value="${model.idLoginUserEntity.nameFull}" placeholder="Name Full" >
                <span class="focus-input100"></span>
                <span class="symbol-input100">
						<i class="glyphicon glyphicon-user"></i>
						</span>


            </div>

                 <div class="loginname">
                    <input   class="input_name" type="text" id="passWord" name="passWord" value="${model.passWord}" placeholder="Password" >
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
						<i class="glyphicon glyphicon-lock"></i>
						</span>
                </div>

            <div class="loginname">
                <input   class="input_name" type="text"  id="email" name="email" value="${model.idLoginUserEntity.email}" placeholder="Email" >
                <span class="focus-input100"></span>
                <span class="symbol-input100">
						<i class="glyphicon glyphicon-envelope"></i>
						</span>


            </div>
            <div class="loginname">
                <input   class="input_name" type="text" id="place" name="place" value="${model.idLoginUserEntity.place}" placeholder="Place" >
                <span class="focus-input100"></span>
                <span class="symbol-input100">
						<i class="glyphicon glyphicon-pencil"></i>
						</span>


            </div>


            <div class="loginname">
                <input   class="input_name" type="text" id="phoneNumber" name="phoneNumber" value="${model.idLoginUserEntity.phoneNumber}" placeholder="Phone Number" >
                <span class="focus-input100"></span>
                <span class="symbol-input100">
						<i class="glyphicon glyphicon-earphone"></i>
						</span>


            </div>
            <div class="radio">


            <c:choose>
                <c:when test="${not empty model.idLoginUserEntity.sex}">

                    <c:if test="${model.idLoginUserEntity.sex==0}">
                        <label><input name="sex" type="radio" value="1"/>Male</label>
                        <label><input name="sex" type="radio" checked="checked" value="0"/>Femail</label>
                    </c:if>
                    <c:if test="${model.idLoginUserEntity.sex==1}">
                    <label><input name="sex"  checked="checked" type="radio" value="1"/>Male</label>
                      <label><input name="sex" type="radio" value="0"/>Female</label>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <label><input type="radio" value="1" name="sex" checked>Male</label>
                    <label><input type="radio" value="0"  name="sex">Female</label>
                </c:otherwise>
            </c:choose>
            </div>
            <div class="radio">

                <c:choose>
                    <c:when test="${not empty model.idLoginUserEntity.block}">
                        <c:if test="${model.idLoginUserEntity.block==1}">
                            <label><input name="block" type="radio" checked="checked" value="1"/>BLOCK</label>
                            <label><input name="block" type="radio"  value="0"/>NOT BLOCK</label>
                        </c:if>
                        <c:if test="${model.idLoginUserEntity.block==0}">
                            <label><input name="block" type="radio" value="1"/>BLOCK</label>
                            <label><input name="block"   checked="checked" type="radio" value="0"/>NOT BLOCK</label>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                            <label><input name="block" hidden  type="radio" value="1"/>BLOCK</label>
                            <label><input name="block" hidden checked="checked" type="radio" value="0"/>NOT BLOCK</label>

                    </c:otherwise>
                </c:choose>

            </div>
            <c:if test="${not empty model.idLoginUserEntity.idUser}">
                <div class="radio" style="margin-top: 10px">
                    <c:choose>
                        <c:when test="${not empty model.idLoginUserEntity.idRoleEntity.idRole}">
                            <c:forEach items="${items}" var="items">
                                 <c:if test="${items.idRole == model.idLoginUserEntity.idRoleEntity.idRole}">
                                     <label><input type="radio" checked value="${items.idRole}" name="role">${items.roleName}</label>
                                 </c:if>
                                <c:if test="${items.idRole != model.idLoginUserEntity.idRoleEntity.idRole}">
                                    <label><input type="radio"  value="${items.idRole}" name="role">${items.roleName}</label>
                                </c:if>
                             </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${items}" var="items">
                                <label><input type="radio" value="${items.idRole}" name="role">${items.roleName}</label>
                            </c:forEach>

                        </c:otherwise>
                    </c:choose>
                </div>
            </c:if>
            <c:if test="${empty model.idLoginUserEntity.idUser}">
            <div class="radio" style="margin-top: 10px">
                <c:forEach items="${items}" var="items" varStatus="loop">
                    <c:if test="${loop.index ==0}">
                        <label><input type="radio" checked value="${items.idRole}" name="role">${items.roleName}</label>
                    </c:if>
                    <c:if test="${loop.index!=0}">
                        <label><input type="radio" value="${items.idRole}" name="role">${items.roleName}</label>
                    </c:if>

                </c:forEach>
            </div>
            </c:if>
            <input type="hidden" value="${model.idLoginUserEntity.idUser}" id="idUser" name="idUser">
        </form>
    </div>
    <div class="modal-footer">
        <div  class="buton_save" >
            <button id="btnSave"  data-toggle="modal"
                    type="button" onclick="return validateForm()" class="login_btn_form">Save</button>
         </div>

    </div>
</div>
    </div>
</div>

