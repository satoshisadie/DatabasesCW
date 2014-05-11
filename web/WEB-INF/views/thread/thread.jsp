<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${thread.subject}</title>
    <link rel="stylesheet" href="<c:url value="/css/jquery-ui-1.10.4.custom.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>" type="text/css">
    <script src="<c:url value="/js/lib/jquery-2.1.1.js"/>" type="application/javascript"></script>
    <script src="<c:url value="/js/lib/jquery-ui-1.10.4.js"/>" type="application/javascript"></script>
    <script src="<c:url value="/js/thread.js"/>" type="application/javascript"></script>
</head>
<body>
    <div id="page-content">
        <span class="thread-subject">${thread.subject}</span><br>
        <jsp:include page="childPost.jsp">
            <jsp:param name="postShift" value="0"/>
            <jsp:param name="postId" value="0"/>
        </jsp:include>
        <button id="write-post">Write new post</button>

        <form id="post-form">
            <input type="hidden" id="post-id">
            <input type="hidden" id="thread-id" value="${thread.id}">
            <label>
                Message<br>
                <textarea id="message"></textarea>
            </label><br>
        </form>
    </div>
</body>
</html>
