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
        <form method="post" action="${pageContext.request.contextPath}/createUser.html" style="margin-left: 50px;">
            <label for="login" style="width: 70px; display: inline-block;">Name</label>
            <input type="text" id="login" name="login" style="margin-top: 20px;">
            <br>
            <label for="password" style="width: 70px; display: inline-block;">Password</label>
            <input type="password" id="password" name="password" style="margin-top: 20px;">
            <br>
            <label for="firstName" style="width: 70px; display: inline-block;">First Name</label>
            <input type="text" id="firstName" name="firstName" style="margin-top: 20px;">
            <br>
            <label for="lastName" style="width: 70px; display: inline-block;">Last Name</label>
            <input type="text" id="lastName" name="lastName" style="margin-top: 20px;">
            <br>
            <label for="email" style="width: 70px; display: inline-block;">Email</label>
            <input type="text" id="email" name="email" style="margin-top: 20px;">
            <br>
            <button class="button blue large" id="create-user" style="margin-top: 30px;">Create user</button>
        </form>
    </div>

    <div id="terms">
        <div id="terms-content" style="width: 300px; display: block;">

        </div>
        <%--<button class="button green" id="accept-terms" style="margin-top: 30px; display: inline;">Accept terms</button>--%>
        <%--<button class="button red" id="cancel-terms" style="margin-top: 30px; display: ">Cancel</button>--%>
    </div>
</body>
</html>
