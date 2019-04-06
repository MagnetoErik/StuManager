<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    
    <title>办公自动化管理系统</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="../css/Style.css" rel="stylesheet" type="text/css"/>
	<style type="text/css">
<!--
body,td,th {
	font-size: 9pt;
}
body {
	background-color: #72A2D2;
}
a:link {
	text-decoration: none;
	color: #000000;
}
a:visited {
	text-decoration: none;
	color: #000000;
}
a:hover {
	text-decoration: none;
	color: #000000;
}
a:active {
	text-decoration: none;
	color: #000000;
}
.style1 {
	color: #00FF00;
	font-weight: bold;
}
.style2 {
	color: #FFFF00;
	font-weight: bold;
}
.style3 {color: #99CC66}
.STYLE4 {color: #000000}
.STYLE5 {color: #1A1712}
-->
</style>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" language="javascript">
	function show(d1,m)
	{
		
		if(document.getElementById(d1).style.display=='none')
		{
			document.getElementById(d1).style.display='block';
			var image=document.getElementById(m);
			image.src="../image/bclass2.gif";
			
		}
		else
		{
			document.getElementById(d1).style.display='none';
			var image=document.getElementById(m);
			image.src="../image/bclass1.gif";
			
		}
	}
	$(function(){
		$("#exit").click(function(){
			var btn = document.getElementById('exit');
			var r = confirm("你确认要退出登陆吗？");
			if(r==true){
				$.ajax({
					type:"get",
					url:"../submit"
				});
				alert("你已成功退出系统！");
				btn.href="../index.jsp";
				btn.target="_parent";
			}
			else{
				return;
			}
		});
	})
</script>
  <meta http-equiv="Content-Type" content="text/html; charset=gb2312"></head>
  
  <body>
    <div>
  <h3 class="style1 STYLE4">学生管理系统：</h3>
	</div>
	<div>
		<a href="javascript:onClick=show('1','img1')" >
		<img src="../image/bclass1.gif" id="img1" border="0" align="absmiddle"/>
		<img src="../image/f_new.gif" border="0" align="absmiddle">个人信息</a>
	</div>
		<div id="1" style="display:none">
			&nbsp;&nbsp;&nbsp;
			<img src="../image/z-top.gif" align="absmiddle">
			<img src="../image/f_hot.gif" width="19" height="9">
			<a href="jsp/modifyInfo.jsp" target="bottomFrame" title="我的信息">我的信息</a><br>
			&nbsp;&nbsp;&nbsp;
			<img src="../image/z-top.gif" align="absmiddle">
			<img src="../image/f_hot.gif" alt="" width="19" height="9">
			<a href="jsp/imageInfo.jsp" target="bottomFrame" title="修改头像">修改头像</a><br>
			&nbsp;&nbsp;&nbsp;
			<img src="../image/z-top.gif" align="absmiddle">
			<img src="../image/f_hot.gif" alt="" width="19" height="9">
			<a href="jsp/modifyPwd.jsp" target="bottomFrame" title="修改密码">修改密码</a><br>
		</div>
	
	
	
	
	
	<div>
		<a href="javascript:onClick=show('7','img7')">
		<img src="../image/bclass1.gif" id="img7" border="0" align="absmiddle"/>
		<img src="../image/f_new.gif" border="0" align="absmiddle">院系管理</a>
	</div>
		<div id="7" style="display:none">
			&nbsp;&nbsp;&nbsp;
			<img src="../image/z-top.gif" align="absmiddle">
			<img src="../image/f_hot.gif" width="19" height="9">
			<a href="jsp/DepInfo/addOrEditDep.jsp" target="bottomFrame" title="新增院系">新增院系</a><br>
			&nbsp;&nbsp;&nbsp;
			<img src="../image/z-top.gif" align="absmiddle">
			<img src="../image/f_hot.gif" width="19" height="9">
			<a href="../depInfo?method=list" target="bottomFrame" title="院系信息">院系信息</a><br>
		</div>
		
		
		
		
		<div>
		<a href="javascript:onClick=show('8','img8')">
		<img src="../image/bclass1.gif" id="img8" border="0" align="absmiddle"/>
		<img src="../image/f_new.gif" border="0" align="absmiddle">专业管理</a>
	</div>
		<div id="8" style="display:none">
			&nbsp;&nbsp;&nbsp;
			<img src="../image/z-top.gif" align="absmiddle">
			<img src="../image/f_hot.gif" width="19" height="9">
			<a href="jsp/SpecInfo/addOrEditSp.jsp" target="bottomFrame" title="新增专业">新增专业</a><br>
			&nbsp;&nbsp;&nbsp;
			<img src="../image/z-top.gif" align="absmiddle">
			<img src="../image/f_hot.gif" width="19" height="9">
			<a href="../specInfo?method=list" target="bottomFrame" title="专业信息">专业信息</a><br>
		</div>
		
		
		
		
		
	<div>
		<a href="javascript:onClick=show('2','img2')" >
		<img src="../image/bclass1.gif" id="img2" border="0" align="absmiddle"/>
		<img src="../image/f_new.gif" border="0" align="absmiddle" >班级管理</a>
	</div>
		<div id="2" style="display:none">
			&nbsp;&nbsp;&nbsp;
			<img src="../image/z-top.gif" align="absmiddle">
			<img src="../image/f_hot.gif" width="19" height="9">
			<a href="../class?method=add" target="bottomFrame">新增班级</a><br>
			&nbsp;&nbsp;&nbsp;
			<img src="../image/z-top.gif" align="absmiddle">
			<img src="../image/f_hot.gif" width="19" height="9">
			<a href="../class?method=list" target="bottomFrame">班级信息</a><br>
		</div>
		
		
		
		
		<div>
		<a href="javascript:onClick=show('9','img9')" >
		<img src="../image/bclass1.gif" id="img9" border="0" align="absmiddle"/>
		<img src="../image/f_new.gif" border="0" align="absmiddle" >教师管理</a>
	</div>
		<div id="9" style="display:none">
			&nbsp;&nbsp;&nbsp;
			<img src="../image/z-top.gif" align="absmiddle">
			<img src="../image/f_hot.gif" width="19" height="9">
			<a href="jsp/TeacherInfo/addEditTeacher.jsp" target="bottomFrame">新增教师</a><br>
			&nbsp;&nbsp;&nbsp;
			<img src="../image/z-top.gif" align="absmiddle">
			<img src="../image/f_hot.gif" width="19" height="9">
			<a href="../teacherInfo?method=list" target="bottomFrame">教师信息</a><br>
		</div>
		
		
		

		
		
		
		
		
	<div>
		<a href="javascript:onClick=show('4','img4')">
		<img src="../image/bclass1.gif" id="img4" border="0" align="absmiddle"/>
		<img src="../image/f_new.gif" border="0" align="absmiddle" >课程管理</a>
	</div>
		<div id="4" style="display:none">
			&nbsp;&nbsp;&nbsp;
			<img src="../image/z-top.gif" align="absmiddle">
			<img src="../image/f_hot.gif" width="19" height="9">
			<a href="jsp/CourseInfo/addEditCourse.jsp" target="bottomFrame">新增课程</a><br>
			&nbsp;&nbsp;&nbsp;
			<img src="../image/z-top.gif" align="absmiddle">
			<img src="../image/f_hot.gif" width="19" height="9">
			<a href="../courseInfo?method=list" target="bottomFrame" title="消息管理">课程信息</a><br>
		</div>
		
		
		
		
		
	<div>
		<a href="javascript:onClick=show('5','img5')">
		<img src="../image/bclass1.gif" id="img5" border="0" align="absmiddle"/>
		<img src="../image/f_new.gif" border="0" align="absmiddle">学生管理</a>
	</div>
		<div id="5" style="display:none">
			&nbsp;&nbsp;&nbsp;
			<img src="../image/z-top.gif" align="absmiddle">
			<img src="../image/f_hot.gif" alt="" width="19" height="9">
			<a href="jsp/StudentInfo/addEditStu.jsp" target="bottomFrame" title="新增学生">新增学生</a><br>
			&nbsp;&nbsp;&nbsp;
			<img src="../image/z-top.gif" align="absmiddle">
			<img src="../image/f_hot.gif" alt="" width="19" height="9">
			<a href="../studentInfo?method=list&count=1" target="bottomFrame" title="学生信息列表">学生信息</a><br>
		</div>
		





	<div>
		<a href="javascript:onClick=show('6','img6')">
		<img src="../image/bclass1.gif" id="img6" border="0" align="absmiddle"/>
		<img src="../image/f_new.gif" border="0" align="absmiddle">报表管理</a>
	</div>
		<div id="6" style="display:none">
			&nbsp;&nbsp;&nbsp;
			<img src="../image/z-top.gif" align="absmiddle">
			<img src="../image/f_hot.gif" width="19" height="9">
			<a href="../count?method=countDep" target="bottomFrame" title="院系专业统计">院系专业人员统计</a><br>
		</div>





<div><a href="javascript:void(0)" target="_parent" id="exit"><img src="../image/f_new.gif" border="0" align="absmiddle">退出系统</a></div>
  </body>
</html>
