<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 1/11/2020
  Time: 12:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="/api/admin/aDhome/pageHistory" var="HomeUrlHis"/>
<c:url value="/api/admin/aDhome/pageReport" var="HomeUrlRep"/>
<c:url value="/api/admin/adHome/showMemberOnline" var="showMemberUrl"/>
<c:url value="/admin/home" var="HomeUrl"/>
<c:url value="/api/admin/adHome/showRevenueOnDay" var="showRevenueOnDayUrl"/>
<c:url value="/api/admin/showBillNew" var="showBillNew"/>

<script>

    var d = new Date();
    $(document).ready(function () {
        var currentPage = 1;
        var dataArray={};
        dataArray["page"]=currentPage;
        dataArray["maxPageItems"]=7;
        loadPageData(dataArray);


        $('#id_scroll_his ul').scroll(function () {
            if ($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
                currentPage += 1;
                dataArray["page"]=currentPage;
                dataArray["maxPageItems"]=7;
                loadPageData(dataArray);
            }
        });
    });

    $('#seeMemberOnline').on('click',function () {
        $('#myModalMemberOnLine').modal('toggle');
        var currentPage = 1;
        var dataArray={};
        dataArray["page"]=currentPage;
        dataArray["maxPageItems"]=10;
        showMember(dataArray);

        $('#memberOnline_list ul').scroll(function () {
            if ($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
                currentPage += 1;
                dataArray["page"]=currentPage;
                dataArray["maxPageItems"]=10;
                showMember(dataArray);
            }
        });

    });
    $('#seeRevenueOnDay').on('click',function () {
        $('#myModalRevenueOnDay').modal('toggle');
        $.ajax({
            url: '${showRevenueOnDayUrl}',
            type:'POST',
            dataType:'json',
            contentType: "application/json",
            success: function (data) {

                var row = '';
                $(data).each(function (i, items) {
                    var nameType="";
                    row+='<li>';
                    <c:forEach var="items" items="${model.typeDTOS}" varStatus="loop">
                      if(i==${loop.index}){
                          nameType = '${items.typeName}';
                      }
                    </c:forEach>
                    row+='<i style="margin-right: 15px;color: red;" class="glyphicon glyphicon-tags"></i>';
                    row+='<span>'+  nameType+' : ' +numberWithCommas(items)+' VND<br></span>';
                    row+='<br>'
                    row+='</li>';

                });
                $('#RevenueOnDay_list ul').html(row);
            },
            error: function (err) {
                alert(Error);
            }
        });
    });

    $('#click_billOnNew').on('click',function () {
        var showUrl = '${showBillNew}';
        $('#myModalBillNew').load(showUrl,'',function () {
            $('#myModalBillNew').modal('toggle');

        });
    });

    function showMember(dataArray){
        $.ajax({
            url: '${showMemberUrl}',
            type:'POST',
            data: JSON.stringify(dataArray),
            dataType:'json',
            contentType: "application/json",
            success: function (data) {

                var row = '';
                $(data).each(function (index, items) {


                    row+='<li class="list-item">';
                    if (items.sex ==1){
                        row+='<img style="width: 40px; " src="/image/man.png" alt="icon_user">'
                    }else{
                        row+='<img style="width: 40px " src="/image/girl.png" alt="icon_user">'
                    }
                    row+='<span><a href="#">'+items.nameFull+'</a></span>';
                    row+='<img style="width: 15px; float: right; margin-right: 15px;" src="<c:url value='/image/green-transparent-3.png'/>" alt="icon_online">';
                    row+='<h6 style="margin: 0px;"><br>'+items.place+'</h6>';
                    row+='</li>';

                });
                $('#memberOnline_list ul').append(row);
            },
            error: function (err) {
                alert(Error);
            }
        });
    }

    $(function() {
        var x=numberWithCommas(${model.revenueOnDay})
        $('#revenue_onday').text(x);

    });

    $(function () {
        currentPageHome = 0;
        $(window).scroll(function () {
            if ($(window).scrollTop() + $(window).height() == $(document).height()) {
                currentPageHome += 1;
                if(currentPageHome==1){
                    loadDataHome1(currentPageHome);
                    $('#id_report_inYear').css('display','block');
                }
                if(currentPageHome==2){
                    setTimeout(function() {
                        loadDataHome2(currentPageHome);
                        $('#chart_div').css('display','block');
                        $('#loading_gif').css('display','none');
                    },1000);


                }

            }
        });
    });
    function loadDataHome2(currentPageHome) {
        $.ajax({
            url: '${HomeUrlRep}',
            type: 'POST',
            data: {currentPageHome:currentPageHome},
            success: function (data) {
                var year_1=[];
                var year_2=[];
                var year_3=[];
                $(data).each(function (index, items) {
                    if(index<=11){
                        year_1.push(items);
                    }
                    if(index<=23 && index>11){
                        year_2.push(items);
                    }
                    if(index>23 ){
                        year_3.push(items);
                    }
                });

                google.charts.load('current', {'packages':['corechart']});
                google.charts.setOnLoadCallback(drawChart);

                function drawChart() {
                    var data = google.visualization.arrayToDataTable([
                        ['Month', (d.getFullYear()-2).toString() , (d.getFullYear()-1).toString(),(d.getFullYear()).toString()],
                        ['1',  year_1[0], year_2[0], year_3[0]],
                        ['2', year_1[1], year_2[1], year_3[1]],
                        ['3', year_1[2], year_2[2], year_3[2]],
                        ['4', year_1[3], year_2[3], year_3[3]],
                        ['5', year_1[4], year_2[4], year_3[4]],
                        ['6', year_1[5], year_2[5], year_3[5]],
                        ['7', year_1[6], year_2[6], year_3[6]],
                        ['8', year_1[7], year_2[7], year_3[7]],
                        ['9', year_1[8], year_2[8], year_3[8]],
                        ['10', year_1[9], year_2[9], year_3[9]],
                        ['11', year_1[10], year_2[10], year_3[10]],
                        ['12', year_1[11], year_2[11], year_3[11]],

                    ]);

                    var options = {
                        title: 'Revenue store in 3 year',
                        hAxis: {title: 'Month',  titleTextStyle: {color: '#333'}},
                        vAxis: {minValue: 0}

                    };

                    var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
                    chart.draw(data, options);
                }
            },
            error: function (err) {
                alert(Error);
            }
        });
    }
    function loadDataHome1(currentPageHome) {
        $.ajax({
            url: '${HomeUrlRep}',
            type: 'POST',
            data: {currentPageHome:currentPageHome},
            success: function (data) {
                var dataArray = {};

                $(data).each(function (index, item) {
                    <c:forEach var="items" items="${model.typeDTOS}" varStatus="loop">
                    if(index==${loop.index}){
                        dataArray["${items.typeName}"]= item;
                    }
                    </c:forEach>

                });


                google.charts.load("current", {packages:["corechart"]});
                google.charts.setOnLoadCallback(drawChart1);


                function drawChart1() {
                    var data = new google.visualization.DataTable();
                    data.addColumn('string', 'Category');
                    data.addColumn('number', 'Amount');

                    $.each( dataArray, function( key, value ) {
                        data.addRow([key,value]);
                    })


                    var options = {
                        title: 'My Store Sales '+d.getFullYear(),
                        pieHole: 0.4,
                    };

                    var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
                    chart.draw(data, options);
                }
            },
            error: function (err) {
                alert(Error);
            }
        });
    }

    function loadPageData(dataArray) {

        $.ajax({
            url: '${HomeUrlHis}',
            type: 'POST',
            data: JSON.stringify(dataArray),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                if(data !=null){
                    var row = '';
                    $(data).each(function (index, items) {
                        var d = new Date(items.dateTime);

                        row+='<li class="list-item">';
                        row+='<span><a href="#">'+items.idHistoryUserEntity.nameFull+'</a></span>';

                        row+='<h6 style="margin: 0px;"><i>'+items.content+'<br><br>('+d.toLocaleDateString()+' - '+ d.toLocaleTimeString()+')</i></h6>';
                        row+='</li>';

                    });
                    $('#id_scroll_his ul').append(row);
                }

            },
            error: function (err) {
                alert(Error);
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

    let myChart = document.getElementById('myChart_on7day').getContext('2d');

    // Global Options
    Chart.defaults.global.defaultFontFamily = 'Lato';
    Chart.defaults.global.defaultFontSize = 18;
    Chart.defaults.global.defaultFontColor = '#777';

    let massPopChart = new Chart(myChart, {
        type:'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
        data:{
            labels:['6','5','4','3','2','1','0'],
            datasets:[{
                label:'VND',
                data:[
                    ${model.findRevenue7Day.get(0)},
                    ${model.findRevenue7Day.get(1)},
                    ${model.findRevenue7Day.get(2)},
                    ${model.findRevenue7Day.get(3)},
                    ${model.findRevenue7Day.get(4)},
                    ${model.findRevenue7Day.get(5)},
                    ${model.findRevenue7Day.get(6)},

                ],
                //backgroundColor:'green',
                backgroundColor:[
                    'rgba(255, 99, 132, 0.6)',
                    'rgba(54, 162, 235, 0.6)',
                    'rgba(255, 206, 86, 0.6)',
                    'rgba(255, 99, 132, 0.6)',
                    'rgba(255, 0, 0, 0.8)',
                    'hsla(0, 100%, 30%, 0.3)',
                    'rgba(255, 0, 0, 0.4)',
                ],
                borderWidth:1,
                borderColor:'#777',
                hoverBorderWidth:1,
                hoverBorderColor:'#000'
            }]
        },
        options:{
            title:{
                display:true,
                text:'Revenue on 7 day ago',
                fontSize:25
            },
            legend:{
                display:true,
                position:'right',
                labels:{
                    fontColor:'#000'
                }
            },
            layout:{
                padding:{
                    left:15,
                    right:0,
                    bottom:0,
                    top:30
                }
            },
            tooltips:{
                enabled:true
            }
        }
    });

    let myChart1 = document.getElementById('myChart_revenue').getContext('2d');
    // Global Options
    Chart.defaults.global.defaultFontFamily = 'Lato';
    Chart.defaults.global.defaultFontSize = 18;
    Chart.defaults.global.defaultFontColor = '#777';


    var n = d.getMonth()+1;
    let massPopChart1 = new Chart(myChart1, {
        type:'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
        data:{
            labels:[
            <c:forEach var="items" items="${model.typeDTOS}" varStatus="loop">
                    '${items.typeName}',
            </c:forEach>
            ],
            datasets:[{
                label:'amont',
                data:[
                    <c:forEach var="items" items="${model.typeDTOS}" varStatus="loop">
                        ${model.findRevenueType.get(loop.index)},
                    </c:forEach>
                ],
                //backgroundColor:'green',
                backgroundColor:[
                    'rgba(255, 99, 132, 0.6)',
                    'rgba(54, 162, 235, 0.6)',
                    'rgba(255, 206, 86, 0.6)',
                ],
                borderWidth:1,
                borderColor:'#777',
                hoverBorderWidth:1,
                hoverBorderColor:'#000'
            }]
        },
        options:{
            title:{
                display:true,
                text:'Amont on '+n +" month",
                fontSize:25
            },
            legend:{
                display:true,
                position:'right',
                labels:{
                    fontColor:'#000',
                    fontSize:20
                }
            },
            layout:{
                padding:{
                    left:15,
                    right:0,
                    bottom:0,
                    top:30
                }
            },
            tooltips:{
                enabled:true
            }
        }
    });

</script>
