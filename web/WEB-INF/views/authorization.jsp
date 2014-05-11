<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Authorization</title>
    <link rel="stylesheet" href="<c:url value="/css/authorization.css"/>" type="text/css">
    <script src="<c:url value="/js/lib/jquery-2.1.1.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/authorization.js"/>" type="text/javascript"></script>
</head>
<body>
    <div id="slick-login">
        <form method="post" action="${pageContext.request.contextPath}/authorize.html">
            <input type="text" name="login" class="placeholder" placeholder="login">
            <input type="password" name="password" class="placeholder" placeholder="password">
            <input type="submit" value="Log In">
        </form>
        <button class="placeholder" id="new-user">Create new user</button>
    </div>
</body>
</html>