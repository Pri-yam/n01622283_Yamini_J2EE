<%--
  Created by IntelliJ IDEA.
  User: yamin
  Date: 2025-05-14
  Time: 7:11 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Message Received</title>
</head>
<body>
  <h1>Your message was:</h1>
  <p style="font-weight:bold;"><%= request.getAttribute("message") %></p>
  <a href="index.jsp">Send another</a>

</body>
</html>
