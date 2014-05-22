<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main page</title>
    <link rel="stylesheet" href="<c:url value="/css/jquery-ui-1.10.4.custom.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap-theme.css"/>" type="text/css">
    <script src="<c:url value="/js/lib/jquery-2.1.1.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/lib/jquery-ui-1.10.4.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/lib/bootstrap.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/index.js"/>" type="text/javascript"></script>
</head>
<body>
    <div class="container">
        <ul class="nav nav-pills nav-justified">
            <li><a href="#" id="create-thread">Create new thread</a></li>
            <li class="active"><a href="#">Main page</a></li>
            <c:if test="${not empty sessionScope.administrator}">
                <li><a href="#">Administrator page</a></li>
            </c:if>
            <c:if test="${not empty sessionScope.moderator}">
                <li><a href="#">Moderator page</a></li>
            </c:if>
            <li><a href="#" id="view-profile">Profile</a></li>
            <li><a href="#" id="logout">Log out</a></li>
        </ul>

        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <c:forEach items="${threads}" var="thread">
                    <c:set var="user" value="${userByThread.get(thread)}"/>

                    <div class="thread">
                        <input type="hidden" class="thread-id" value="${thread.id}">
                        <div class="topic-header">
                            <c:choose>
                                <c:when test="${followedThreads.contains(thread.id)}">
                                    <span class="ui-icon ui-icon-circle-minus cancel-following" style="display: inline-block; cursor: pointer;"></span>
                                </c:when>
                                <c:otherwise>
                                    <span class="ui-icon ui-icon-circle-plus follow-thread" style="display: inline-block; cursor: pointer;"></span>
                                </c:otherwise>
                            </c:choose>

                            <span class="thread-subject ${thread.active ? "alive-thread-subject" : "dead-thread-subject"}">${thread.subject}</span>
                            <span class="thread-tags">
                                <c:forEach items="${tagsByThreadId.get(thread.id)}" var="tagId">
                                    <%--<a href="#" class="tag">${tagById.get(tagId).name}</a>--%>
                                    <%--<a href="#" class="tag">${tagById.get(tagId).name}</a>--%>
                                    <span class="label label-info">${tagById.get(tagId).name}</span>
                                </c:forEach>
                            </span>
                        </div>

                        <span class="thread-info">
                            Created ${thread.dateCreatedFormatted} by <strong>${user.login}</strong><br>
                            Last post ${thread.dateLastPostFormatted}<br>
                            Viewed ${thread.viewCount} times
                        </span>
                    </div>
                    <hr>
                </c:forEach>

                <form class="form-horizontal" id="thread-form">
                    <div class="form-group">
                        <label for="subject">Subject</label>
                        <textarea id="subject" class="form-control" rows="3"></textarea>
                    </div>

                    <div class="form-group">
                        <label for="initial-post">Message</label>
                        <textarea id="initial-post" class="form-control" rows="3"></textarea>
                    </div>

                    <div class="form-group">
                        <label for="post-tags">Tags</label>
                        <select multiple id="post-tags" class="form-control">
                            <c:forEach items="${tags}" var="tag">
                                <option value="${tag.id}">${tag.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>