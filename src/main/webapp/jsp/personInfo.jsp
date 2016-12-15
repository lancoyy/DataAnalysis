<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
				<div class="pageheader" style="height: 20px">

					<div class="breadcrumbs">
						<ol class="breadcrumb">
							<li>你在这里</li>
							<li><a href="#" style="color: #fff">政法委大数据分析平台</a></li>
							<li class="active">重要人物分析</li>
						</ol>
					</div>


				</div>
				<!-- /page header -->

				<!-- content main container -->
				<div class="main">

					<!-- row -->
					<div class="row">

						<!-- col 4  -->
						<div class="col-lg-4 col-md-12 col-sm-12 col-xs-12">
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
									<span class="pull-left" style="padding-left:10px"> <img
										src="./res/img/profile-photo.jpg" alt class="img-circle">
									</span>

									<div class="media-body" style="color: #fff">
										<div class="col-lg-8 col-md-8 col-sm-8">
											<h3 class="media-heading">
												<strong>杨 小慧</strong>

											</h3>
											<small>学生</small> <span class="glyphicon glyphicon-search"></span><small>四川成都</small></span>

										</div>
										<!-- 备注：根据积分的颜色变化 -->
										<div id="fraction" class="col-lg-4 col-md-4 col-sm-4">
											<h1>93</h1>
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
																<td>汉</td>
															</tr>
															<tr>
																<td>级别</td>
																<td>1</td>
															</tr>
															<tr>
																<td>类别</td>
																<td>特殊利益诉求群体类重点人员</td>
															</tr>
															<tr>
																<td>身份证号</td>
																<td>510106199410251245</td>
															</tr>
															<tr>
																<td>现居地址</td>
																<td>四川省 成都市 电子科技大学 13栋 505</td>
															</tr>
															<tr>
																<td>包保领导</td>
																<td>韩明皓</td>
															</tr>

														</tbody>
													</table>
												</div>
											</div>
										</div>
										<div class="panel panel-drank">
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
						</div>
						<!-- /col4 -->

						<!-- col 8 -->
						<div class="col-lg-8 col-md-12 col-xs-12 text-center sm-left">



							<h1 class="timeline-heading">
								<strong>This</strong> month
							</h1>

							<ol class="timeline">

								<li class="color transparent-black">
									<div class="pointer slategray"></div>
									<div class="el-container">
										<div class="side bg-greensea">
											<i class="fa fa-user"></i>
										</div>
										<div class="content">
											<span class="time"><i class="fa fa-clock-o"></i>
												Today, 04:24pm</span>
											<h1>
												<strong>Left Side</strong> Icon
											</h1>
											<p>Lorem ipsum dolor sit amet, consectetur adipisicing
												elit, sed do eiusmod tempor incididunt ut labore et dolore
												magna aliqua.</p>
										</div>
									</div>
								</li>

								<li class="color transparent-black textured">
									<div class="pointer slategray">
										<i class="fa fa-calendar"></i>
									</div>
									<div class="el-container">
										<div class="content">
											<span class="time"><i class="fa fa-clock-o"></i>
												Today, 03:15pm</span>
											<h1>
												<strong>Right Side</strong> Icon
											</h1>
											<p>Lorem ipsum dolor sit amet, consectetur adipisicing
												elit, sed do eiusmod tempor incididunt ut labore et dolore
												magna aliqua. Ut enim ad minim veniam, quis nostrud
												exercitation ullamco laboris nisi ut aliquip ex ea commodo
												consequat.Lorem ipsum dolor sit amet, consectetur
												adipisicing elit, sed do eiusmod tempor incididunt ut labore
												et dolore magna aliqua.</p>
										</div>
										<div class="side">
											<i class="fa fa-cog"></i>
										</div>
									</div>
								</li>

								<li class="color transparent-white">
									<div class="pointer slategray">
										<i class="fa fa-clock-o"></i>
									</div>
									<div class="el-container">
										<div class="side">
											<img src="./res/img/carousel/carousel1.jpg" alt>
										</div>
										<div class="content">
											<span class="time"><i class="fa fa-clock-o"></i>
												Today, 03:12pm</span>
											<h1>
												<strong>Left Side</strong> Image
											</h1>
											<p>Lorem ipsum dolor sit amet, consectetur adipisicing
												elit, sed do eiusmod tempor incididunt ut labore et dolore
												magna aliqua. Ut enim ad minim veniam, quis nostrud
												exercitation ullamco laboris nisi ut aliquip ex ea commodo
												consequat.</p>
										</div>
									</div>
								</li>

								<li class="color transparent-black">
									<div class="pointer slategray">
										<i class="fa fa-coffee"></i>
									</div>
									<div class="el-container">
										<div class="content">
											<span class="time"><i class="fa fa-clock-o"></i>
												Today, 01:55pm</span>
											<h1>
												<strong>Right Side</strong> Image
											</h1>
											<p>Lorem ipsum dolor sit amet, consectetur adipisicing
												elit, sed do eiusmod tempor incididunt ut labore et dolore
												magna aliqua. Ut enim ad minim veniam, quis nostrud
												exercitation ullamco laboris nisi ut aliquip ex ea commodo
												consequat.</p>
										</div>
										<div class="side">
											<img src="./res/img/carousel/carousel2.jpg" alt>
										</div>
									</div>
								</li>

								<li class="color transparent-black">
									<div class="pointer slategray">
										<i class="fa fa-envelope"></i>
									</div>
									<div class="el-container">
										<div class="content">
											<span class="time"><i class="fa fa-clock-o"></i>
												Yesterday, 18:40pm</span>
											<h1>
												<strong>Top</strong> Icon
											</h1>
											<i class="fa fa-info-circle block"></i>
											<p>Lorem ipsum dolor sit amet, consectetur adipisicing
												elit, sed do eiusmod tempor incididunt ut labore et dolore
												magna aliqua. Ut enim ad minim veniam, quis nostrud
												exercitation ullamco laboris nisi ut aliquip ex ea commodo
												consequat.</p>
										</div>
									</div>
								</li>

								<li class="color transparent-white">
									<div class="pointer slategray">
										<i class="fa fa-comments"></i>
									</div>
									<div class="el-container">
										<div class="content">
											<span class="time"><i class="fa fa-clock-o"></i>
												Yesterday, 22:12pm</span>
											<h1>
												<strong>Bottom</strong> Image
											</h1>
											<p>Lorem ipsum dolor sit amet, consectetur adipisicing
												elit, sed do eiusmod tempor incididunt ut labore et dolore
												magna aliqua. Ut enim ad minim veniam, quis nostrud
												exercitation ullamco laboris nisi ut aliquip ex ea commodo
												consequat.</p>
											<img src="./res/img/carousel/carousel3.jpg" alt>
										</div>
									</div>
								</li>

								<li class="color red">
									<div class="pointer red">
										<i class="fa fa-heart"></i>
									</div>
									<div class="el-container">
										<div class="content">
											<span class="time"><i class="fa fa-clock-o"></i>
												Yesterday, 13:22pm</span>
											<h1>Colored Element</h1>
											<p>Lorem ipsum dolor sit amet, consectetur adipisicing
												elit, sed do eiusmod tempor incididunt ut labore et dolore
												magna aliqua. Ut enim ad minim veniam, quis nostrud
												exercitation ullamco laboris nisi ut aliquip ex ea commodo
												consequat.</p>
										</div>
									</div>
								</li>

								<li class="full-width">
									<div class="pointer slategray">
										<i class="fa fa-question-circle"></i>
									</div>
									<div class="el-container">
										<div class="content">
											<span class="time"><i class="fa fa-clock-o"></i>
												Yesterday, 10:18am</span>
											<h1>Full-Width Element</h1>
											<p>Lorem ipsum dolor sit amet, consectetur adipisicing
												elit, sed do eiusmod tempor incididunt ut labore et dolore
												magna aliqua. Ut enim ad minim veniam, quis nostrud
												exercitation ullamco laboris nisi ut aliquip ex ea commodo
												consequat. Duis aute irure dolor in reprehenderit in
												voluptate velit esse cillum dolore eu fugiat nulla pariatur.
												Excepteur sint occaecat cupidatat non proident, sunt in
												culpa qui officia deserunt mollit anim id est laborum.</p>
										</div>
									</div>
								</li>

							</ol>

							<h1 class="timeline-heading">
								<strong>Older</strong>
							</h1>


						</div>
						<!-- /col 18 -->

					</div>
					<!-- /row -->

				</div>
				<!-- /content container -->


			</div>
			<!-- Page content end -->




			<div id="mmenu" class="right-panel">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs nav-justified">
					<li class="active"><a href="#mmenu-users" data-toggle="tab"><i
							class="fa fa-users"></i></a></li>
					<li class=""><a href="#mmenu-history" data-toggle="tab"><i
							class="fa fa-clock-o"></i></a></li>
					<li class=""><a href="#mmenu-friends" data-toggle="tab"><i
							class="fa fa-heart"></i></a></li>
					<li class=""><a href="#mmenu-settings" data-toggle="tab"><i
							class="fa fa-gear"></i></a></li>
				</ul>

				<!-- Tab panes -->
				<div class="tab-content">
					<div class="tab-pane active" id="mmenu-users">
						<h5>
							<strong>Online</strong> Users
						</h5>

						<ul class="users-list">

							<li class="online">
								<div class="media">
									<a class="pull-left profile-photo" href="#"> <img
										class="media-object" src="./res/img/ici-avatar.jpg" alt>
									</a>
									<div class="media-body">
										<h6 class="media-heading">
											Ing. Imrich <strong>Kamarel</strong>
										</h6>
										<small><i class="fa fa-map-marker"></i> Ulaanbaatar,
											Mongolia</small> <span class="badge badge-outline status"></span>
									</div>
								</div>
							</li>

							<li class="online">
								<div class="media">

									<a class="pull-left profile-photo" href="#"> <img
										class="media-object" src="./res/img/arnold-avatar.jpg" alt>
									</a>

									<div class="media-body">
										<h6 class="media-heading">
											Arnold <strong>Karlsberg</strong>
										</h6>
										<small><i class="fa fa-map-marker"></i> Bratislava,
											Slovakia</small> <span class="badge badge-outline status"></span>
									</div>

								</div>
							</li>

							<li class="online">
								<div class="media">
									<a class="pull-left profile-photo" href="#"> <img
										class="media-object" src="./res/img/peter-avatar.jpg" alt>

									</a>
									<div class="media-body">
										<h6 class="media-heading">
											Peter <strong>Kay</strong>
										</h6>
										<small><i class="fa fa-map-marker"></i> Kosice,
											Slovakia</small> <span class="badge badge-outline status"></span>
									</div>
								</div>
							</li>

							<li class="online">
								<div class="media">
									<a class="pull-left profile-photo" href="#"> <img
										class="media-object" src="./res/img/george-avatar.jpg" alt>
									</a>
									<div class="media-body">
										<h6 class="media-heading">
											George <strong>McCain</strong>
										</h6>
										<small><i class="fa fa-map-marker"></i> Prague, Czech
											Republic</small> <span class="badge badge-outline status"></span>
									</div>
								</div>
							</li>

							<li class="busy">
								<div class="media">
									<a class="pull-left profile-photo" href="#"> <img
										class="media-object" src="./res/img/random-avatar1.jpg" alt>
									</a>
									<div class="media-body">
										<h6 class="media-heading">
											Lucius <strong>Cashmere</strong>
										</h6>
										<small><i class="fa fa-map-marker"></i> Wien, Austria</small>
										<span class="badge badge-outline status"></span>
									</div>
								</div>
							</li>

							<li class="busy">
								<div class="media">
									<a class="pull-left profile-photo" href="#"> <img
										class="media-object" src="./res/img/random-avatar2.jpg" alt>
									</a>
									<div class="media-body">
										<h6 class="media-heading">
											Jesse <strong>Phoenix</strong>
										</h6>
										<small><i class="fa fa-map-marker"></i> Berlin,
											Germany</small> <span class="badge badge-outline status"></span>
									</div>
								</div>
							</li>

						</ul>



					</div>

					<div class="tab-pane" id="mmenu-history">
						<h5>
							<strong>Chat</strong> History
						</h5>

						<ul class="history-list">

							<li class="online">
								<div class="media">
									<a class="pull-left profile-photo" href="#"> <img
										class="media-object" src="./res/img/ici-avatar.jpg" alt>
									</a>
									<div class="media-body">
										<h6 class="media-heading">
											Ing. Imrich <strong>Kamarel</strong>
										</h6>
										<small>Lorem ipsum dolor sit amet, consectetur
											adipisicing elit, sed do eiusmod tempor</small> <span
											class="badge badge-outline status"></span>
									</div>
								</div>
							</li>

							<li class="busy">
								<div class="media">

									<a class="pull-left profile-photo" href="#" style="witdh:45px">
										<img class="media-object" src="./res/img/arnold-avatar.jpg"
										alt>
									</a>

									<div class="media-body">
										<h6 class="media-heading">
											Arnold <strong>Karlsberg</strong>
										</h6>
										<small>Duis aute irure dolor in reprehenderit in
											voluptate velit esse cillum dolore eu fugiat nulla pariatur</small> <span
											class="badge badge-outline status"></span>
									</div>

								</div>
							</li>

							<li class="offline">
								<div class="media">
									<a class="pull-left profile-photo" href="#"> <img
										class="media-object" src="./res/img/peter-avatar.jpg" alt>

									</a>
									<div class="media-body">
										<h6 class="media-heading">
											Peter <strong>Kay</strong>
										</h6>
										<small>Excepteur sint occaecat cupidatat non proident,
											sunt in culpa qui officia deserunt mollit </small> <span
											class="badge badge-outline status"></span>
									</div>
								</div>
							</li>

						</ul>

					</div>

					<div class="tab-pane" id="mmenu-friends">
						<h5>
							<strong>Friends</strong> List
						</h5>
						<ul class="favourite-list">

							<li class="online">
								<div class="media">

									<a class="pull-left profile-photo" href="#"> <img
										class="media-object" src="./res/img/arnold-avatar.jpg" alt>
									</a>


									<div class="media-body">
										<h6 class="media-heading">
											Arnold <strong>Karlsberg</strong>
										</h6>
										<small><i class="fa fa-map-marker"></i> Bratislava,
											Slovakia</small> <span class="badge badge-outline status"></span>
									</div>

								</div>
							</li>

							<li class="offline">
								<div class="media">
									<a class="pull-left profile-photo" href="#"> <img
										class="media-object" src="./res/img/random-avatar8.jpg" alt>
									</a>
									<div class="media-body">
										<h6 class="media-heading">
											Anna <strong>Opichia</strong>
										</h6>
										<small><i class="fa fa-map-marker"></i> Budapest,
											Hungary</small> <span class="badge badge-outline status"></span>
									</div>
								</div>
							</li>

							<li class="busy">
								<div class="media">
									<a class="pull-left profile-photo" href="#"> <img
										class="media-object" src="./res/img/random-avatar1.jpg" alt>
									</a>
									<div class="media-body">
										<h6 class="media-heading">
											Lucius <strong>Cashmere</strong>
										</h6>
										<small><i class="fa fa-map-marker"></i> Wien, Austria</small>
										<span class="badge badge-outline status"></span>
									</div>
								</div>
							</li>

							<li class="online">
								<div class="media">
									<a class="pull-left profile-photo" href="#"> <img
										class="media-object" src="./res/img/ici-avatar.jpg" alt>
									</a>
									<div class="media-body">
										<h6 class="media-heading">
											Ing. Imrich <strong>Kamarel</strong>
										</h6>
										<small><i class="fa fa-map-marker"></i> Ulaanbaatar,
											Mongolia</small> <span class="badge badge-outline status"></span>
									</div>
								</div>
							</li>

						</ul>
					</div>

					<div class="tab-pane pane-settings" id="mmenu-settings">
						<h5>
							<strong>Chat</strong> Settings
						</h5>

						<ul class="settings">

							<li>
								<div class="form-group">
									<label class="col-xs-8 control-label">Show Offline
										Users</label>
									<div class="col-xs-4 control-label">
										<div class="onoffswitch greensea">
											<input type="checkbox" name="onoffswitch"
												class="onoffswitch-checkbox" id="show-offline" checked="">
											<label class="onoffswitch-label" for="show-offline">
												<span class="onoffswitch-inner"></span> <span
												class="onoffswitch-switch"></span>
											</label>
										</div>
									</div>
								</div>
							</li>

							<li>
								<div class="form-group">
									<label class="col-xs-8 control-label">Show Fullname</label>
									<div class="col-xs-4 control-label">
										<div class="onoffswitch greensea">
											<input type="checkbox" name="onoffswitch"
												class="onoffswitch-checkbox" id="show-fullname"> <label
												class="onoffswitch-label" for="show-fullname"> <span
												class="onoffswitch-inner"></span> <span
												class="onoffswitch-switch"></span>
											</label>
										</div>
									</div>
								</div>
							</li>

							<li>
								<div class="form-group">
									<label class="col-xs-8 control-label">History Enable</label>
									<div class="col-xs-4 control-label">
										<div class="onoffswitch greensea">
											<input type="checkbox" name="onoffswitch"
												class="onoffswitch-checkbox" id="show-history" checked="">
											<label class="onoffswitch-label" for="show-history">
												<span class="onoffswitch-inner"></span> <span
												class="onoffswitch-switch"></span>
											</label>
										</div>
									</div>
								</div>
							</li>

							<li>
								<div class="form-group">
									<label class="col-xs-8 control-label">Show Locations</label>
									<div class="col-xs-4 control-label">
										<div class="onoffswitch greensea">
											<input type="checkbox" name="onoffswitch"
												class="onoffswitch-checkbox" id="show-location" checked="">
											<label class="onoffswitch-label" for="show-location">
												<span class="onoffswitch-inner"></span> <span
												class="onoffswitch-switch"></span>
											</label>
										</div>
									</div>
								</div>
							</li>

							<li>
								<div class="form-group">
									<label class="col-xs-8 control-label">Notifications</label>
									<div class="col-xs-4 control-label">
										<div class="onoffswitch greensea">
											<input type="checkbox" name="onoffswitch"
												class="onoffswitch-checkbox" id="show-notifications">
											<label class="onoffswitch-label" for="show-notifications">
												<span class="onoffswitch-inner"></span> <span
												class="onoffswitch-switch"></span>
											</label>
										</div>
									</div>
								</div>
							</li>

							<li>
								<div class="form-group">
									<label class="col-xs-8 control-label">Show Undread
										Count</label>
									<div class="col-xs-4 control-label">
										<div class="onoffswitch greensea">
											<input type="checkbox" name="onoffswitch"
												class="onoffswitch-checkbox" id="show-unread" checked="">
											<label class="onoffswitch-label" for="show-unread"> <span
												class="onoffswitch-inner"></span> <span
												class="onoffswitch-switch"></span>
											</label>
										</div>
									</div>
								</div>
							</li>

						</ul>

					</div>
					<!-- tab-pane -->

				</div>
				<!-- tab-content -->
			</div>






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
		$(function() {
	
			// Initialize card flip
			$('.card.hover').hover(function() {
				$(this).addClass('flip');
			}, function() {
				$(this).removeClass('flip');
			});
	
	
			$(window).resize(function() {
				// redraw the graph in the correctly sized div
				plot.resize();
				plot.setupGrid();
				plot.draw();
			});
	
			$('#mmenu').on(
				"opened.mm",
				function() {
					// redraw the graph in the correctly sized div
					plot.resize();
					plot.setupGrid();
					plot.draw();
				}
			);
	
			$('#mmenu').on(
				"closed.mm",
				function() {
					// redraw the graph in the correctly sized div
					plot.resize();
					plot.setupGrid();
					plot.draw();
				}
			);
	
			// tooltips showing
			$("#statistics-chart").bind("plothover", function(event, pos, item) {
				if (item) {
					var x = item.datapoint[0],
						y = item.datapoint[1];
	
					$("#tooltip").html('<h1 style="color: #418bca">' + months[x - 1] + '</h1>' + '<strong>' + y + '</strong>' + ' ' + item.series.label)
						.css({
							top : item.pageY - 30,
							left : item.pageX + 5
						})
						.fadeIn(200);
				} else {
					$("#tooltip").hide();
				}
			});
	
	
			//tooltips options
			$("<div id='tooltip'></div>").css({
				position : "absolute",
				//display: "none",
				padding : "10px 20px",
				"background-color" : "#ffffff",
				"z-index" : "99999"
			}).appendTo("body");
	
			//generate actual pie charts
			$('.pie-chart').easyPieChart();
	
	
			//server load rickshaw chart
			var graph;
	
			var seriesData = [ [], [] ];
			var random = new Rickshaw.Fixtures.RandomData(50);
	
			for (var i = 0; i < 50; i++) {
				random.addData(seriesData);
			}
	
			graph = new Rickshaw.Graph({
				element : document.querySelector("#serverload-chart"),
				height : 150,
				renderer : 'area',
				series : [
					{
						data : seriesData[0],
						color : '#6e6e6e',
						name : 'File Server'
					}, {
						data : seriesData[1],
						color : '#fff',
						name : 'Mail Server'
					}
				]
			});
	
			var hoverDetail = new Rickshaw.Graph.HoverDetail({
				graph : graph,
			});
	
			setInterval(function() {
				random.removeData(seriesData);
				random.addData(seriesData);
				graph.update();
	
			}, 1000);
	
			// Morris donut chart
			Morris.Donut({
				element : 'browser-usage',
				data : [
					{
						label : "Chrome",
						value : 25
					},
					{
						label : "Safari",
						value : 20
					},
					{
						label : "Firefox",
						value : 15
					},
					{
						label : "Opera",
						value : 5
					},
					{
						label : "Internet Explorer",
						value : 10
					},
					{
						label : "Other",
						value : 25
					}
				],
				colors : [ '#00a3d8', '#2fbbe8', '#72cae7', '#d9544f', '#ffc100', '#1693A5' ]
			});
	
			$('#browser-usage').find("path[stroke='#ffffff']").attr('stroke', 'rgba(0,0,0,0)');
	
			//sparkline charts
			$('#projectbar1').sparkline('html', {
				type : 'bar',
				barColor : '#22beef',
				barWidth : 4,
				height : 20
			});
			$('#projectbar2').sparkline('html', {
				type : 'bar',
				barColor : '#cd97eb',
				barWidth : 4,
				height : 20
			});
			$('#projectbar3').sparkline('html', {
				type : 'bar',
				barColor : '#a2d200',
				barWidth : 4,
				height : 20
			});
			$('#projectbar4').sparkline('html', {
				type : 'bar',
				barColor : '#ffc100',
				barWidth : 4,
				height : 20
			});
			$('#projectbar5').sparkline('html', {
				type : 'bar',
				barColor : '#ff4a43',
				barWidth : 4,
				height : 20
			});
			$('#projectbar6').sparkline('html', {
				type : 'bar',
				barColor : '#a2d200',
				barWidth : 4,
				height : 20
			});
	
			// sortable table
			$('.table.table-sortable th.sortable').click(function() {
				var o = $(this).hasClass('sort-asc') ? 'sort-desc' : 'sort-asc';
				$('th.sortable').removeClass('sort-asc').removeClass('sort-desc');
				$(this).addClass(o);
			});
	
			//todo's
			$('#todolist li label').click(function() {
				$(this).toggleClass('done');
			});
	
			// Initialize tabDrop
			$('.tabdrop').tabdrop({
				text : '<i class="fa fa-th-list"></i>'
			});
	
			//load wysiwyg editor
			$('#quick-message-content').summernote({
				toolbar : [
					//['style', ['style']], // no style button
					[ 'style', [ 'bold', 'italic', 'underline', 'clear' ] ],
					[ 'fontsize', [ 'fontsize' ] ],
					[ 'color', [ 'color' ] ],
					[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
					[ 'height', [ 'height' ] ],
				//['insert', ['picture', 'link']], // no insert buttons
				//['table', ['table']], // no table button
				//['help', ['help']] //no help button
				],
				height : 143 //set editable area's height
			});
	
			//multiselect input
			$(".chosen-select").chosen({
				disable_search_threshold : 10
			});
	
		})
	</script>



</body>

</html>
