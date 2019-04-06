
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="../../../WEB-INF/c.tld" %>
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
	$(function(){
		 var value = $("#method").val(); 
		 if(value=="updateSpecilInfoMsg"){
			 document.getElementById("depinfoid").readOnly="true";
			 document.getElementById("spilinfocode").readOnly="true";
		 }
	})
	 
	 
	function submitForm(){
	var depinfoid = document.getElementById("depinfoid").value;
	if(depinfoid==''){
		alert('请输入所属院系名称！!');
		return;
	}
	if(isNaN(depinfoid)){
		alert('所属院系编号格式错误只能为数字');
		return;
	}
	var spilinfocode = document.getElementById("spilinfocode").value;
	if(spilinfocode==''){
		alert('请输入专业编号!');
		return;
	}
	if(isNaN(spilinfocode)){
		alert('专业编号格式错误只能为数字');
		return;
	}
	var spilinfoname = document.getElementById("spilinfoname").value;
	if(spilinfoname==''){
		alert('请输入专业名称!');
		return;
	}
	var spilinfoaim = document.getElementById("spilinfoaim").value;
	if(spilinfoaim==''){
		alert('请输入学习内容!');
		return;
	}
	var spilinfoprodire = document.getElementById("spilinfoprodire").value;
	if(spilinfoprodire==''){
		alert('请输入职业方向!');
		return;
	}
	$("#info").submit();
}
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">系统管理&gt;&gt;课程专业</span>
</div>
<form id="info" name="info" action="<%=path %>/specInfo" method="post">    
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">新增或修改专业信息</TD>
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
     		<c:choose>
     			<c:when test="${spList==null}">
     				<input type="hidden" name="method" value="add" id="method" />
     			</c:when>
     		<c:otherwise>
     			<input type="hidden" name="method" value="updateSpecilInfoMsg" id="method"  />
     		</c:otherwise>
     		</c:choose>
          <td align="right" style="padding-right:5px"><font color="red">*</font>所属院系id：</td>
          <td colspan="3">
            <input type="text" id="depinfoid" name="depinfoid"  value="${spList.depinfoid }"/>
          </td> 
        </tr>
     	
        <tr>
          <td width="15%" align="right" style="padding-right:5px"><font color="red">*</font>专业编号：</td>
          <td width="85%">
          	 <input type="hidden" id="spilinfoid" name="spilinfoid" value="" />
         	 <input type="text" id="spilinfocode" name="spilinfocode" value="${spList.spilinfocode }"/>
          </td>
 		</tr> 
            
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>专业名称：</td>
          <td colspan="3">
            <input type="text" id="spilinfoname" name="spilinfoname" value="${spList.spilinfoname }"/>
          </td> 
        </tr> 
        
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>学习内容：</td>
          <td colspan="3">
            <input type="text" id="spilinfoaim" name="spilinfoaim" value="${spList.spilinfoaim }"/>
          </td> 
        </tr> 
        
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>职业方向：</td>
          <td colspan="3">
            <input type="text" id="spilinfoprodire" name="spilinfoprodire" value="${spList.spilinfoprodire }"/>
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