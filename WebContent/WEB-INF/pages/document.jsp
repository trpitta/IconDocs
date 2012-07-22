<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>IconDocs: Document</title>
</head>
<body>

<form:form action="documForm.html" method="POST" commandName="docum" >

    <table>
		<tr>
		<td><form:label for="uiid" path="uiid">Document Id: ${document.id}</form:label></td>
		<td><form:label for="uiid" path="uiid">Docum Id: ${docum.uiid}</form:label></td>
		<input name="uiid" type="hidden" value="${document.id}"/>
		</tr>

		<tr>
		<td><form:label for="title" path="title">Document Title: ${document.title}</form:label></td>
		</tr>

		<tr>
		<td><form:label for="type" path="type">Document Type: ${document.type}</form:label></td>
		</tr>

		<tr>
		<td><form:label for="path" path="path">Document Type: ${document.path}</form:label></td>
		</tr>

        <c:if test="${document.type} == File">
        <tr class="${rowStyle}">
          <td>Domain Path: ${document.path}</td>
        </tr>
        </c:if> 

		<tr>
			<!--<td><input type="submit" name="Clone" value="Clone" /></td>-->
			<td><input type="submit" name="Delete" value="Delete" /></td>
		</tr>

    </table>
    
</form:form>
    
</body>
</html>