<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 6/26/2019
  Time: 2:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url value="/admin/home" var="HomeUrl"/>
<c:url value="/admin/product/list" var="ProductUrl"/>
<c:url  var="editProductUrl" value="/api/admin/product/edit"/>

<html>
<body>

<div class="card_hr row">
    <hr>
</div>

<div class="container-fluid">
    <div class="row">

        <div class="link_header">
            <i class="glyphicon glyphicon-hand-right"></i>
            <a href="${HomeUrl}"><spring:message  code="label.web.home"/></a>
            <span>/</span>
            <a href="${ProductUrl}"><spring:message code="label.list.product"/> </a>
            <span>/</span>
            <span><spring:message code="label.add"/> </span>
        </div>

    </div>

    </div>


<div class="container">
    <br>
    <br>
    <div class="row">
        <div id="nofiction" class="alert alert-warning alert-dismissible" style="display: none;">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <span><spring:message code="label.uer.erorr.register2"/></span>
            <br>
        </div>

    </div>
    <div id="form_product" class="col-md-12 col-sm-12 col-12">
        <form  class="form-sigin">
            <div class="row">
                <div class="col-md-6 col-sm-6 col-6">
                    <div class="loginname">
                        <input  class="input_name " type="text" id="nameproduct" value="${model.nameProduct}" name="nameProduct"  placeholder="Name Product" >
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
                            <i class="glyphicon glyphicon-tags"></i>
                    </span>

                    </div>

                </div>
                <div class="col-md-6 col-sm-6 col-6">
                    <div class="loginname">
                        <input  class="input_name " type="number" value="${model.cost}" id="cost" name="cost"  placeholder="Cost" >
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
                            <i class="glyphicon glyphicon-usd"></i>
                    </span>

                    </div>

                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-sm-6 col-6">
                    <div class="loginname">
                        <input  style="padding-top: 12px;" class="input_name "  type="file"  name="image"  id="uploadImage" >
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
                            <i class="glyphicon glyphicon-picture"></i>
                    </span>

                    </div>
                </div>
                <div class="col-md-6 col-sm-6 col-6">
                    <c:choose>
                        <c:when  test="${not empty model.idProduct}">
                            <select  id="typeDTOList" class="form-control input_name"   name="typeId">
                                <option value="${model.typeIdEntity.typeId}">${model.typeIdEntity.typeName}</option>
                                <c:forEach items="${model.typeDTOList}" var="itemType">
                                    <c:if test="${itemType.typeId!=model.typeIdEntity.typeId}">
                                        <option value="${itemType.typeId}">${itemType.typeName}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </c:when>
                        <c:otherwise>
                            <select  id="typeDTOList" class="form-control input_name"    name="typeId">
                                <option value=""><spring:message code="label.list.product.typename"/></option>
                                <c:forEach items="${model.typeDTOList}" var="itemType">
                                    <option value="${itemType.typeId}">${itemType.typeName}</option>
                                </c:forEach>
                            </select>
                        </c:otherwise>
                    </c:choose>

                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
                            <i style="margin-left: 15px;" class="glyphicon glyphicon-cutlery"></i>
                    </span>


                </div>

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
            <br>
            <div class="row">
                <div class="class_infor_pro">
                    <div class="col-md-12 col-sm-12 col-12">
                        <c:if test="${not empty model.information}">
                            <c:set var="information" value="${model.information}"/>
                        </c:if>
                        <textarea name="information" cols="120" rows="10" id="informationproduct">${information}</textarea>
                    </div>
                </div>
            </div>


            <input type="hidden" value="${model.idProduct}" id="idproduct" name="idProduct">



        </form>

        <button type="button" id="clickEdit" class="btn-save-pro btn btn-warning"><spring:message code="label.btn.save"/></button>

    </div>
</div>
<script>
    $(document).ready(function () {
       CKEDITOR.replace('informationproduct');

        $('#uploadImage').change(function () {
            readUrl(this,'viewImage');

        });
    });


    $('#clickEdit').click(function (e) {
        e.preventDefault();
        if(validateForm()===false){
            $('#nofiction').show();
        }else{
            $('#nofiction').hide();
            addOrUpdate();
        }
    });
    
    function addOrUpdate() {
        var dataArray = {};
        var files = $('#uploadImage')[0].files[0];
        var idProduct = $('#idproduct').val();
        dataArray["typeId"]=$('#typeDTOList').val();
        dataArray["nameProduct"] = $('#nameproduct').val();
        dataArray["idProduct"] = idProduct;
        dataArray["cost"] = $('#cost').val();
        dataArray["information"] = CKEDITOR.instances.informationproduct.getData();

        if (files != undefined) {
            dataArray["image"] = files.name;
            var reader = new FileReader();
            reader.onload = function (e) {
                dataArray["thumbnailBase64"] = e.target.result;
                if ($('#idproduct').val() !="") {
                    editProduct(dataArray, $('#idproduct').val());
                }
                else {
                    addProduct(dataArray);
                }
            };
            reader.readAsDataURL(files);
        } else {
            if ($('#idproduct').val() !="") {
                editProduct(dataArray, $('#idproduct').val());
            }
            else {
                addProduct(dataArray);
            }

        }
    }

    function  editProduct(data,id){
        $.ajax({
            url: '${editProductUrl}/'+id,
            type: 'PUT',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                if(res){
                    window.location.href = "<c:url value='/admin/product/list?message=update_success'/>";
                }
                else{
                    console.log(res);
                    window.location.href = "<c:url value='/admin/product/list?message=error_system'/>";
                }
            }
        });
    }

    function addProduct(data) {
        $.ajax({
            url: '${editProductUrl}',
            type: 'POST',
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function (res) {
                if(res){
                    window.location.href = "<c:url value='/admin/product/list?message=insert_success'/>";
                }
                else{
                    console.log(res);
                    window.location.href = "<c:url value='/admin/product/list?message=error_system'/>";
                }
            }
        });
    }


    function readUrl(input,imageId) {
        if(input.files && input.files[0]){
            var reader = new FileReader();
            reader.onload=(function (e) {
                $('#'+ imageId).attr('src',reader.result);
            });

            reader.readAsDataURL(input.files[0]);

        }
    }

    function validateForm(){
        if($('#nameproduct').val()=='' ||$('#typeDTOList').val()=='' || $('#cost').val()==''  ||  CKEDITOR.instances.informationproduct.getData()==='' || $('#cost').val()==''){
            if($('#nameproduct').val()=='' ){
                $('#nameproduct').css("border", "1px solid red");

            }else{
                $('#nameproduct').css("border", 'none');

            }

            if($('#cost').val()==''){
                $('#cost').css("border", "1px solid red");

            }else{
                $('#cost').css("border", 'none');

            }

            if($('#typeDTOList').val()==''){
                $('#typeDTOList').css("border", "1px solid red");

            }else{
                $('#typeDTOList').css("border", 'none');

            }
            if (CKEDITOR.instances.informationproduct.document.getBody().getChild(0).getText() ===  '') {
                alert('Editor value cannot be empty!') ;

            }
            return false;
        }else {

            return true;
        }
    }


</script>
</body>
</html>
