<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Hello <%= request.getParameter("name") == null ? "World" : request.getParameter("name") %></h1>
    <h1>Hello ${empty param.name ? 'World' : param.name}</h1>

<%

    out.println("Hello");
%>

</body>
</html>
