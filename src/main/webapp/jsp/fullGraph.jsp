<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<head>
	<meta charset="utf-8">
	<META HTTP-EQUIV="pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
</head>
<style>

.node {
  stroke-width: 1.5px;
}
body {
  margin: 0;
  background: #fff;
}

.link {
  stroke: #09f;
  stroke-opacity: .6;
}
.nodetext {
  fill: #000;
  font-size:12px;
  font-family:'楷体'
  cursor:pointer;
  pointer-events:none;
 }
#tooltip {   
		position: absolute;           
		text-align: center;
		padding: 20px;             
		margin: 10px;
		font: 12px sans-serif;        
		background: lightsteelblue;   
		border: 1px;      
		border-radius: 2px;           
		pointer-events: none;         
	}
	#tooltip h4{
		margin:0;
		font-size:14px;
	}
	#tooltip{
		background:rgba(0,0,0,0.9);
		border:1px solid grey;
		border-radius:5px;
		font-size:12px;
		width:auto;
		padding:4px;
		color:white;
		opacity:0;
	}
	#tooltip table{
		table-layout:fixed;
	}
	#tooltip tr td{
		padding:0;
		margin:0;
	}
	#tooltip tr td:nth-child(1){
		width:50px;
	}
	#tooltip tr td:nth-child(2){
		text-align:center;
	}

</style>
<body>

<script src="./res/js/d3.min.js"></script>
<script type="text/javascript" src="./res/js/jquery-2.1.1.min.js"></script>
<script language=javascript type=text/javascript>
<!--
document.oncontextmenu = function ()
{
	return false;
};
-->
</script>
<script>
var margin = {top: -5, right: -5, bottom: -5, left: -5},
width = 1700 - margin.left - margin.right,

height = 768 - margin.top - margin.bottom;

var zoom = d3.behavior.zoom()
.scaleExtent([0.3, 10])
.on("zoom", zoomed);

var drag = d3.behavior.drag()
.origin(function(d) { return d; })
.on("dragstart", dragstarted)
.on("drag", dragged)
.on("dragend", dragended);



/* var width = 845,
    height = 630; */

 var color = d3.scale.category20(); 
// var color = ["#1f77b4","#8C0044","#A42D00","#886600","#668800","#008800","#8c6d31","#770077","#AA0000","#CC6600","#00AA00","#00AA88","#227700","#000088","#FF0000"];
 
var force = d3.layout.force()
    .charge(-200)// 获取或设置节点的电荷数.(电荷数决定结点是互相排斥还是吸引)
    .linkDistance(20)// 获取或设置节点间的连接线距离.
    .linkStrength(0)
    .friction(0.9)
    .gravity(0.01)
    .size([width, height]);//获取或设置布局的 宽 和 高 的大小.

var svg = d3.select("body").append("svg")
  .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
  	.append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.right + ")")
    .call(zoom);

var rect = svg.append("rect")
.attr("width", width)
.attr("height", height)
.style("fill", "none")
.style("pointer-events", "all");

var container = svg.append("g");


/*****************************/
var group = new Array();

var sizeofGroup;
//根据groupId 获取聚集点坐标, 分4列显示

var COLUMNS = 4;
var SocityWidth = ~~(width/COLUMNS);

var fixedCenterX = new Array();
fixedCenterX.push(~~(SocityWidth/2));

for(var i=1;i<COLUMNS;++i){
	fixedCenterX.push(fixedCenterX[fixedCenterX.length-1]+SocityWidth);
}

var getCenterY = function(id){
	return (~~(SocityWidth/2))+ Math.floor(id/COLUMNS)*SocityWidth;
};
var getCenterX = function(id){
	return fixedCenterX[id%COLUMNS];
};
//alert(getCenterX(0)+" "+getCenterX(1)+" "+getCenterX(2));
//Console.log(fixedCenterX[0]);
/*****************************/
    
d3.json("getDatas", function(error, graph) {
  force
      .nodes(graph.nodes)//获得或设置布局中的节点（node）阵列组
      .links(graph.links)//获得或设置布局中节点间的连接（Link）阵列组
      .start();
      //得到社团数量，供系统自适应高度
		sizeofGroup = 0;
		for(var node in graph.nodes){
			sizeofGroup = Math.max(sizeofGroup,graph.nodes[node].group);
			group.push(graph.nodes[node].group);
		}
		//log(sizeofGroup);
		height = getCenterY(sizeofGroup-1)+(~~(SocityWidth/2));
//定义连线
	var link = container.selectAll(".link")
	  .data(graph.links)
	  .enter()
	  .append("line")
	  .attr("class", "link")
	  .attr("stroke","#09F")
	  .attr("stroke-opacity","0.4")
	  .style("stroke-width",2)
	   .on("mouseover",function(d){
	    	d3.select(this).style("stroke-width",4).style("cursor","hand");
	   }).on("mouseout",function(d){
	    	d3.select(this).style("stroke-width",2);
	   });


//定义节点标记
	var node = container.selectAll(".node")
	  .data(graph.nodes)
	  .enter()
	  .append("g");
	
	//节点圆形标记
	node.append("circle")
	  .attr("class", "node")
	  .attr("r",10)
	  .attr("stroke","#ff0")
	  .style("fill", function(d) { return color(d.group); })
	   .call(force.drag)
	    .on("mouseover",function(d){
	    	d3.select(this).attr("r",15);
	   }).on("mouseout",function(d){
	    	d3.select(this).attr("r",10);
	   }).on("click", function(d) {
		   var r=confirm("确定进入"+d.name+"团吗？");
			  if (r==true)
			    {
				  window.parent.location.href="graph!searchByName?nodename="+d.name; 
			    }
		   	
	   }).on("contextmenu", function(d) {
		  
		   window.clipboardData.setData("Text", d.name);
	   });;
	
	//标记鼠标悬停的标签
	node.append("title")
	  .text(function(d) { return d.name; });
	
	//节点上显示的姓名
	var i=0;
	svg.on("dblclick",function(d){
		if(i%2==0){
			node.append("text")
			  .attr("dy", ".3em")
			  .attr("class","nodetext")
			  .style("text-anchor", "middle")
			  .text(function(d) { return d.name; });
			i++;
		}else{
			node.select("text").remove("nodetext");
			i++;
		}
		
	});
	
	//开始力学动作
	force.on("tick", function(e) {
		var k = .2 * e.alpha;
		  graph.nodes.forEach(function(o, i) {
			    //alert(getCenterX(group[i])+"  "+getCenterY(group[i]));
		    	o.y += (getCenterY(group[i])-o.y) * k;
		    	o.x += (getCenterX(group[i])-o.x) * k;
		  });
	
		link.attr("x1", function(d) { return d.source.x; })
			.attr("y1", function(d) { return d.source.y; })
			.attr("x2", function(d) { return d.target.x; })
			.attr("y2", function(d) { return d.target.y; });
		
		node.attr("transform", function(d){ return "translate("+d.x+"," + d.y + ")";});
	});
	});
	
function dottype(d) {
	  d.x = +d.x;
	  d.y = +d.y;
	  return d;
	}

	function zoomed() {
	  container.attr("transform", "translate(" + d3.event.translate + ")scale(" + d3.event.scale + ")");
	}

	function dragstarted(d) {
	  d3.event.sourceEvent.stopPropagation();
	  d3.select(this).classed("dragging", true);
	}

	function dragged(d) {
	  d3.select(this).attr("cx", d.x = d3.event.x).attr("cy", d.y = d3.event.y);
	}

	function dragended(d) {
	  d3.select(this).classed("dragging", false);
	}





</script>