<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
    <c:forEach items="${threads}" var="thread">
        <div>
            <input type="hidden" id="id" value="${thread.id}">
            Thread subject ${thread.subject}<br/>
            Created ${thread.dateCreated}<br/>
            View count ${thread.viewCount}<br/>
            Active ${thread.active}<br/>
        </div>
    </c:forEach>
</body>
</html>
