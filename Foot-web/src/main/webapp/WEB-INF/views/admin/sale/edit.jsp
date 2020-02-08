<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 7/27/2019
  Time: 3:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/admin/sale/list" var="urlSaleList"/>
<div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
        <div  id="location_edituser">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <c:if test="${not empty model.idSale}">
                    <div class="titel_lo_re">
                        <span class="register_click" >Detail Sale</span>
                    </div>
                </c:if>
                <c:if test="${empty model.idSale}">
                    <div class="titel_lo_re">
                        <span class="register_click" >Add Sale</span>
                    </div>
                </c:if>
                <div id="nofiction" class="alert alert-warning alert-dismissible" style="display: none;">
                    <a href="${urlSaleList}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <span><spring:message code="label.uer.erorr.register2"/></span>
                </div>

            </div>


            <div id="form_register_user">
                <form  id="form_sale" class="form-sigin">
                    <div class="loginname">
                        <input  class="input_name " type="text" id="name" value="${model.name}"  name="name"  placeholder="Name" >
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
						<i class="glyphicon glyphicon-tag"></i>
						</span>

                    </div>
                    <div class="loginname">
                        <fmt:formatDate value="${model.timeStart}" var="timeStart" pattern="yyyy-MM-dd'T'hh:mm:ss"/>
                         <input   class="input_name" type="datetime-local" id="timeStart" name="timeStart" value="${timeStart}" placeholder="Time Start" >
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
						<i class="glyphicon glyphicon-time"></i>
						</span>


                    </div>

                    <div class="loginname">
                        <fmt:formatDate value="${model.timeEnd}" var="timeEnd" pattern="yyyy-MM-dd'T'hh:mm:ss"/>
                        <input   class="input_name" type="datetime-local" id="timeEnd" name="timeEnd" value="${timeEnd}" placeholder="Time End" >
                         <span class="focus-input100"></span>
                        <span class="symbol-input100">
						<i class="glyphicon glyphicon-time"></i>
						</span>
                    </div>

                    <div class="loginname">
                        <c:if test="${not empty model.idSale}">
                            <textarea style="height: 100px;"  class="input_name"   id="detail" name="detail" placeholder="Detail sale here..." rows="4" cols="50">${model.detail}</textarea>
                        </c:if>
                        <c:if test="${ empty model.idSale}">
                            <textarea style="height: 100px;"  class="input_name" id="detail" name="detail" placeholder="Detail sale here..." rows="4" cols="50"></textarea>
                        </c:if>
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
						<i class="glyphicon glyphicon-pencil"></i>
						</span>
                    </div>
                    <div class="loginname">
                        <input  style="padding-top: 12px;" class="input_name "  type="file"  name="image"  id="uploadImage" >
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
						<i class="glyphicon glyphicon-picture"></i>
						</span>
                    </div>
                    <div class="row">

                        <div class="col-md-6 col-sm-6 col-6">

                            <c:if test="${not empty model.image}">
                                <c:set var="image" value="/repository${model.image}"/>
                                <img src="<c:url value='${image}'/>" class="img-circle" id="viewImage" height="250px">

                            </c:if>
                            <c:if test="${empty model.image}">

                                <img src="<c:url value='/image/picture.jpg'/>" class="img-circle" id="viewImage" width="250px" height="250ox">

                            </c:if>
                        </div>

                    </div>


                    <input type="hidden" value="${model.idSale}" id="idSale" name="idSale">
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
<script>
    $(document).ready(function () {

        $('#uploadImage').change(function () {
            readUrl(this,'viewImage');

        });

    });
    function readUrl(input,imageId) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = (function (e) {
                $('#' + imageId).attr('src', reader.result);
            });

            reader.readAsDataURL(input.files[0]);

        }
    }
</script>