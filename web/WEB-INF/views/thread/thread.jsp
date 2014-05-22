<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${thread.subject}</title>
    <link rel="stylesheet" href="<c:url value="/css/jquery-ui-1.10.4.custom.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap-theme.css"/>" type="text/css">
    <script src="<c:url value="/js/lib/jquery-2.1.1.js"/>" type="application/javascript"></script>
    <script src="<c:url value="/js/lib/jquery-ui-1.10.4.js"/>" type="application/javascript"></script>
    <script src="<c:url value="/js/lib/bootstrap.js"/>" type="application/javascript"></script>
    <script src="<c:url value="/js/thread.js"/>" type="application/javascript"></script>
</head>
<body>
    <div id="page-content">
        <ul class="nav nav-pills nav-justified">
            <li><a href="#">Main page</a></li>
            <c:if test="${not empty sessionScope.administrator}">
                <li><a href="#">Administrator page</a></li>
            </c:if>
            <c:if test="${not empty sessionScope.moderator}">
                <li><a href="#">Moderator page</a></li>
            </c:if>
            <li><a href="#" id="view-profile">Profile</a></li>
            <li><a href="#" id="logout">Log out</a></li>
        </ul>

        <span class="thread-subject ${thread.active ? "alive-thread-subject" : "dead-thread-subject"}">${thread.subject}</span><br>
        <jsp:include page="childPost.jsp">
            <jsp:param name="postShift" value="0"/>
            <jsp:param name="postId" value="0"/>
        </jsp:include>

        <c:if test="${thread.active}">
            <button class="btn btn-primary" id="write-post" style="margin-top: 20px;">Write new post</button>
        </c:if>

        <input type="hidden" id="thread-status" value="${thread.active}">
        <input type="hidden" id="post-id">

        <div class="modal fade" id="complain-form">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Complain form</h4>
                    </div>

                    <div class="modal-body">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="violated-rule" class="control-label">Select violated rule</label>
                                <select id="violated-rule" class="form-control">
                                    <c:forEach items="${forumRules}" var="forumRule">
                                        <option value="${forumRule.id}">${forumRule.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="violated-comment" class="control-label">Comment</label>
                                <textarea id="violated-comment" class="form-control"></textarea>
                            </div>
                        </form>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary">Complain post</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="post-form">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Post form</h4>
                    </div>

                    <div class="modal-body">
                        <form class="form-horizontal">
                            <input type="hidden" id="thread-id" value="${thread.id}">
                            <label for="message" class="control-label">Message</label>
                            <textarea id="message" class="form-control" rows="3"></textarea>
                        </form>
                    </div>

                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary">Create post</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
