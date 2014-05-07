<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Main page</title>
    <script src="<c:url value="/js/lib/jquery-2.1.1.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/index.js"/>" type="text/javascript"></script>
</head>
<body>
    <c:forEach items="${threads}" var="thread">
        <div class="thread">
            <input type="hidden" id="id" value="${thread.id}">
            Thread subject ${thread.subject}<br/>
            Created by <br/>
            Created date ${thread.dateCreated}<br/>
            View count ${thread.viewCount}<br/>
            Active ${thread.active}<br/><br/>
            <button class="followThread">Follow thread</button><br/>
            <button class="openThread">Open thread</button><br/>
        </div>
    </c:forEach>
</body>
</html>
