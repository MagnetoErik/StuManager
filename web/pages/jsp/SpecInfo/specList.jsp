
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="../../../WEB-INF/c.tld" %>
 <%String path = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>专业信息信息列表</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
//整个页面加载完成后执行
	function select(i){
		$(function(){
			$("#query").click(function(){			
					$("#info").submit();
			});
		});
	}
	
	$(function(){
		$("#add").click(function(){
			window.location.href="<%=path%>/pages/jsp/SpecInfo/addOrEditSp.jsp"	;
		})
	})
	
	
	$(function(){
		$("#depid").val('${id}');
	})
	
	function del(specilinfo){
		var r = confirm("你确认要删除专业信息吗？");
		if(r==true){
			window.location.href="specInfo?method=delete&specilinfo="+specilinfo;
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
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">专业管理&gt;&gt;专业查询</span>
</div>
<form id="info" name="info" action="specInfo?method=searchSpecilList" method="post">  
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">专业列表</td>
    <td width="" align="right">
            院系名称：
      <select onchange="select(this.value)" id="depid" name="depid">
      	<option value="">--请选择院系信息--</option>
      	<option value="1">电子工程学院</option>
      	<option value="2">机电工程学院</option>
      	<option value="3">计算机工程学院</option>
      	<option value="4">经济管理学院</option>
      </select>
      
      <input type="text" id="specname" name="specname" value="${specname}" class="inputstyle"/>&nbsp;&nbsp;
      <input type="button" value="搜索" id="query" class="btnstyle"/>&nbsp;
      <input type="button" value="增加" id="add" class="btnstyle"/> &nbsp;
    </td>
  </tr>
  <tr><td colspan="2" height="2px"></td></tr>  
</table>
  <table width="95%" align="center" class="table_list" cellpadding="0" cellspacing="0">
   <tr class="listtitle">
   	 <td width="40" align="center">序号</td>
   	 <td width="" align="center">所属院系名称</td>
     <td width="" align="center">专业编号</td>
     <td width="" align="center">专业名称</td>
     <td width="" align="center">学习内容</td>
     <td width="" align="center">职业方向</td>
     <td width="" align="center">操作</td>
   </tr>
   
   <c:choose>
   	<c:when test="${beanList==null}">
	   	<tr>
	     <td height="60" colspan="8" align="center"><b>&lt;不存在专业信息&gt;</b></td>
	   	</tr>
   	</c:when>
   	<c:otherwise>
   		<c:forEach items="${beanList}" var="spec" varStatus="index">
  			<tr>
  				<input type="hidden" name="specinfoid" id="specinfoid" value="${spec.spilinfoid}" />
  				<td width="" align="center">${index.count}</td>
  				<td width="" align="center">${spec.depinfoname}</td>
  				<td width="" align="center">${spec.spilinfocode}</td>
  				<td width="" align="center">${spec.spilinfoname}</td>
  				<td width="" align="center">${spec.spilinfoaim}</td>
  				<td width="" align="center">${spec.spilinfoprodire}</td>
  				<td width="" align="center">
   				<a href="specInfo?method=updateSpecilInfoForWord&spilinfocode=${spec.spilinfocode}">编辑</a>
   				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   				<a href="javascript:onclick=del(${spec.spilinfoid})">删除</a>
  				</td>
  			</tr>
   		</c:forEach>
   	</c:otherwise>
   </c:choose>
</table>
</form>
</body>
</html>