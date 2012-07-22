<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IconDocs: Search Document</title>
</head>
<body>

<form:form action="getDocum.html" method="POST" commandName="docum" >

<table align="center" >

<tr>

<td><form:label for="name" path="name">Document Name:</form:label></td>

<td><form:input path="name"/></td>

</tr>

<tr>

<td><form:label for="title" path="title">Document Title:</form:label></td>

<td><form:input path="title" /></td>

</tr>

<tr>

<td><form:label for="uiid" path="uiid">Document Uiid:</form:label></td>

<td><form:input path="uiid"/></td>

</tr>

<tr>

<td><input type="submit" name="Submit" value="Submit" /></td>

</tr>

</table>

</form:form>

</body>
</html>