
<%@ page import="com.hieuminh.utils.SecurityUtils" %>
<%@ include file="/common/taglib.jsp"%>
<c:url value="/admin/user/list" var="UserUrl"/>
<c:url value="/admin/product/list" var="ProductUrl"/>
<c:url value="/admin/bill/list" var="BillUrl"/>
<c:url value="/admin/sale/list" var="SaleUrl"/>
<c:url value="/admin/role/list" var="RoleUrl"/>
<c:url value="/admin/transporter/list" var="Transporter"/>
<c:url value="/api/admin/aDhome/countMessenger" var="countMessenger"/>
<c:url value="/api/admin/feedBack/showModel" var="showFeedBackUrl"/>
<c:url value="/logout" var="logOut"/>
<c:url value="/admin/typeproduct/list" var="TypeUrl"/>
<c:url value="/admin/configuration/list" var="ConfigurationUrl"/>
<c:url value="/admin/report/list" var="ReportUrl"/>


<%--
<c:url value="/userImage" var="setUser"/>
--%>

<div class="container-fluid">
    <div id="header_admin" class="row">
        <div id="set_size_img" class="col-md-3 col-sm-6 col-xs-6">
            <img   src='<c:url value="/image/logo.png"/>' alt="foot_fast">
        </div>
        <div   class="col-md-9 col-sm-6 col-xs-6">

            <a data-toggle="dropdown" href="">
                <div class="infor_user" title="Information">
                    <c:if test="<%=SecurityUtils.getPrincipal().getSex() ==1 %>">
                    <img class="img_user"  src= '<c:url value="/image/man.png"/>' alt="admin">
                </c:if>
                    <c:if test="<%=SecurityUtils.getPrincipal().getSex() ==0 %>">
                        <img class="img_user"  src= '<c:url value="/image/girl.png"/>' alt="admin">
                    </c:if>
                    <button class="name_user"><%=SecurityUtils.getPrincipal().getUsername()%></button>
                    <ul  class="dropdown-menu">
                        <li><a href="#">Office</a></li>
                        <li><a href="${logOut}"><i class="glyphicon glyphicon-off"></i> Logout</a></li>

                    </ul>
                </div>
            </a>

            <div class="email_icon" title="Email">
                <a id="seeFeedBack" onclick="showmodel()">
					<span class="messenge_icon">
						<i class="glyphicon glyphicon-envelope"></i>
					</span>
                </a>
            </div>
            <div  class="count_messenge">
                <span id="id_count_messenger">0</span>
            </div>

        </div>
    </div>

    <div class="card_hr row">
        <hr>
    </div>

    <div id="maneger_row" class="row">
        <div class="col-md-1 col-sm-3 col-xs-3">
            <div class="dropdown">
                <button id="button_product" class="btn btn_primary_button dropdown-toggle" type="button" data-toggle="dropdown">Product
                    <span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li><a href="${ProductUrl}">Product</a></li>
                    <li><a href="${SaleUrl}">Sale</a></li>
                    <li><a href="${TypeUrl}">Type</a></li>
                </ul>
            </div>

        </div>

        <div class="col-md-1  col-sm-3 col-xs-3">
             <div class="dropdown">
                <button class="btn btn_primary_button dropdown-toggle" type="button" data-toggle="dropdown">Members
                    <span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <li><a href="${UserUrl}">Member</a></li>
                    <li><a href="${RoleUrl}">Role</a></li>
                </ul>
            </div>
        </div>
        <div class="col-md-1  col-sm-3 col-xs-3">
            <a href="${BillUrl}"><button class="btn btn_primary_button" type="button">Invoice</button></a>
        </div>
        <div class="col-md-1  col-sm-3 col-xs-3">
            <a href="${Transporter}"><button class="btn btn_primary_button" type="button">Transporter</button></a>
        </div>
        <div class="col-md-1  col-sm-3 col-xs-3">
            <a href="${ConfigurationUrl}"><button class="btn btn_primary_button" type="button">Configuration</button></a>
        </div>
        <div class="col-md-1  col-sm-3 col-xs-3">
            <a href="${ReportUrl}"><button class="btn btn_primary_button" type="button">Report</button></a>
        </div>
    </div>


</div>

<div class="modal fade" id="myModalFeedBack" role="dialog">

</div>

<script>
    $(function() {
        $.ajax({
            url: '${countMessenger}',
            type: 'GET',
            success: function (data) {
                var row = data;
                $('#id_count_messenger').text(row);
            },
            error: function (err) {
                alert(Error);
            }
        });
    });

    function showmodel() {
        var showFeedBackUrl = '${showFeedBackUrl}';
        $('#myModalFeedBack').load(showFeedBackUrl,'',function () {
            $('#myModalFeedBack').modal('toggle');
        });

    };





</script>
