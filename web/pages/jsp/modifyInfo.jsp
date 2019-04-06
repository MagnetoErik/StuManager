
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
 <%
 	String path = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改个人信息</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(document).ready(function(){
	//checked="checked"
	document.getElementById("sex${userBean.sex}").checked="checked";
	//$("#sex${userBean.sex}").attr("checked","checked");
	
	
});	 
	
function submitForm(){
	$("#info").submit();
}
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">系统管理&gt;&gt;个人信息</span>
</div>
<form id="info" name="info" action="<%=path %>/adminUser?method=updateMsg" method="post">    
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">编辑个人信息</TD>
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
     	<tr>
          <td width="15%" align="right" style="padding-right:5px">头像：</td>
          <td width="85%">
			<img alt="" src="<%=path %>/${userBean.imagepath}" width="80px" height="80px">
		 </td>
 		
         </tr> 
        <tr>
          <td width="15%" align="right" style="padding-right:5px">用户名：</td>
          <td width="85%">${userBean.adminusername}</td>
 			<input type="hidden" value="${userBean.adminuserid}" name="adminuserid" />
 			<input type="hidden" value="${userBean.adminusername}" name="adminusername" />
         </tr>  
            
         <tr>
          <td align="right" style="padding-right:5px">真实姓名：</td>
          <td colspan="3">
            <input type="text" id="user_age" name="trueName" value="${userBean.truename}"/>
          </td> 
        </tr>       
         <tr>
         <td align="right" style="padding-right:5px"><font color="red">*</font> 性别：</td>
          <td>
             <input type="radio" name="sex" id="sex男" value="男"/>男&nbsp;&nbsp;
             <input type="radio" name="sex" id="sex女" value="女"/>女
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
            <input type="button" id="saveBtn" Class="btnstyle" value="编 辑" onclick="submitForm()"/> 
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>