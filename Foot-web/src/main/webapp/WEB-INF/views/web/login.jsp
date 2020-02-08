<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>

<c:url var="formLogin" value="/j_spring_security_check"/>
<c:url var="formRegister" value="/register"/>
<c:url var="resetPassword" value="/api/user/reset/password"/>
<br><br>
<div class="col-md-8">
<div  id="location" class="container">

    <div id="config_col"  class="col-md-6 col-sm-12 col-12">
        <div class="img_login">
            <img class="img_wellcome" src='<c:url value="/image/sanwick.jpeg"/>'>

        </div>
        <div class="text_wellcome" >
            <hl class="set_text_wel">
                <span>Wellcome</span>
                <span >Enjoy our cuisine</span>
            </hl>
        </div>
    </div>


    <div id="form_login" class="col-md-6 col-sm-12 col-12">

        <div class="titel_lo_re">
            <span  class="login100_form_title login_click">Login</span>
            <span class="login100_form_title2">/</span>
            <span class="register_click" >Register</span>


        </div>
        <c:if test="${not empty messageResponse}">
            <div class="alert alert-${alert} alert-dismissible">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    ${messageResponse}
            </div>

        </c:if>

        <div class="form_login_action">
            <form action="${formLogin}" method="POST" class="form-sigin">
                <div class="loginname">
                    <input  class="input_name "  name="j_username" type="text"  placeholder="Name Login" >
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
						<i class="glyphicon glyphicon-user"></i>
						</span>
                </div>
                <div class="loginname">
                    <input   class="input_name" type="password"  name="j_password" placeholder="Password" >
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
						<i class="glyphicon glyphicon-lock"></i>
						</span>
                </div>
                <div class="buton_login">
                    <button type="submit" class="login_btn_form">Login</button>

                </div>
                <span class="link_fogot_pass"><i>you forgot password ?</i></span>
            </form>

            <c:if test="${param.incorrectAccount !=null}">

                <div class="alert alert-danger alert-dismissible">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                     <span><spring:message code="label.login.wrong"/></span>
                </div>
            </c:if>
            <c:if test="${param.accessDenied !=null}">

                <div class="alert alert-danger alert-dismissible">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <span><spring:message code="label.login.access.denaid"/></span>
                </div>
            </c:if>
        </div>

    </div>

    <div id="form_register" class="col-md-6 col-sm-12 col-12" style="display: none;">


        <form  id="submit_form_regis" action="${formRegister}" method="POST" name="submit_form_regis"  class="form-sigin">
            <div id="nofiction" class="alert alert-warning alert-dismissible" style="display: none;">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <span><spring:message code="label.message.error.register"/></span>
                <br>
                <span><spring:message code="label.uer.erorr.register2"/></span>
            </div>
            <div class="loginname">
                <input  class="input_name " id="nameLogin" name="nameLogin" type="text"  placeholder="Name Login" >
                <span class="focus-input100"></span>
                <span class="symbol-input100">
						<i class="glyphicon glyphicon-user"></i>
						</span>

            </div>
            <div class="loginname">
                <input  class="input_name " id="nameFull" name="nameFull" type="text"  placeholder="Name Full" >
                <span class="focus-input100"></span>
                <span class="symbol-input100">
						<i class="glyphicon glyphicon-user"></i>
						</span>

            </div>
            <div class="loginname">
                <input   class="input_name" type="password"  id="passWord" name="passWord" placeholder="Password" >
                <span class="focus-input100"></span>
                <span class="symbol-input100">
						<i class="glyphicon glyphicon-lock"></i>
						</span>


            </div>
            <div class="loginname">
                <input   class="input_name" type="text" id="email" name="email" placeholder="Email" >
                <span class="focus-input100"></span>
                <span class="symbol-input100">
						<i class="glyphicon glyphicon-envelope"></i>
						</span>


            </div>
            <div class="loginname">
                <input   class="input_name" type="text" id="place" name="place" placeholder="Place" >
                <span class="focus-input100"></span>
                <span class="symbol-input100">
						<i class="glyphicon glyphicon-pencil"></i>
						</span>


            </div>


            <div class="loginname">
                <input   class="input_name" type="text" id="phoneNumber" name="phoneNumber" placeholder="Phone Number" >
                <span class="focus-input100"></span>
                <span class="symbol-input100">
						<i class="glyphicon glyphicon-earphone"></i>
						</span>


            </div>

            <div class="radio">
                <label><input type="radio" value="1" name="sex" checked>Male</label>

                <label><input type="radio" value="0" name="sex">Female</label>
            </div>

            <div class="buton_login">
                <button type="button" id="btn_register" onclick="return validateForm()"   class="login_btn_form">Register</button>

            </div>



        </form>
    </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade " id="myModalReset" role="dialog">
    <div class="modal-dialog modal-md">
    <!-- Modal content-->
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">Reset your password</h4>
        </div>
        <div class="modal-body">
            <div class="loginname">
                <input   class="input_name" type="text" id="email_reset" name="email" placeholder="Email" >
                <span class="focus-input100"></span>
                <span class="symbol-input100">
						<i class="glyphicon glyphicon-envelope"></i>
						</span>


            </div>
        </div>
        <div class="modal-footer">
            <button id="btn_reset_pass" type="button" class="btn btn-warning">Reset</button>
        </div>
    </div>
    </div>
</div>

<script type="text/javascript" >
    $(document).ready(function () {

    });
    $('.link_fogot_pass').click(function () {
        $('#myModalReset').modal('toggle');
    });

    $('#btn_reset_pass').click(function () {
        var dataArray={};
        dataArray["email"]=$('#email_reset').val();


        if($('#email_reset').val()!=''){
            $.ajax({
                url: '${resetPassword}',
                type: 'POST',
                data: JSON.stringify(dataArray),
                dataType: 'json',
                contentType: "application/json",
                success: function (res) {

                    if(res){
                        window.location.href = "<c:url value='/login?message=reset_password_success'/>";
                    }else{
                        window.location.href = "<c:url value='/login?message=error_system'/>";
                    }
                },
                error: function (res) {
                    window.location.href = "<c:url value='/login?message=error_system'/>";

                }
            });
        }else{
            $('#email_reset').css("border", "1px solid red");
        }
    });

    $("#btn_register").click(function (e) {
        e.preventDefault();
        if(validateForm() === false){
            $('#nofiction').show();
        }else{
            $('#nofiction').hide();
            addUser();

        }


    });

    function validateForm(){
        if($('#nameLogin').val()==''){
            return false;
        }else if($('#nameFull').val()==''){
            return false;
        }
        else if($('#password').val()==''){
            return false;
        }

        else if($('#email').val()==''){
            return false;
        }

        else if($('#place').val()==''){
            return false;
        }

        else if($('#phoneNumber').val()==''){
            return false;
        }
            return true;



    }

    function addUser(){

        registerUser()
    }

    function registerUser() {
        $.ajax({
            url: '${formRegister}',
            type: 'POST',
            data: $('#submit_form_regis').serialize(),
            success: function (res) {
                window.location.href = "<c:url value='/login?message=create_success'/>";

            },
            error: function (res) {
                window.location.href = "<c:url value='/login?message=error_system'/>";

            }
        });
    }





</script>