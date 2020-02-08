<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 7/24/2019
  Time: 7:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-dialog modal-lg">
    <!-- Modal content-->
    <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <div class="titel_lo_re">
                        <span class="register_click" >Detail Bill</span>
                    </div>
            </div>
        <div class="modal-body">
        <div class="container">
            <div id="form_bill_detail" class="col-md-9 col-sm-9 col-9">
                <form  class="form-sigin">
                    <div class="row">
                        <div class="col-md-6 col-sm-6 col-6">
                            <div class="loginname">
                                <input  class="input_name_bill " type="text" value="${model.idUserEntity.email}" id="email" name="email"  placeholder="email" >
                                <span class="focus-input100">

                                </span>
                                <span class="symbol-input100">
                            <i class="glyphicon glyphicon-envelope"></i>

                                    <span>&nbsp;Email</span>

                            </span>

                                </span>

                            </div>

                        </div>
                        <div class="col-md-6 col-sm-6 col-6">
                            <div class="loginname">
                                <input  class="input_name_bill " type="text" value="${model.idUserEntity.nameFull}" id="idUser" name="idUser"  placeholder="idUser" >
                                <span class="focus-input100"></span>
                                <span class="symbol-input100">
                            <i class="glyphicon glyphicon-user"></i>
                                    <span>&nbsp;Name</span>

                            </span>

                            </div>

                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 col-sm-6 col-6">
                            <div class="loginname">
                                <input  class="input_name_bill " type="text" value="${model.phoneNumber}" id="phoneNumber" name="phoneNumber"  placeholder="phone Number" >
                                <span class="focus-input100">

                                </span>
                                <span class="symbol-input100">
                            <i class="glyphicon glyphicon-earphone"></i>

                                    <span>&nbsp;Phone</span>

                            </span>

                                </span>

                            </div>
                        </div>
                        <div class="col-md-6 col-sm-6 col-6">
                            <div class="loginname">
                                <input  class="input_name_bill " type="text" value="${model.place}" id="place" name="place"  placeholder="place" >
                                <span class="focus-input100">

                                </span>
                                <span class="symbol-input100">
                            <i class="glyphicon glyphicon-home"></i>

                                    <span>&nbsp;Place</span>

                            </span>

                                </span>

                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-md-6 col-sm-6 col-6">
                            <div class="loginname">
                                <input  class="input_name_bill" type="text" value="${model.dateTime}" id="dateTime" name="dateTime"  placeholder="date Time" >
                                <span class="focus-input100">

                                </span>
                                <span class="symbol-input100">
                            <i class="glyphicon glyphicon-calendar"></i>

                                    <span>&nbsp;&nbsp;Date </span>

                            </span>

                                </span>

                            </div>
                        </div>
                        <div class="col-md-6 col-sm-6 col-6">
                            <div class="loginname">
                                <c:if test="${model.status==1}">
                                    <input  class="input_name_bill " type="text" value="<spring:message key="label.list.bill.status.success"/>" id="status" name="place"  placeholder="place" >
                                </c:if>
                                <c:if test="${model.status==0}">

                                    <input  class="input_name_bill " type="text" value="<spring:message key="label.list.bill.status.danger"/>" id="status" name="place"  placeholder="place" >

                                </c:if>
                                <span class="focus-input100">

                                </span>
                                <span class="symbol-input100">
                            <i class="glyphicon glyphicon-shopping-cart">  </i>

                                    <span>&nbsp;Status</span>

                            </span>
                                </span>

                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 col-sm-6 col-6">
                            <div class="loginname">

                                <input  class="input_name_bill " type="text" value="<spring:formatNumber type = "number" pattern = "###,###" value = "${model.cost}" />" id="cost" name="cost"  placeholder="cost" >
                                <span class="focus-input100">

                                </span>
                                <span class="symbol-input100">
                            <i class="glyphicon glyphicon-usd"></i>

                                    <span>&nbsp;Money</span>

                            </span>

                                </span>

                            </div>
                        </div>
                    </div>

                    <input type="hidden" value="${model.idBill}" id="idBillDetail"/>

                    <div class="table_pro_load">
                        <table class="table table-striped" style="color: #4CAF50;">
                            <tr>
                                <th style="width: 180px;"><spring:message key="label.list.product.images"/> </th>
                                <th><spring:message key="label.product.name"/></th>
                                <th><spring:message key="label.list.bill.count"/></th>
                            </tr>
                            <c:forEach items="${items}" var="items">
                                    <tr>
                                        <td>
                                            <div class="img_pro_load">
                                                <c:set var="image" value="/repository/${items.idDBillProductEntity.image}"/>
                                                <img style="width: 90px" src="<c:url value='${image}'/>" alt="${items.idDBillProductEntity.image}">
                                            </div>
                                        </td>
                                        <td>
                                            <span>${items.idDBillProductEntity.nameProduct}</span>
                                        </td>
                                        <td>
                                                ${items.count}
                                        </td
                                    </tr>
                            </c:forEach>
                        </table>
                    </div>
                </form>
                <div class="modal-footer">
                    <button type="button" onclick="exportPdf()" id="exportPdf" class="btn-save-pro btn btn-warning"><spring:message key="label.btn.export.pdf"/></button>

                </div>

            </div>
        </div>
    </div>
    </div>
</div>

