<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 9/6/2019
  Time: 12:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="addBill" value="/api/user/order/add/bill/"/>
<html>
<head>
    <title>Title</title>
    <style>
        #menu_console{
            display: none;
        }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAI9kPkskayYti5ttrZL_UfBlL3OkMEbvs&libraries=places&sensor=false" async defer></script>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
    <script type="text/javascript" src='<c:url value="/template/web/jquery-3.4.1.min.js"/>'></script>

    <script type="text/javascript">
        google.maps.event.addDomListener(window,'load',intilize);

        function intilize(){
            var autocomplete=new google.maps.places.Autocomplete(document.getElementById('txtautocomplete'));

            google.maps.event.addListener(autocomplete,'place_changed', function() {

                var place =autocomplete.getPlace();
                var location= place.formatted_address +  "&ensp;<i style='font-size: 30px;' class='glyphicon glyphicon-saved'></i><br/>";

                $("#latitude").val(place.geometry.location.lat());
                $("#longitude").val(place.geometry.location.lng());

                document.getElementById('lblResult').innerHTML = location;
            });
        };
    </script>
</head>
<body>

<div class="container-fluid" style="margin-top: 35px;  padding-bottom: 20px;  ">

    <div class="title_product_load_cart" style="margin-bottom: 10px;">
        <c:set var="image" value="/repository/product/pngtree-vector-shopping-cart-icon-png-image_313450.jpg"/>
        <img src="<c:url value='${image}'/>" class="img-circle" id="viewImage"  style= " width: auto;">

        <span style="color:  #666666">Đặt Hàng</span>
    </div>

    <div class="col-md-12 col-sm-12 col-xs-12">
        <c:if test="${not empty messageResponse}">
            <div class="alert alert-${alert} alert-dismissible" style="margin-top: 10px">
                <a href="${requestUrl}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <span>${messageResponse}</span>
            </div>
        </c:if>
    </div>
    <div id="form_product" class="col-md-12 col-sm-12 col-12">
        <c:if test="${not empty items}">
        <div class="form_bill">
            <table class="place_choose">
                <c:set var="image_location" value="/repository/product/12345678-removebg-preview.png"/>
                <span id="title_bill_img" style="font-size: larger;color: #d65229;"><img src="<c:url value='${image_location}'/>">&ensp;Chọn địa chỉ giao hàng của bạn</span>
                <c:forEach var="items" items="${items}">
                <tr>
                    <td>
                        <input checked type="radio" name="bill_select" value="${items.idBill}">&ensp;${items.place}&ensp;-&ensp;${items.phoneNumber}<br>
                    </td>
                </tr>
                </c:forEach>
                <tr>
                    <td style="color: #da8405 !important;">
                        <input id="click_def_place" type="radio" name="bill_select" value="0">&ensp;Địa chỉ khác
                    </td>
                </tr>
            </table>
        </div>
        </c:if>
        <form class="form-sigin">
            <div class="row">
                <div class="col-md-6 col-sm-6 col-6">
                    <div class="loginname">
                        <input required readonly style="padding-left: 158px;" value="${model.nameProduct}" class="input_name " type="text"  name="nameProduct"  placeholder="Name Product" >
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
                            <i class="glyphicon glyphicon-tags"></i>
                            <span style="  margin-left: 5px;">Name Product</span>
                    </span>

                    </div>

                </div>
                <div class="col-md-6 col-sm-6 col-6">
                    <div class="loginname">
                        <input required readonly style="padding-left: 158px;" id="cost_pro_order" value=""  class="input_name " type="text"  name="cost"  placeholder="Cost" >
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
                            <i class="glyphicon glyphicon-usd"></i>
                             <span style="  margin-left: 5px;">Money</span>
                    </span>

                    </div>

                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-sm-6 col-6">
                    <div class="loginname">
                        <input required readonly style="padding-left: 158px;" type="text" name="quantity" value="${model.typeIdEntity.typeName}"  placeholder="Loại sản phẩm"  class="input_name">
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
                            <i class="glyphicon glyphicon-upload"></i>
                            <span style="  margin-left: 5px;">Type Product</span>
                    </span>

                    </div>
                </div>
                <div class="col-md-6 col-sm-6 col-6">
                    <div class="loginname">
                        <input required   style="padding-left: 158px;" type="number" id="amount_order" name="quantity" min="1" value="1"  class="input_name ">
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
                            <i  class="glyphicon glyphicon-cog"></i>
                            <span style="  margin-left: 5px;">Amount</span>
                    </span>


                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-sm-6 col-6">
                    <div class="loginname">
                        <input  disabled  style="padding-left: 158px;" type="text" name="phoneNumber" id="phone_number_order"  placeholder="Phone number"  class="input_name">
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
                            <i class="glyphicon glyphicon-phone"></i>
                            <span style="  margin-left: 5px;">Phone </span>
                    </span>

                    </div>
                </div>
                <div class="col-md-6 col-sm-6 col-6">
                    <div class="loginname">
                        <input disabled  style="padding-left: 158px;" type="text" name="place" id="place_order"  placeholder="Place"   class="input_name ">
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
                            <i  class="glyphicon glyphicon-home"></i>
                            <span style="  margin-left: 5px;">Place</span>
                    </span>


                    </div>
                </div>
            </div>
            <div class="row">

                <div class="col-md-6 col-sm-6 col-6">
                    <c:set var="image" value="/repository${model.image}"/>
                    <img src="<c:url value='${image}'/>" class="img-circle" id="viewImage" height="250px">
                </div>
                <div class="col-md-6 col-sm-6 col-6">
                    <div class="loginname">
                        <input  disabled style="padding-left: 158px;" type="text" name="location"  id="txtautocomplete"    class="input_name ">
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
                            <i  class="glyphicon glyphicon-map-marker"></i>
                            <span style="  margin-left: 5px;">Location</span>
                    </span>

                    </div>
                    <i id="innofortion" style="font-size: 13px;color: red;">(*Please,give us location address of you,this help us giving you the best service)
                    </i>
                    <br>
                    <label  style="color: #8ece8e" id="lblResult"></label>
                </div>

            </div>


            <input type="hidden" name="idUser">
            <input type="hidden" value="" name="latitude" id="latitude">
            <input type="hidden" value="" name="longitude" id="longitude">
        </form>

        <button type="button" id="btn_add_bill" class="btn btn-warning " style="padding: 10px 30px 10px 30px; float: right;">Đặt hàng</button>


     </div>
</div>
<script>

    $(document).ready(function () {

        $('input[name="bill_select"]:radio' ).on('change', function(e) {
             if(this.value==0){
                 $('#txtautocomplete').prop('disabled', false);
                 $('#place_order').prop('disabled', false);
                 $('#phone_number_order').prop('disabled', false);

             }else{
                 $('#txtautocomplete').prop('disabled', true);
                 $('#place_order').prop('disabled', true);
                 $('#phone_number_order').prop('disabled', true);

             }

        });


    });

    $(function () {

        $('#cost_pro_order').val(numberWithCommas(${model.cost})+" VND");

        if($('#lblResult').val()=='' || $('#lblResult').val()==null){
            $('#innofortion').css('display','block');
        }else{
            $('#innofortion').css('display','none');
        }
    });
    $('#btn_add_bill').on('click',function () {
        var dataArray={
            "id":$('input[name="bill_select"]:checked').val(),
            "phoneNumber":$('#phone_number_order').val(),
            "place":$('#place_order').val(),
            "cost":${model.cost},
            "count":$('#amount_order').val(),
            "latitude":$('#latitude').val(),
            "longitude":$('#longitude').val()
        };

        if(validateForm()){
            addBillFromUser(dataArray);
        }
     });
    function validateForm(){
        if(document.getElementById('click_def_place').checked){
            if($('#phone_number_order').val()=='' ||$('#place_order').val()=='' || $('#amount_order').val()==''|| $('#txtautocomplete').val()=='' ){
                if($('#phone_number_order').val()=='' ){
                    $('#phone_number_order').css("border", "1px solid red");

                }else{
                    $('#phone_number_order').css("border", 'none');

                }

                if($('#place_order').val()==''){
                    $('#place_order').css("border", "1px solid red");

                }else{
                    $('#place_order').css("border", 'none');
                }

                if($('#amount_order').val()==''){
                    $('#amount_order').css("border", "1px solid red");

                }else{
                    $('#amount_order').css("border", 'none');

                }
                if($('#txtautocomplete').val()==''){
                    $('#txtautocomplete').css("border", "1px solid red");

                }else{
                    $('#txtautocomplete').css("border", 'none');

                }
                return false;

            }else {

                return true;
            }
        }else{
            if($('#amount_order').val()==''){


                if($('#amount_order').val()==''){
                    $('#amount_order').css("border", "1px solid red");

                }else{
                    $('#amount_order').css("border", 'none');

                }
                return false;
            }else {

                return true;
            }
        }

    }
    function  addBillFromUser(dataArray) {
        $.ajax({
            url:'${addBill}'+${model.idProduct},
            type: 'POST',
            data: JSON.stringify(dataArray),
            dataType:'json',
            contentType: 'application/json',
            success: function (res) {
                if(res){
                    window.location.href = "/home-order-product-${model.idProduct}?message=insert_success";
                }else{
                    window.location.href = "/home-order-product-${model.idProduct}?message=error_system";
                }
            },
            error:function () {
                alert("wrong");
            }

        });
    }

    function numberWithCommas(x) {
        x = x.toString();
        var pattern = /(-?\d+)(\d{3})/;
        while (pattern.test(x))
            x = x.replace(pattern, "$1,$2");
        return x;
    }

</script>

</body>
</html>
