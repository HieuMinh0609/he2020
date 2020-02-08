<%@ page import="com.hieuminh.utils.SecurityUtils" %>


<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 6/4/2019
  Time: 2:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url value="/home" var="HomePage"/>


<c:url value="/api/wBHome/loadType" var="loadType"/>
<c:url value="/type-sale-page-1" var="sale"/>
<c:url value="/home-cart" var="cartLink"/>
<c:url value="/api/user/cart/count" var="cartCount"/>
<c:url value="/home-office" var="office"/>
<div class="container-fluid">

    <div id="header_admin" class="row">
        <a href="${HomePage}" title="home page">
        <div id="set_size_img" class="col-md-3 col-sm-6 col-xs-6">
            <c:set var="image" value="/repository/product/logo.png"/>
            <img src="<c:url value='${image}'/>"  alt="foot_fast">
        </div>
        </a>
        <form action="/find" method="get">
        <div class="col-md-6 col-md-12 col-xs-12" >

            <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="input_search_area">
                    <input  class="input_search" type="text" name="nameProduct" placeholder="Name Food" >
                    <span class="focus-input100"></span>
                    <div class="symbol-input100">

                        <i class="glyphicon glyphicon-search"></i>

                    </div>

                </div>
                <button type="submit" class="btn_sreach_homepage btn btn-success">Search</button>

            </div>


        </div>
        </form>
        <div   class="col-md-3 col-sm-12  col-xs-12">
<security:authorize access ="isAuthenticated()">
            <a data-toggle="dropdown" href="">
                <div class="infor_user" title="Information">

                    <c:if test="<%=SecurityUtils.getPrincipal().getSex() ==1 %>">
                        <img class="img_user"  src= '<c:url value="/image/man.png"/>' alt="1">
                    </c:if>
                    <c:if test="<%=SecurityUtils.getPrincipal().getSex() ==0 %>">
                        <img class="img_user"  src= '<c:url value="/image/girl.png"/>' alt="2">
                    </c:if>

                    <button class="name_user"><%=SecurityUtils.getPrincipal().getUsername()%></button>
                    <ul  class="dropdown-menu">
                        <li><a href="${office}"><i class="glyphicon glyphicon-user"></i> Office</a></li>
                        <li><a href="/logout"><i class="glyphicon glyphicon-off"></i> Logout</a></li>

                    </ul>
                </div>
            </a>
            <div class="email_icon" title="Cart">
                <a href="${cartLink}">
					<span class="messenge_icon">
						<i style="font-size: 28px;" class="glyphicon glyphicon-shopping-cart"></i>
					</span>
                </a>
                <div class="count_messenge">
                    <span id="count_bill_product"></span>
                </div>
            </div>


</security:authorize>
            <security:authorize access="isAnonymous()">
                <div class="btn_login_header">
                    <a href="/login">
                        <button class="btn btn-warning" >
                            Login/Register
                        </button>
                    </a>
                </div>
            </security:authorize>

        </div>
    </div>
    <div class=" card_hr row">
        <hr>
    </div>
    <div id="maneger_row" class="row">
        <div id="nav_menu" class="col-md-6">
            <div class="col-md-3  col-sm-3 col-xs-3">
                <a    href="${sale}"><button class="btn btn_primary_button" type="button">SALE</button></a>
            </div>
        </div>
    </div>
</div>
<div style="    margin-right: 0;" class="card_hr row">
    <hr>
</div>
<div class="container">
    <br>
    <div id="title_introduce" class="row">
        <div class="col-md-4 text-center">
            <c:set var="image_saveMoney" value="/repository/product/saveMoney.png"/>
            <img style="  margin-top: 10px;" src="<c:url value='${image_saveMoney}'/>" alt="save_money">
            <br>
            <span>Với dịch vụ của chúng tôi bạn có thể tiết kiệm tới 15% chi phí cho món ăn của bạn không phải mất chi phí đi lại  </span>
        </div>
        <div class="col-md-4 text-center">
            <c:set var="image_flash_moto" value="/repository/product/flash_moto.png"/>
             <img style="width: 35% ;  margin-top: 10px;" src="<c:url value='${image_flash_moto}'/>" alt="moto">
            <br>
            <span>Giao hàng nhanh đúng thời gian nhất đội vận chuyển luôn trong quá trình sẵn sàng với đơn hàng của bạn</span>
        </div>
        <div class="col-md-4 text-center">
            <c:set var="image_safe_food" value="/repository/product/safe_food.png"/>

            <img   src="<c:url value='${image_safe_food}'/>" alt="safe">
            <br>
            <span>Thực phẩm an toàn đã được chứng nhận bởi bộ y tế ,bạn sẽ không phải lo lắng về sản phẩm của chúng tôi</span>
        </div>
    </div>
</div>

<script>
    $(function () {
        if($('.name_user').text()!=''){
            $.ajax({
                url:'${cartCount}',
                type: 'GET',
                dataType: 'json',
                contentType: "application/json",
                success: function (res) {
                    var row = res;
                    $('#count_bill_product').text(res);
                },
                error:function (res) {
                    var row = res;
                    $('#count_bill_product').text(res);
                }

            });

        }

        $.ajax({
            url:'${loadType}',
            type: 'GET',
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                var row = '';
                $(data).each(function (index, items) {
                row+='<div class="col-md-3 col-sm-3 col-xs-3 ">';
                row+='<a href="type-food-'+items.typeId+'-page-1"><button  class="btn btn_primary_button" type="button">'+items.typeName+'</button></a>';
                row+='</div>';
                });
                $('#nav_menu').append(row);
            },
            error:function (data) {

            }

        });

    });
</script>