
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%@taglib prefix="c" uri="../../../WEB-INF/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增班级信息</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	$(function(){
		$.ajax({
			type:"get",
			url:"<%=path%>/courseInfo?method=queryCourseTypeList",
			data:{},
			success:function(str){
				var list = JSON.parse(str)
				$("#courseTypeId").empty();
				$("#courseTypeId").append('<option value="">请选择所属课程类别信息</option>');
				$.each(list,function(n,bean){
					$("#courseTypeId").append('<option value="'+bean.coursetypeid+'">'+bean.coursetypename+'</option>');
				})
			}
		})
		var method = $("#method").val();
		if(method=="add"){
			var i = 1;	
			$("#courseInfoCode").blur(function(){
				var courseInfoCode = $("#courseInfoCode").val();
				var courseInfoName = $("#courseInfoName").val();
				var succ = document.getElementById("succ");
				var err = document.getElementById("err");
				$.ajax({
					type:"post",
					url:"<%=path%>/courseInfo?method=selectCourseCN",
					data:{courseInfoCode:courseInfoCode,courseInfoName:courseInfoName},
					success:function(str){
						if(str==0){
							succ.style.display="block";
							err.style.display="none";
							i = 0;
						}
						else{
							succ.style.display="none";
							err.style.display="block";
							i = 1;
						}
					}
				})
			})
			$("#saveBtn").click(function(){
				if(i==0){
					btn();
				}
				else{
					alert('已存在课程代号');
					$("#courseInfoCode").focus();
				}
			})
			
		}
		if(method=="updateCourseInfoMsg"){
			document.getElementById("courseInfoCode").readOnly="true";
			$("#saveBtn").click(function(){
				btn();
			})
		}
	})

		function btn(){
			var courseTypeId = $("#courseTypeId").val();
			if(courseTypeId==''){
				alert('请选择所属课程类别信息');
				return;
			}
			var courseInfoCode = document.getElementById("courseInfoCode").value;
			if(courseInfoCode==''){
				alert('请输入课程代号！!');
				return;
			}
			if(isNaN(courseInfoCode)){
				alert('课程代号格式错误只能为数字');
				return;
			}
			var courseInfoName = document.getElementById("courseInfoName").value;
			if(courseInfoName==''){
				alert('请输入课程名称!');
				return;
			}
			//提交表单
			$("#info").submit();
		}

</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">班级管理&gt;&gt;新增班级信息</span>
</div>
<form id="info" name="info" action="<%=path %>/courseInfo" method="post">    
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">新增/修改班级信息</TD>
              <TD class="edittitleright">&nbsp;</TD>
            </TR>
        </TABLE>
     </td>
   </tr>
   <tr>
     <td height="1" bgcolor="#8f8f8f"></td>
   </tr>
   <tr>
     <td >
     <table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
       
       <c:choose>
       	<c:when test="${courseList==null}">
       		<input type="hidden" name="method" value="add" id="method"/>
       	</c:when>
       	<c:otherwise>
       		<input type="hidden" name="method" value="updateCourseInfoMsg" id="method"/>
       	</c:otherwise>
       </c:choose>

        
        <tr>
          <td align="right" style="padding-right:5px">所属课程类别：</td>
          <td id="text">
          
          	 <select name="courseTypeId" id="courseTypeId" >
             		<option value="">请选择所属课程类别信息</option>
             		<c:forEach items="${depList}" var="dep">
             			<option value="${dep.courseTypeId}">${dep.courseTypeName}</option>
             		
             		</c:forEach>
             </select>
          </td>
          
        </tr>
        
        
        <input type="hidden" name="courseInfoId" id="courseInfoId" value="${courseList.courseInfoId }" />
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>课程代码：</td>
          <td colspan="3">
            <input style="float: left;" type="text" id="courseInfoCode" name="courseInfoCode" value="${courseList.courseInfoCode }"/>
            <span style="display: none;float: left;color: yellowgreen" id="succ">不存在院系代码，可以正常添加</span>
            <span style="display: none;float: left;color: red" id="err">已存在院系代码，请重新输入</span>
          </td> 
        </tr> 
        
        
        <tr>
          <td width="15%" align="right" style="padding-right:5px"><font color="red">*</font>课程名称：</td>
          <td width="85%">
          		<input type="text" name="courseInfoName" id="courseInfoName" value="${courseList.courseInfoName }" />
          </td>
         </tr> 
            
            
            
        <tr>
          <td align="right" style="padding-right:5px">课程介绍：</td>
          <td colspan="3">
            <input type="text" id="courseInfoProj" name="courseInfoProj" value="${courseList.courseInfoProj }"/>
          </td> 
        </tr> 
        
        
         <tr>
          <td align="right" style="padding-right:5px">理论学时：</td>
          <td colspan="3">
            <input type="text" id="courseInfoRstPer" name="courseInfoRstPer" value="${courseList.courseInfoRstPer }"/>
          </td> 
        </tr> 
        
        
         <tr>
          <td align="right" style="padding-right:5px">实践学时：</td>
          <td colspan="3">
            <input type="text" id="courseInfoPraPer" name="courseInfoPraPer" value="${courseList.courseInfoPraPer }"/>
          </td> 
        </tr> 
        
         <tr>
          <td align="right" style="padding-right:5px">课程学分：</td>
          <td colspan="3">
            <input type="text" id="courseInfoCreHor" name="courseInfoCreHor" value="${courseList.courseInfoCreHor }"/>
          </td> 
        </tr> 
        
        
         
         
     </table>
     </td>
   </tr>  
   <tr>
     <td>
       <table width="100%" align="center" cellpadding="0" cellspacing="0" class="editbody">
        <tr class="editbody">
          <td align="center" height="30">
            <input type="button" id="saveBtn" Class="btnstyle" value="保存"/> 
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>