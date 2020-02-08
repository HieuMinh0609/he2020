<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 8/23/2019
  Time: 9:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="product-" var="detailProduct"/>
<br><br>
<div  class="col-md-8">
    <div id="boder_list_pro" class="row">
<c:forEach var="items" items="${model}">
        <div   class="col-md-4 col-sm-6">
        <div class="boder_list">
           <a href="${detailProduct}${items.idProduct}">
                <div class="img_restaurant">
                    <c:set var="image" value="/repository${items.image}"/>
                     <img src="<c:url value="${image}"/>" alt="pro">
                     </div>
                 <div class="infor_restaurant">
                    <h4>${items.nameProduct}</h4>
                    </div>
                <c:if test="${items.downpercen!=0}">
                <div class="content_pro">
                    <span>
                        <i class="glyphicon glyphicon-arrow-down"></i>
                        ${items.downpercen }%
                        </span>
                   </div>
                </c:if>
               <c:if test="${items.downpercen==0}">
                <div style="height: 31px" class="content_pro">
                     <span >

                       </span>
                    </div>
               </c:if>

                 <div class="online_pro">

                     <c:if test="${items.status==1}">
                     <span class="stt offline " title=" Tạm ngưng"  >

                                          </span>
                     </c:if>
                     <c:if test="${items.status==0}">
                     <span class="stt online" title=" Mở cửa"  >
                                            </span>
                     </c:if>

                    </div>

               <c:if test="${items.downpercen!=0}">
                <div class="class_cost">
                    <span  class="cost" >

                 <fmt:formatNumber type = "number"
                                   pattern = "###,###" value = "${items.cost - (items.cost * items.downpercen)/100}" />

                         </span>
                    </div>
               </c:if>
               <c:if test="${items.downpercen==0}">
                <div class="class_cost">
                    <span class="cost" >
                           <fmt:formatNumber type = "number"
                                             pattern = "###,###" value = "${items.cost}" />
                    </span>
                    </div>
               </c:if>

                </a>
            </div>

        </div>
</c:forEach>
         </div>
    <div class="row">
        <div   class="transion_page">
            <div class="row">
                <ul class="pager">
                    <li><a id="click_back" href="#boder_list_pro"><i title="back" class="glyphicon glyphicon-arrow-left"></i></a></li>
                    <li><span id="numberPage">
                    </span></li>
                    <li><a id="click_next" href="#boder_list_pro"><i  title="next" class="glyphicon glyphicon-arrow-right"></i></a></li>
                </ul>
            </div>

        </div>
    </div>
</div>

<script>



    $(document).ready(function () {
        //var currentHost1 = window.location.pathname;
        //var reuslt =  currentHost1.substring(currentHost1.length-1,currentHost1.length);
        var currentPage = parseInt(${page});
        $('#numberPage').text(currentPage);
       if(currentPage==1){
           $('#click_back').css('display','none');
       }
       $('#click_back').on('click',function () {
           currentPage -= parseInt(1);
           if(currentPage==1){
               $('#click_back').css('display','none');
           }else{
               $('#click_back').css('display','block');
           }
           var currentHost = window.location.pathname;
           var res =  currentHost.substring(0,currentHost.length-(currentPage+1).toString().length);

           window.location.href =res+parseInt(currentPage);


       });
       $('#click_next').on('click',function () {
           currentPage += parseInt(1);
           if(currentPage==1){
               $('#click_back').css('display','none');
           }else{
               $('#click_back').css('display','block');
           }
           var currentHost = window.location.pathname;
           var res =  currentHost.substring(0, currentHost.length-(currentPage-1).toString().length);
           window.location.href =res+currentPage;

       });

   });


    function numberWithCommas(x) {
        x = x.toString();
        var pattern = /(-?\d+)(\d{3})/;
        while (pattern.test(x))
            x = x.replace(pattern, "$1,$2");
        return x;
    }
</script>