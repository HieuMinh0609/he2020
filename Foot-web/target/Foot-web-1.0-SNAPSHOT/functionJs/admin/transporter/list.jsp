<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 1/11/2020
  Time: 12:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="notTransUrl" value="/api/admin/transporter/nottrans"/>
<c:url var="onlineTransUrl" value="/api/admin/transporter/trans"/>
<c:url var="oldTransUrl" value="/api/admin/transporter/transOld"/>
<c:url var="passToTrans" value="/api/admin/transporter/give"/>
<c:url var="detailBill" value="/api/admin/bill/details"/>
<c:url var="inforTransporter" value="/api/admin/transporter/infor/"/>

<script>

    $(function () {
        $('#btn_bill_not_trans').click(function () {
            $('#btn_bill_not_trans').css('background','#8ece8e');
            $('#btn_bill_trans').css('background','white');

            $('#form_bill_not_trans').css('display','block');
            $('#form_bill_onTrans').css('display','none');
        });
        $('#btn_bill_trans').click(function () {
            $('#btn_bill_trans').css('background','#8ece8e');
            $('#btn_bill_not_trans').css('background','white');

            $('#form_bill_not_trans').css('display','none');
            $('#form_bill_onTrans').css('display','block');
        });

    });

    var urlTrans;
    $(document).ready(function () {
        var currentPage = 1;
        var dataArray={};
        dataArray["page"]=currentPage;
        dataArray["maxPageItems"]=7;
        loadPageDataBillNotTrans(dataArray);
        $('#form_not_bill').scroll(function ()  {
            if ($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
                currentPage += 1;
                dataArray["page"]=currentPage;
                dataArray["maxPageItems"]=7;
                loadPageDataBillNotTrans(dataArray);
            }
        });
});

    $('#btn_statistic').on('click',function () {
        if($('#select_transporter option:selected').val()!=0){
            var showUrl = '${inforTransporter}'+ $('#select_transporter option:selected').val();
            $('#showInformationTrans').load(showUrl,'',function () {
                $('#showInformationTrans').modal('toggle');

            });
        }

    });


    $('#btn_default_history').click(function () {
        $('#btn_default_history').css('background','#8ece8e');
        $('#btn_strans_infor').css('background','white');
        urlTrans ='${oldTransUrl}';
        if($('#select_transporter option:selected').val()!=0){
            var currentPageOnline = 1;
            var dataArrayOnline={};
            dataArrayOnline["page"]=currentPageOnline;
            dataArrayOnline["maxPageItems"]=7;
            dataArrayOnline["id"]=$('#select_transporter option:selected').val();
            loadPageDataBillTransSelect(dataArrayOnline,urlTrans);
        }
    });

    $('#btn_strans_infor').on('click', function() {
        $('#btn_strans_infor').css('background','#8ece8e');
        $('#btn_default_history').css('background','white');
         urlTrans ='${onlineTransUrl}';
        if($('#select_transporter option:selected').val()!=0){
            var currentPageOnline = 1;
            var dataArrayOnline={};
            dataArrayOnline["page"]=currentPageOnline;
            dataArrayOnline["maxPageItems"]=7;
            dataArrayOnline["id"]=$('#select_transporter option:selected').val();
            loadPageDataBillTransSelect(dataArrayOnline,urlTrans);
        }

    });
    $('#id_table_online_trans').scroll(function ()  {
        if ($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
            currentPageOnline += 1;
            dataArrayOnline["page"]=currentPageOnline;
            dataArrayOnline["maxPageItems"]=7;
            dataArrayOnline["id"]=$('#select_transporter option:selected').val()
            loadDataTransOnline(dataArrayOnline,urlTrans);
        }
    });


    $('#btn_tranformer').on('click',function (e) {
        e.preventDefault();
        $.ajax({
            url: '${passToTrans}',
            type: 'GET',
            success: function (res) {
               var res = JSON.stringify(res);
                if(res!=null){
                    swal("Good job!",res, "success",4000 , false);
                    setTimeout(function(){
                        window.location.href = "<c:url value='/admin/transporter/list'/>";
                    }, 4000);

            }
            },
            error:function (res) {
                // alert("have error ,dalete fail!");
                swal("Opp!", "System Wrong!!!!", "error", 4000, false);
            }
            });

    });
    function loadDataTransOnline(dataArray,url) {
        $.ajax({
            url:url,
            type: 'POST',
            data: JSON.stringify(dataArray),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {

                if(data !=null){

                    var row = '';
                    $(data).each(function (index, items) {
                        var timeEnd = new Date(items.timeEnd);
                        var timeStart = new Date(items.timeStart);
                        row+='<tr>';
                        row+='<td scope="row"><a  onclick="detailBillCart(this)" urc_idBill_detail="'+items.transBillEntity.idBill+'"  id="bill_process_'+items.transBillEntity.idBill+'"  class="btn btn-default">Detail</a></td>';
                        row+='<td scope="row">'+items.transBillEntity.idBill+'</td>';
                        if(items.status==0){
                            row+='<td><i title="not final" style="color: #FF9800;   margin-left: 23px;" class="glyphicon glyphicon-remove-circle"></i> </td>';
                        }else{
                            row+='<td><i title="final" style="color: #8BC34A;   margin-left: 23px;" class="glyphicon glyphicon-ok-circle"></i> </td>';
                        }
                        row+='<td>'+timeStart.toLocaleDateString() +" "+ timeStart.toLocaleTimeString()+'</td>';
                        if(items.status!=0){
                            row+='<td>'+timeEnd.toLocaleDateString() +"  "+ timeEnd.toLocaleTimeString()+'</td>';
                        }else{
                            row+='<td></td>';
                        }
                        row+='<td>'+numberWithCommas(items.transBillEntity.cost)+' VND</td>';
                        row+='<td>'+items.transBillEntity.place+' VND</td>';
                        row+='</tr>';
                    });
                    $('#body_table_trans').append(row);
                }

            },
            error: function (err) {
                alert(Error);
            }
        });


    }

    function loadPageDataBillNotTrans(dataArray) {
        $.ajax({
            url: '${notTransUrl}',
            type: 'POST',
            data: JSON.stringify(dataArray),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {

                if(data !=null){
                    var row = '';
                    $(data).each(function (index, items) {
                        var d = new Date(items.dateTime);
                        row+='<tr>';
                        row+='<td scope="row"><a  onclick="detailBillCart(this)" urc_idBill_detail="'+items.idBill+'"  id="bill_process_'+items.idBill+'"  class="btn btn-default">Detail</a></td>';
                        row+='<td scope="row">'+items.idBill+'</td>';
                        row+='<td>'+d.toLocaleDateString()+'</td>';
                        row+='<td>'+numberWithCommas(items.cost)+' VND</td>';
                        row+='<td>'+items.place+'</td>';
                        row+='</tr>';
                    });
                    $('#table_notBill').append(row);
                }
            },
            error: function (err) {
                alert(Error);
            }
        });
    }
    function loadPageDataBillTransSelect(dataArray,url) {
        $.ajax({
            url:url,
            type: 'POST',
            data: JSON.stringify(dataArray),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {

                if(data !=null){

                    var row = '';
                    $(data).each(function (index, items) {
                        var timeEnd = new Date(items.timeEnd);
                        var timeStart = new Date(items.timeStart);
                        row+='<tr>';
                        row+='<td scope="row"><a  onclick="detailBillCart(this)" urc_idBill_detail="'+items.transBillEntity.idBill+'"  id="bill_process_'+items.transBillEntity.idBill+'"  class="btn btn-default">Detail</a></td>';
                        if(items.status==0){
                            row+='<td><i title="not final" style="color: #FF9800;   margin-left: 23px;" class="glyphicon glyphicon-remove-circle"></i> </td>';
                        }else{
                            row+='<td><i title="final" style="color: #8BC34A;   margin-left: 23px;" class="glyphicon glyphicon-ok-circle"></i> </td>';
                        }
                        row+='<td scope="row">'+items.transBillEntity.idBill+'</td>';
                        row+='<td>'+timeStart.toLocaleDateString() +" "+ timeStart.toLocaleTimeString()+'</td>';
                        if(items.status!=0){
                            row+='<td>'+timeEnd.toLocaleDateString() +"  "+ timeEnd.toLocaleTimeString()+'</td>';
                        }else{
                            row+='<td></td>';
                        }
                        row+='<td>'+numberWithCommas(items.transBillEntity.cost)+' VND</td>';
                        row+='<td>'+items.transBillEntity.place+' VND</td>';
                        row+='</tr>';
                    });
                    $('#body_table_trans').html(row);
                }
            },
            error: function (err) {
                alert(Error);
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
                alert("have error ,dalete fail!");
            }
        });
    }
</script>