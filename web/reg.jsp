<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生管理系统---用户注册</title>
<link href="css/css.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	//注册按钮事件
	$(function(){
		$("#regButton").click(function(){
			var username = $("#username").val();
			if(username==''){
				alert("请输入用户名!");
				return;
			}
			
			var password = $("#password").val();
			if(password==''){
				alert("请输入密码");
				return;
			}
			var password1 = $("#password1").val();
			if(password1==''){
				alert("请确认密码！");
				return;
			}

			if(password!=password1){
				alert("两次输入的密码不一致，请重新输入！");
				return;
			}
			var val = $("input[name='user_sex']:checked").val();
			if(val==''){
				alert("请选择正确的性别");
				return;
			}
			var imagepath = $("#imagepath").val();
			if(imagepath==''){
				alert("请上传头像！");
				return;
			}
			if(i==0){
				alert("用户名已存在，请重新输入");
				$("#username").focus();
			}
			else if(i==2){
				alert("请输入用户名！");
				$("#username").focus();
			}
			else{
				$("#userRegFormId").submit();
			}		
		});
		$("#username").blur(function(){
			var username = $("#username").val();
			var content = document.getElementById("content");
			var content1 = document.getElementById("content1");
			var content2 = document.getElementById("content2");
			$.ajax({
				type:"post",
				url:"adminUser?method=usernameCheck",
				data:{username:username},
				success:function(data){
					if(username==''){
						content2.style.display="block";
						content.style.display="none";
						content1.style.display="none";
						content2.style.color="red";
						i=2;
					}
					else if(data==1){
						content.style.display="block";
						content.style.color="red";
						content1.style.display="none";
						content2.style.display="none";
						i=0;
					}else{
						content1.style.display="block";
						content1.style.color="yellowgreen";
						content.style.display="none";
						content2.style.display="none";
						i=1;
					}
				},
				error:function(err){
					
				}
			})
		});		
	})
</script>
</head>

<body>
<body>
<div class="wrapper">
	<form action="adminUser?method=regUser" id="userRegFormId" method="post" enctype="multipart/form-data">
	<div class="loginBox">
	<!-- autofocus="autofocus" required="required" autocomplete="off"  -->
		<div class="loginBoxCenter">
			<p><label for="username">用户名：</label></p>
			<p><input type="text" id="username" name="username" class="loginInput" autofocus="autofocus"  placeholder="请输入用户名" value=""/>
				<span id="content" style="display: none" >用户名已存在请重新输入</span>
				<span id="content1" style="display: none" >用户名可以正常注册啦</span>
				<span id="content2" style="display: none;" >用户名为空,请输入用户名</span>
			</p>
			<p><label for="password">密码：</label></p>
			<p><input type="password" id="password" name="password" class="loginInput"  placeholder="请输入密码" value="" /></p>
			<p><label for="password">确认密码：</label></p>
			<p><input type="password" id="password1" name="password1" class="loginInput"  placeholder="请输入密码" value="" /></p>
			<p><label for="username">真实性名：</label></p>
			<p><input type="text" id="trueName" name="trueName" class="loginInput" placeholder="请输入真实姓名" value="" /></p>
			<p><label for="username">性别：</label></p>
			<p><input type="radio" name="user_sex" id="sex1" value="男" checked="checked"/>男&nbsp;&nbsp;
             <input type="radio" name="user_sex" id="sex2" value="女"/>女</p>
            <p><label for="username">头像:</label></p>
            <p>
            	<input type="file" name="imagepath" id="imagepath"/>
            </p>
		</div>
		<div class="loginBoxButtons">
			<button class="loginBtn" id="regButton" type="button">注册</button>
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
