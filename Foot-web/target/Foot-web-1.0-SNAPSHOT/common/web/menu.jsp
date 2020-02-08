<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 8/16/2019
  Time: 10:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var="MenuPageUrl" value="api/wBHome/loadMenuPage"/>
<c:url value="product-" var="detailProduct"/>
<div id="menu_console" class="col-md-4">

    <div class="row">
        <div class="title_best_pro">
            <c:set var="image_Favorites_2" value="/repository/product/Favorites_2.png"/>

            <img  src="<c:url value='${image_Favorites_2}'/>" alt="star">
            <span> Sản phẩm được ưa thích</span>
        </div>
        <div id="list_best_menu" class="pro_best_view">

                <ul class="list-history-menu">



                </ul>




        </div>




    </div>

    <div class="col-md-12">
        <div class="row">
            <div class="gird1">
                <div  class="img-banner">

                    <div class="blur"></div>
                    <c:set var="image_host_line" value="/repository/product/host_line.png"/>
                    <img class="banner-text" src="<c:url value='${image_host_line}'/>" alt="">
                </div>
            </div>
        </div>

    </div>

</div>

<script>
    $(function () {

        var currentPage = 1;
        var dataArray={};
        dataArray["page"]=currentPage;
        dataArray["maxPageItems"]=7;
        loadMenuData(dataArray);


        $('#list_best_menu ul').scroll(function () {
            if ($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
                currentPage += 1;
                dataArray["page"]=currentPage;
                dataArray["maxPageItems"]=7;
                loadMenuData(dataArray);
            }
        });
    });

    function loadMenuData(dataArray) {

        $.ajax({
            url: '${MenuPageUrl}',
            type: 'POST',
            data: JSON.stringify(dataArray),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                if(data !=null){
                    var row = '';
                    $(data).each(function (index, items) {

                        row+='<li>'

                        row+='<div class="boder_list">'
                        row+='<a href="${detailProduct}'+items.idProduct+'">'
                        row+='<div class="img_restaurant">'

                        row+= '<img src="<c:url value="repository\'+items.image+'"/>" alt="pro">'
                        row+='</div>'
                        row+='<div class="infor_restaurant">'
                        row+='<h4>'+items.nameProduct+'</h4>'
                        row+='</div>'
                        row+='<div class="content_view_reply">'

                        row+='<i style="color: #633a26;" title="view" class="glyphicon glyphicon-eye-open">'
                        row+='  </i>'
                        row+='<span class="title_view_count"> ' +numberWithCommas(items.view)+'&nbsp;view</span>'
                        row+='<i style="color: #633a26;  margin-left: 60px;" title="reply" class="glyphicon glyphicon-comment"></i>'
                        row+='<span class="title_reply_count"> ' +numberWithCommas(items.comment)+'&nbsp;reply</span>'

                        row+='</div>'
                        if(items.downpercen!=0){
                            row+='<div class="content_pro">'
                            row+='<span>'
                            row+='<i class="glyphicon glyphicon-arrow-down"></i>'
                            row+=items.downpercen+' %'
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
                            row+= numberWithCommas(items.cost  -(items.cost *items.downpercen)/100)
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

                        row+='</li>'
                    });
                    $('#list_best_menu ul').append(row);
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
