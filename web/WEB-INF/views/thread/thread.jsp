<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${thread.subject}</title>
    <script src="<c:url value="/js/lib/jquery-2.1.1.js"/>" type="application/javascript"></script>
    <script src="<c:url value="/js/thread.js"/>" type="application/javascript"></script>
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>" type="text/css">
</head>
<body>
    <input type="hidden" id="threadId" value="${thread.id}">
    <jsp:include page="childPost.jsp">
        <jsp:param name="postShift" value="0"/>
        <jsp:param name="postId" value="0"/>
    </jsp:include>
    <button class="writePost">Write new post</button>
</body>
</html>
