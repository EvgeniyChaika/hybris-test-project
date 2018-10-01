<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: evgeniy
  Date: 10/1/18
  Time: 6:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<! doctype html>
<html>
<head>
    <title>Band List</title>
</head>
<body>
<h1>Band List</h1>
<ul>
    <c:forEach var="band" items="${bands}">
        <li><a href="./bands/${band.id}">${band.name}</a></li>
    </c:forEach>
</ul>
</body>
</html>
