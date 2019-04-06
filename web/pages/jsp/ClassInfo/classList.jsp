
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

	//动态显示专业信息下拉框
	function selectAjaxList(id){
		$.ajax({
			type:"get",
			url:"class?method=ajaxList",
			data:{'depid':id},
			success:function(str){
				var list = JSON.parse(str)
				$("#specializeId").empty();
				$("#specializeId").append('<option value="" >请选择专业名称</option>');
				$.each(list,function(n,bean){
					$("#specializeId").append('<option value="'+bean.spilinfoId+'">'+bean.spilinfoname+'</option>');
				});
			}
		});
	}
	
	$(function(){
		$("#depinfoid").val('${depinfoid}');
		$("#specializeId").val('${specializeId}');
	})
	
	
	//查询方法
	function selectList(){
		$("#info").submit();
	}
	
	function del(classInfoId){
		var r = confirm("你确认要删除专业信息吗？");
		if(r==true){
			window.location.href="class?method=delete&classInfoId="+classInfoId;
			}
		else{
			return;
		}
	}
	
	function add(){
		window.location.href="class?method=add"
	}
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">班级管理&gt;&gt;班级查询</span>
</div>
<form id="info" name="info" action="<%=path %>/class?method=search" method="post">    
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">班级列表</td>
    <td width="" align="right">
    院系名称:
    
    <select name="depinfoid" id="depinfoid" onchange="selectAjaxList(this.value)" >
    	<option value="" >请选择院系名称</option>
	    	<c:forEach items="${depList}" var="dep">	
	    		<option value="${dep.depinfoid}">${dep.depinfoname}</option>
	   		</c:forEach>
    </select>
    &nbsp;&nbsp;&nbsp;&nbsp;
    
  专业名称:  
    <select name="specializeId" id="specializeId" >
    	<option value="" >请选择专业名称</option>
	    	<c:forEach items="${spList}" var="sp">
	    		<option value="${sp.spilinfoid}">${sp.spilinfoname}</option>
	   		</c:forEach>
    </select>
            班级名称：
      <input type="text" id="className" name="className" value="${className }" class="inputstyle"/>&nbsp;&nbsp;
      <input type="button" value="搜索" onclick="selectList()" class="btnstyle"/>
      <input type="button" value="添加" onclick="add()" class="btnstyle"/>
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
  <table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
     <td width="40" align="center">序号</td>
     <td width="" align="center">所属院系名称</td>
     <td width="" align="center">所属专业名称</td>
     <td width="" align="center">班级代号</td>
     <td width="" align="center">班级名称</td>
     <td width="" align="center">班级人数</td>
     <td width="" align="center">操作</td>
   </tr>
   <c:choose>
   	<c:when test="${classList==null}">
	   	<tr>
	     <td height="60" colspan="5" align="center"><b>&lt;不存在班级信息&gt;</b></td>
	   </tr>
   	</c:when>
   	<c:otherwise>
   		 <c:forEach items="${classList}" var="classvar" varStatus="num"> 
   		 <tr>
   		 <input type="hidden" name="classInfoid" id="classInfoid" value="${classvar.classInfoID} "/>
   			<td width="" align="center">${num.count}</td>
   			<td width="" align="center">${classvar.depinfoname}</td>
   			<td width="" align="center">${classvar.spilinfoname}</td>
   			<td width="" align="center">${classvar.classInfoCode}</td>
   			<td width="" align="center">${classvar.classInfoName}</td>
   			<td width="" align="center">${classvar.classInfosum}</td>
   			<td width="" align="center">
   				<a href="class?method=updateClassInfoForWord&classInfoCode=${classvar.classInfoCode}">编辑</a>
   				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   				<a href="javascript:onclick=del(${classvar.classInfoID})">删除</a>
			</td>
   		</tr>
	    </c:forEach>
   	</c:otherwise>
   </c:choose>
   
  
   
   
</table>
</form>
</body>
</html>