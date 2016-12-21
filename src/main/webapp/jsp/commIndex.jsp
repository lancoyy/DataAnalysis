<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>社团划分</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8" />

<!-- Bootstrap -->
<link href="./res/css/vendor/bootstrap/bootstrap.min.css"
	rel="stylesheet">
<link href="./res/css/font-awesome.css" rel="stylesheet">
<link rel="stylesheet" href="./res/css/vendor/animate/animate.min.css">
<link type="text/css" rel="stylesheet" media="all"
	href="./res/js/vendor/mmenu/css/jquery.mmenu.all.css" />
<link rel="stylesheet"
	href="./res/js/vendor/videobackground/css/jquery.videobackground.css">
<link rel="stylesheet" href="./res/css/vendor/bootstrap-checkbox.css">

<link rel="stylesheet"
	href="./res/js/vendor/rickshaw/css/rickshaw.min.css">
<link rel="stylesheet" href="./res/js/vendor/morris/css/morris.css">
<link rel="stylesheet" href="./res/js/vendor/tabdrop/css/tabdrop.css">
<link rel="stylesheet"
	href="./res/js/vendor/summernote/css/summernote.css">
<link rel="stylesheet"
	href="./res/js/vendor/summernote/css/summernote-bs3.css">
<link rel="stylesheet" href="./res/js/vendor/chosen/css/chosen.min.css">
<link rel="stylesheet"
	href="./res/js/vendor/chosen/css/chosen-bootstrap.css">

<link href="./res/css/minimal.css" rel="stylesheet">

</head>


<body class="bg-city">



	<!-- Preloader -->
	<div class="mask">
		<div id="loader"></div>
	</div>
	<!--/Preloader -->

	<!-- Wrap all page content here -->
	<div id="wrap">

		<!-- Make page fluid -->
		<div class="row">

			<!-- Fixed navbar -->
			<div
				class="navbar navbar-default navbar-fixed-top navbar-transparent-black mm-fixed-top"
				role="navigation" id="navbar">

				<!-- Branding -->
				<div class="navbar-header col-md-2">
					<a class="navbar-brand" href="index.html"> <strong>MIN</strong>IMAL
					</a>

				</div>
				<!-- Branding end -->

				<!-- .nav-collapse -->
				<div class="navbar-collapse">

					<!-- Page refresh -->
					<ul class="nav navbar-nav refresh">
						<li class="divided"><a href="#" class="page-refresh"><i
								class="fa fa-refresh"></i></a></li>
					</ul>
					<!-- /Page refresh -->

					<!-- Search -->
					<div class="search" id="main-search">
						<i class="fa fa-search"></i> <input type="text"
							placeholder="Search...">
					</div>
					<!-- Search end -->

					<!-- Quick Actions -->
					<ul class="nav navbar-nav quick-actions">



						<li class="dropdown divided user" id="current-user">
							<div class="profile-photo">
								<img src="./res/img/profile-photo.jpg" alt />
							</div> <a class="dropdown-toggle options" data-toggle="dropdown"
							href="#"> John Douey <i class="fa fa-caret-down"></i>
						</a>

							<ul class="dropdown-menu arrow settings">

								<li>

									<div class="form-group videobg-check">
										<label class="col-xs-8 control-label">Video BG</label>
										<div class="col-xs-4 control-label">
											<div class="onoffswitch greensea small">
												<input type="checkbox" name="onoffswitch"
													class="onoffswitch-checkbox" id="videobg-check"> <label
													class="onoffswitch-label" for="videobg-check"> <span
													class="onoffswitch-inner"></span> <span
													class="onoffswitch-switch"></span>
												</label>
											</div>
										</div>
									</div>

								</li>

								<li class="divider"></li>

								<li><a href="#"><i class="fa fa-user"></i> Profile</a></li>

								<li><a href="#"><i class="fa fa-calendar"></i> Calendar</a>
								</li>

								<li><a href="#"><i class="fa fa-envelope"></i> Inbox <span
										class="badge badge-red" id="user-inbox">3</span></a></li>

								<li class="divider"></li>

								<li><a href="#"><i class="fa fa-power-off"></i> Logout</a>
								</li>
							</ul>
						</li>


					</ul>
					<!-- /Quick Actions -->

				</div>
				<!--/.nav-collapse -->

			</div>
			<!-- Fixed navbar end -->

			<!-- Page content -->
			<div id="content" class="col-md-12">



				<!-- page header -->
				<div class="pageheader" style="height: 30px">


					<div class="breadcrumbs">
						<ol class="breadcrumb">
							<li style="color: #fff">你在这里</li>
							<li><a href="#" style="color: #fff">政法委大数据分析平台</a></li>
							<li style="color: #fff">重要人物分析</li>
						</ol>
					</div>


				</div>
				<!-- /page header -->

				<!-- content main container -->
				<div class="main">

					<!-- row -->
					<div class="row">
						<!-- col 3 -->
						<div class="col-lg-3 col-md-12">
							<!-- cards -->
							<div class="row cards">

								<div class="card-container">
									<div class="card card-greensea hover">
										<div class="front" style="height: 200px"></div>
										<div class="back" style="height: 200px">
											<a href="#"> <i class="fa fa-bar-chart-o fa-4x"></i> <span>Check
													Summary</span>
											</a>
										</div>
									</div>
								</div>


								<div class="card-container">
									<div class="card card-blue hover">
										<div class="front" style="height: 200px"></div>
										<div class="back" style="height: 200px">
											<a href="#"> <i class="fa fa-bar-chart-o fa-4x"></i> <span>Check
													Summary</span>
											</a>
										</div>
									</div>
								</div>



								<div class="card-container ">
									<div class="card card-redbrown hover">
										<div class="front" style="height: 200px"></div>
										<div class="back" style="height: 200px">
											<a href="#"> <i class="fa fa-bar-chart-o fa-4x"></i> <span>Check
													Summary</span>
											</a>
										</div>
									</div>
								</div>


							</div>
							<!-- /cards -->
						</div>
						<!-- /col 3 -->


						<!-- col 6 -->
						<div class="col-lg-6 col-md-12">

							<!-- tile -->
							<section class="tile transparent">
								<!-- tile header -->
								<div
									class="tile-header color transparent-black textured rounded-top-corners">
									<div class="col-md-6">
										<h1>
											<strong>社交网络分析</strong>
										</h1>
									</div>
									<div class="col-md-6 col-md-offset-6">
										<h1>选择时间</h1>
									</div>
									<div class="controls">
										<a href="javascript:doComm();"><i class="fa fa-refresh"></i></a>

									</div>
								</div>
								<!-- /tile header -->
								<!-- tile widget -->
								<div class="tile-widget color transparent-black textured">
									<div id="statistics-chart" class="chart statistics"
										style="height: 550px;"></div>
								</div>
								<!-- /tile widget -->
							</section>
							<!-- /tile -->

						</div>
						<!-- /col 6 -->



						<!-- col 3 -->
						<div class="col-lg-3 col-md-12 col-sm-12 col-xs-12">

							<!-- tile -->
							<section class="tile transparent">
								<!-- tile header -->
								<div
									class="tile-header color redbrown rounded-top-corners text-center">

									<h2>
										<strong>重要人物</strong> 列表
									</h2>
									<div class="controls">
										<a href="#" class="refresh"><i class="fa fa-refresh"></i></a>

									</div>
								</div>
								<!-- /tile header -->
								<!-- tile body -->
								<div
									class="tile-body color transparent-black rounded-bottom-corners">
									<input type="text" placeholder="输入重要人员姓名..."
										class="w100p bottommargin">
									<div id="keyPersonList">
										<ul>
											<c:forEach items="${page.kpList}" var="person">
												<li class="online">
													<div class="media">
														<a class="pull-left profile-photo"
															href="searchById?id=${person.keyPersonID}"> <img
															class="media-object"
															src="http://localhost:8080/pic/${person.keyPersonID}.jpg"
															alt
															style="width:45px; height: 45px; border-radius: 50%; padding-bottom:5px;">
														</a>
														<div class="media-body">
															<h6 class="media-heading">
																<strong><a
																	href="searchById?id=${person.keyPersonID}"
																	style="color: #fff">${person.keypersonname}</a></strong>
															</h6>
															<small><i class="fa fa-map-marker"></i>
																${person.popedom}</small> <span
																class="badge badge-outline status"></span>
														</div>
													</div>
												</li>
											</c:forEach>

										</ul>
										<ul class="pager pager-custom">
											<c:choose>
												<c:when test="${page.currentPage==1}">
													<li class="disabled"><a><i
															class="fa fa-angle-left"></i></a></li>
												</c:when>
												<c:otherwise>
													<li><a href="javascript:doPage(${page.uppage});"><i
															class="fa fa-angle-left"></i></a></li>
												</c:otherwise>
											</c:choose>
											<li class="active">${page.currentPage}</li>
											<li><a href="javascript:doPage(${page.nextpage});"><i
													class="fa fa-angle-right"></i></a></li>

										</ul>
									</div>
								</div>
								<!-- /tile body -->
							</section>
							<!-- /tile -->


						</div>
						<!-- /col 3 -->


					</div>
					<!-- /row -->


					<!-- row -->
					<div class="row"></div>
					<!-- /row -->



				</div>
				<!-- /content container -->


			</div>
			<!-- Page content end -->


		</div>
		<!-- Make page fluid-->




	</div>
	<!-- Wrap all page content end -->




	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://code.jquery.com/jquery.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="./res/js/vendor/bootstrap/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="./res/js/vendor/mmenu/js/jquery.mmenu.min.js"></script>
	<script type="text/javascript"
		src="./res/js/vendor/sparkline/jquery.sparkline.min.js"></script>
	<script type="text/javascript"
		src="./res/js/vendor/nicescroll/jquery.nicescroll.min.js"></script>
	<script type="text/javascript"
		src="./res/js/vendor/animate-numbers/jquery.animateNumbers.js"></script>
	<script type="text/javascript"
		src="./res/js/vendor/videobackground/jquery.videobackground.js"></script>
	<script type="text/javascript"
		src="./res/js/vendor/blockui/jquery.blockUI.js"></script>

	<script src="./res/js/vendor/flot/jquery.flot.min.js"></script>
	<script src="./res/js/vendor/flot/jquery.flot.time.min.js"></script>
	<script src="./res/js/vendor/flot/jquery.flot.selection.min.js"></script>
	<script src="./res/js/vendor/flot/jquery.flot.animator.min.js"></script>
	<script src="./res/js/vendor/flot/jquery.flot.orderBars.js"></script>
	<script src="./res/js/vendor/easypiechart/jquery.easypiechart.min.js"></script>

	<script src="./res/js/vendor/rickshaw/raphael-min.js"></script>
	<script src="./res/js/vendor/rickshaw/d3.v2.js"></script>
	<script src="./res/js/vendor/rickshaw/rickshaw.min.js"></script>

	<script src="./res/js/vendor/morris/morris.min.js"></script>

	<script src="./res/js/vendor/tabdrop/bootstrap-tabdrop.min.js"></script>

	<script src="./res/js/vendor/summernote/summernote.min.js"></script>

	<script src="./res/js/vendor/chosen/chosen.jquery.min.js"></script>

	<script src="./res/js/minimal.min.js"></script>

	<script src="./res/js/d3.min.js"></script>
	<!-- 
	<script type="text/javascript" src="./res/js/jquery-2.1.1.min.js"></script>
 -->
	<script type="text/javascript">
		$(function() {
			$('.card.hover').hover(function() {
				$(this).addClass('flip');
			}, function() {
				$(this).removeClass('flip');
			});
		});
		
	
		page ="<%=session.getAttribute("page")%>";
		console.log(page);
		
		function doPage(p){
			$('#keyPersonList').html("");
			$.ajax({
				type:"GET",
				url:"showPersonByPage",
				data:{currentpage: p},
				dataType:"html",
				cache: false,
				success:function(data){
					$('#keyPersonList').html(data);
				},
				error:function(error){alert(error);}
			});
		}
		
		//返回主团的AJAX
		function doComm(){
			$('#statistics-chart').html("");
			$.ajax({
				type:"GET",
				url:"community",
				dataType:"html",
				cache: false,
				success:function(data){
							var margin = {
				top : -5,
				right : -5,
				bottom : -5,
				left : -5
			},
			width = document.getElementById("statistics-chart").offsetWidth,
	
			height = document.getElementById("statistics-chart").offsetHeight;
	
		var zoom = d3.behavior.zoom()
			.scaleExtent([ 0.3, 10 ])
			.on("zoom", zoomed);
	
		var drag = d3.behavior.drag()
			.origin(function(d) {
				return d;
			})
			.on("dragstart", dragstarted)
			.on("drag", dragged)
			.on("dragend", dragended);
	
	
	
		/* var width = 845,
		    height = 630; */
	
		var color = d3.scale.category20();
		//var color = [ "#1f77b4", "#8C0044", "#A42D00", "#886600", "#668800", "#008800", "#8c6d31", "#770077", "#AA0000", "#CC6600", "#00AA00", "#00AA88", "#227700", "#000088", "#FF0000" ];
	
		var force = d3.layout.force()
			.charge(-700) // 获取或设置节点的电荷数.(电荷数决定结点是互相排斥还是吸引)
			.linkDistance(200) // 获取或设置节点间的连接线距离.
			.size([ width, height ]); //获取或设置布局的 宽 和 高 的大小.
	
		var svg = d3.select("#statistics-chart").append("svg")
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
		
		d3.json("getDatas", function(error, graph) {
			force
				.nodes(graph.nodes) //获得或设置布局中的节点（node）阵列组
				.links(graph.links) //获得或设置布局中节点间的连接（Link）阵列组
				.start();
	
			//定义连线
			var link = container.selectAll(".link")
				.data(graph.links)
				.enter()
				.append("line")
				.attr("class", "link")
				.attr("stroke", "#09F")
				.attr("stroke-opacity", "0.4")
				.style("stroke-width", 2)
				.on("mouseover", function(d) {
					d3.select(this).style("stroke-width", 4).style("cursor", "hand");
				}).on("mouseout", function(d) {
				d3.select(this).style("stroke-width", 2);
			});
	
	
			//定义节点标记
			var node = container.selectAll(".node")
				.data(graph.nodes)
				.enter()
				.append("g");
	
			//节点圆形标记
			node.append("circle")
				.attr("class", "node")
				.attr("r", 10)
				.attr("stroke", "#ff0")
				.style("fill", function(d) {
					return color(d.group);
				})
				.call(force.drag)
				.on("mouseover", function(d) {
					d3.select(this).attr("r", 15);
				}).on("mouseout", function(d) {
				d3.select(this).attr("r", 10);
			}).on("click", function(d) {
				var r = confirm("确定进入" + d.name + "所在的子团吗？");
				if (r == true) {
					//window.parent.location.href = "subCommunity?nodename=" + d.name;
					window.parent.location.href = "javascript:doSubComm("+d.name+");"
				}
	
			}).on("contextmenu", function(d) {
	
				window.clipboardData.setData("Text", d.name);
			});
			;
	
			//标记鼠标悬停的标签
			node.append("title")
				.text(function(d) {
					return d.name;
				});
	
			//节点上显示的姓名
			var i = 0;
			svg.on("dblclick", function(d) {
				if (i % 2 == 0) {
					node.append("text")
						.attr("dy", ".3em")
						.attr("class", "nodetext")
						.style("text-anchor", "middle")
						.text(function(d) {
							return d.name;
						});
					i++;
				} else {
					node.select("text").remove("nodetext");
					i++;
				}
	
			});
	
			//开始力学动作
			force.on("tick", function() {
				link.attr("x1", function(d) {
					return d.source.x;
				})
					.attr("y1", function(d) {
						return d.source.y;
					})
					.attr("x2", function(d) {
						return d.target.x;
					})
					.attr("y2", function(d) {
						return d.target.y;
					});
	
				node.attr("transform", function(d) {
					return "translate(" + d.x + "," + d.y + ")";
				});
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
				}
			});
		}
		
		
		//进入子团的AJAX
		function doSubComm(node){
			$('#statistics-chart').html("");
			$.ajax({
				type:"GET",
				url:"subCommunity",
				data:{nodename: node},
				dataType:"html",
				cache: false,
				success:function(data){
					var margin = {
				top : -5,
				right : -5,
				bottom : -5,
				left : -5
			},
			width = document.getElementById("statistics-chart").offsetWidth,
	
			height = document.getElementById("statistics-chart").offsetHeight;
	
		var zoom = d3.behavior.zoom()
			.scaleExtent([ 0.3, 10 ])
			.on("zoom", zoomed);
	
		var drag = d3.behavior.drag()
			.origin(function(d) {
				return d;
			})
			.on("dragstart", dragstarted)
			.on("drag", dragged)
			.on("dragend", dragended);
	
	
	
		/* var width = 845,
		    height = 630; */
	
		var color = d3.scale.category20();
		//var color = [ "#1f77b4", "#8C0044", "#A42D00", "#886600", "#668800", "#008800", "#8c6d31", "#770077", "#AA0000", "#CC6600", "#00AA00", "#00AA88", "#227700", "#000088", "#FF0000" ];
	
		var force = d3.layout.force()
			.charge(-700) // 获取或设置节点的电荷数.(电荷数决定结点是互相排斥还是吸引)
			.linkDistance(200) // 获取或设置节点间的连接线距离.
			.size([ width, height ]); //获取或设置布局的 宽 和 高 的大小.
	
		var svg = d3.select("#statistics-chart").append("svg")
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
				.nodes(graph.nodes) //获得或设置布局中的节点（node）阵列组
				.links(graph.links) //获得或设置布局中节点间的连接（Link）阵列组
				.start();
	
			//定义连线
			var link = container.selectAll(".link")
				.data(graph.links)
				.enter()
				.append("line")
				.attr("class", "link")
				.attr("stroke", "#09F")
				.attr("stroke-opacity", "0.4")
				.style("stroke-width", 2)
				.on("mouseover", function(d) {
					d3.select(this).style("stroke-width", 4).style("cursor", "hand");
				}).on("mouseout", function(d) {
				d3.select(this).style("stroke-width", 2);
			});
	
	
			//定义节点标记
			var node = container.selectAll(".node")
				.data(graph.nodes)
				.enter()
				.append("g");
	
			//节点圆形标记
			node.append("circle")
				.attr("class", "node")
				.attr("r", 10)
				.attr("stroke", "#ff0")
				.style("fill", function(d) {
					return color(d.group);
				})
				.call(force.drag)
				.on("mouseover", function(d) {
					d3.select(this).attr("r", 15);
				}).on("mouseout", function(d) {
				d3.select(this).attr("r", 10);
			}).on("click", function(d) {
				var r = confirm("确定进入" + d.name + "所在的子团吗？");
				if (r == true) {
					//window.parent.location.href = "subCommunity?nodename=" + d.name;
					window.parent.location.href = "javascript:doSubComm("+d.name+");"
					
				}
	
			}).on("contextmenu", function(d) {
	
				window.clipboardData.setData("Text", d.name);
			});
			
	
			//标记鼠标悬停的标签
			node.append("title")
				.text(function(d) {
					return d.name;
				});
	
			//节点上显示的姓名
			var i = 0;
			svg.on("dblclick", function(d) {
				if (i % 2 == 0) {
					node.append("text")
						.attr("dy", ".3em")
						.attr("class", "nodetext")
						.style("text-anchor", "middle")
						.text(function(d) {
							return d.name;
						});
					i++;
				} else {
					node.select("text").remove("nodetext");
					i++;
				}
	
			});
	
			//开始力学动作
			force.on("tick", function() {
				link.attr("x1", function(d) {
					return d.source.x;
				})
					.attr("y1", function(d) {
						return d.source.y;
					})
					.attr("x2", function(d) {
						return d.target.x;
					})
					.attr("y2", function(d) {
						return d.target.y;
					});
	
				node.attr("transform", function(d) {
					return "translate(" + d.x + "," + d.y + ")";
				});
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
		}});}
		
		
	</script>

	<script>
		var margin = {
				top : -5,
				right : -5,
				bottom : -5,
				left : -5
			},
			width = document.getElementById("statistics-chart").offsetWidth,
	
			height = document.getElementById("statistics-chart").offsetHeight;
	
		var zoom = d3.behavior.zoom()
			.scaleExtent([ 0.3, 10 ])
			.on("zoom", zoomed);
	
		var drag = d3.behavior.drag()
			.origin(function(d) {
				return d;
			})
			.on("dragstart", dragstarted)
			.on("drag", dragged)
			.on("dragend", dragended);
	
	
	
		/* var width = 845,
		    height = 630; */
	
		var color = d3.scale.category20();
		//var color = [ "#1f77b4", "#8C0044", "#A42D00", "#886600", "#668800", "#008800", "#8c6d31", "#770077", "#AA0000", "#CC6600", "#00AA00", "#00AA88", "#227700", "#000088", "#FF0000" ];
	
		var force = d3.layout.force()
			.charge(-700) // 获取或设置节点的电荷数.(电荷数决定结点是互相排斥还是吸引)
			.linkDistance(200) // 获取或设置节点间的连接线距离.
			.size([ width, height ]); //获取或设置布局的 宽 和 高 的大小.
	
		var svg = d3.select("#statistics-chart").append("svg")
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
		var commFlag="<%=session.getAttribute("commFlag")%>";
		console.log(commFlag);
		
		d3.json(commFlag, function(error, graph) {
			force
				.nodes(graph.nodes) //获得或设置布局中的节点（node）阵列组
				.links(graph.links) //获得或设置布局中节点间的连接（Link）阵列组
				.start();
	
			//定义连线
			var link = container.selectAll(".link")
				.data(graph.links)
				.enter()
				.append("line")
				.attr("class", "link")
				.attr("stroke", "#09F")
				.attr("stroke-opacity", "0.4")
				.style("stroke-width", 2)
				.on("mouseover", function(d) {
					d3.select(this).style("stroke-width", 4).style("cursor", "hand");
				}).on("mouseout", function(d) {
				d3.select(this).style("stroke-width", 2);
			});
	
	
			//定义节点标记
			var node = container.selectAll(".node")
				.data(graph.nodes)
				.enter()
				.append("g");
	
			//节点圆形标记
			node.append("circle")
				.attr("class", "node")
				.attr("r", 10)
				.attr("stroke", "#ff0")
				.style("fill", function(d) {
					return color(d.group);
				})
				.call(force.drag)
				.on("mouseover", function(d) {
					d3.select(this).attr("r", 15);
				}).on("mouseout", function(d) {
				d3.select(this).attr("r", 10);
			}).on("click", function(d) {
				var r = confirm("确定进入" + d.name + "所在的子团吗？");
				if (r == true) {
					//window.parent.location.href = "subCommunity?nodename=" + d.name;
					window.parent.location.href = "javascript:doSubComm("+d.name+");"
				}
	
			}).on("contextmenu", function(d) {
	
				window.clipboardData.setData("Text", d.name);
			});
			;
	
			//标记鼠标悬停的标签
			node.append("title")
				.text(function(d) {
					return d.name;
				});
	
			//节点上显示的姓名
			var i = 0;
			svg.on("dblclick", function(d) {
				if (i % 2 == 0) {
					node.append("text")
						.attr("dy", ".3em")
						.attr("class", "nodetext")
						.style("text-anchor", "middle")
						.text(function(d) {
							return d.name;
						});
					i++;
				} else {
					node.select("text").remove("nodetext");
					i++;
				}
	
			});
	
			//开始力学动作
			force.on("tick", function() {
				link.attr("x1", function(d) {
					return d.source.x;
				})
					.attr("y1", function(d) {
						return d.source.y;
					})
					.attr("x2", function(d) {
						return d.target.x;
					})
					.attr("y2", function(d) {
						return d.target.y;
					});
	
				node.attr("transform", function(d) {
					return "translate(" + d.x + "," + d.y + ")";
				});
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

</body>

</html>
