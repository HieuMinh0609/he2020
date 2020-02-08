<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 6/4/2019
  Time: 2:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="/api/wBHome/homepage" var="HomePageUrl"/>
<c:url value="product-" var="detailProduct"/>

<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${model.size()>0}">
<div class="container-fluid">
    <br>
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->


        <ol class="carousel-indicators">
        <c:forEach var = "i" begin = "0" end = "${model.size()-1}">
            <li id="class_slider_${i}" data-target="#myCarousel" data-slide-to="${i}"  ></li>
        </c:forEach>
        </ol>
        <!-- Wrapper for slides -->
        <div id="class_slider" class="carousel-inner">
            <c:forEach  var="items" items="${model}" varStatus="loop">
                <div id="class_body_${loop.index}" class="item">
                    <a href="">
                        <c:set var="image" value="/repository${items.image}"/>
                        <img src="<c:url value='${image}'/>"  alt="${items.idSale}">
                    </a>
                    <div  class="carousel-caption title_silder" style="border-radius: 50px;">
                        <h3>${items.name}</h3>
                        <h3><spring:formatDate value="${items.timeStart}"/> -<spring:formatDate value="${items.timeEnd}"/> </h3>
                        <p>${items.detail}</p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>
</c:if>
<br><br>
<div  class="col-md-8">
    <div id="boder_list_pro" class="row">


    </div>
    <div   class="transion_page">
                <div class="row">
                    <ul class="pager">
                        <li><a id="click_back" href="#boder_list_pro"><i title="back" class="glyphicon glyphicon-arrow-left"></i></a></li>
                        <li><span id="numberPage"></span></li>
                        <li><a id="click_next" href="#boder_list_pro"><i  title="next" class="glyphicon glyphicon-arrow-right"></i></a></li>
                    </ul>
                </div>
    </div>
</div>



<script>

    $(function () {
        $('#class_slider_0').addClass('active');
        $('#class_body_0').addClass('active');
    });
    $(document).ready(function () {
        var currentPage = 1;
        $('#numberPage').text(currentPage);
        if(currentPage==1){
            $('#click_back').css('display','none');
        }

        var dataArray={};
        dataArray["page"]=currentPage;
        dataArray["maxPageItems"]=12;
        loadHomePageData(dataArray);

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

    function loadHomePageData(dataArray) {

        $.ajax({
            url: '${HomePageUrl}',
            type: 'POST',
            data: JSON.stringify(dataArray),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
               if(data !=null){
                    var row = '';
                    $(data).each(function (index, items) {


                        row+=' <div   class="col-md-4 col-sm-6">'
                        row+='<div class="boder_list">'
                                row+='<a href="${detailProduct}'+items.idProduct+'">'
                        row+='<div class="img_restaurant">'

                                    row+= '<img src="<c:url value="repository\'+items.image+'"/>" alt="pro">'
                                   row+='</div>'
                                    row+='<div class="infor_restaurant">'
                                        row+='<h4>'+items.nameProduct+'</h4>'
                                     row+='</div>'
                                    if(items.downpercen!=0){
                                        row+='<div class="content_pro">'
                                        row+='<span>'
                                        row+='<i class="glyphicon glyphicon-arrow-down"></i>'
                                        row+=items.downpercen+'%'
                                        row+='</span>'
                                        row+='</div>'
                                    }else{
                                        row+='<div style="height: 31px" class="content_pro">'
                                        row+='<span >'

                                        row+='</span>'
                                        row+='</div>'
                                    }

                                    row+='<div class="online_pro">'
                                        if(items.status==1){
                                            row+='<span class="stt offline " title=" Tạm ngưng"  >'

                                            row+='</span>'
                                        }else{
                                            row+='<span class="stt online" title=" Mở cửa"  >'
                                            row+='</span>'
                                        }

                                    row+='</div>'
                                    if(items.downpercen!=0){
                                        row+='<div class="class_cost">'
                                        row+='<span class="cost" >'
                                        row+= numberWithCommas(items.cost - (items.cost *items.downpercen)/100)
                                        row+='</span>'
                                        row+='</div>'
                                    }else{
                                        row+='<div class="class_cost">'
                                        row+='<span class="cost" >'
                                        row+= numberWithCommas((items.cost))
                                        row+='</span>'
                                        row+='</div>'
                                    }

                        row+='</a>'
                        row+='</div>'

                        row+='</div>'
                        row+='</div>'
                    });
                    $('#boder_list_pro').html(row);
                }

            },
            error: function (err) {
                alert(Error);
            }
        });


        function numberWithCommas(x) {
            x = x.toString();
            var pattern = /(-?\d+)(\d{3})/;
            while (pattern.test(x))
                x = x.replace(pattern, "$1,$2");
            return x;
        }

    }

</script>

</body>
</html>
