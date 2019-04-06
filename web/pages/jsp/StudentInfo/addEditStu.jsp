
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%@taglib prefix="c" uri="../../../WEB-INF/c.tld" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增学生信息</title>
<link href="<%=path %>/css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=path %>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=path %>/js/echarts.min.js"></script>
<script language="javascript" type="text/javascript"> 
	
	function depchange(id){
		$.ajax({
			type:"get",
			url:"<%=path%>/class?method=addSp",
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
	
	function spchange(id){
		$.ajax({
			type:"get",
			url:"<%=path%>/studentInfo?method=ajaxSelectClassList",
			data:{'id':id},
			success:function(str){
				var list = JSON.parse(str)
				$("#classInfoId").empty();
				$("#classInfoId").append('<option value="">请选择所属班级信息</option>');
				$.each(list,function(n,bean){
					$("#classInfoId").append('<option value="'+bean.classInfoID+'">'+bean.classInfoName+'</option>');
				})
			}
		})
	}
	
	$(function(){
		var method = $("#method").val();
		if(method=='updateStuInfoMsg'){
			document.getElementById("stdinfocode").readOnly="true";
		}
		else if(method=='add'){
			$.ajax({
				type:'get',
				url:"<%=path%>/studentInfo?method=code",
				data:{},
				success:function(str){
					$("#stdinfocode").val(str);
					document.getElementById("stdinfocode").readOnly='true';
				}
			})
		}
		
		

	})

	
	function submitForm(){
		var stdinfoname = $("#stdinfoname").val();
		if(stdinfoname==''){
			alert('请输入学生姓名！!');
			return;
		}
		var classInfoId = $("#classInfoId").val();
		if(classInfoId==''){
			alert('请选择对应的班级信息');
			return;
		}
		//提交表单
		$("#info").submit();
		
	}

	
	
	

</script>
<style type="text/css">
</style>
</head>
<body>
<div class="pageTitle">
	&nbsp;&nbsp;<img src="<%=path %>/image/right1.gif" align="middle" /> &nbsp;<span id="MainTitle" style="color:white">班级管理&gt;&gt;新增班级信息</span>
</div>
<form id="info" name="info" action="<%=path %>/studentInfo" method="post">    
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
       	<c:when test="${stuList==null}">
       		<input type="hidden" name="method" value="add" id="method"/>
       	</c:when>
       	<c:otherwise>
       		<input type="hidden" name="method" value="updateStuInfoMsg" id="method"/>
       	</c:otherwise>
       </c:choose>

        
        <input type="hidden" name="stdinfoid" id="stdinfoid" value="${stuList.stdinfoid }" />
        
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>学生编号：</td>
          <td colspan="3">
            <input type="text" id="stdinfocode" name="stdinfocode" value="${stuList.stdinfocode }"/>
          </td> 
        </tr> 
        
        <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>学生姓名：</td>
          <td colspan="3">
            <input type="text" id="stdinfoname" name="stdinfoname" value="${stuList.stdinfoname }"/>
          </td> 
        </tr> 
        
        
        <tr>
          <td width="15%" align="right" style="padding-right:5px"><font color="red">*</font>性别：</td>
          <td colspan="3">
                          男&nbsp;&nbsp;<input type="radio" name="stdinfosex" value="男" checked="checked"/>&nbsp;&nbsp;
       	      女&nbsp;&nbsp;<input type="radio" name="stdinfosex" value="女"/>
          </td> 
         </tr> 
            
            
            
        <tr>
          <td align="right" style="padding-right:5px">身份证号：</td>
          <td colspan="3">
            <input type="text" id="stdinfocard" name="stdinfocard" value="${stuList.stdinfocard }"/>
          </td> 
        </tr> 
        
        
         <tr>
          <td align="right" style="padding-right:5px">出生日期：</td>
          <td colspan="3">
            <input type="text" id="stdinfobirthd" name="stdinfobirthd"  onClick="WdatePicker({el:this,dateFmt:'yyyy-MM-dd'})" value="${stuList.stdinfobirthd }"/>
          </td> 
        </tr> 
        
        
        <tr>
          <td align="right" style="padding-right:5px">民族：</td>
          <td colspan="3">
            <input type="text" id="stdinfonatns" name="stdinfonatns" value="${stuList.stdinfonatns }"/>
          </td> 
        </tr> 
        
        <tr>
          <td align="right" style="padding-right:5px">电话号码：</td>
          <td colspan="3">
            <input type="text" id="stdinfotel" name="stdinfotel" value="${stuList.stdinfotel }"/>
          </td> 
        </tr> 
        
        
        <tr>
          <td align="right" style="padding-right:5px">电子邮箱：</td>
          <td colspan="3">
            <input type="text" id="stdinfoemail" name="stdinfoemail" value="${stuList.stdinfoemail }"/>
          </td> 
        </tr> 
        
        
        
         <tr>
          <td align="right" style="padding-right:5px"><font color="red">*</font>班级信息：</td>
          <td id="text">
          
          	 <select name="depinfoid" id="depinfoid" onchange="depchange(this.value)">
             		<option value="">请选择所属院系信息</option>
					<option value="1">电子工程学院</option>
			      	<option value="2">机电工程学院</option>
			      	<option value="3">计算机工程学院</option>
			      	<option value="4">经济管理学院</option>
             </select>
          	
             <select name="specializeId" id="specializeId" onchange="spchange(this.value)">
					<option value="">请选择所属专业信息</option>

             </select>
             
             <select name="classInfoId" id="classInfoId">
					<option value="">请选择所属班级信息</option>
             		
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