
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
	
	function change(id){
		$.ajax({
			type:"get",
			url:"class?method=addSp",
			data:{'id':id},
			success:function(str){
				var list = JSON.parse(str)
				$("#specializeId").empty();
				$("#specializeId").append('<option value="">请选择所属专业信息</option>');
				$.each(list,function(n,bean){
					$("#specializeId").append('<option value="'+bean.spilinfoid+'">'+bean.spilinfoname+'</option>');
				})
			}
		})
	}
	
	
	function submitForm(){
		var classInfoCode = document.getElementById("classInfoCode").value;
		if(classInfoCode==''){
			alert('请输入班级代号名称！!');
			return;
		}
		if(isNaN(classInfoCode)){
			alert('班级代号格式错误只能为数字');
			return;
		}
		var classInfoName = document.getElementById("classInfoName").value;
		if(classInfoName==''){
			alert('请输入班级名称!');
			return;
		}
		var classInfosum = document.getElementById("classInfosum").value;
		if(classInfosum==''){
			alert('请输入班级人数!');
			return;
		}
		if(isNaN(classInfosum)){
			alert('班级人数格式错误只能为数字');
			return;
		}
		var classinformk = document.getElementById("classinformk").value;
		if(classinformk==''){
			alert('请输入班级备注!');
			return;
		}
		if(isNaN(classinformk)){
			alert('班级备注格式错误只能为数字');
			return;
		}
		//提交表单
		$("#info").submit();
		
	}


	$(function(){
		var method = $("#method").val();
		if(method=="updateClassInfoMsg"){
			document.getElementById("classInfoCode").readOnly="true";
		}
	})
	
	
	

</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">班级管理&gt;&gt;新增班级信息</span>
</div>
<form id="info" name="info" action="<%=path %>/class" method="post">    
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
       	<c:when test="${classList==null}">
       		<input type="hidden" name="method" value="addMsg" id="method"/>
       	</c:when>
       	<c:otherwise>
       		<input type="hidden" name="method" value="updateClassInfoMsg" id="method"/>
       	</c:otherwise>
       </c:choose>

        
        <input type="hidden" name="classInfoId" id="classInfoId" value="${classList.classInfoID }" />
        <tr>
          <td align="right" style="padding-right:5px">班级代码：</td>
          <td colspan="3">
            <input type="text" id="classInfoCode" name="classInfoCode" value="${classList.classInfoCode }"/>
          </td> 
        </tr> 
        
        
        <tr>
          <td width="15%" align="right" style="padding-right:5px">班级名称：</td>
          <td width="85%">
          		<input type="text" name="classInfoName" id="classInfoName" value="${classList.classInfoName }"/>
          </td>
         </tr> 
            
            
            
        <tr>
          <td align="right" style="padding-right:5px">班级人数：</td>
          <td colspan="3">
            <input type="text" id="classInfosum" name="classInfosum" value="${classList.classInfosum }"/>
          </td> 
        </tr> 
        
        
         <tr>
          <td align="right" style="padding-right:5px">班级备注：</td>
          <td colspan="3">
            <input type="text" id="classinformk" name="classinformk" value="${classList.classinformk }"/>
          </td> 
        </tr> 
        
        
        
         <tr>
          <td align="right" style="padding-right:5px">所属专业：</td>
          <td id="text">
          
          	 <select name="depinfoid" id="depinfoid" onchange="change(this.value)">
             		<option value="">请选择请选择所属院系</option>
             		<c:forEach items="${depList}" var="dep">
             			<option value="${dep.depinfoid}">${dep.depinfoname}</option>
             		
             		</c:forEach>
             </select>
          	
             <select name="specializeId" id="specializeId">
					<option value="">请选择所属专业信息</option>
             		<c:forEach items="${spList}" var="sp">
             			<option value="${sp.spilinfoid}">${sp.spilinfoname}</option>
             		
             		</c:forEach>
             </select>
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
            <input type="button" id="saveBtn" Class="btnstyle" value="保存" onclick="submitForm()"/> 
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>