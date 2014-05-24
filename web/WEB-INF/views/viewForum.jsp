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
    <script src="<c:url value="/js/menu.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/viewForum.js"/>" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <jsp:include page="menu.jsp"/>

    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <c:if test="${subforums.size() > 0}">
                <h3>Forums</h3>
                <c:forEach items="${subforums}" var="forum">
                    <div class="forum">
                        <input type="hidden" class="forum-id" value="${forum.id}">
                        <div class="forum-header">
                            <span class="forum-subject">${forum.name}</span>
                        <span class="forum-tags">
                            <%--<c:forEach items="${tagsByThreadId.get(thread.id)}" var="tagId">--%>
                                <%--<span class="label label-default">${tagById.get(tagId).name}</span>--%>
                            <%--</c:forEach>--%>
                        </span>
                        </div>
                    </div>
                    <hr class="forums-separator">
                </c:forEach>
            </c:if>

            <c:if test="${threads.size() > 0}">
                <h3>Topics</h3>
                <c:forEach items="${threads}" var="thread">
                    <c:set var="user" value="${userByThread.get(thread)}"/>

                    <div class="topic">
                        <input type="hidden" class="topic-id" value="${thread.id}">
                        <div class="topic-header">
                            <c:choose>
                                <c:when test="${followedThreads.contains(thread.id)}">
                                    <span class="ui-icon ui-icon-circle-minus cancel-following" style="display: inline-block; cursor: pointer;"></span>
                                </c:when>
                                <c:otherwise>
                                    <span class="ui-icon ui-icon-circle-plus follow-topic" style="display: inline-block; cursor: pointer;"></span>
                                </c:otherwise>
                            </c:choose>

                            <span class="topic-subject ${thread.active ? "alive-topic-subject" : "dead-topic-subject"}">${thread.subject}</span>
                            <span class="topic-tags">
                                <c:forEach items="${tagsByThreadId.get(thread.id)}" var="tagId">
                                    <span class="label label-default">${tagById.get(tagId).name}</span>
                                </c:forEach>
                            </span>
                        </div>

                        <span class="topic-info">
                            Created ${thread.dateCreatedFormatted} by <strong>${user.login}</strong><br>
                            Last post ${thread.dateLastPostFormatted}<br>
                            Viewed ${thread.viewCount} times
                        </span>
                    </div>
                    <hr>
                </c:forEach>
            </c:if>

            <form class="form-horizontal" id="topic-form">
                <div class="form-group">
                    <label for="subject">Subject</label>
                    <textarea id="subject" class="form-control" rows="3"></textarea>
                </div>

                <div class="form-group">
                    <label for="initial-post">Message</label>
                    <textarea id="initial-post" class="form-control" rows="3"></textarea>
                </div>

                <div class="form-group">
                    <label for="topic-tags">Tags</label>
                    <select multiple id="topic-tags" class="form-control">
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