<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 8/23/2019
  Time: 9:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url var="deleteBill" value="/api/user/web/cart/delete"/>
<c:url var="detailBill" value="/api/user/web/cart/detail"/>
<c:url var="mapBill" value="/api/user/web/cart/map"/>


<style>
    #menu_console{
        display: none;
    }
    #map {
         height: 500px;  /* The height is 400 pixels */
         width:860px;  /* The width is the width of the web page */
     }

</style>
<br><br>
<div  class="col-md-12">
    <div class="title_bar_cart">
        <div id="btn_default_cart_online" style="background:#8ece8e " class="btn btn-default">Online transporter</div>
        <div id="btn_default_cart_Processing" style=" margin-left: 20px;" class="btn btn-default">Processing product</div>
    </div>
    <div id="area_online_cart">
    <div class="title_product_load_cart">
        <c:set var="image1" value="/repository/product/door-delivery-png-4.png"/>
        <img  src="<c:url value='${image1}'/>" alt="star">
        <span><spring:message key="label.transporter.online"/></span>
    </div>
    <div class="table_pro_load">
        <table class="table table-striped" style="color: #4CAF50;">
            <tr>
                <th><spring:message key="label.id"/></th>
                <th><spring:message key="label.list.bill.sumsell"/></th>
                <th><spring:message key="label.time.go"/></th>
                <th><spring:message key="label.so.far"/></th>
                <th><spring:message key="label.action"/></th>
            </tr>
            <c:forEach items="${model}" var="items">
                <c:if test="${items.online==1}">
                    <tr>
                        <td>
                            ${items.idBill}
                        </td>
                        <td>
                            <spring:formatNumber type = "number" pattern = "###,###" value = "${items.cost}" />  VND
                        </td>
                        <td>
                                <spring:formatNumber type = "number" groupingUsed = "false" value = "${items.time}" />(Phút)
                        </td>
                        <td>
                            <spring:formatNumber type = "number" groupingUsed = "false" value = " ${items.distance}" />(Km)
                        </td>
                        <td>
                            <a urc_seemap_id="${items.idBill}" id="see_map_${items.idBill}" onclick="seeMapBill(this)" class="btn btn-warning">See map</a>
                            <a onclick="detailBillCart(this)" urc_idBill_detail="${items.idBill}"  id="bill_process_${items.idBill}" class="btn btn-default"><spring:message key="label.list.bill.detail"/></a>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
    </div>
    <div id="area_load_cart" style="display: none">
    <div class="title_product_load_cart">
        <c:set var="image2" value="/repository/product/401143.png"/>
        <img  src="<c:url value='${image2}'/>" alt="star">
        <span><spring:message key="label.transporter.process"/></span>
    </div>
    <div class="table_pro_load">
        <table class="table table-striped" style="color: #4CAF50;">
            <tr>
                <th><spring:message key="label.id"/></th>
                <th><spring:message key="label.bill.time"/></th>
                <th><spring:message key="label.list.bill.sumsell"/></th>
                <th><spring:message key="label.action"/></th>
            </tr>
            <c:forEach items="${model}" var="items">
            <c:if test="${items.online==0}">
            <tr>
                <td>
                     ${items.idBill}
                </td>
                <td>
                    <spring:formatDate type="both" dateStyle="short" timeStyle="short"   value = "${items.dateTime}" />
                </td>
                <td>
                    <spring:formatNumber type = "number" pattern = "###,###" value = "${items.cost}" />  VND
                </td>

                <td>
                    <a onclick="deleteBillCart(this)" urc_idBill="${items.idBill}" class="btn btn-warning"><spring:message key="label.cart.delete"/></a>
                    <a  onclick="detailBillCart(this)" urc_idBill_detail="${items.idBill}"  id="bill_his_${items.idBill}" class="btn btn-default"><spring:message key="label.list.bill.detail"/></a>
                </td>

            </tr>
            </c:if>
            </c:forEach>
        </table>
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
                    <span class="register_click" id="title_name"><spring:message key="label.list.product"/></span>
                </div>
            </div>
            <div class="modal-body">
                <table class="table table-striped" style="color: #4CAF50;">
                    <tr>
                        <th style="width: 180px;"><spring:message key="label.list.product.images"/> </th>
                        <th><spring:message key="label.product.name"/></th>
                        <th><spring:message key="label.list.bill.count"/></th>
                    </tr>
                    <tbody class="table_bill_show_cart">


                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="showMapBill" role="dialog">
    <div class="modal-dialog modal-lg">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>

            </div>
            <div class="modal-body">
                <div id="map"></div>
            </div>
        </div>
    </div>
</div>

<script>

    $(function () {
       $('#btn_default_cart_online').click(function () {
           $('#btn_default_cart_online').css('background','#8ece8e');
           $('#btn_default_cart_Processing').css('background','white');

           $('#area_online_cart').css('display','block');
           $('#area_load_cart').css('display','none');
       });
        $('#btn_default_cart_Processing').click(function () {
            $('#btn_default_cart_Processing').css('background','#8ece8e');
            $('#btn_default_cart_online').css('background','white');
            $('#area_online_cart').css('display','none');
            $('#area_load_cart').css('display','block');
        });

    });


    function deleteBillCart(btn) {
        var idBill=$(btn).attr('urc_idBill');
        deleteBill(idBill);

    }

    function seeMapBill(btn) {
        var idBill=$(btn).attr('urc_seemap_id');
        $('#showMapBill').modal('toggle');
            MapBill(idBill);
    }
    function MapBill(id) {
        var dataArray={};
        dataArray["id"]=id;
        $.ajax({
            url: '${mapBill}',
            type: 'POST',
            data:JSON.stringify(dataArray) ,
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {

                initMap(data.latitude,data.longitude);
            },
            error:function (data) {
                alert("have error ,map fail!");
            }
        });
    }

     function detailBillCart(btn) {
         var idBill=$(btn).attr('urc_idBill_detail');
         $('#showDetailBill').modal('toggle');
         detailBill(idBill);
     }
    function  detailBill(idBill) {
        var dataArray={};
        dataArray["id"]=idBill;
        $.ajax({
            url: '${detailBill}',
            type: 'POST',
            data:JSON.stringify(dataArray) ,
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                var row = '';
                $(data).each(function (index, items) {
                    row+='<tr>';
                    row+='<td>';
                    row+='<div class="img_pro_load" style=" height: 60px;">';
                    row+='<img style="width: 90px" src="<c:url value="/repository'+items.idDBillProductEntity.image+'"/>" alt="'+items.idDBillProductEntity.image+'">';
                    row+='</div>';
                    row+='<td>'+items.idDBillProductEntity.nameProduct+'</td>';
                    row+='<td>'+items.count+'</td>';
                    row+='</tr>';
                });
                $('.table_bill_show_cart').html(row);
            },
            error:function (data) {
                alert("have error ,detail fail!");
            }
        });
    }

    function initMap(lat,longi) {
        // The location of Uluru
        var uluru = {lat: Number(lat), lng: Number(longi)};
        // The map, centered at Uluru
        var map = new google.maps.Map(
            document.getElementById('map'), {zoom: 18 , center: uluru});
        // The marker, positioned at
        var image= '<c:url value="/image/12345678-removebg-preview.png"/>';
        var marker = new google.maps.Marker({
            position: uluru,
            map: map,
            icon:image,
            animation:  google.maps.Animation.BOUNCE,
            visible: true,
            flat: true,
            optimized: false
        });

    }



    function deleteBill(idBill) {
        var dataArray={};
        dataArray["idBill"]=idBill;
        $.ajax({
            url: '${deleteBill}',
            type: 'POST',
            data:JSON.stringify(dataArray) ,
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                alert("delete success!");
                window.location.href = "<c:url value='/home-cart'/>";
            },
            error:function (data) {
                alert("have error ,dalete fail!");
            }
        });
    }
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAI9kPkskayYti5ttrZL_UfBlL3OkMEbvs&callback=initMap&language=vi"
        async defer></script>