<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'personList.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>

<body>
	<ul>
		<c:forEach items="${page.kpList}" var="person">
			<li class="online">
				<div class="media">
					<a class="pull-left profile-photo"
						href="searchById?id=${person.keyPersonID}"> <img
						class="media-object" src="http://localhost:8080/pic/${person.keyPersonID}.jpg" alt
						style="width:45px; height: 45px; border-radius: 50%; padding-bottom:5px;">
					</a>
					<div class="media-body">
						<h6 class="media-heading">
							<strong><a href="searchById?id=${person.keyPersonID}"
								style="color: #fff">${person.keypersonname}</a></strong>
						</h6>
						<small><i class="fa fa-map-marker"></i> ${person.popedom}</small>
						<span class="badge badge-outline status"></span>
					</div>
				</div>
			</li>
		</c:forEach>

	</ul>
	<ul class="pager pager-custom">
		<c:choose>
			<c:when test="${page.currentPage==1}">
				<li class="disabled"><a><i class="fa fa-angle-left"></i></a></li>
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
</body>
</html>
