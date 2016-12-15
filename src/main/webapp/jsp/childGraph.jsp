<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<meta charset="utf-8">
<style>

.node {
  stroke-width: 1.5px;
}
body {
  margin: 0;
  background: #000;
}

.link {
  stroke: #09f;
  stroke-opacity: .6;
}
.nodetext {
  fill: #fff;
  font-size:12px;
  font-family:'楷体'
  cursor:pointer;
  pointer-events:none;
 }


</style>
<body>
<script src="./res/js/d3.min.js"></script>
<script type="text/javascript" src="./res/js/jquery-2.1.1.min.js"></script>
<script language=javascript type=text/javascript>
<!--
document.oncontextmenu = function ()
{
	return false
}
-->
</script>
<script>
var margin = {top: -5, right: -5, bottom: -5, left: -5},
width = 1600 - margin.left - margin.right,
height = 750 - margin.top - margin.bottom;

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

/* var color = d3.scale.category20(); */
 var color = ["#1f77b4","#8C0044","#A42D00","#886600","#668800","#008800","#8c6d31","#770077","#AA0000","#CC6600","#00AA00","#00AA88","#227700","#000088","#FF0000"];
 
var force = d3.layout.force()
    .charge(-700)// 获取或设置节点的电荷数.(电荷数决定结点是互相排斥还是吸引)
    .linkDistance(200)// 获取或设置节点间的连接线距离.
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
    
    
d3.json("getSubDatas", function(error, graph) {
  force
      .nodes(graph.nodes)//获得或设置布局中的节点（node）阵列组
      .links(graph.links)//获得或设置布局中节点间的连接（Link）阵列组
      .start();

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
	  .style("fill", function(d) { return color[d.group%15]; })
	   .call(force.drag)
	    .on("mouseover",function(d){
	    	d3.select(this).attr("r",15);
	   }).on("mouseout",function(d){
	    	d3.select(this).attr("r",10);
	   }).on("click", function(d) {
		   //var r=confirm("确定进入"+d.name+"所在的子团吗？");
			  //if (r==true)
			    //{
				  //window.parent.location.href="subConnInfo!selectPrivateInfo?privateEmail="+d.name; 
			    //}
		   	
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
	force.on("tick", function() {
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