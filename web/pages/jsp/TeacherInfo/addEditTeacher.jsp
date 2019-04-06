
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
		var teachinfocode = $("#teachinfocode").val();
		if(teachinfocode==''){
			alert('请输入教师编号');
			return;
		}
		if(isNaN(teachinfocode)){
			alert('教师编号格式错误，只能为数字');
		}
		var teachinfoname = $("#teachinfoname").val();
		if(teachinfoname==''){
			alert('请输入教师姓名');
			return;
		}
		var teachknowl = $("#teachknowl").val();
		if(teachknowl==''){
			alert('请输入学历');
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
<form id="info" name="info" action="<%=path %>/teacherInfo" method="post">    
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">新增/修改教师信息</TD>
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
       	<c:when test="${teacherList==null}">
       		<input type="hidden" name="method" value="add" id="method"/>
       	</c:when>
       	<c:otherwise>
       		<input type="hidden" name="method" value="updateClassInfoMsg" id="method"/>
       	</c:otherwise>
       </c:choose>

        
        <input type="hidden" name="teachinfoid" id="teachinfoid" value="${teacherList.teachInfoId }" />
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>教师编号：</td>
          <td colspan="3">
            <input type="text" id="teachinfocode" name="teachinfocode" value="${teacherList.teachInfoCode }"/>
          </td> 
        </tr> 
        
        
        <tr>
          <td width="15%" align="right" style="padding-right:5px"><font color="red">*</font>教师名称：</td>
          <td width="85%">
          		<input type="text" name="teachinfoname" id="teachinfoname" value="${teacherList.teachInfoname }"/>
          </td>
         </tr> 
            
            
            
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>性别：</td>
          <td colspan="3">
                          男&nbsp;&nbsp;<input type="radio" name="teachinfosex" value="男" checked="checked"/>&nbsp;&nbsp;
       	      女&nbsp;&nbsp;<input type="radio" name="teachinfosex" value="女"/>
          </td> 
        </tr> 
        
        
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>学历：</td>
          <td colspan="3">
            <input type="text" id="teachknowl" name="teachknowl" value="${teacherList.teachKnowl }"/>
          </td> 
        </tr> 
        
        
         <tr>
          <td align="right" style="padding-right:5px">学位：</td>
          <td colspan="3">
            <input type="text" id="teachDeg" name="teachDeg" value="${teacherList.teachDeg }"/>
          </td> 
        </tr> 
        
        
         <tr>
          <td align="right" style="padding-right:5px">所学专业：</td>
          <td colspan="3">
            <input type="text" id="teachSpec" name="teachSpec" value="${teacherList.teachSpec }"/>
          </td> 
        </tr> 
        
        
         <tr>
          <td align="right" style="padding-right:5px">职称：</td>
          <td colspan="3">
            <input type="text" id="teachTitle" name="teachTitle" value="${teacherList.teachTitle }"/>
          </td> 
        </tr> 
        
        
        
        
        
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>所属类别和院系：</td>
          <td id="text">
          
          	 <select name="teacherTypeId" id="teacherTypeId" onchange="change(this.value)">
           		<option value="">请选择请选择教师类别</option>
             	<option value="1">教学人员</option>
             	<option value="2">高等教育教师</option>
             	<option value="3">中等职业教育教师</option>
             	<option value="4">兼职外聘教师</option>
             	<option value="5">辅导员</option>	
             </select>
          	
             <select name="depInfoId" id="depInfoId">
             	<option value="">请选择请选择所属院系信息</option>
				<option value="1">电子工程学院</option>
		      	<option value="2">机电工程学院</option>
		      	<option value="3">计算机工程学院</option>
		      	<option value="4">经济管理学院</option>
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