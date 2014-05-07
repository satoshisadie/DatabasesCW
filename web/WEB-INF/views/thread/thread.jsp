<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Thread</title>
</head>
<body>
    <c:forEach items="${posts}" var="post">
        <div class="thread">
            <input type="hidden" value="${post.id}" id="postId">
            <input type="hidden" value="${post.threadId}" id="threadId">
            <input type="hidden" value="${post.userId}" id="userId">
            Subject ${post.subject}<br/>
            Message ${post.message}<br/>
            Created ${post.dateCreated}<br/>
        </div>
    </c:forEach>
</body>
</html>
