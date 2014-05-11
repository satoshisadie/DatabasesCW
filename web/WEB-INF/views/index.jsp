<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main page</title>
    <link rel="stylesheet" href="<c:url value="/css/jquery-ui-1.10.4.custom.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>" type="text/css">
    <script src="<c:url value="/js/lib/jquery-2.1.1.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/lib/jquery-ui-1.10.4.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/index.js"/>" type="text/javascript"></script>
</head>
<body>
    <div id="page-content">
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
                <span class="thread-tags">
                    <c:forEach items="${tagsByThreadId.get(thread.id)}" var="tagId">
                        <a href="#" class="tag">${tagById.get(tagId).name}</a>
                    </c:forEach>
                </span><br>
                <span class="thread-info">
                    Created ${thread.dateCreatedFormatted} by ${user.login}<br>
                    Last post ${thread.dateLastPostFormatted}<br>
                    Viewed by ${thread.viewCount} users
                </span><br>
                <span>Active ${thread.active}</span><br>

                <button class="follow-thread">Follow</button><br>
            </div>
            <hr>
        </c:forEach>

        <form id="thread-form">
            <label>
                Subject<br>
                <textarea id="subject"></textarea>
            </label><br>
            <label>
                Message<br>
                <textarea id="initial-post"></textarea>
            </label><br>
            <label>
                Tags<br>
                <select id="post-tags" multiple>
                    <c:forEach items="${tags}" var="tag">
                        <option value="${tag.id}">${tag.name}</option>
                    </c:forEach>
                </select>
            </label>
        </form>
    </div>
</body>
</html>