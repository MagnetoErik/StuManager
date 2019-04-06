
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
//整个页面加载完成后执行
	$(function(){
		$("#query").click(function(){
			var depname = $("#depname").val();
			if(depname==''){
				alert('请输入搜索条件');
				return;
			}
			else{
				$("#info").submit();
			}
		});
		$("#add").click(function(){
			window.location.href="<%=path%>/pages/jsp/DepInfo/addOrEditDep.jsp"	;
		})	
	})
	
	function del(depinfoid){
		var r = confirm("你确认要删除院系信息吗？");
		if(r==true){
			window.location.href="depInfo?method=delete&depid="+depinfoid;
			}
		else{
			return;
		}
	}

</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">院系管理&gt;&gt;院系查询</span>
</div>
<form id="info" name="info" action="<%=path %>/depInfo" method="post">    
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">院系列表</td>
    <td width="" align="right">

            院系名称：
      <input type="text" id="depname" name="depname" value="${depname}" class="inputstyle"/>&nbsp;&nbsp;
      <input type="hidden" name="method" value="searchDepList" />
      <input type="button" value="搜索" id="query" class="btnstyle"/>&nbsp;
      <input type="button" value="增加" id="add" class="btnstyle"/> &nbsp;
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
  <table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
   	 <td width="40" align="center">序号</td>
     <td width="" align="center">院系编号</td>
     <td width="" align="center">院系名称</td>
     <td width="" align="center">教学编制</td>
     <td width="" align="center">教辅编制</td>
     <td width="" align="center">院系总专业数</td>
     <td width="" align="center">院系总班级数</td>
     <td width="" align="center">操作</td>
   </tr>
   
   <c:choose>
   	<c:when test="${beanList==null}">
	   	<tr>
	     <td height="60" colspan="8" align="center"><b>&lt;不存在院系信息&gt;</b></td>
	   	</tr>
   	</c:when>
   	<c:otherwise>
   		<c:forEach items="${beanList}" var="dep" varStatus="index">
		   			<tr>
		   				<input type="hidden" name="depinfoid" id="depinfoid" value="${dep.depinfoid}" />
		   				<td width="" align="center">${index.count}</td>
		   				<td width="" align="center">${dep.depinfocode}</td>
		   				<td width="" align="center">${dep.depinfoname}</td>
		   				<td width="" align="center">${dep.depinfopreoftech}</td>
		   				<td width="" align="center">${dep.depinfoassteach}</td>
		   				<td width="" align="center"><a href="specInfo?method=list&depid=${dep.depinfoid}" title="点击查看详情">${dep.depinfospnum}</a></td>
		   				<td width="" align="center"><a href="class?method=list&depid=${dep.depinfoid}" title="点击查看详情">${dep.depinfoclassnum}</a></td>
		   				<td width="" align="center">
			   				<a href="depInfo?method=updateDepInfoForWord&depcode=${dep.depinfocode}">编辑</a>
			   				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			   				<a href="javascript:onclick=del(${dep.depinfoid})">删除</a>
		   				</td>
		   			</tr>
   		</c:forEach>
   	</c:otherwise>
   </c:choose>
   
   
   
</table>
</form>
</body>
</html>