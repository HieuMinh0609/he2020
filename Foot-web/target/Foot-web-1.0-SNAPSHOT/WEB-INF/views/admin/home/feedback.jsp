<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 8/11/2019
  Time: 12:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url value="/api/admin/feedBack/showList" var="showFeedBackList"/>

<div class="modal-dialog ">
    <!-- Modal content-->
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <div class="titel_lo_re">
                <span class="register_click" >Feed back from user</span>
            </div>
        </div>
        <div class="modal-body">
            <div class="container">
                <div id="feedBackOnline_list">
                    <ul class="list-memeber-online">
                        <a href="/admin/feedbackRead">Marked as read all()</a>
                        <br><br>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

    $(function () {
        var currentPage = 1;
        var dataArray_feed={};
        dataArray_feed["page"]=currentPage;
        dataArray_feed["maxPageItems"]=7;
        loadPageFeedBack(dataArray_feed);
        $('#feedBackOnline_list ul').scroll(function () {
            if ($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
                currentPage += 1;
                dataArray_feed["page"]=currentPage;
                dataArray_feed["maxPageItems"]=7;
                loadPageFeedBack(dataArray_feed);
            }
        });
    });

    function  loadPageFeedBack(dataArray){
        $.ajax({
            url: '${showFeedBackList}',
            type: 'POST',
            data: JSON.stringify(dataArray),
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                var row = '';
                $(data).each(function (index, items) {
                    var d = new Date(items.dateTime)


                    if(items.check==1){
                        row+='<li class="list-item " style="background: #8bc34a8c;     margin-bottom: 3px;">';
                        row+='<span><a href="#">'+items.idUserFeedBack.email+'</a></span>';
                        row+='<h5 style="margin: 0px;"><i>'+items.feedBackContent+'<br><br>('+d.toLocaleDateString()+' - '+ d.toLocaleTimeString()+')</i></h5>';
                        row+='</li>';
                    }else{
                        row+='<li class="list-item">';
                        row+='<span><a href="#">'+items.idUserFeedBack.email+'</a></span>';
                        row+='<h5 style="margin: 0px;"><i>'+items.feedBackContent+'<br><br>('+d.toLocaleDateString()+' - '+ d.toLocaleTimeString()+')</i></h5>';
                        row+='</li>';
                    }



                });
                $('#feedBackOnline_list ul').append(row);
            },
            error: function (err) {
                alert(Error);
            }
        });
    }
</script>

