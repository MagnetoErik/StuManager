<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生管理系统---用户登录test</title>
<link href="css/css.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function(){
		function submit(){
			var Username = $("#username").val();
			if(Username==''){
				alert('请输入用户名');
				return;
			}
			var pwd = $("#password").val();
			if(pwd==''){
				alert("请输入密码");
				return;				
			}
			var Verify = $("#verify").val();	
			if(Verify==''){
				alert("请输入验证码");
			}
			$.ajax({
				type:"post",
				url:"adminUser?method=submit",
				data:({username:Username,password:pwd,verify:Verify}),
				success:function(data){
					if(data=='0'){
						alert("验证码错误，请重新输入。");
						$("#verify").val('');
						document.getElementById("verifyimg").src="verify?"+Math.random();
					}
					if(data=='1'){
						window.location.href='pages/home.jsp';
					}
					if(data=='2'){
						alert('登陆失败,请检查用户名或密码是否输入正确');
						$("#password").val('');
						$("#verify").val('');
						document.getElementById("verifyimg").src="verify?"+Math.random();
					}
					if(data=='3'){
						alert('用户名不存在，请先注册后再登陆！');
						window.location.href='reg.jsp';
					}
					if(data=='4'){
						alert('内部错误，请联系工作人员。Q2227591877');
					}
				}
			});
		}
		
		function f1(){
			var img = document.getElementById("verifyimg");
			img.src="verify?"+Math.random();
		}
		
		
		document.onkeydown = function (e) {
            if (!e) e = window.event;
            if ((e.keyCode || e.which) == 13) {
            	submit();
            }
		}		
		
		$("#sbmbtn").click(function(){
			submit();
		})

	})
	
	
</script>
</head>
<body>
<body>
<div class="wrapper">
	<form action="submit" method="post" id="formid">
	<div class="loginBox">
		<div class="loginBoxCenter">
			<p><label for="username">用户名：</label></p>
			<p><input type="text" id="username" name="username" class="loginInput" autofocus="autofocus" required="required" autocomplete="off" placeholder="请输入用户名" value="" /></p>
			<p><label for="password">密码</label><a class="forgetLink" href="reg.jsp" style="margin-left: 20px">用户注册</a><a class="forgetLink" href="#">忘记密码?</a></p>
			<p><input type="password" id="password" name="password" class="loginInput" required="required" placeholder="请输入密码" value="" /></p>
			<p><label for="username">验证码：</label></p>
			<p><input type="text" id="verify" name="verify" class="loginInput" style="width: 160px"/>
				<a href="javascript:void(0);" onclick="f1()"><img id="verifyimg" src="verify" style="height: 40px;margin-top: -5px;margin-left: 20px;position: absolute;" title="验证码"></a>
			</p>
		</div>
		<div class="loginBoxButtons">
			<input id="remember" type="checkbox" name="remember" />
			<label for="remember">记住登录状态</label>
			<button class="loginBtn" type="button" id="sbmbtn">登录</button>
		</div>
	</div>
	</form>
	<%-- //使用userBean创建用户实体,setProperty的name代表java实体对象
	<jsp:useBean id = "sampleBean2" class = "com.jsp.bean.UserInfoBean"/> 
	<jsp:setProperty property="userName" name="sampleBean2" value="123"/>
	<jsp:getProperty property="userName" name="sampleBean2"/> --%>
</div>
</body>
</html>