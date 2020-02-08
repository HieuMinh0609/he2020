<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 7/8/2019
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<c:url value="/admin/product/list" var="requestUrl"/>
<c:url value="/admin/home" var="HomeUrl"/>
<c:url value="/admin/product/list" var="ProductUrl"/>
<c:url value="/api/admin/imageProduct/delete" var="deleteImage"/>
<c:url value="/api/admin/imageProduct/add/" var="addImage"/>
<c:url value="#" var="deleteImageProduct"/>
<html>
<head>
    <title>ImageProduct</title>
    <style>
        body {
            font-family: Verdana, sans-serif;
            margin: 0;
        }

        * {
            box-sizing: border-box;
        }

        .row1 > .column {
            padding: 0 8px;
        }

        .row1:after {
            content: "";
            display: table;
            clear: both;
        }

        .column1 {
            float: left;
            width: 25%;
        }

        /* The Modal (background) */
        .modal1 {
            display: none;
            position: fixed;
            z-index: 1;
            padding-top: 20px;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: black;
        }

        /* Modal Content */
        .modal-content1 {
            position: relative;
            background-color: #fefefe;
            margin: auto;
            padding: 0;
            width: 90%;
            max-width: 900px;
        }

        /* The Close Button */
        .close1 {
            color: white;
            position: absolute;
            top: 10px;
            right: 25px;
            font-size: 35px;
            font-weight: bold;
        }

        .close1:hover,
        .close1:focus {
            color: #999;
            text-decoration: none;
            cursor: pointer;
        }

        .mySlides {
            display: none;
        }

        .cursor {
            cursor: pointer;
        }

        /* Next & previous buttons */
        .prev1,
        .next1 {
            cursor: pointer;
            position: absolute;
            top: 50%;
            width: auto;
            padding: 16px;
            margin-top: -50px;
            color: white;
            font-weight: bold;
            font-size: 20px;
            transition: 0.6s ease;
            border-radius: 0 3px 3px 0;
            user-select: none;
            -webkit-user-select: none;
        }

        /* Position the "next button" to the right */
        .next1 {
            right: 0;
            border-radius: 3px 0 0 3px;
        }

        /* On hover, add a black background color with a little bit see-through */
        .prev1:hover,
        .next1:hover {
            background-color: rgba(0, 0, 0, 0.8);
        }

        /* Number text (1/3 etc) */
        .numbertext1 {
            color: #f2f2f2;
            font-size: 12px;
            padding: 8px 12px;
            position: absolute;
            top: 0;
        }

        img {
            margin-bottom: -4px;
        }

        .caption-container1 {
            text-align: center;
            background-color: black;
            padding: 2px 16px;
            color: white;
        }

        .demo1 {
            opacity: 0.6;
        }

        .active1,
        .demo1:hover {
            opacity: 1;
        }

        img.hover-shadow1 {
            transition: 0.3s;
        }

        .hover-shadow1:hover {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
        }
    </style>
</head>
<body>


    <div class="container-fluid">
        <div class="card_hr row">
            <hr>
        </div>
        <div class="row">

            <div class="link_header">
                <i class="glyphicon glyphicon-hand-right"></i>
                <a href="${HomeUrl}"><spring:message  code="label.web.home"/></a>
                <span>/</span>
                <a href="${ProductUrl}"><spring:message code="label.list.product"/> </a>
                <span>/</span>
                <span><spring:message code="label.list.product.images"/> </span>
            </div>

        </div>

    </div>
<br>
<div class="container-fluid">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <c:if test="${not empty messageResponse}">
            <div class="alert alert-${alert} alert-dismissible" style="margin-top: 10px">
                <c:set var="idIamge" value="${model.id}"/>
                <a href="<c:url value="${idIamge}"/>" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <span>${messageResponse}</span>
            </div>
        </c:if>
    </div>
</div>
<br>
<br>
<br>

<div class="container">


    <form>

        <div id="button_select" class="row">
            <button class="btn btn-danger button_select" type="button" id='deleteAll' disabled='false' onclick="waringBeforDelete()" title="Delete"><i class="glyphicon glyphicon-trash"></i></button>
            <a  onclick="openModelImage()" type="button" class="btn btn-warning button_select" title="Add"><i class="glyphicon glyphicon-plus-sign"></i></a>
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
                            <input type='checkbox' value="${tableList.idImageProduct}" class='check-box-element' name="checkList" id="checkbox_${tableList.idImageProduct}"/>
                        </fieldset>
                    </display:column>


                    <display:column  >
                        <div class="row">
                          <div class="col-md-3">
                              <c:set var="images" value="/repository${tableList.images}"/>
                              <img style="height: 50px; width:80px; "  src="<c:url value='${images}'/>" class="img-thumbnail  " id="viewImage_${tableList.idImageProduct}" height="250px">
                          </div>
                            <div class="col-md-9">
                                <a  style="cursor: default;" id_product="${model.id}" onclick="seeImage(this)">Link: ${tableList.images}</a>
                            </div>
                        </div>


                    </display:column>


                </display:table>

            </fmt:bundle>


        </div

    </form>

</div>

<!-- The Modal -->
<div id="myModal" class="modal1" >

    <a onclick="closeModal()" type="button" class="close1">&times;</a>
    <div class="modal-content1">

    <c:forEach items="${model.listResult}" var="items" varStatus="loop">
        <div class="mySlides">
            <div class="numbertext1">${loop.index +1}/${model.totalItems}</div>
            <c:set var="images" value="/repository${items.images}"/>
            <img style="width:100%" src="<c:url value='${images}'/>"   id="viewImage_${items.idImageProduct}" >
            <div class="caption-container1">${items.images}</div>
        </div>
    </c:forEach>
        <a class="prev1" onclick="plusSlides(-1)">&#10094;</a>
        <a class="next1" onclick="plusSlides(1)">&#10095;</a>

    <c:forEach items="${model.listResult}" var="items" varStatus="loop">
        <div class="column1">
            <c:set var="images" value="/repository${items.images}"/>
            <img class="demo1 cursor1" style="width:100%" src="<c:url value='${images}'/>" onclick="currentSlide(${loop.index +1})"  id="viewImage_${items.idImageProduct}"  alt="${items.images}">
         </div>
    </c:forEach>
    </div>
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
                        <div id="file-uploader">
                            <form>
                                <span class="file-preview-button" >
                                    <span class="btn btn-primary file-preview-shadow">
                                        <span >Add File</span>
                                         <input id="uploadImage" type="file" multiple="multiple" />
                                    </span>

                                </span>

                            </form>
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
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-warning" id="btnUploadImage"><spring:message
                        code="label.update.image"/></button>
            </div>
        </div>
    </div>
</div>

<script>

    $(document).ready(function () {

    });
    var fileArray = [];
    var arr=[];
    $('#uploadImage').change(function () {
        $('#text-waring').text("");
        fileArray = Array.from($('#uploadImage')[0].files);
        var row = '';
        for(var index=0;index<$('#uploadImage')[0].files.length;++index){

            readUrlImageList(this,index+"_viewImage",index);

            row += '<tr class="file-preview-row">';
            row += '<td> <img src="" id="'+index+'_viewImage" class="file-preview-placeholder image"/></td>';
            row += '<td class="filename">'+$('#uploadImage')[0].files[index].name+'</td>';
            row += '<td class="filesize">'+$('#uploadImage')[0].files[index].size+'&nbsp;kb</td>';
            row += '<td class="remove-file"><button id="btn_close_'+index+'" class="btn btn-danger">&times;</button></td>';
            row += '</tr>';
        }
        $('#file-preview-table tbody').html(row);

    });


    $('#file-preview-table').on("click", "tbody tr", function() {
        var row_inx=$(this).index();
        fileArray.splice(row_inx,1);
        var row = '';
        for(var index=0;index<fileArray.length;++index){
            readUrlImageArray(fileArray,index+"_viewImage",index);
            row += '<tr class="file-preview-row">';
            row += '<td> <img src="" id="'+index+'_viewImage" class="file-preview-placeholder image"/></td>';
            row += '<td class="filename">'+fileArray[index].name+'</td>';
            row += '<td class="filesize">'+fileArray[index].size+'&nbsp;bytes</td>';
            row += '<td class="remove-file"><button id="btn_close_'+index+'" class="btn btn-danger">&times;</button></td>';
            row += '</tr>';
        }
        $('#file-preview-table tbody').html(row);
    });


    function readUrlImageArray(input,imageId,index) {
        if(input.length>0){
            var reader = new FileReader();
            reader.onload=(function (e) {
                $('#'+ imageId).attr('src',reader.result);
            });

            reader.readAsDataURL(input[index]);

        }
    }

    function readUrlImageList(input,imageId,index) {
        if(input.files && input.files[index]){
            var reader = new FileReader();
            reader.onload=(function (e) {
                $('#'+ imageId).attr('src',reader.result);
            });
            reader.readAsDataURL(input.files[index]);

        }
    }

     function openModelImage() {
         $('#myModalFile').modal();
     }

    $('#btnUploadImage').on('click',function (ex) {

               addImage();

    });
    function addImage(){
        $.each(fileArray, function (i, value) {

                var reader = new FileReader();
                reader.onload = (function (e) {

                    arr.push(
                        value.name
                    );
                    arr.push(
                        e.target.result
                    );

                });
                reader.readAsDataURL(value);

        });

       if(arr.length>0){
           $.ajax({
               url: '${addImage}'+${model.id},
               type: 'POST',
               dataType: 'json',
               contentType: 'application/json',
               data: JSON.stringify(arr),
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
    
    function seeImage(btn) {
        $('#myModal').css('display','block')
        currentSlide(1);
    }

    function closeModal() {
        $('#myModal').css('display','none')
    }

    var slideIndex = 1;
    showSlides(slideIndex);

    function plusSlides(n) {
        showSlides(slideIndex += n);
    }

    function currentSlide(n) {
        showSlides(slideIndex = n);
    }




    $("#tableList").on("click", "tbody tr", function() {
        var row_inx=$(this).index()+1;
        showSlides(slideIndex = row_inx);
    });

    function showSlides(n) {
        var i;
        var slides = document.getElementsByClassName("mySlides");

        var captionText = document.getElementById("caption");
        if (n > slides.length) {slideIndex = 1}
        if (n < 1) {slideIndex = slides.length}
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }

        slides[slideIndex-1].style.display = "block";


    }



    function validateForm() {
        if ($('#uploadImage').val() == '') {
            return false;
        } else {

            return true;
        }
    }


    function waringBeforDelete() {

        showAlertBeforDelete(function () {
            event.preventDefault();
            var dataArray =$('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val();
            }).get();
            deleteImage(dataArray);
        });
    }
    function deleteImage(dataArray){
        $.ajax({
            url: '${deleteImage}',
            type: 'DELETE',
            data: JSON.stringify(dataArray),
            dataType: 'json',
            contentType: "application/json",
            success: function (res) {
                if(res){
                    window.location.href = "<c:url value="${model.id}?message=delete_success"/>";

                }else{
                    window.location.href = "<c:url value='${model.id}?message=error_system'/>";

                }

            }

            
        });
    }

</script>
</body>
</html>
