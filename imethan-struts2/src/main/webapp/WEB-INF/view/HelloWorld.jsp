<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello World!</title>
</head>
<body>
	<h2>messageStore.message：<s:property value="messageStore.message" /></h2>
	<h2>messageStore：<s:property value="messageStore" /></h2>
	<hr>
	<p>I've said hello <s:property value="helloCount" /> times!</p>
	<hr>
	<p>userName：<s:property value="userName"/> </p>
</body>
</html>