<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/authorize.html">
        <label>
            Login
            <input type="text" name="login">
        </label><br/>
        <label>
            Password
            <input type="password" name="password">
        </label><br/>
        <input type="submit" value="Ok">
    </form>
    <a href="${pageContext.request.contextPath}/registration.html">Create new user</a>
</body>
</html>
