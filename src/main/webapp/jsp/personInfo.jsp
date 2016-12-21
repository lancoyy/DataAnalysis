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

<title>重要人物个人信息</title>

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
				class="navbar navbar-default navbar-fixed-top navbar-transparent-white mm-fixed-top"
				role="navigation" id="navbar">

				<!-- Branding -->
				<div class="navbar-header col-md-2">
					<a class="navbar-brand" href="#"> <strong>MIN</strong>IMAL
					</a>

				</div>
				<!-- Branding end -->

				<!-- .nav-collapse -->
				<div class="navbar-collapse">

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
							<li><a href="#" style="color: #fff">重要人物分析</a></li>
							<li style="color: #fff">重要人物信息展示</li>
						</ol>
					</div>


				</div>
				<!-- /page header -->

				<!-- content main container -->
				<div class="main">

					<!-- row -->
					<div class="row">

						<!-- col 8  -->
						<div class="col-lg-8 col-md-12 col-sm-12 col-xs-12">
							<!-- tile -->
							<section class="tile color transparent-white">

								<!-- tile header -->
								<div class="tile-header nopadding">
									<div class="controls">
										<a href="#" class="refresh"><i class="fa fa-refresh"></i></a>
										<a href="#" class="remove"><i class="fa fa-times"></i></a>
									</div>
								</div>
								<!-- /tile header -->

								<!-- tile widget -->

								<div class="media social-feed">
									<span class="pull-left" style="padding-left:60px"> <img
										src="http://localhost:8080/pic/${keyPersonInfo.keyPersonID}.jpg" alt class="img-circle">
									</span>

									<div class="media-body" style="color: #fff">
										<div class="col-lg-8 col-md-8 col-sm-8">
											<h2 class="media-heading">
												<strong>${keyPersonInfo.keypersonname}</strong>
											</h2>
											<small>&nbsp;${keyPersonInfo.jobName}&nbsp;&nbsp;&nbsp;<i
												class="fa fa-map-marker"></i>${keyPersonInfo.popedom}</small></span>

										</div>
										<!-- 备注：根据积分的颜色变化 -->
										<div id="fraction" class="col-lg-4 col-md-4 col-sm-4">
											<h1>${keyPersonInfo.fraction}</h1>
										</div>

									</div>
								</div>

								<!-- /tile widget -->

								<!-- tile body -->
								<div class="tile-body">

									<div class="panel-group accordion" id="multi-accordion">
										<div class="panel panel-default">
											<div class="panel-heading">
												<h4 class="panel-title">
													<a data-toggle="collapse" href="#multicollapseOne"> <strong>个人信息</strong></a>
												</h4>
											</div>
											<div id="multicollapseOne" class="panel-collapse">
												<div class="panel-body">
													<table class="table table-hover" style="font-size: 14px">
														<tbody>
															<tr>
																<td>民族</td>
																<td>${keyPersonInfo.nation}</td>
															</tr>
															<tr>
																<td>级别</td>
																<td>${keyPersonInfo.level}</td>
															</tr>
															<tr>
																<td>类别</td>
																<td>${keyPersonInfo.category}</td>
															</tr>
															<tr>
																<td style="width: 120px">身份证号</td>
																<td>${keyPersonInfo.idNum}</td>
															</tr>
															<tr>
																<td>现居地址</td>
																<td>${keyPersonInfo.address}</td>
															</tr>
															<tr>
																<td>包保领导</td>
																<td>${keyPersonInfo.leader}</td>
															</tr>

														</tbody>
													</table>
												</div>
											</div>
										</div>
										<div class="panel panel-redbrown">
											<div class="panel-heading">
												<h4 class="panel-title">
													<a data-toggle="collapse" href="#multicollapseThree"> <strong>诉求</strong>
													</a>
												</h4>
											</div>
											<div id="multicollapseThree" class="panel-collapse collapse">
												<ul class="list-group">
													<li class="list-group-item">2012-01-20: 给钱</li>
													<li class="list-group-item">2012-01-20：让娃上个好学校</li>
												</ul>
											</div>
										</div>
									</div>

								</div>
							</section>
							<!-- /tile body -->
							<!-- /tile -->

							<!-- tile -->
							<section class="tile transparent">
								<div class="tile-header transparent">
									<h1>
										<strong>通联列表</strong>
									</h1>
									<span class="note">including: <span class="italic">col
											reorder with resize</span></span>
									<div class="controls">
										<a href="#" class="refresh"><i class="fa fa-refresh"></i></a>
										<a href="#" class="remove"><i class="fa fa-times"></i></a>
									</div>
								</div>
							</section>
							<div class="col-lg-6 col-md-12 col-xs-12"
								style="padding-left: 0px;">

								<section class="tile color transparent-black"
									style="height:300px">
									<!-- tile header -->
									<div class="tile-header">
										<h1>
											<strong>Transparent Black</strong> Tile
										</h1>
										<div class="controls">
											<a href="#" class="refresh"><i class="fa fa-refresh"></i></a>
											<a href="#" class="remove"><i class="fa fa-times"></i></a>
										</div>
									</div>
									<!-- /tile header -->

									<!-- tile body -->
									<div id="subcommunity" class="tile-body"></div>
									<!-- /tile body -->

								</section>
							</div>

							<div class="col-lg-6 col-md-12 col-xs-12"
								style="padding-right: 0px;">
								<section class="tile transparent">
									<!-- tile body -->

									<div class="tile-body rounded-corners">

										<div class="table-responsive">
											<div id="drillDownDataTable_wrapper"
												class="dataTables_wrapper form-inline" role="grid">
												<div class="row">
													<div class="col-md-6">
														<div id="drillDownDataTable_length"
															class="dataTables_length"></div>
													</div>
													<div class="col-md-6">
														<div class="dataTables_filter"
															id="drillDownDataTable_filter">
															<label><input aria-controls="drillDownDataTable"
																placeholder="Search" type="text"></label>
														</div>
													</div>
													<div id="drillDownDataTable_processing"
														class="dataTables_processing" style="visibility: hidden;">Processing...</div>
												</div>
												<table class="table table-datatable table-custom dataTable"
													id="drillDownDataTable"
													aria-describedby="drillDownDataTable_info">
													<thead>
														<tr role="row">
															<th class="no-sort sorting_disabled control text-center"
																style="width: 45px; cursor: pointer;"
																role="columnheader" rowspan="1" colspan="1"
																aria-label=""></th>
															<th class="sort-alpha sorting" role="columnheader"
																tabindex="0" aria-controls="drillDownDataTable"
																rowspan="1" colspan="1"
																style="width: 504px; cursor: pointer;"
																aria-label="Rendering engine: activate to sort column ascending">Rendering
																engine</th>
															<th class="sort-alpha sorting" role="columnheader"
																tabindex="0" aria-controls="drillDownDataTable"
																rowspan="1" colspan="1"
																style="width: 271px; cursor: pointer;"
																aria-label="Browser: activate to sort column ascending">Browser</th>
															<th class="sorting_asc" role="columnheader" tabindex="0"
																aria-controls="drillDownDataTable" rowspan="1"
																colspan="1" style="width: 276px; cursor: pointer;"
																aria-sort="ascending"
																aria-label="CSS grade: activate to sort column descending">CSS
																grade</th>
														</tr>
													</thead>

													<tbody role="alert" aria-live="polite" aria-relevant="all">
														<tr class="odd">
															<td colspan="4" class="dataTables_empty" valign="top">No
																data available in table</td>
														</tr>
													</tbody>
												</table>
												<div class="row">
													<div class="col-md-4 sm-center">
														<div class="dataTables_info" id="drillDownDataTable_info">Showing
															0 to 0 of 0 entries</div>
													</div>
													<div class="col-md-4"></div>
													<div class="col-md-4 text-right sm-center">
														<div
															class="dataTables_paginate paging_bootstrap paging_custombootstrap">
															<ul class="pagination">
																<li class="prev disabled"><a href="#">Previous</a></li>
																<li class="next disabled"><a href="#">Next</a></li>
															</ul>
														</div>
													</div>
												</div>
											</div>
										</div>

									</div>
									<!-- /tile body -->



								</section>
							</div>
						</div>
						<!-- /col8 -->

						<!-- col4 -->
						<div class="col-lg-4 col-md-12 col-xs-12 sm-left">
							<section tile transparent no-shadow>
								<h1 class="timeline-heading">
									<strong>时间轴</strong>
								</h1>


								<ol class="timeline timeline-mini">
									<c:forEach items="${contradictions}" var="contradiction">
										<li class="color transparent-black">
											<div class="pointer slategray"></div>
											<div class="el-container">
												<div class="content">
													<span class="time"><i class="fa fa-clock-o"></i>${contradiction.addTime}</span>
													<h1>
														<strong>${contradiction.keyProblem}</strong><br> <small>${contradiction.keyword}</small>
													</h1>

													<p>${contradiction.issueContent}</p>
												</div>
											</div>
										</li>
									</c:forEach>

								</ol>

								<h1 class="timeline-heading">
									<strong>Older</strong>
								</h1>

							</section>
						</div>
						<!-- /col4 -->

					</div>
					<!-- /row -->

				</div>

				<!-- /content container -->


			</div>
			<!-- Page content end -->


		</div>
		<!-- Make page fluid-->




	</div>
	<!-- Wrap all page content end -->



	<section class="videocontent" id="video"></section>



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
	
		var svg = d3.select("#subcommunity").append("svg")
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
