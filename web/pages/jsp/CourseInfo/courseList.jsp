
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="../../../WEB-INF/c.tld" %>
 <%String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班级信息列表</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript"> 

	$(function(){	
		$("#search").click(function(){
			$("#info").submit();		
		})
		//回填课程类别名称
		$("#courseTypeId").val('${courseTypeId}');
	})


	function del(courseInfoId){
		var r = confirm("你确认要删除课程信息吗？");
		if(r==true){
			window.location.href="courseInfo?method=delete&courseInfoId="+courseInfoId;
			}
		else{
			return;
		}
	}
	
	function add(){
		window.location.href="pages/jsp/CourseInfo/addEditCourse.jsp"
	}
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">班级管理&gt;&gt;班级查询</span>
</div>
<form id="info" name="info" action="<%=path %>/courseInfo?method=searchList" method="post">    
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">班级列表</td>
    <td width="" align="right">
    课程类别名称:
    
    <select name="courseTypeId" id="courseTypeId">
    	<option value="" >--请选择课程类别--</option>
	    	<c:forEach items="${courseTypeList}" var="courseType">	
	    		<option value="${courseType.coursetypeid}">${courseType.coursetypename}</option>
	   		</c:forEach>
    </select>
    &nbsp;&nbsp;&nbsp;&nbsp;

            课程名称：
      <input type="text" id="courseInfoName" name="courseInfoName" value="${courseInfoName }" class="inputstyle"/>&nbsp;&nbsp;
      <input type="button" value="搜索" id="search" class="btnstyle"/>
      <input type="button" value="添加" onclick="add()" class="btnstyle"/>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
  <table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center">序号</td>
     <td width="" align="center">课程类别名称</td>
     <td width="" align="center">课程编号</td>
     <td width="" align="center">课程名称</td>
     <td width="" align="center">课程介绍</td>
     <td width="" align="center">理论学时</td>
     <td width="" align="center">实践学时</td>
     <td width="" align="center">课程学分</td>
     <td width="" align="center">操作</td>
   </tr>
   <c:choose>
   	<c:when test="${courseList==null}">
	   	<tr>
	     <td height="60" colspan="5" align="center"><b>&lt;不存在班级信息&gt;</b></td>
	   </tr>
   	</c:when>
   	<c:otherwise>
   		 <c:forEach items="${courseList}" var="course" varStatus="num"> 
   		 <tr>
   		 <input type="hidden" name="course" id="course" value="${course.courseInfoId} "/>
   			<td width="" align="center">${num.count}</td>
   			<td width="" align="center">${course.courseTypeName}</td>
   			<td width="" align="center">${course.courseInfoCode}</td>
   			<td width="" align="center">${course.courseInfoName}</td>
   			<td width="" align="center">${course.courseInfoProj}</td>
   			<td width="" align="center">${course.courseInfoRstPer}</td>
   			<td width="" align="center">${course.courseInfoPraPer}</td>
   			<td width="" align="center">${course.courseInfoCreHor}</td>
   			<td width="" align="center">
   				<a href="courseInfo?method=updateCourseInfoForWord&courseInfoId=${course.courseInfoId}">编辑</a>
   				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   				<a href="javascript:onclick=del(${course.courseInfoId})">删除</a>
			</td>
   		</tr>
	    </c:forEach>
   	</c:otherwise>
   </c:choose>
   
  
   
   
</table>
</form>
</body>
</html>