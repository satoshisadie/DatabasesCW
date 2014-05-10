<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main page</title>
    <link rel="stylesheet" href="css/jquery-ui-1.10.4.custom.css" type="text/css">
    <link rel="stylesheet" href="css/styles.css" type="text/css">
    <script src="<c:url value="/js/lib/jquery-2.1.1.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/lib/jquery-ui-1.10.4.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/index.js"/>" type="text/javascript"></script>
</head>
<body>
    <c:if test="${not empty sessionScope.administrator}">
        <button id="administrator-page">Open administrator page</button>
    </c:if>
    <button id="create-thread">Create new thread</button>
    <button id="logout">Log out</button><br>

    <c:forEach items="${threads}" var="thread">
        <c:set var="user" value="${userByThread.get(thread)}"/>
        <div class="thread">
            <input type="hidden" class="thread-id" value="${thread.id}">
            <span class="thread-subject">${thread.subject}</span><br>
            <span class="thread-info">Created ${thread.dateCreatedFormatted} by ${user.login}</span><br>
            <span class="thread-last-post-date">Last post ${thread.dateLastPostFormatted}</span><br>
            <span class="thread-view-count">Viewed by ${thread.viewCount} users</span><br>
            Active ${thread.active}<br>

            <button class="follow-thread">Follow</button><br>
        </div>
        <hr>
    </c:forEach>

    <form id="thread-form">
        <label>
            Subject<br>
            <textarea id="subject"></textarea><br>
        </label>
        <label>
            Message<br>
            <textarea id="initialPost"></textarea>
        </label>
    </form>
</body>
</html>