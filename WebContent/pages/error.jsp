<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
  <head>
    <title>错误页面</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/css/bootstrap/css/bootstrap.css" />
  </head>
  <body>
	<div class="jumbotron">
	  <h1>Error　Happend!</h1>
	  <p>我们很抱歉，一些错误发生了!错误原因可能包括:</p>
	  <ol>
	    <li>您提交的数据中存在非法数据!</li>
	    <li>你访问的页面不存在!</li>
	    <li>网站内部故障!</li>
	    <li>工程师傻逼的忘了把某些功能实现啦!</li>
	  </ol>
	  <img src="<%=basePath%>/resources/img/404.jpg" width="300px" height="200px"/>
	</div>
    <script src="<%=basePath%>/resources/js/jquery/jquery.js"></script>
  </body>
</html>
