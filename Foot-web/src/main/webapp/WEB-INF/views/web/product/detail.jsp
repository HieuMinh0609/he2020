<%@ page import="com.hieuminh.utils.SecurityUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 6/4/2019
  Time: 9:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="CommentDataUrl" value="/api/product/comment/"/>
<c:url var="addComment" value="/api/user/product/comment/add"/>
<c:url var="deleteComment" value="/api/admin/delete/comment"/>
<c:url var="order_product" value="/home-order-product-"/>
<br><br>
<div  class="col-md-8">
    <div id="id_boder_detail" class="row">
        <div class="col-md-7">
            <div class="img_detail">
                <c:set var="image" value="/repository${model.image}"/>
                <img src="<c:url value='${image}'/>"  alt="${model.nameProduct}">
            </div>
        </div>
        <div class="col-md-5">
            <div class=detail_type>
                <span>${model.typeIdEntity.typeName}</span>
            </div>
            <div class="detail_infor">
                <h4 title="${model.nameProduct}">${model.nameProduct}</h4>
                <br>
                <div class=detail_type>
                    <i class="glyphicon glyphicon-eye-open"></i>
                    <span style="margin-right: 20px">${model.view} &nbsp;view</span>
                    <i class="glyphicon glyphicon-comment"></i>
                    <span>${model.comment}&nbsp; phản hồi</span>
                </div>
                <div class="detail_star">
                    <c:forEach begin = "1" end = "${model.rating}">
                        <i class="glyphicon glyphicon-star"></i>
                    </c:forEach>
                  <c:if test="${model.rating<5}">
                      <c:forEach begin = "1" end = "${5-model.rating}">
                          <i class="glyphicon glyphicon-star-empty"></i>
                      </c:forEach>
                  </c:if>
                </div>
                <div class="online_detail">
                    <c:if test="${model.status ==0 }">
                        <i class="glyphicon glyphicon-ok"></i>
                        <span>ready</span>
                    </c:if>
                    <c:if test="${model.status==1}">
                        <i style="color: orangered" class="glyphicon glyphicon-exclamation-sign"></i>
                        <span>Pause</span>
                    </c:if>

                    <i id="detail_time_online_icon" class="glyphicon glyphicon-time"></i>
                    <span class="detail_time_online_time">8:00 AM&nbsp;-&nbsp; 2:00AM</span>
                </div>
                <div class="cost_detail">
                    <c:if test="${model.downpercen==0}">
                        <i   class="glyphicon glyphicon-usd"></i>
                        <span id="cost_not_sale_alone"></span>
                    </c:if>
                    <c:if test="${model.downpercen>0}">
                        <i class="glyphicon glyphicon-usd"></i>
                        <span id="cost_sale_icon"></span>
                        <i   class="glyphicon glyphicon-usd"></i>
                        <span id="cost_not_sale"></span>
                    </c:if>
                </div>
            </div>
            <security:authorize access="isAnonymous()">
                <div class="btn_bill">
                    <a href="/login"><button class="btn btn-success">Đặt hàng</button></a>
                </div>
            </security:authorize>
            <security:authorize access="isAuthenticated()">
                <div class="btn_bill">
                    <a href="${order_product}${model.idProduct}"> <button id="btn_order" class="btn btn-success">Đặt hàng</button></a>
                </div>
            </security:authorize>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="content_detail">
                    <span>${model.information}</span>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">

            </div>
        </div>

       <c:if test="${model.imageProductDTOS.size()>0}">
           <div id="slideshow">

               <c:forEach var="items" items="${model.imageProductDTOS}">
                   <div>
                       <c:set var="image" value="/repository${items.images}"/>
                       <img src="<c:url value='${image}'/>"  alt="${images}">
                   </div>
               </c:forEach>

           </div>
       </c:if>
    </div>


    <div id="id_rading_detail" class="row">
        <div class="detail_rading">
						<span>
							Đánh giá và bình luận sản phẩm
						</span>
        </div>
        <br>
        <div class="col-md-1">

            <div class="img_user_detail">
            <security:authorize  access="isAuthenticated()">
                    <img    src= '<c:url value="/image/man.png"/>' alt="man">
            </security:authorize>
            <security:authorize  access="isAnonymous()">
                <img    src= '<c:url value="/image/user-login-man.png"/>' alt="man">
            </security:authorize>
            </div>
        </div>
        <div class="col-md-11">
            <div class="rate">
                <input type="radio" id="star5" name="rate" value="5" />
                <i id="class_i5" title="Excellent" class="glyphicon glyphicon-star-empty"></i>
                <input type="radio"  id="star4" name="rate" value="4" />
                <i id="class_i4" title="good"  class="glyphicon glyphicon-star-empty"></i>
                <input type="radio" id="star3" name="rate" value="3" />
                <i id="class_i3" title="ok" class="glyphicon glyphicon-star-empty"></i>
                <input type="radio" id="star2" name="rate" value="2" />
                <i id="class_i2"  title="Poor" class="glyphicon glyphicon-star-empty"></i>
                <input type="radio" id="star1" name="rate" value="1" />
                <i  id="class_i1" title="very bad" class="glyphicon glyphicon-star-empty"></i>

            </div>
            <div class="detail_comment">
                <textarea id="text_reply" spellcheck="false" placeholder="Thêm bình luận..." rows="3" cols="70"></textarea>

            </div>
            <security:authorize  access="isAuthenticated()">
                <div class="btn_button_reply">
                    <button id="btn_reply_click" class="btn btn-info">Bình Luận</button>
                </div>
            </security:authorize>

            <security:authorize  access="isAnonymous()">
                <div class="btn_button_reply">
                    <button   class="btn btn-info" disabled title="you need login">Bình Luận</button>
                </div>
            </security:authorize>
        </div>
    </div>
    <div id="class_comment_detail" class="row">
        <ul id="id_detail_ul" style="margin-left: -35px">
            <li id="load_comment_from_ajax"></li>
        </ul>


        <div class="col-md-12">
            <div class="see_more_reply">
                <span id="see_more_comment">Xem thêm</span>
            </div>
        </div>
    </div>
</div>
<script>

     $('#btn_reply_click').on('click',function () {
         var dataArray = {};

         dataArray["rate"]=$("input[name='rate']:checked").val();
         dataArray["content"] = $('#text_reply').val();

         if(validateForm()){
             addReplyAjax(dataArray);
         }



     });



     function validateForm(){
         if($('.rate').find("input:radio:checked").length == 0 || $('#text_reply').val()=='' ){
             if($('.rate').find("input:radio:checked").length == 0){
                 $('.rate').css("border", "1px solid red");

             }else{
                 $('.rate').css("border", 'none');

             }

             if($('#text_reply').val()==''){
                 $('#text_reply').css("border", "1px solid red");

             }else{
                 $('#text_reply').css("border", '1px solid #00000038');

             }



             return false;
         }else {

             return true;
         }
     }




     function  addReplyAjax(dataArray){
         $.ajax({
             url: '${addComment}/'+${model.idProduct},
             type: 'POST',
             dataType: 'json',
             contentType: 'application/json',
             data: JSON.stringify(dataArray),
             success: function (res) {
                 var d = new Date(res.dateTime);
                 var row='';
                 row+='<div class="one_area_comment">'
                 row+='<div class="col-md-6">'

                 row+='<div class="img_user_detail">'
                 if(res.idUserEntity.sex==1){
                     row+='<img  src="image/man.png" alt="man">'
                 }else{
                     row+='<img  src="image/girl.png" alt="girl">'
                 }

                 row+='   <span>'+res.idUserEntity.nameFull+'</span>'
                 row+='   <span style="font-size: 10px">('+res.idUserEntity.place+')</span>'
                 row+='     </div>'
                 row+=' </div>'
                 row+=' <div class="col-md-6">'
                 row+='  <div class="time_star_reply">'
                 row+='      <div class="detail_star">'
                 for (var i = 0; i < res.rate; i++) {

                     row+='<i class="glyphicon glyphicon-star"></i>'
                 }
                 if(res.rate<5){
                     for (var i = 0; i < (5- res.rate); i++) {
                         row+=' <i class="glyphicon glyphicon-star-empty"></i>'
                     }
                 }

                 row+='        </div>'
                 row+='         <div class="time_repy_detail">'
                 row+='             <i class="glyphicon glyphicon-time"></i>'
                 row+='              <span id="detail_time_online_time">'+d.toLocaleDateString()+' - '+ d.toLocaleTimeString()+'</span>'
                 row+='          </div>'
                 row+='      </div>'
                 row+='   </div>'
                 row+=' <div class="col-sm-12">'
                 row+='   <div class="text_comment">'
                 row+='        <span>'+res.content+'</span>'
                 row+='     </div>'
                 row+='   </div>'
                 row+=' </div>'





         $('#load_comment_from_ajax').prepend(row);
             },
             error:function (err) {
                 alert(err);
             }
         });
     }

    $(function () {
        $('#cost_sale_icon').text(numberWithCommas(${model.cost}));
        $('#cost_not_sale').text(numberWithCommas(${model.cost - (model.cost * model.downpercen)/100}));
        $('#cost_not_sale_alone').text(numberWithCommas(${model.cost}));
    });

    $(document).ready(function () {
        var currentPage = 1;


        var dataArray={};
        dataArray["page"]=currentPage;
        dataArray["maxPageItems"]=9;
        loadCommentData(dataArray);


        $('#see_more_comment').on('click',function () {
            currentPage += 1;
            dataArray["page"]=currentPage;
            dataArray["maxPageItems"]=9;


            loadCommentData(dataArray);
        });



    });

    function loadCommentData(dataArray) {

        $.ajax({
            url: '${CommentDataUrl}'+'${model.idProduct}',
            type: 'POST',
            data: JSON.stringify(dataArray),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                if(data !=null){
                    var row = '';
                    $(data).each(function (index, items)  {
                        var d = new Date(items.dateTime);
                        row+='<li>'
                        row+='   </div>'
                        <security:authorize access="isAuthenticated()">
                        <c:if test="${SecurityUtils.getAuthorities().contains('ADMIN')}">
                        row+='<a  title="delete" onclick="deleteComment(this)" id_comment="'+items.idComment +'" ><i class="glyphicon glyphicon-remove-circle" style="color:red; cursor: pointer; "></i></a>'
                        </c:if>
                        </security:authorize>
                        row+=' </div>'
                        row+='     </div>'
                        row+='<div class="one_area_comment">'
                        row+='<div class="col-md-6">'

                        row+='<div class="img_user_detail">'
                        if(items.idUserEntity.sex==1){
                            row+='<img  src="image/man.png" alt="man">'
                        }else{
                            row+='<img  src="image/girl.png" alt="girl">'
                        }

                        row+='   <span>'+items.idUserEntity.nameFull+'</span>'
                        row+='   <span style="font-size: 10px">('+items.idUserEntity.place+')</span>'
                        row+='     </div>'
                        row+=' </div>'
                        row+=' <div class="col-md-6">'
                        row+='  <div class="time_star_reply">'
                        row+='      <div class="detail_star">'
                        for (var i = 0; i < items.rate; i++) {

                            row+='<i class="glyphicon glyphicon-star"></i>'
                        }
                        if(items.rate<5){
                            for (var i = 0; i < (5- items.rate); i++) {
                                row+=' <i class="glyphicon glyphicon-star-empty"></i>'
                            }
                        }

                        row+='        </div>'
                        row+='         <div class="time_repy_detail">'
                        row+='             <i class="glyphicon glyphicon-time"></i>'
                        row+='              <span id="detail_time_online_time">'+d.toLocaleDateString()+' - '+ d.toLocaleTimeString()+'</span>'
                        row+='          </div>'
                        row+='      </div>'
                        row+='   </div>'
                        row+=' <div class="col-sm-12">'
                        row+='   <div class="text_comment">'
                        row+='        <span>'+items.content+'</span>'


                        row+='</li>'

                    });

                    $('#class_comment_detail ul').append(row);

                }

            },
            error: function (err) {
                alert(Error);
            }
        });
    }


    function deleteComment(btn) {
        var idComment=$(btn).attr('id_comment');
            //deleteCmt(idComment);
        showAlertBeforDeleteComment(function () {
            event.preventDefault();
            deleteCmt(idComment);
        });
    }
    function deleteCmt(idComment) {
        var dataArray={};
        dataArray["idComment"]=idComment;
        $.ajax({
        url: '${deleteComment}',
            type: 'POST',
            data:JSON.stringify(dataArray) ,
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {

             //   alert("delete success!");
                swal("Good job!", "Comment deleted", "success", 2000, false);
                setTimeout(function(){
                    window.location.href = "<c:url value='/product-${model.idProduct}'/>";
                }, 3000);
              },
            error:function (data) {
               // alert("have error ,dalete fail!");

                swal("Cancelled!", "You Cancelled", "error", 2000, false)
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
    $("#slideshow > div:gt(0)").hide();

    setInterval(function() {
        $('#slideshow > div:first')
            .fadeOut(1000)
            .next()
            .fadeIn(1000)
            .end()
            .appendTo('#slideshow');
    },  3000);


    $("#class_i1").click(function(){
        $("#class_i1").css('color','#ffc107');
        $("#class_i2").css('color','#989494');
        $("#class_i3").css('color','#989494');
        $("#class_i4").css('color','#989494');
        $("#class_i5").css('color','#989494');
        $("#star1").prop("checked", true);

    });
    $("#class_i2").click(function(){
        $("#class_i1").css('color','#ffc107');
        $("#class_i2").css('color','#ffc107');
        $("#class_i3").css('color','#989494');
        $("#class_i4").css('color','#989494');
        $("#class_i5").css('color','#989494');
        $("#star2").prop("checked", true);
    });
    $("#class_i3").click(function(){
        $("#class_i1").css('color','#ffc107');
        $("#class_i2").css('color','#ffc107');
        $("#class_i3").css('color','#ffc107');
        $("#class_i4").css('color','#989494');
        $("#class_i5").css('color','#989494');
        $("#star3").prop("checked", true);

    });
    $("#class_i4").click(function(){
        $("#class_i1").css('color','#ffc107');
        $("#class_i2").css('color','#ffc107');
        $("#class_i3").css('color','#ffc107');
        $("#class_i4").css('color','#ffc107');
        $("#class_i5").css('color','#989494');
        $("#star4").prop("checked", true);
    });
    $("#class_i5").click(function(){
        $("#class_i1").css('color','#ffc107');
        $("#class_i2").css('color','#ffc107');
        $("#class_i3").css('color','#ffc107');
        $("#class_i4").css('color','#ffc107');
        $("#class_i5").css('color','#ffc107');
        $("#star5").prop("checked", true);
    });

</script>