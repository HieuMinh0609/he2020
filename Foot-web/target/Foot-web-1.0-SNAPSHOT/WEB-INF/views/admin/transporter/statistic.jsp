
<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 1/11/2020
  Time: 12:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="/common/taglib.jsp" %>

<c:url value="/admin/transporter/list" var="urltransporter"/>
<c:url value="/api/admin/show/statistic" var="urlShowStatistic"/>

<div class="modal-dialog modal-lg">
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
                <div id="nofiction" class="alert alert-warning alert-dismissible" style="display: none;">
                    <a href="${urltransporter}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
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
                <div class="title_statistic" class="container">
                    <span><spring:message code="label.manager.user"/></span>
                </div>
                <form  action="#" style="border: 1px solid #FF9800;padding: 10px;border-radius: 20px;" class="form-sigin">
                    <div class="row">
                        <div class="col-md-4 col-sm-4">
                        <div class="loginname">
                            <input   class="input_name" type="text" id="nameFull" name="nameFull" value="${model.idLoginUserEntity.nameFull}" placeholder="Name Full" >
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                            <i class="glyphicon glyphicon-user"></i>
                            </span>


                        </div>
                    </div>

                        <div class="col-md-4 col-sm-4">
                            <div class="loginname">
                                <input   class="input_name" type="text" id="place" name="place" value="${model.idLoginUserEntity.place}" placeholder="Place" >
                                <span class="focus-input100"></span>
                                <span class="symbol-input100">
						    <i class="glyphicon glyphicon-pencil"></i>
						    </span>
                            </div>
                        </div>

                        <div class="col-md-4 col-sm-4">
                            <div class="loginname">
                                <input   class="input_name" type="text"  id="email" name="email" value="${model.idLoginUserEntity.email}" placeholder="Email" >
                                <span class="focus-input100"></span>
                                <span class="symbol-input100">
						<i class="glyphicon glyphicon-envelope"></i>
						</span>
                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-md-4 col-sm-4">
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
                        </div>

                        <div class="col-md-4 col-sm-4">
                            <div class="loginname">
                                <input   class="input_name" type="text" id="phoneNumber" name="phoneNumber" value="${model.idLoginUserEntity.phoneNumber}" placeholder="Phone Number" >
                                <span class="focus-input100"></span>
                                <span class="symbol-input100">
						<i class="glyphicon glyphicon-earphone"></i>
						</span>
                            </div>
                        </div>
                    </div>

                </form>
                <div class="title_statistic" class="container">
                    <span><spring:message code="label.transporter.informaion"/></span>
                </div>
                <form  action="#" style="border: 1px solid #FF9800;padding: 10px;border-radius: 20px;" class="form-sigin">
                    <div class="row">
                        <div class="col-md-4 col-sm-4">
                            <div class="loginname">
                                <input   class="input_name" type="text" id="store" name="idStore" value="${items.storeLocationEntity.name}" placeholder="Store Name" >
                                <span class="focus-input100"></span>
                                <span class="symbol-input100">
						<i class="glyphicon glyphicon-home"></i>
                                </span>
                            </div>
                        </div>
                            <div class="col-md-4 col-sm-4">
                                <div class="loginname">
                                    <input   class="input_name" type="text" id="CountBill" name="CountBill" value="${items.countBill}" placeholder="Count Bill" >
                                    <span class="focus-input100"></span>
                                    <span class="symbol-input100">
						<i class="glyphicon glyphicon-calendar"></i>
						</span>
                                </div>
                            </div>

                            <div class="col-md-4 col-sm-4">
                                <div class="loginname">
                                    <c:if test="${items.ready ==1}">
                                        <input  class="input_name" type="text" id="ready" name="ready" value="<spring:message code="label.transporter.not.ready"/>" placeholder="Ready" >
                                    </c:if>
                                    <c:if test="${items.ready !=1}">
                                        <input  class="input_name" type="text" id="notready" name="ready" value="<spring:message code="label.transporter.ready"/>" placeholder="Ready" >
                                    </c:if>
                                    <span class="focus-input100"></span>
                                    <span class="symbol-input100">
                            <i class="glyphicon glyphicon-plane"></i>
                            </span>


                                </div>
                            </div>

                        </div>
                    <div class="row">
                        <div class="col-md-4 col-sm-4">
                            <div class="loginname">
                                <input  class="input_name" type="text" id="km" name="ready" value="<fmt:formatNumber type = "number" groupingUsed = "false" value = "${items.km}" />" placeholder="Ready" >

                                <span class="focus-input100"></span>
                                <span class="symbol-input100">
						    <i class="glyphicon glyphicon-road"></i>
						    </span>
                            </div>
                        </div>

                    </div>

                </form>
                <div class="title_statistic" class="container">
                <span><spring:message code="label.statistic"/></span>
                </div>
                <div class="container" style="width: 100%;">

                </div>
                <form action="#">
                <div  id="form_find_statistic" class="container">
                    <div class="row">
                   <div class="col-sm-3">
                        <div class="loginname">
                            <input style="padding-left: 110px;" id="date_infor"  class="input_name " type="text"    placeholder="Date" >
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                            <i class="glyphicon glyphicon-calendar"></i>
                             <span style="  margin-left: 5px;">Date</span>
                         </span>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="loginname">
                            <input  style="padding-left: 110px;" id="month_infor"  class="input_name " type="text"   placeholder="Month" >
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                    <i class="glyphicon glyphicon-calendar"></i>
                                <span style="  margin-left: 5px;">Month</span>
                                </span>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="loginname">
                            <input  style="padding-left: 110px;" id="year_infor"  class="input_name " type="text"   placeholder="Year" >
                            <span class="focus-input100"></span>
                            <span class="symbol-input100">
                                    <i class="glyphicon glyphicon-calendar"></i>
                                <span style="  margin-left: 5px;">Year</span>
                                </span>
                        </div>
                    </div>
                </div>
                    <div class="col-sm-12">
                        <a id="btn_show_statistic" style="margin-top: 10px;" class="btn btn-warning"><spring:message code="label.statistic"/></a>
                    </div>
                    <div class="row">
                        <table class="table table-hover" style="width: 500px;margin: 20px 0 20px 190px;">
                            <tbody id="table_statistic" >

                            </tbody>
                        </table>

                    </div>


                </div>

                </form>
    </div>
        </div>
</div>
</div>

<script>
    $( document ).ready(function() {
        var d = new Date();
        $('#date_infor').val(d.getDate());
        $('#month_infor').val(d.getMonth()+1);
        $('#year_infor').val(d.getFullYear());




        showStatisticByIdUser(d.getDate(),d.getMonth()+1,d.getFullYear());

    });

    $('#btn_show_statistic').on('click',function () {
        showStatisticByIdUser($('#date_infor').val(),$('#month_infor').val(),$('#year_infor').val());
    });

    function showStatisticByIdUser(date, number, fullYear) {
       var dataArray= {};
        dataArray["id"]= ${model.idLoginUserEntity.idUser};
        dataArray["day"]=  $('#date_infor').val();
        dataArray["month"]=  $('#month_infor').val();
        dataArray["year"]=  $('#year_infor').val();
        $.ajax({
            url: '${urlShowStatistic}',
            type: 'POST',
            data: JSON.stringify(dataArray),
            dataType:'json',
            contentType: "application/json",
            success: function (data) {
                if(data !=null){
                    var row = '';
                    var array =  $.map(data, function(value,key){
                        row+='<tr class="success"><td>'+key+'</td><td>'+value+'</td> </tr>';

                    });
                    $('#table_statistic').html(row);
                }
            },
            error: function (data) {

            }
        });

    }




</script>



