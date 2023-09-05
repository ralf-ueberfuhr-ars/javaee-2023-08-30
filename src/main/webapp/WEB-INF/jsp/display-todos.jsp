<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Todos</title>
</head>
<body>
<h1>Todos</h1>

<!-- JSTL: JSP/Jakarta Standard Tag Library -->
<c:forEach items="${todos}" var="t"  >
    ${t.title}
</c:forEach>
</body>
</html>
