<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 9/21/2019
  Time: 9:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="product-" var="detailProduct"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<br><br>
<div  class="col-md-8">
    <div id="boder_list_pro" class="row">
        <c:forEach items="${model}" var="items">
        <div class="col-md-4 col-sm-6">
            <div class="boder_list">
                <a href="${detailProduct}${items.idProduct}">
                    <div class="img_restaurant">
                        <c:set var="image" value="/repository/${items.image}"/>
                        <img src="<c:url value="${image}"/>" alt="">
                    </div>
                    <div class="infor_restaurant">
                        <h4>${items.nameProduct}</h4>
                    </div>


                     <c:if test="${items.downpercen!=0}">
                         <div class="content_pro">
						<span>

                            <i class="glyphicon glyphicon-arrow-down"></i>
                            ${items.downpercen}%
                        </span>
                         </div>
                     </c:if>
                    <c:if test="${items.downpercen==0}">
                        <div style="height: 31px" class="content_pro">
						<span>


                        </span>
                        </div>
                    </c:if>


                <div class="online_pro">
                    <c:choose>
                        <c:when test="${items.status==1}">
                            <span class="stt offline " title=" Tạm ngưng"  ></span>
                        </c:when>
                        <c:otherwise>
                            <span class="stt online" title="Mở cửa">

                        </span>
                        </c:otherwise>
                    </c:choose>

                </div>

                <c:choose>
                    <c:when test="${items.downpercen!=0}">
                        <div class="class_cost">
                        <span class="cost" >
                        <spring:formatNumber type = "number" pattern = "###,###" value = "${items.cost - (items.cost *items.downpercen)/100}" />
                       </span>
                      </div>
                    </c:when>
                    <c:otherwise>
                        <div class="class_cost">
                        <span class="cost" >
                        <spring:formatNumber type = "number" pattern = "###,###" value = "${items.cost}" />
                       </span>
                        </div>
                    </c:otherwise>
                </c:choose>
                </a>
            </div>
        </div>
        </c:forEach>
    </div>

</div>
<script>
    $(function () {
        var currentPage = 1;
        $('#numberPage').text(currentPage);
        if(currentPage==1){
            $('#click_back').css('display','none');
        }

        var dataArray={};
        dataArray["page"]=currentPage;
        dataArray["maxPageItems"]=12;
        $('#click_back').on('click',function () {
            currentPage -= 1;
            if(currentPage==1){
                $('#click_back').css('display','none');
            }else{
                $('#click_back').css('display','block');
            }
            dataArray["page"]=currentPage;
            dataArray["maxPageItems"]=12;
            $('#numberPage').text(currentPage);
            loadHomePageData(dataArray);

        });
        $('#click_next').on('click',function () {
            currentPage += 1;
            dataArray["page"]=currentPage;
            dataArray["maxPageItems"]=12;
            if(currentPage==1){
                $('#click_back').css('display','none');
            }else{
                $('#click_back').css('display','block');
            }
            $('#numberPage').text(currentPage);
            loadHomePageData(dataArray);
        });
    });

</script>
</body>
</html>
