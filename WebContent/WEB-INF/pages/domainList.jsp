<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>IconDocs: Domains</title>
  </head>
  <body>
    <table>
        <tr class="${rowStyle}">
	      <td>Id</td>
          <td>Title</td>
          <td>Type</td>
          <td>Path</td>
        </tr>
        
      <c:forEach var="document" items="${domainList}" varStatus="rowCounter">
        <c:choose>
          <c:when test="${rowCounter.count % 2 == 0}">
            <c:set var="rowStyle" scope="page" value="odd"/>
          </c:when>
          <c:otherwise>
            <c:set var="rowStyle" scope="page" value="even"/>
          </c:otherwise>
        </c:choose>
        <tr class="${rowStyle}">
	      <td><a href="document/${document.id}">${document.id}</a></td>
          <td>${document.title}</td>
          <td>${document.type}</td>
          <td>${document.path}</td>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>