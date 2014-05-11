<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="<c:url value="/css/jquery-ui-1.10.4.custom.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>" type="text/css">
    <script src="<c:url value="/js/lib/jquery-2.1.1.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/lib/jquery-ui-1.10.4.js"/>" type="text/javascript"></script>
</head>
<body>
    <div id="page-content">
        <form method="post" action="${pageContext.request.contextPath}/createUser.html">
            <label>
                Name
                <input type="text" name="login">
            </label><br>
            <label>
                Password
                <input type="password" name="password">
            </label><br>
            <label>
                First Name
                <input type="text" name="firstName">
            </label><br>
            <label>
                Last Name
                <input type="text" name="lastName">
            </label><br>
            <label>
                Email
                <input type="text" name="email">
            </label><br>
            <input type="submit" value="Ok">
        </form>
    </div>
</body>
</html>
