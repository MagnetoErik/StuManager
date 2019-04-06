<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html style="height: 90%">
   <head>
       <meta charset="utf-8">
       <link rel="stylesheet" href="<%=path %>/css/mg/bootstrap.css" />
   </head>
   <body style="height: 100%; margin: 0;width: 98%">
       <div id="container" style="height: 100%"></div>
       <input class="btn" id="back" value="点击返回院系人员统计" type="button" style="float: right">
       <script type="text/javascript" src="<%=path %>/js/echarts.min.js"></script>   
       <script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>    
       <script type="text/javascript">
       $("#back").click(function(){
				history.back();
			})	
       
       function fun1(){
 			  var jsonstr = [];
 			  for (var i = 0; i < list.length; i++) {
 			  var json = {};
 			  json.name = list[i].spName;
 			  json.value = list[i].count;
 			  json.url = '<%=path%>/count?method=countSex&id='+list[i].index;
 			  jsonstr.push(json);
 			  }
 			  return jsonstr;
 			}
  		   	  
       
       
      		  var list = JSON.parse('${jsonStr}');
      		  var dom = document.getElementById("container");
    		  var myChart = echarts.init(dom);
    		  var app = {};
    		  option = null;
    		  option = {
    		      title : {
    		          text: [list[0].depName],
    		          x:'center'
    		      },
    		      tooltip : {
    		          trigger: 'item',
    		          formatter: "{a} <br/>{b} : {c} ({d}%)"
    		      },
    		      legend: {
    		          orient: 'vertical',
    		          right: 'right',
    		      },
    		      series : [
    		          {
    		              name: '点击查看该院系男女比例统计',
    		              type: 'pie',
    		              radius : '75%',
    		              center: ['50%', '60%'],
    		              data:fun1(),
    		              itemStyle: {
    		                  emphasis: {
    		                      shadowBlur: 10,
    		                      shadowOffsetX: 0,
    		                      shadowColor: 'rgba(0, 0, 0, 0.5)'
    		                  }
    		              }
    		          }
    		      ]
    		  };
    		  ;
    		  if (option && typeof option === "object") {
    		      myChart.setOption(option, true);
    		      myChart.on('click', function(param) {
    		          //console.log(param);
    		          var url = param.data.url;
    		          window.location.href = url;
    		      });
    		  }
           		  

    		  
       </script>
       
       
   </body>
</html>