<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    String path = request.getContextPath();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>密码修改</title>
<link href="../../css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#saveBtn").click(function(){
			$("#pwdinfoId").submit();
		});
	})
</script>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="../../image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">系统管理&gt;&gt;个人信息</span>
</div>
<form id="pwdinfoId" name="pwdinfo" action="<%=path %>/adminUser?method=updatePwd" method="post"> 
	<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
		<tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">密码修改</TD>
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
		          <td width="15%" align="right" style="padding-right:5px">原密码：</td>
		          <td width="85%"><input type="text" id="oldpwd" name="oldpwd"/></td>
		        </tr> 
		     </table>
	     </td>
	   </tr>
	   
	   <tr>
	     <td >
	     	<table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
	     		<tr>
		          <td width="15%" align="right" style="padding-right:5px">新密码：</td>
		          <td width="85%"><input type="text" id="newpwd" name="newpwd"/></td>
		        </tr> 
		     </table>
	     </td>
	   </tr>
	   
	   <tr>
	     <td >
	     	<table width="100%" align="center" cellpadding="1" cellspacing="1" class="editbody">
	     		<tr>
		          <td width="15%" align="right" style="padding-right:5px">确认密码：</td>
		          <td width="85%"><input type="text" id="reppwd" name="reppwd"/></td>
		        </tr> 
		     </table>
	     </td>
	   </tr>
	   
	   <tr class="editbody">
          <td align="center" height="30">
            <input type="button" id="saveBtn" Class="btnstyle" value="修 改" onclick="submitForm()"/> 
          </td>
        </tr>
	    
	</table>
</form>
</body>
</html>