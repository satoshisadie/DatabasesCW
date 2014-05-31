<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main page</title>
    <link rel="stylesheet" href="<c:url value="/css/jquery-ui-1.10.4.custom.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap-theme.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/font-awesome.css"/>" type="text/css">
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
                                <%--<c:forEach items="${tagsByThreadId.get(topic.id)}" var="tagId">--%>
                                    <%--<span class="label label-default">${tagById.get(tagId).name}</span>--%>
                                <%--</c:forEach>--%>
                            </span>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>

                <h3>Topics</h3>
                <c:forEach items="${topics}" var="topic">
                    <c:set var="user" value="${userByTopic.get(topic)}"/>

                    <div class="topic">
                        <input type="hidden" class="topic-id" value="${topic.id}">
                        <div class="topic-header">
                            <c:choose>
                                <c:when test="${followedTopics.contains(topic.id)}">
                                    <span class="fa fa-minus-square cancel-following" style="display: inline-block; cursor: pointer;"></span>
                                </c:when>
                                <c:when test="${!topic.active}">
                                    <span class="fa fa-lock" style="display: inline-block;"></span>
                                </c:when>
                                <c:otherwise>
                                    <span class="fa fa-plus-square follow-topic" style="display: inline-block; cursor: pointer;"></span>
                                </c:otherwise>
                            </c:choose>

                            <span class="topic-subject">${topic.subject}</span>
                            <span class="topic-tags">
                                <c:forEach items="${tagsByTopicId.get(topic.id)}" var="tagId">
                                    <span class="label label-default tag" data-id="${tagById.get(tagId).id}">${tagById.get(tagId).name}</span>
                                </c:forEach>
                            </span>
                        </div>

                        <span class="topic-info">
                            Created ${topic.dateCreatedFormatted} by <strong>${user.login}</strong><br>
                            Last post ${topic.dateLastPostFormatted}<br>
                            Viewed ${topic.viewCount} times
                        </span>
                    </div>
                    <hr class="topics-separator">
                </c:forEach>
                <button class="btn btn-primary" id="create-topic">Create new topic</button>
            </div>
        </div>

        <div class="modal fade" id="topic-form">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <textarea id="subject" class="form-control" rows="3" placeholder="Subject"></textarea>
                            </div>

                            <div class="form-group">
                                <textarea id="initial-post" class="form-control" rows="3" placeholder="Message"></textarea>
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

                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="submit-topic-form">Create topic</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <input type="hidden" id="forumId" value="${forumId}">
</body>
</html>