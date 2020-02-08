<%--
Created by IntelliJ IDEA.
User: hieu4
Date: 6/13/2019
Time: 7:53 AM
To change this template use File | Settings | File Templates.
--%>
<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url value="/api/admin/aDhome/pageHistory" var="HomeUrlHis"/>
<c:url value="/api/admin/aDhome/pageReport" var="HomeUrlRep"/>
<c:url value="/api/admin/adHome/showMemberOnline" var="showMemberUrl"/>
<c:url value="/admin/home" var="HomeUrl"/>
<c:url value="/api/admin/adHome/showRevenueOnDay" var="showRevenueOnDayUrl"/>
<div class="card_hr row" style="    margin-right: 0">
<hr>
</div>
<div class="container-fluid">
<div class="row">

    <div class="link_header">
        <i class="glyphicon glyphicon-hand-right"></i>
            <a href="${HomeUrl}"><spring:message  code="label.web.home"/></a>
        </div>

    </div>
</div>
<br><br>
<div class="container-fluid">

            <div class="col-lg-9 col-md-9">
                <br><br><br>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="col-lg-3">
                            <div class="box_report">
                                <div class="titel_infor_report">
                                    <i class="glyphicon glyphicon-user"></i>
                                    <div class="info_report">
                                        <span class="span1">${model.userDTOList.size()}</span>
                                        <br>
                                        <span>Member Online</span>
                                    </div>


                                </div>
                                <div class="detail_report">
                                    <span><a href="#" id="seeMemberOnline" >See detail</a></span>
                                    <i class="glyphicon glyphicon-log-in"></i>

                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3">

                            <div class="box_report " style="border: 1px solid #ff0000ad">
                                <div class="titel_infor_report" style="background: #ff0000ad">
                                    <i class="glyphicon glyphicon-usd"></i>
                                    <div class="info_report">
                                        <span id="revenue_onday" class="span2"></span>
                                        <br>
                                        <span>Revenue on day</span>
                                    </div>


                                </div>
                                <div class="detail_report">
                                    <span><a id="seeRevenueOnDay" href="#">See detail</a></span>
                                    <i  style="color: #ff0000ad" class="glyphicon glyphicon-log-in"></i>

                                </div>

                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="box_report"  style="border: 1px solid #008000a8">
                                <div class="titel_infor_report" style="background: #008000a8;">
                                    <i class="glyphicon glyphicon-plane"></i>
                                    <div class="info_report">
                                        <span class="span1">${model.onBillTransport.size()}</span>
                                        <br>
                                        <span>Transport Online</span>
                                    </div>
                                </div>
                                <div class="detail_report">
                                    <span><a href="#">See detail</a></span>
                                    <i style="color: #008000a8;" class="glyphicon glyphicon-log-in"></i>

                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <div class="box_report" style="border: 1px solid #ffb733">
                                <div class="titel_infor_report" style="background:#ffa500cc;">
                                    <i class="glyphicon glyphicon-tasks"></i>
                                    <div class="info_report">
                                        <span class="span1">${model.billOnNew.size()}</span>
                                        <br>
                                        <span>Bill on new</span>
                                    </div>


                                </div>
                                <div class="detail_report">
                                    <span><a id="click_billOnNew" href="#">See detail</a></span>
                                    <i style="color:#ffa500cc;" class="glyphicon glyphicon-log-in"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    </div>
                <br><br><br>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="report_TypeSale">
                                <canvas id="myChart_on7day"></canvas>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div  class="report_TypeSale" >
                            <canvas id="myChart_revenue"></canvas>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div id="id_report_inYear" class="report_TypeSale" style="display: none">
                            <div id="donutchart" style="width: 600px; height: 400px;"></div>
                            </div>
                        </div>
                        <img id="loading_gif" style="width: 40px;margin-left: 450px; padding-top: 10px;" src="/image/loading.gif" alt="loading">
                        <div class="col-md-12">
                        <div class="report_TypeSale">
                            <div id="chart_div" style="display: none; height: 350px;"></div>

                        </div>
                    </div>

                    </div>
            </div>
            <div class="col-lg-3 col-md-3">
                 <div class="row">

                             <div class="title_crollView">
                                 <i style="font-size: 25px;padding-top: 10px; margin-right: 10px;" class="glyphicon glyphicon-edit">
                                 </i>
                                 <h2>History</h2>
                             </div>
                 </div>
                        <div class="col-md-12 col-lg-12" style="padding-right: 0px;">
                            <div id="id_scroll_his" class="right-box-content">
                               <ul class="list-history" style=" width: auto; height: 400px;">

                               </ul>
                            </div>
                        </div>
                 </div>
</div>
<div class="modal fade" id="myModalMemberOnLine" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <div class="titel_lo_re">
                    <span class="register_click" >Member Online</span>
                </div>
            </div>
            <div class="modal-body">

                    <div id="memberOnline_list">
                        <ul class="list-memeber-online">

                        </ul>
                    </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModalRevenueOnDay" role="dialog">
    <div class="modal-dialog ">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <div class="titel_lo_re">
                    <span class="register_click" >Revenue On Day</span>
                </div>
            </div>
            <div class="modal-body">
                    <div id="RevenueOnDay_list">
                        <ul class="list-RevenueOnDay">

                        </ul>
                    </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModalBillNew" role="dialog">

</div>

<jsp:include page="/functionJs/admin/home/home.jsp"/>


