<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html style="height: 90%">
   <head>
       <meta charset="utf-8">
   </head>
   <body style="height: 100%; margin: 0;width: 98%">
       <div id="container" style="height: 100%"></div>
       <script type="text/javascript" src="<%=path %>/js/echarts.min.js"></script>   
       <script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.min.js"></script>    
       <script type="text/javascript">
    		  var list = JSON.parse('${jsonStr}');
    		  var dom = document.getElementById("container");
    		  var myChart = echarts.init(dom);
    		  var app = {};
    		  option = null;
    		  option = {
    		      title : {
    		          text: '院系人员统计',
    		          x:'center'
    		      },
    		      tooltip : {
    		          trigger: 'item',
    		          formatter: "{a} <br/>{b} : {c} ({d}%)"
    		      },
    		      legend: {
    		          orient: 'vertical',
    		          right: 'right',
    		          data: [{name:list[0].depName},{name:list[1].depName},{name:list[2].depName},{name:list[3].depName}]
    		      },
    		      series : [
    		          {
    		              name: '访问来源，点击查看详情',
    		              type: 'pie',
    		              radius : '75%',
    		              center: ['50%', '60%'],
    		              data:[
    		                  {value:list[0].count, name:list[0].depName, url: '<%=path%>/count?method=countSp&id=1'},
    		                  {value:list[1].count, name:list[1].depName, url: '<%=path%>/count?method=countSp&id=2'},
    		                  {value:list[2].count, name:list[2].depName, url: '<%=path%>/count?method=countSp&id=3'},
    		                  {value:list[3].count, name:list[3].depName, url: '<%=path%>/count?method=countSp&id=4'},
    		              ],
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