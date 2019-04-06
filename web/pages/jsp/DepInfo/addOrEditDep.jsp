<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="../../../WEB-INF/c.tld" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增院系信息</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
$(function(){
	
	var i = 0;
	
	 var value = $("#method").val(); 
	 if(value=="updateDepInfoMsg"){
		 document.getElementById("depinfocode").readOnly="true"
		 i = 1;
	 }
	 
	 
	 //发送ajax请求 查询院系信息编号是否存在
		 $("#depinfocode").blur(function(){
			 var depinfocode = $("#depinfocode").val();
			 success = document.getElementById("content1")
			 error = document.getElementById("content")
			 $.ajax({
				 type:"get",
				 url:"../../../depInfo?method=ajaxSelectDepCode",
				 data:{depinfocode:depinfocode},
				 success:function(str){
					if(str==1){
						success.style.display="none";
						error.style.color="red";
						error.style.display="block";
						i = 0;
					}
					else{
						error.style.display="none";
						success.style.color="yellowgreen";
						success.style.display="block";  
						i = 1;
					}
				}
			})
		});
	 
	 
		 $("#saveBtn").click(function(){
				var depinfocode = document.getElementById("depinfocode").value;
				if(depinfocode==''){
					alert('请输入院系编号!');
					return;
				}
				if(isNaN(depinfocode)){
					alert('院系编号格式错误只能为数字');
					return;
				}
				var depinfoname = document.getElementById("depinfoname").value;
				if(depinfoname==''){
					alert('请输入院系名称!');
					return;
				}
				var depinfopreoftech = document.getElementById("depinfopreoftech").value;
				if(depinfopreoftech==''){
					alert('请输入教学编制!');
					return;
				}
				if(isNaN(depinfopreoftech)){
					alert('教学编制只能为数字!');
					return;
				}
				var depinfoassteach = document.getElementById("depinfoassteach").value;
				if(depinfoassteach==''){
					alert('请输入教辅编制!');
					return;
				}
				if(isNaN(depinfoassteach)){
					alert('教辅编制只能为数字!');
					return;
				}
				
				if(i==0){
					alert('当前院系信息已存在，请重新输入！');
					$("#depinfocode").focus();
				}else{
					$("#info").submit();	
				}
					
				
			}
);
})
	
</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">系统管理&gt;&gt;课程专业</span>
</div>
<form id="info" name="info" action="<%=path %>/depInfo" method="post">    
<table width="800" align="center" cellpadding="0" cellspacing="0" style="margin-top:10px;margin-bottom:10px;">
  <tr> 
     <td height="24">
       <Table border="0" cellspacing="0" cellpadding="0" align="center" width="100%"> 
            <TR>
              <TD height="24" class="edittitleleft">&nbsp;</TD>
              <TD class="edittitle">新增院系信息</TD>
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
     	<c:when test="${depList==null}">
     		<input type="hidden" name="method" value="add" id="method"/>
     	</c:when>
     	<c:otherwise>
     		<input type="hidden" name="method" value="updateDepInfoMsg" id="method"/>
     	</c:otherwise>
     </c:choose>
     
     	<tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>院系编号：</td>
          <td colspan="3">
            <input type="text" id="depinfocode" name="depinfocode" value="${depList.depinfocode }" style="float: left;"/>
            <span id="content" style="display: none;float: left;">该院系编号已存在！请重新输入！</span>
          	<span id="content1" style="display: none;float: left;">当前院系编号不存在，可以正常添加！</span>
          </td> 
        </tr>
     	
        <tr>
          <td width="15%" align="right" style="padding-right:5px"><font color="red">*</font>院系名称：</td>
          <td width="85%">
          		<input type="text" name="depinfoname" id="depinfoname" value="${depList.depinfoname }"/>	
          		<input type="hidden" name="depinfoid" id="depinfoid" value="${depList.depinfoid }"/>	        		
          </td>
 		</tr> 
            
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>教学编制：</td>
          <td colspan="3">
            <input type="text" id="depinfopreoftech" name="depinfopreoftech" value="${depList.depinfopreoftech }"/>
          </td> 
        </tr> 
        
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>教辅编制：</td>
          <td colspan="3">
            <input type="text" id="depinfoassteach" name="depinfoassteach" value="${depList.depinfoassteach }"/>
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
            <input type="button" id="saveBtn" Class="btnstyle" value="保存" /> 
          </td>
        </tr>
      </table>
     </td>
   </tr>
</table>
</form>
</body>
</html>