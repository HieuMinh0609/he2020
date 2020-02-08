<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 7/27/2019
  Time: 7:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/admin/sale/list" var="requestUrl"/>
<c:url value="/admin/home" var="HomeUrl"/>
<c:url value="/api/admin/sale/" var="AddSale"/>
<c:url value="/api/admin/sale/edit" var="editSaleUrl"/>
<c:url value="/api/admin/sale/delete" var="deleteSale"/>
<c:url value="/admin/saleDetail/" var="addSaleDetail"/>

<div class="card_hr row">
    <hr>
</div>
<div class="container-fluid">
    <div class="row">

        <div class="link_header">
            <i class="glyphicon glyphicon-hand-right"></i>
            <a href="${HomeUrl}"><spring:message  code="label.web.home"/></a>
            <span>/</span>
            <span><spring:message code="label.sale"/> </span>
        </div>

    </div>
</div>
<div class="container">

    <form method="GET"  action="${requestUrl}">
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <c:if test="${not empty messageResponse}">
                    <div class="alert alert-${alert} alert-dismissible" style="margin-top: 10px">
                        <a href="${requestUrl}" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <span>${messageResponse}</span>
                    </div>
                </c:if>
            </div>
            <div class="col-md-10 col-sm-10 col-xs-10">
                <div class="input_search_area">
                    <input  class="input_search" type="text" name="name" placeholder="<spring:message code="label.sale.name"/>" >
                    <span class="focus-input100"></span>
                    <div class="symbol-input100">

                        <i class="glyphicon glyphicon-search"></i>

                    </div>

                </div>

            </div>
            <div class="col-md-2 col-sm-2 col-xs-2">
                <button type="submit" class="btn_sreach btn btn-success">Search</button>
            </div>
        </div>
    </form>
    <form>

        <div id="button_select" class="row">
            <button class="btn btn-danger button_select" type="button" id='deleteAll' disabled='false' onclick="waringBeforDelete()" title="Delete"><i class="glyphicon glyphicon-trash"></i></button>
            <a onclick="update(this)" type="button" class="btn btn-warning button_select" title="Add"><i class="glyphicon glyphicon-plus-sign"></i></a>
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
                            <input type='checkbox' value="${tableList.idSale}" class='check-box-element' name="checkList" id="checkbox_${tableList.idSale}"/>
                        </fieldset>
                    </display:column>

                    <display:column property="idSale" titleKey="label.id" sortable="true" sortName="idSale"/>
                    <display:column property="name" titleKey="label.sale.name" sortable="true" sortName="name"/>
                    <display:column property="timeStart" titleKey="label.sale.time.start" sortable="true" sortName="timeStart"/>
                    <display:column property="timeEnd" titleKey="label.sale.time.end" sortable="true" sortName="timeEnd"/>
                     <display:column headerClass="col-actions" titleKey="label.action">
                        <a class="btn btn-sm btn-primary btn-edit" id_Sale="${tableList.idSale}"  onclick="update(this)" data-toggle="tooltip" title="<spring:message code="label.list.bill.detail"/>">
                            <i class="glyphicon glyphicon-pencil" aria-hidden="true"></i>
                        </a>
                        <a class="btn btn-sm btn-warning btn-edit"  href="${addSaleDetail}${tableList.idSale}" data-toggle="tooltip" title="<spring:message code="label.add.sale.product"/>">
                            <i class="glyphicon glyphicon-edit" aria-hidden="true"></i>
                        </a>
                    </display:column>
                </display:table>
            </fmt:bundle>
        </div

    </form>

</div>
<!-- Modal -->
<div class="modal fade" id="myModalSale" role="dialog">

</div>

<script>


    function update(btn) {
        var id_Sale=$(btn).attr('id_Sale');
        var showUrl = '${AddSale}'+id_Sale;
        if( typeof  id_Sale== 'undefined'){
            showUrl='${AddSale}';
        }

        $('#myModalSale').load(showUrl,'',function () {
            $('#myModalSale').modal();
            edirOrAdd();
        });
    }

    function edirOrAdd(){
        $('#btnSave').click(function (e) {
            e.preventDefault();
            if(validateForm()===false){
                $('#nofiction').show();
            }else{
                $('#nofiction').hide();
                var dataArray = {};

                var files = $('#uploadImage')[0].files[0];
                var id_Sale = $('#idSale').val();
                dataArray["name"] = $('#name').val();
                dataArray["idSale"] = id_Sale;
                dataArray["dateTimeEnd"] = $('#timeEnd').val();
                dataArray["dateTimeStart"] = $('#timeStart').val();
                dataArray["detail"] = $('#detail').val();

                if (files != undefined) {
                    dataArray["image"] = files.name;
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        dataArray["thumbnailBase64"] = e.target.result;
                        if ($('#idSale').val() !="") {
                            editSale(dataArray, $('#idSale').val());
                        }
                        else {
                            addSale(dataArray);
                        }
                    };
                    reader.readAsDataURL(files);
                } else {
                    if ($('#idSale').val() !="") {
                        editSale(dataArray,$('#idSale').val());
                    }
                    else {
                        addSale(dataArray);
                    }

                }

            }


        });




    }

    function  editSale(data,id){
        $.ajax({
            url: '${editSaleUrl}/'+id,
            type: 'PUT',
            dataType: 'json',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (res) {
                if(res){
                    window.location.href = "<c:url value='/admin/sale/list?message=update_success'/>";
                }
                else{
                    console.log(res);
                    window.location.href = "<c:url value='/admin/sale/list?message=error_system'/>";
                }
            }
        });
    }

    function addSale(data) {
        $.ajax({
            url: '${editSaleUrl}',
            type: 'POST',
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function (res) {
                if(res){
                    window.location.href = "<c:url value='/admin/sale/list?message=insert_success'/>";
                }
                else{
                    console.log(res);
                    window.location.href = "<c:url value='/admin/sale/list?message=error_system'/>";
                }
            }
        });
    }

    function validateForm(){
        if($('#name').val()=='' ||$('#timeStart').val()=='' || $('#timeEnd').val()=='' || $('#detail').val()=='' ){
            if($('#name').val()=='' ){
                $('#name').css("border", "1px solid red");

            }else{
                $('#name').css("border", 'none');

            }

            if($('#timeStart').val()==''){
                $('#timeStart').css("border", "1px solid red");

            }else{
                $('#timeStart').css("border", 'none');

            }
            if($('#detail').val()==''){
                $('#detail').css("border", "1px solid red");

            }else{
                $('#detail').css("border", 'none');

            }

            if($('#timeEnd').val()==''){
                $('#timeEnd').css("border", "1px solid red");

            }else{
                $('#timeEnd').css("border", 'none');

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
            deleteSale(dataArray);
        });
    }
    function deleteSale(dataArray){
        $.ajax({
            url: '${deleteSale}',
            type: 'DELETE',
            data: JSON.stringify(dataArray),
            dataType: 'json',
            contentType: "application/json",
            success: function (res) {
                if(res){
                    window.location.href = "/admin/sale/list?message=delete_success";
                }else{
                    window.location.href = "/admin/sale/list?message=error_system";
                }
            }
        });
    }


</script>
