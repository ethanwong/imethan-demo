<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Basic Struts 2 Application - Welcome</title>
</head>
<body>
	<h2>Welcome to Struts 2!</h2>
	<p><s:url action='hello'/></p>
	<p><a href="<s:url action='hello'/>">Hello World</a></p>
	
	<hr>
	
	<s:url action="hello" var="helloLink">
	 	<s:param name="userName">Bruce Phillips</s:param>
	</s:url>
	<p><s:property value="helloLink"/></p>
	<p><a href="<s:property value="helloLink"/>">Hello Bruce Phillips</a></p>
	
	<hr>
	
	<p>Get your own personal hello by filling out and submitting this form.</p>
	<s:form action="hello">
	  <s:textfield name="userName" label="Your name" />
	   <s:submit value="Submit" />
	</s:form>
	
	<hr>

		
</body>
</html>
