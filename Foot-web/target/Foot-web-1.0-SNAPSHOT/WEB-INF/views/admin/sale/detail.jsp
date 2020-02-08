<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 8/2/2019
  Time: 5:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/admin/home" var="HomeUrl"/>
<c:url value="/admin/sale/list" var="requestUrl"/>
<c:url value="/api/admin/sale/loadProduct" var="loadProduct"/>
<c:url value="/api/admin/sale/addDetail" var="addDetail"/>
<c:url value="/api/admin/sale/deleteDetail" var="deleteDetail"/>
<div class="container-fluid">
    <div class="card_hr row">
        <hr>
    </div>
    <div class="row">

        <div class="link_header">
            <i class="glyphicon glyphicon-hand-right"></i>
            <a href="${HomeUrl}"><spring:message  code="label.web.home"/></a>
            <span>/</span>
            <a href="${requestUrl}"><spring:message code="label.list.sale"/> </a>
            <span>/</span>
            <span><spring:message code="label.list.sale.product"/> </span>
        </div>

    </div>

</div>
<br>


<div class="container">
    <div class="container-fluid">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <c:if test="${not empty messageResponse}">
                <div class="alert alert-${alert} alert-dismissible" style="margin-top: 10px">
                    <c:set var="idSale" value="${model.id}"/>
                    <a href="<c:url value="/admin/saleDetail/${idSale}"/>" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <span>${messageResponse}</span>
                </div>
            </c:if>
        </div>
    </div>
    <br>
    <br>
    <br>

    <form>

        <div id="button_select" class="row">
            <button class="btn btn-danger button_select" type="button" id='deleteAll' disabled='false' onclick="waringBeforDelete()" title="Delete"><i class="glyphicon glyphicon-trash"></i></button>
            <a  onclick="openModelDetail()" type="button" class="btn btn-warning button_select" title="Add"><i class="glyphicon glyphicon-plus-sign"></i></a>
        </div>
        <div class="row">

            <fmt:bundle basename="bundles">
                <display:table id="tableList" name="model.listResult" cellspacing="0" cellpadding="0" partialList="true" size="${model.totalItems}"
                               pagesize="${model.maxPageItems}" sort="external"  requestURI="${requestUrl}"
                               class="table table_backcolor table-bordered"
                               style="margin: 3em 0 1.5em;">
                    <display:column title="<fieldset >
                                                            <input type='checkbox' class='check-box-element' id='checkAll'>
                                                            </fieldset>" class='center select-cell' headerClass="center select-cell">
                        <fieldset>
                            <input type='checkbox' value="${tableList.idDetailSale}" class='check-box-element' name="checkList" id="checkbox_${tableList.idDetailSale}"/>
                        </fieldset>
                    </display:column>


                    <display:column  >
                        <div class="row">
                            <div class="col-md-3">
                                <c:set var="images" value="/repository${tableList.idProductSaleEntity.image}"/>
                                <img style="height: 50px; width:80px; "  src="<c:url value='${images}'/>" class="img-thumbnail  " id="viewImage_${tableList.idDetailSale}" height="250px">
                            </div>
                            <div class="col-md-6">
                                 <span>${tableList.idProductSaleEntity.nameProduct}</span>
                            </div>
                            <div class="col-md-3">
                                <span>&darr;${tableList.downpercen}%</span>
                            </div>
                        </div>


                    </display:column>


                </display:table>

            </fmt:bundle>


        </div

    </form>

</div>

<div class="modal fade" id="myModalFile" role="dialog">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>

            </div>
            <div class="modal-body">
                <div class="container">
                    <div class="row">
                        <div class="col-md-3 col-sm-3 col-3">
                            <div class="loginname">
                                <input  class="input_name " type="text"  id="idProduct" name="idProduct"  placeholder="ID Product" >
                                <span class="focus-input100"></span>
                                <span class="symbol-input100">
                            <i class="glyphicon glyphicon-leaf"></i>
                                </span>

                            </div>


                        </div>
                        <div class="col-md-3 col-sm-3 col-3">
                            <div class="loginname">
                                <input  class="input_name " type="number"  id="downpercen" name="downpercen"  placeholder="Down percen" >
                                <span class="focus-input100"></span>
                                <span class="symbol-input100">
                            <i class="glyphicon glyphicon-arrow-down"></i>
                                </span>

                            </div>


                        </div>
                        </div>
                </div>
                </div>
                <span id="text-waring"></span>
                <table  class=" table table-striped file-preview-table" id="file-preview-table">
                    <tbody>

                    </tbody>
                </table>
                <div id="fieldHidden">
                </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-warning" id="btnUploadSale"><spring:message code="label.sale.add"/></button>
            </div>
        </div>
    </div>
            </div>



<script>
    $(document).ready(function () {
        $('#idProduct').keyup(function () {
          if($('#idProduct').val()!=''){
              var id=$('#idProduct').val();
              var downpercen=$('#downpercen').val();
              loadProduct(id,downpercen);
          }
        });

    });
    function  openModelDetail(){
            $('#myModalFile').modal();
    }

    function loadProduct(id,downpercen) {
        $.ajax({
            url: '${loadProduct}/' + id,
            type: 'GET',
            dataType: 'json',
            success: function (result) {
                if (result.nameProduct != null &&  result.message=="true") {



                        var row = '';
                        row += '<tr class="file-preview-row">';
                        row += '<td> <img style="width: 150px;" src="<c:url value="/repository'+result.image+'"/>"  class="file-preview-placeholder image"/></td>';
                        row += '<td id="nameProductSale"  class="filename " style="padding-right: 300px;"><span >' + result.nameProduct + '</span></td>';
                        row += '</tr>';
                        $('#file-preview-table tbody').html(row);
                    } else {
                        var row = '';
                        row += '<tr class="file-preview-row">';
                        row += '<td> <img  src="<c:url value='/image/loading.gif'/>"   class="file-preview-placeholder image"/></td>';
                        row += '</tr>';

                        if(result.message=="false"){
                            row += '<tr class="file-preview-row">';
                            row+= '<td><span style="color: #ff6b38; font-weight: bold ; padding-left: 10px" >Sản phẩm đang trong khuyến mãi</span></td>';
                            row += '</tr>'

                            $('#idProduct').val('');
                            $('#downpercen').val("");
                        }

                        $('#file-preview-table tbody').html(row);

                    }
                }


        });
    }

    $('#btnUploadSale').on('click',function (ex) {
        var dataArray={};
        dataArray["idSale"]=""+${model.id};
        dataArray["idDetailSale"]='';
        dataArray["idProduct"] = $('#idProduct').val();
        dataArray["downpercen"] = $('#downpercen').val();
        if(validateForm()==true){
            addSale(dataArray);
        }
    });
    function addSale(dataArray) {

        $.ajax({
            url: '${addDetail}/'+${model.id},
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(dataArray),
            success: function (res) {
                if(res){
                    window.location.href = "<c:url value='${model.id}?message=insert_success'/>";
                }
                else{
                    console.log(res);
                    window.location.href = "<c:url value='${model.id}?message=error_system'/>";
                }
            }
        });
    }

    function validateForm(){
        if($('#idProduct').val()=='' ||   $('#downpercen').val()=='' ||  $('#nameProductSale span').text()==''){
            if($('#idProduct').val()=='' ){
                $('#idProduct').css("border", "1px solid red");

            }else{
                $('#idProduct').css("border", 'none');

            }

            if($('#downpercen').val()==''){
                $('#downpercen').css("border", "1px solid red");

            }else{
                $('#downpercen').css("border", 'none');

            }
            return false;
        }else {

            return true;
        }
    }

    function waringBeforDelete() {
        showAlertBeforDelete(function () {
            event.preventDefault();
            var dataArray =$('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteSaleDetail(dataArray);
        });
    }

    function deleteSaleDetail(dataArray){
        $.ajax({
            url: '${deleteDetail}',
            type: 'DELETE',
            data: JSON.stringify(dataArray),
            dataType: 'json',
            contentType: "application/json",
            success: function (res) {
                if(res){
                    window.location.href = "${model.id}?message=delete_success";
                }else{
                    window.location.href = "${model.id}?message=error_system";
                }
            }
        });
    }



</script>
