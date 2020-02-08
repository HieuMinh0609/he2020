<%@ taglib prefix="string" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 12/24/2019
  Time: 8:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/admin/home" var="HomeUrl"/>
<div class="card_hr row"  style="width: 101.1%;">
    <hr>
</div>
<div class="container-fluid">
    <div class="row">
        <div class="link_header">
            <i class="glyphicon glyphicon-hand-right"></i>
            <a href="${HomeUrl}"><spring:message  code="label.web.home"/></a>
            <span>/</span>
            <span><spring:message code="label.transporters.title"/></span>
            <span>/</span>
            <span><spring:message code="label.transporter.title"/></span>
        </div>
    </div>
</div>
<div class="container">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <c:if test="${not empty messageResponse}">
            <div class="alert alert-${alert} alert-dismissible" style="margin-top: 10px">
                <a href="${requestUrl}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <span>${messageResponse}</span>
            </div>
        </c:if>
    </div>
    <br>
    <div class="row" style="margin-right: 10px">
        <div class="title_bar_cart" style="width: 295px;margin-left: 12px;margin-top: 8px;">
            <a style="background: #8ece8e" id="btn_bill_not_trans" class="btn btn-default">Hóa đơn chưa giao</a>
            <a id="btn_bill_trans" style=" margin-left: 6px;" class="btn btn-default">Người vận chuyển</a>
          </div>
    </div>

    <div id="form_bill_not_trans" class="row">
        <div class="col-md-12">
            <div class="tilte_bill_no_sporter">
                <img  src="<c:url value="/image/Favorites_2.png"/>" alt="star">
                <span>Sản phẩm chưa được phân cho người vận chuyển</span>
            </div>
            <div class="form-action" >
                <button id="btn_tranformer" class="btn btn-success" >Chuyển giao cho người vận chuyển <i id="icon_tranfor" class="glyphicon glyphicon-share-alt" style="position: relative; float: right; color: #eab021"></i></button>
                <span id="sum_bill_not">${model} Hóa đơn</span>
            </div>
            <div id="form_not_bill" class="bill_no_transporter">
                <div  class="table_bill_no_trans"  style="color: #3f9e42;">
                    <table id="table_notBill" class="table table_backcolor table-bordered">
                        <tr>
                            <th><string:message key="label.action"/></th>
                            <th scope="col"><string:message key="label.id"/></th>
                            <th scope="col"><string:message key="label.bill.time"/></th>
                            <th scope="col"><string:message key="label.list.bill.sumsell"/></th>
                            <th scope="col" style="width: auto"><string:message key="label.list.user.address"/></th>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div id="form_bill_onTrans" style="display: none" class="row">
        <div class="col-md-12">
            <div  class="tilte_bill_no_sporter">
                <img  src="<c:url value='/image/door-delivery-png-4.png'/>" style="width: 5%" alt="star">
                <span>Thông tin người vận chuyển</span>
            </div>
            <div class="form-action" style="height: 120px;">
                <div class="row">

                    <div class="col-md-4 col-sm-4 col-4">
                        <select id="select_transporter" class="form-control input_name">
                            <option value="0">--Chọn người vận chuyển--</option>
                            <c:forEach var="item" items="${items}" >
                                <option value="${item.idUser}">${item.nameFull}</option>
                            </c:forEach>
                        </select>
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
                            <i style="margin-left: 15px;" class="glyphicon glyphicon-user"></i>
                        </span>
                    </div>
                </div>
                <div class="row" style="margin-right: 10px">
                    <div class="title_bar_cart" style="width: 371px;margin-left: 12px;margin-top: 8px;">
                        <a id="btn_strans_infor" class="btn btn-default">Đang giao</a>
                        <a id="btn_default_history" style=" margin-left: 6px;" class="btn btn-default">Đã giao</a>
                        <a id="btn_statistic" style=" margin-left: 6px;" class="btn btn-default"><spring:message code="label.statistic"/> </a>
                        <a id="map_trans" style=" margin-left: 6px; " class="btn btn-default"><i class="glyphicon glyphicon-map-marker" style="color: #fab431;margin-right: 5px;"></i><spring:message code="label.map"/></a>
                    </div>
                </div>


            </div>
            <div id="form_transporter_online"  class="bill_no_transporter">
                <table id="id_table_online_trans"  style="padding-top: 10px;color: #3f9e42;" class="table table_backcolor table-bordered">
                    <tr>
                        <th><string:message key="label.action"/></th>
                        <th scope="col"><string:message key="label.product.status"/></th>
                        <th scope="col"><string:message key="label.id"/></th>
                        <th scope="col"><string:message key="label.sale.time.start"/></th>
                        <th scope="col"><string:message key="label.sale.time.end"/></th>
                        <th scope="col"><string:message key="label.list.bill.sumsell"/></th>
                        <th scope="col" style="width: auto"><string:message key="label.list.user.address"/></th>
                    </tr>
                    <tbody id="body_table_trans"></tbody>
                </table>
            </div>
        </div>
    </div>

</div>
<div class="modal fade" id="showDetailBill" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <div class="titel_lo_re">
                    <span class="register_click" id="title_name"><spring:message code="label.list.product"/></span>
                </div>
            </div>
            <div class="modal-body">
                <table class="table table-striped" style="color: #4CAF50;">
                    <tr>
                        <th style="width: 180px;"><spring:message code="label.list.product.images"/> </th>
                        <th><spring:message code="label.product.name"/></th>
                        <th><spring:message code="label.list.bill.count"/></th>
                    </tr>
                    <tbody class="table_bill_show_cart">

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" id="showInformationTrans" role="dialog">

</div>

<jsp:include page="/functionJs/admin/transporter/list.jsp"/>
