<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- JSTL: JSP/Jakarta Standard Tag Library -->
<!-- JSTL 2.0 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- JSTL 3.0+ -->
<!-- %@ taglib prefix="c" uri="jakarta.tags.core" % -->
<html>
<head>
    <title>Todos</title>
</head>
<body>
<h1>Todos</h1>
Gefundene Einträge: ${fn:length(todos)}
<c:if test="${not empty todos}">
    <table>
        <thead>
        <tr>
            <th>Titel</th>
            <th>Frist</th>
        </tr>
        </thead>
        <tbody>
    <c:forEach items="${todos}" var="t">
        <tr>
            <td>
                <c:out value="${t.title}"/>
            </td>
            <td>
                    <fmt:formatDate value="${t.dueDateAsDate}" pattern="dd.MM.yyyy"/>
            </td>
        </tr>
    </c:forEach>
        </tbody>
    </table>
</c:if>
</body>
</html>
