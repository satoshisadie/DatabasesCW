<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/createThread.html">
    <label>
        Subject
        <input type="text" name="subject">
    </label><br/>
    <input type="submit" value="Ok">
</form>
</body>
</html>
