
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
<link rel="stylesheet" href="<%=path %>/css/mg/bootstrap.css" />
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
//整个页面加载完成后执行
	var count = '${count}';
	var maxPage = '${maxPage}'
	$(function(){
		$("#add").click(function(){
			window.location.href="<%=path%>/pages/jsp/StudentInfo/addEditStu.jsp";
		})
		$("#query").click(function(){
			$("#info").submit();	
		})
		$("#depId").val('${depId}');
		$("#spId").val('${spId}');
		$("#classId").val('${classId}');
		$("#next").click(function(){
			if(count==maxPage){
			
			}
			else{
				var next = $("#next").val();
				window.location.href='studentInfo?method=list&count='+count+'&id='+next
			}
			
		})
		$("#last").click(function(){
			if(count==1){
							
			}else{
				var last = $("#last").val();
				window.location.href='studentInfo?method=list&count='+count+'&id='+last
			}
			
		})
		$("#top").click(function(){
			if(count==1){
				
			}else{
				var top = $("#top").val();
				window.location.href='studentInfo?method=list&count='+count+'&id='+top
			}
			
		})
		$("#buttom").click(function(){
			if(count==maxPage){
				
			}
			else{
				var buttom = $("#buttom").val();
				window.location.href='studentInfo?method=list&count='+count+'&id='+buttom
			}
		})
	})
	
	
	function depchange(id){
		$.ajax({
			type:"get",
			url:"<%=path%>/class?method=addSp",
			data:{'id':id},
			success:function(str){
				var list = JSON.parse(str)
				$("#spId").empty();
				$("#spId").append('<option value="">--请选择所属专业信息--</option>');
				$.each(list,function(n,bean){
					$("#spId").append('<option value="'+bean.spilinfoid+'">'+bean.spilinfoname+'</option>');
				})
			}
		})
	}
	
	function spchange(id){
		$.ajax({
			type:"get",
			url:"<%=path%>/studentInfo?method=ajaxSelectClassList",
			data:{'id':id},
			success:function(str){
				var list = JSON.parse(str)
				$("#classId").empty();
				$("#classId").append('<option value="">--请选择所属班级信息--</option>');
				$.each(list,function(n,bean){
					$("#classId").append('<option value="'+bean.classInfoID+'">'+bean.classInfoName+'</option>');
				})
			}
		})
	}

	
	function del(stdinfoid){
		var r = confirm("你确认要删除学生信息吗？");
		if(r==true){
			window.location.href="studentInfo?method=delete&stdinfoid="+stdinfoid;
			}
		else{
			return;
		}
	}

	
</script>
<style type="text/css">
</style>
</head>
<body align="center">
<div class="pageTitle">
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" style="float: left;margin-top: 5px;margin-left: 5px"/> &nbsp;<span id="MainTitle" style="color:white;float: left;margin-left: 5px">专业管理&gt;&gt;专业查询</span>
</div>
<form id="info" name="info" action="studentInfo?method=query" method="post">  
<table width="95%" align="center" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" height="10px">&nbsp;</td></tr>
  <tr>
    <td width="">专业列表</td>
    <td width="" align="right">
        院系名称：
      <select id="depId" name="depId" onchange="depchange(this.value)">
      	<option value="">--请选择所属院系信息--</option>
      	<option value="1">电子工程学院</option>
      	<option value="2">机电工程学院</option>
      	<option value="3">计算机工程学院</option>
      	<option value="4">经济管理学院</option>
      </select>     
          
            
            
            专业名称：
      <select id="spId" name="spId" onchange="spchange(this.value)">
		<option value="">--请选择所属专业信息--</option>
			<c:forEach items="${spList}" var="sp">
	    		<option value="${sp.spilinfoid}">${sp.spilinfoname}</option>
	   		</c:forEach>
      </select>
      &nbsp;&nbsp;
      班级名称：
      <select id="classId" name="classId">
		<option value="">--请选择所属班级信息--</option>
			<c:forEach items="${classList}" var="cl">
	    		<option value="${cl.classInfoID}">${cl.classInfoName}</option>
	   		</c:forEach>
      </select>
      &nbsp;&nbsp;  
      学生姓名：
      <input type="text" id="stuName" name="stuName" value="${stuName}" class="inputstyle"/>&nbsp;&nbsp;
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
     <td width="" align="center">所属专业名称</td>
     <td width="" align="center">所属班级名称</td>
     <td width="" align="center">姓名</td>
     <td width="" align="center">性别</td>
     <td width="" align="center">身份证号码</td>
     <td width="" align="center">民族</td>
     <td width="" align="center">电话号码</td>
     <td width="" align="center">选择课程数量</td>
     <td width="" align="center">操作</td>
   </tr>
   
   <c:choose>
   	<c:when test="${stuList==null}">
	   	<tr>
	     <td height="60" colspan="8" align="center"><b>&lt;不存在学生信息&gt;</b></td>
	   	</tr>
   	</c:when>
   	<c:otherwise>
   		<c:forEach items="${stuList}" var="stu" varStatus="index">
  			<tr>
  				<input type="hidden" name="stdinfoid" id="stdinfoid" value="${stu.stdinfoid}" />
  				<td width="" align="center">${index.count}</td>
  				<td width="" align="center">${stu.depinfoname}</td>
  				<td width="" align="center">${stu.spilinfoname}</td>
  				<td width="" align="center">${stu.classinfoname}</td>
  				<td width="" align="center">${stu.stdinfoname}</td>
  				<td width="" align="center">${stu.stdinfosex}</td>
  				<td width="" align="center">${stu.stdinfocard}</td>
  				<td width="" align="center">${stu.stdinfonatns}</td>
  				<td width="" align="center">${stu.stdinfotel}</td>
  				<td width="" align="center">${stu.count}</td>
  				<td width="" align="center">
   				<a href="studentInfo?method=updateSpecilInfoForWord&stdinfoid=${stu.stdinfoid}">编辑</a>
   				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   				<a href="javascript:onclick=del(${stu.stdinfoid})">删除</a>
  				</td>
  			</tr>
   		</c:forEach>
   	</c:otherwise>
   </c:choose>
</table>
</form>

<div style="">
	<Button type="button" id="top" class="btn" value="top">首页</Button>
	<Button type="button" id="last" class="btn" value="last">上一页</Button>
	<span style="font-size: 18px">当前是第${count}页/${maxPage}页</span>
	<Button type="button" id="next" class="btn" value="next">下一页</Button>
	<Button type="button" id="buttom" class="btn" value="buttom">尾页</Button>
</div>
</body>
</html>