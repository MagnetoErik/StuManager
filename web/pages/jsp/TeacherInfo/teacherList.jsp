
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
		$("#depinfoid").val('${depinfoid}');
	})
	
	
	//查询方法
	function selectList(){
		$("#info").submit();
	}
	
	function del(teachInfoId){
		var r = confirm("你确认要删除教师信息吗？");
		if(r==true){
			window.location.href="teacherInfo?method=delete&teachInfoId="+teachInfoId;
			}
		else{
			return;
		}
	}
	
	function add(){
		window.location.href="pages/jsp/TeacherInfo/addEditTeacher.jsp"
	}
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">教师管理&gt;&gt;教师查询</span>
</div>
<form id="info" name="info" action="<%=path %>/teacherInfo?method=search" method="post">    
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">教师列表</td>
    <td width="" align="right">
    
       
    
    
    院系名称:
    
    <select name="depinfoid" id="depinfoid" >
    	<option value="" >请选择所属院系信息</option>
			<option value="1">电子工程学院</option>
	      	<option value="2">机电工程学院</option>
	      	<option value="3">计算机工程学院</option>
	      	<option value="4">经济管理学院</option>
    </select>
    &nbsp;&nbsp;&nbsp;&nbsp;

            教师名称：
      <input type="text" id="teachInfoname" name="teachInfoname" value="${teachInfoname}" class="inputstyle"/>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="selectList()" class="btnstyle"/>
      <input type="button" value="添加" onclick="add()" class="btnstyle"/>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
  <table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center">序号</td>
     <td width="" align="center">教师类别</td>
     <td width="" align="center">所属专业名称</td>
     <td width="" align="center">教师姓名</td>
     <td width="" align="center">性别</td>
     <td width="" align="center">学历</td>
     <td width="" align="center">操作</td>
   </tr>
   <c:choose>
   	<c:when test="${teacherList==null}">
	   	<tr>
	     <td height="60" colspan="7" align="center"><b>&lt;不存在教师信息&gt;</b></td>
	   </tr>
   	</c:when>
   	<c:otherwise>
   		 <c:forEach items="${teacherList}" var="tvar" varStatus="num"> 
   		 <tr>
   		 <input type="hidden" name="teachInfoId" id="teachInfoId" value="${tvar.teachInfoId} "/>
   			<td width="" align="center">${num.count}</td>
   			<td width="" align="center">${tvar.teachTypeName}</td>
   			<td width="" align="center">${tvar.depInfoName}</td>
   			<td width="" align="center">${tvar.teachInfoname}</td>
   			<td width="" align="center">${tvar.teachSex}</td>
   			<td width="" align="center">${tvar.teachKnowl}</td>
   			<td width="" align="center">
   				<a href="teacherInfo?method=updateTeacherInfoForWord&teachInfoId=${tvar.teachInfoId}">编辑</a>
   				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   				<a href="javascript:onclick=del(${tvar.teachInfoId})">删除</a>
			</td>
   		</tr>
	    </c:forEach>
   	</c:otherwise>
   </c:choose>
   
  
   
   
</table>
</form>
</body>
</html>