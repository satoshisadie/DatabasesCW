<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Authorization</title>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap-theme.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>" type="text/css">
    <script src="<c:url value="/js/lib/jquery-2.1.1.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/lib/bootstrap.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/authorization.js"/>" type="text/javascript"></script>
</head>
<body>
    <div class="container" style="margin-top: 200px;">
        <div class="row">
            <div class="col-md-5 col-md-offset-3">
                <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/authorize.html">
                    <div class="form-group">
                        <label class="control-label col-md-5" for="login">Login</label>
                        <div class="col-md-6">
                            <input type="text" id="login" class="form-control" name="login" placeholder="Login">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-md-5" for="password">Password</label>
                        <div class="col-md-6">
                            <input type="password" id="password" class="form-control" name="password" placeholder="Password">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-md-offset-5 col-md-6">
                            <button class="btn btn-primary btn-block" type="submit">Log In</button>
                            <div class="btn btn-primary btn-block" id="new-user">Create new user</div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>