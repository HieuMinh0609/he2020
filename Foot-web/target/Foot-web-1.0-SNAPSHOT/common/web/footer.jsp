<%--
  Created by IntelliJ IDEA.
  User: hieu4
  Date: 6/4/2019
  Time: 2:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container-fluid">
    <div class="col-sm-12 col-md-12">
        <div class="row">
            <div class="box_1">
                <h3>Đơn hàng của bạn sẽ được bảo quản như thế nào?</h3>
                <span>
				EveryThing sẽ bảo quản đơn của bạn bằng túi & thùng để chống nắng mưa, giữ nhiệt... trên đường đi một cách tốt nhất.
				</span>
                <br><br>
                <c:set var="image_preservation" value="/repository/product/Box-food-preservation-footer.jpg"/>

                <img src="<c:url value='${image_preservation}'/>" alt="box_moto">

            </div>
        </div>
    </div>
    <br>
    <br>
    <div class="col-sm-12 col-md-12">
        <div class="row">
            <div class="box_2">
                <div class="col-md-9">
                    <h3>EveryThing.vn Hợp tác nhân viên giao nhận - Now Partners</h3>
                    <span>
				Giúp bạn tăng thu nhập trong thời gian rảnh rỗi
				</span>
                    <br>
                    <span>EveryThing tìm kiếm hợp tác với các cá nhân để thực hiện việc giao hàng, chúng tôi sẽ cung cấp ứng dụng (app),
				1 số dụng cụ cần thiết để bạn có thể tiếp nhận & giao hàng, kiếm thêm thu nhập </span><br>
                    <span>Đăng ký tham gia tại đây hoặc gửi Email qua jobs@everything.vn - hoặc gọi qua số điện thoại 0283 925 1111.</span>

                </div>

                <div class="class=" col-md-3>
                <c:set var="image_deliverynow" value="/repository/product/bg-deliverynow-dat-mon-truc-tuyen-giao-hang-tan-noi.png"/>
                 <img src="<c:url value='${image_deliverynow}'/>"  alt="phone">
            </div>
        </div>
    </div>
</div>
</div>
<div class="container-fluid">

    <div class="card_hr_footers row">
        <hr>
    </div>
    <div id="footer" class="row">
        <div class="col-md-4">
            <c:set var="image_logo" value="/repository/product/logo.png"/>
             <img style="width: 40%;" src="<c:url value='${image_logo}'/>" alt="logo">
            <br>
            <br>
            <span>&copy; 2019 EveryThing - Foody restaurants</span>
        </div>

        <div class="col-md-4">
            <span style="font-weight: bold">Địa chỉ nhà hàng</span>
            <br>
            <span>

					EveryThing
					Tòa nhà Jabes 1,
					244 Cống Quỳnh, P. Phạm Ngũ Lão, Cầu Giấy, Tp.HN
					GP ĐKKD: 0311828036
					do sở Kế hoạch và Đầu Tư TPHN cấp ngày 11/06/2012
			  	</span>
        </div>
        <div class="col-lg-4">
            <span style="font-weight: bold">EveryThing</span>
            <br>
            <a href="">Giới thiệu</a>
            <br>
            <a href="">Góp ý</a>
            <br>
            <a href="">Quy chế</a>
        </div>
    </div>
</div>