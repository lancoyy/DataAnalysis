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

									<h3>Backgrounds:</h3>
									<ul id="color-schemes">
										<li><a href="#" class="bg-1"></a></li>
										<li><a href="#" class="bg-2"></a></li>
										<li><a href="#" class="bg-3"></a></li>
										<li><a href="#" class="bg-4"></a></li>
										<li><a href="#" class="bg-5"></a></li>
										<li><a href="#" class="bg-6"></a></li>
									</ul>

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
				<div class="pageheader">


					<h2>
						<i class="fa fa-tachometer"></i> Dashboard <span>// Place
							subtitle here...</span>
					</h2>


					<div class="breadcrumbs">
						<ol class="breadcrumb">
							<li>You are here</li>
							<li><a href="index.html">Minimal</a></li>
							<li class="active">Dashboard</li>
						</ol>
					</div>


				</div>
				<!-- /page header -->

				<!-- content main container -->
				<div class="main">

					<!-- cards -->
					<div class="row cards">

						<div class="card-container col-lg-3 col-sm-6 col-sm-12">
							<div class="card card-redbrown hover">
								<div class="front">

									<div class="media">
										<span class="pull-left"> <i
											class="fa fa-users media-object"></i>
										</span>

										<div class="media-body">
											<small>New Users</small>
											<h2 class="media-heading animate-number" data-value="3659"
												data-animation-duration="1500">0</h2>
										</div>
									</div>

									<div class="progress-list">
										<div class="details">
											<div class="title">This month plan %</div>
										</div>
										<div class="status pull-right bg-transparent-black-1">
											<span class="animate-number" data-value="83"
												data-animation-duration="1500">0</span>%
										</div>
										<div class="clearfix"></div>
										<div
											class="progress progress-little progress-transparent-black">
											<div class="progress-bar animate-progress-bar"
												data-percentage="83%"></div>
										</div>
									</div>

								</div>
								<div class="back">
									<a href="#"> <i class="fa fa-bar-chart-o fa-4x"></i> <span>Check
											Summary</span>
									</a>
								</div>
							</div>
						</div>


						<div class="card-container col-lg-3 col-sm-6 col-sm-12">
							<div class="card card-blue hover">
								<div class="front">

									<div class="media">
										<span class="pull-left"> <i
											class="fa fa-shopping-cart media-object"></i>
										</span>

										<div class="media-body">
											<small>New Orders</small>
											<h2 class="media-heading animate-number" data-value="19214"
												data-animation-duration="1500">0</h2>
										</div>
									</div>

									<div class="progress-list">
										<div class="details">
											<div class="title">This month plan %</div>
										</div>
										<div class="status pull-right bg-transparent-black-1">
											<span class="animate-number" data-value="100"
												data-animation-duration="1500">0</span>%
										</div>
										<div class="clearfix"></div>
										<div
											class="progress progress-little progress-transparent-black">
											<div class="progress-bar animate-progress-bar"
												data-percentage="100%"></div>
										</div>
									</div>

								</div>
								<div class="back">
									<a href="#"> <i class="fa fa-bar-chart-o fa-4x"></i> <span>Check
											Summary</span>
									</a>
								</div>
							</div>
						</div>



						<div class="card-container col-lg-3 col-sm-6 col-sm-12">
							<div class="card card-greensea hover">
								<div class="front">

									<div class="media">
										<span class="pull-left"> <i
											class="fa fa-usd media-object"></i>
										</span>

										<div class="media-body">
											<small>Sales</small>
											<h2 class="media-heading animate-number" data-value="169541"
												data-animation-duration="1500">0</h2>
										</div>
									</div>

									<div class="progress-list">
										<div class="details">
											<div class="title">This month plan %</div>
										</div>
										<div class="status pull-right bg-transparent-black-1">
											<span class="animate-number" data-value="42"
												data-animation-duration="1500">0</span>%
										</div>
										<div class="clearfix"></div>
										<div
											class="progress progress-little progress-transparent-black">
											<div class="progress-bar animate-progress-bar"
												data-percentage="42%"></div>
										</div>
									</div>

								</div>
								<div class="back">
									<a href="#"> <i class="fa fa-bar-chart-o fa-4x"></i> <span>Check
											Summary</span>
									</a>
								</div>
							</div>
						</div>


						<div class="card-container col-lg-3 col-sm-6 col-xs-12">
							<div class="card card-slategray hover">
								<div class="front">

									<div class="media">
										<span class="pull-left"> <i
											class="fa fa-eye media-object"></i>
										</span>

										<div class="media-body">
											<small>Visits</small>
											<h2 class="media-heading animate-number" data-value="9634"
												data-animation-duration="1500">0</h2>
										</div>
									</div>

									<div class="progress-list">
										<div class="details">
											<div class="title">This month plan %</div>
										</div>
										<div class="status pull-right bg-transparent-black-1">
											<span class="animate-number" data-value="25"
												data-animation-duration="1500">0</span>%
										</div>
										<div class="clearfix"></div>
										<div
											class="progress progress-little progress-transparent-black">
											<div class="progress-bar animate-progress-bar"
												data-percentage="25%"></div>
										</div>
									</div>

								</div>
								<div class="back">
									<a href="#"> <i class="fa fa-bar-chart-o fa-4x"></i> <span>Check
											Summary</span>
									</a>
								</div>
							</div>
						</div>


					</div>
					<!-- /cards -->




					<!-- row -->
					<div class="row">


						<!-- col 8 -->
						<div class="col-lg-8 col-md-12">




							<!-- tile -->
							<section class="tile transparent">
								<!-- tile header -->
								<div
									class="tile-header color transparent-black textured rounded-top-corners">
									<h1>
										<strong>Statistic</strong> Chart
									</h1>
									<div class="controls">
										<a href="#" class="refresh"><i class="fa fa-refresh"></i></a>

									</div>
								</div>
								<!-- /tile header -->
								<!-- tile widget -->
								<div class="tile-widget color transparent-black textured">
									<div id="statistics-chart" class="chart statistics"
										style="height: 520px;"></div>
								</div>
								<!-- /tile widget -->
							</section>
							<!-- /tile -->






						</div>
						<!-- /col 8 -->



						<!-- col 4 -->
						<div class="col-lg-4 col-md-12 col-sm-12 col-xs-12">

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
									<input type="text" placeholder="Add new todo..."
										class="w100p bottommargin">


									<ul class="users-list">

										<li class="online">
											<div class="media">
												<a class="pull-left profile-photo" href="#"> <img
													class="media-object" src="./res/img/ici-avatar.jpg" alt
													style="width:45px; border-radius: 50%; padding-bottom:3px;">
												</a>
												<div class="media-body">
													<h6 class="media-heading">
														Ing. Imrich <strong>Kamarel</strong>
													</h6>
													<small><i class="fa fa-map-marker"></i>
														Ulaanbaatar, Mongolia</small> <span
														class="badge badge-outline status"></span>
												</div>
											</div>
										</li>

										<li class="online">
											<div class="media">

												<a class="pull-left profile-photo" href="#"> <img
													class="media-object" src="./res/img/arnold-avatar.jpg" alt
													style="width:45px; border-radius: 50%; padding-bottom:3px;">
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
													class="media-object" src="./res/img/peter-avatar.jpg" alt
													style="width:45px; border-radius: 50%; padding-bottom:3px;">

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
													class="media-object" src="./res/img/george-avatar.jpg" alt
													style="width:45px; border-radius: 50%; padding-bottom:3px;">
												</a>
												<div class="media-body">
													<h6 class="media-heading">
														George <strong>McCain</strong>
													</h6>
													<small><i class="fa fa-map-marker"></i> Prague,
														Czech Republic</small> <span class="badge badge-outline status"></span>
												</div>
											</div>
										</li>

										<li class="busy">
											<div class="media">
												<a class="pull-left profile-photo" href="#"> <img
													class="media-object" src="./res/img/random-avatar1.jpg" alt
													style="width:45px; border-radius: 50%; padding-bottom:3px;">
												</a>
												<div class="media-body">
													<h6 class="media-heading">
														Lucius <strong>Cashmere</strong>
													</h6>
													<small><i class="fa fa-map-marker"></i> Wien,
														Austria</small> <span class="badge badge-outline status"></span>
												</div>
											</div>
										</li>

										<li class="busy">
											<div class="media">
												<a class="pull-left profile-photo" href="#"> <img
													class="media-object" src="./res/img/random-avatar2.jpg" alt
													style="width:45px; border-radius: 50%; padding-bottom:3px;">
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
								<!-- /tile body -->
							</section>
							<!-- /tile -->


						</div>
						<!-- /col 4 -->


					</div>
					<!-- /row -->


					<!-- row -->
					<div class="row"></div>
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
    $(function(){

      // Initialize card flip
      $('.card.hover').hover(function(){
        $(this).addClass('flip');
      },function(){
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
        function()
        {
          // redraw the graph in the correctly sized div
          plot.resize();
          plot.setupGrid();
          plot.draw();
        }
      );

      $('#mmenu').on(
        "closed.mm",
        function()
        {
          // redraw the graph in the correctly sized div
          plot.resize();
          plot.setupGrid();
          plot.draw();
        }
      );

      // tooltips showing
      $("#statistics-chart").bind("plothover", function (event, pos, item) {
        if (item) {
          var x = item.datapoint[0],
              y = item.datapoint[1];

          $("#tooltip").html('<h1 style="color: #418bca">' + months[x - 1] + '</h1>' + '<strong>' + y + '</strong>' + ' ' + item.series.label)
            .css({top: item.pageY-30, left: item.pageX+5})
            .fadeIn(200);
        } else {
          $("#tooltip").hide();
        }
      });

      
      //tooltips options
      $("<div id='tooltip'></div>").css({
        position: "absolute",
        //display: "none",
        padding: "10px 20px",
        "background-color": "#ffffff",
        "z-index":"99999"
      }).appendTo("body");

      //generate actual pie charts
      $('.pie-chart').easyPieChart();


      //server load rickshaw chart
      var graph;

      var seriesData = [ [], []];
      var random = new Rickshaw.Fixtures.RandomData(50);

      for (var i = 0; i < 50; i++) {
        random.addData(seriesData);
      }

      graph = new Rickshaw.Graph( {
        element: document.querySelector("#serverload-chart"),
        height: 150,
        renderer: 'area',
        series: [
          {
            data: seriesData[0],
            color: '#6e6e6e',
            name:'File Server'
          },{
            data: seriesData[1],
            color: '#fff',
            name:'Mail Server'
          }
        ]
      } );

      var hoverDetail = new Rickshaw.Graph.HoverDetail( {
        graph: graph,
      });

      setInterval( function() {
        random.removeData(seriesData);
        random.addData(seriesData);
        graph.update();

      },1000);

      // Morris donut chart
      Morris.Donut({
        element: 'browser-usage',
        data: [
          {label: "Chrome", value: 25},
          {label: "Safari", value: 20},
          {label: "Firefox", value: 15},
          {label: "Opera", value: 5},
          {label: "Internet Explorer", value: 10},
          {label: "Other", value: 25}
        ],
        colors: ['#00a3d8', '#2fbbe8', '#72cae7', '#d9544f', '#ffc100', '#1693A5']
      });

      $('#browser-usage').find("path[stroke='#ffffff']").attr('stroke', 'rgba(0,0,0,0)');

      //sparkline charts
      $('#projectbar1').sparkline('html', {type: 'bar', barColor: '#22beef', barWidth: 4, height: 20});
      $('#projectbar2').sparkline('html', {type: 'bar', barColor: '#cd97eb', barWidth: 4, height: 20});
      $('#projectbar3').sparkline('html', {type: 'bar', barColor: '#a2d200', barWidth: 4, height: 20});
      $('#projectbar4').sparkline('html', {type: 'bar', barColor: '#ffc100', barWidth: 4, height: 20});
      $('#projectbar5').sparkline('html', {type: 'bar', barColor: '#ff4a43', barWidth: 4, height: 20});
      $('#projectbar6').sparkline('html', {type: 'bar', barColor: '#a2d200', barWidth: 4, height: 20});

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
      $('.tabdrop').tabdrop({text: '<i class="fa fa-th-list"></i>'});

      //load wysiwyg editor
      $('#quick-message-content').summernote({
        toolbar: [
          //['style', ['style']], // no style button
          ['style', ['bold', 'italic', 'underline', 'clear']],
          ['fontsize', ['fontsize']],
          ['color', ['color']],
          ['para', ['ul', 'ol', 'paragraph']],
          ['height', ['height']],
          //['insert', ['picture', 'link']], // no insert buttons
          //['table', ['table']], // no table button
          //['help', ['help']] //no help button
        ],
        height: 143   //set editable area's height
      });

      //multiselect input
      $(".chosen-select").chosen({disable_search_threshold: 10});
      
    })
      
    </script>

	<script>
		var margin = {
				top : -5,
				right : -5,
				bottom : -5,
				left : -5
			},
			width = 1700 - margin.left - margin.right,
			height = 768 - margin.top - margin.bottom;
	
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
	
	
		var color = d3.scale.category20();
	
		var force = d3.layout.force()
			.charge(-200) // 获取或设置节点的电荷数.(电荷数决定结点是互相排斥还是吸引)
			.linkDistance(20) // 获取或设置节点间的连接线距离.
			.linkStrength(0)
			.friction(0.9)
			.gravity(0.01)
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
	
	
		/*****************************/
		var group = new Array();
	
		var sizeofGroup;
		//根据groupId 获取聚集点坐标, 分4列显示
	
		var COLUMNS = 4;
		var SocityWidth = ~~(width / COLUMNS);
	
		var fixedCenterX = new Array();
		fixedCenterX.push(~~(SocityWidth / 2));
	
		for (var i = 1; i < COLUMNS; ++i) {
			fixedCenterX.push(fixedCenterX[fixedCenterX.length - 1] + SocityWidth);
		}
	
		var getCenterY = function(id) {
			return (~~(SocityWidth / 2)) + Math.floor(id / COLUMNS) * SocityWidth;
		};
		var getCenterX = function(id) {
			return fixedCenterX[id % COLUMNS];
		};
		//alert(getCenterX(0)+" "+getCenterX(1)+" "+getCenterX(2));
		//Console.log(fixedCenterX[0]);
		/*****************************/
	
		d3.json("getDatas", function(error, graph) {
			force
				.nodes(graph.nodes) //获得或设置布局中的节点（node）阵列组
				.links(graph.links) //获得或设置布局中节点间的连接（Link）阵列组
				.start();
			//得到社团数量，供系统自适应高度
			sizeofGroup = 0;
			for (var node in graph.nodes) {
				sizeofGroup = Math.max(sizeofGroup, graph.nodes[node].group);
				group.push(graph.nodes[node].group);
			}
			//log(sizeofGroup);
			height = getCenterY(sizeofGroup - 1) + (~~(SocityWidth / 2));
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
				var r = confirm("确定进入" + d.name + "团吗？");
				if (r == true) {
					window.parent.location.href = "graph!searchByName?nodename=" + d.name;
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
			force.on("tick", function(e) {
				var k = .2 * e.alpha;
				graph.nodes.forEach(function(o, i) {
					//alert(getCenterX(group[i])+"  "+getCenterY(group[i]));
					o.y += (getCenterY(group[i]) - o.y) * k;
					o.x += (getCenterX(group[i]) - o.x) * k;
				});
	
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
