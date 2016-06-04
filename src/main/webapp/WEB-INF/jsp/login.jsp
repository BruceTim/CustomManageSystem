<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>登录页面</title>
<style>
	body ｛
		margin-top: 50px;
	｝
</style>
</head>
<body>
	<center>
		<form action="${pageContext.request.contextPath }/login.do"
			method="post">
			<p>用户名&nbsp;&nbsp;<input type="text" placeholder="用户名" name="username" required /></p>
			<p>密&nbsp;&nbsp;码&nbsp;&nbsp;<input type="password" placeholder="密码" name="userpwd" required /></p>
			<p><center><button type="submit">登录</button></center></p>
		</form>
	</center>
</body>
</html>
