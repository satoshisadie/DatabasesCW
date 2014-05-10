<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
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
</body>
</html>
