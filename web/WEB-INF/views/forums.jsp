<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Forums</title>
    <link rel="stylesheet" href="<c:url value="/css/jquery-ui-1.10.4.custom.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap-theme.css"/>" type="text/css">
    <script src="<c:url value="/js/lib/jquery-2.1.1.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/lib/jquery-ui-1.10.4.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/lib/bootstrap.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/forums.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/menu.js"/>" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <jsp:include page="menu.jsp"/>

    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h3>Forums</h3>
            <c:forEach items="${forums}" var="forum">
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
        </div>
    </div>
</div>
</body>
</html>