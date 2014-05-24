<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="<c:url value="/css/jquery-ui-1.10.4.custom.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/css/bootstrap-theme.css"/>" type="text/css">
    <script src="<c:url value="/js/lib/jquery-2.1.1.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/lib/jquery-ui-1.10.4.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/lib/bootstrap.js"/>" type="text/javascript"></script>
</head>
<body>
    <div class="container" style="margin-top: 100px;">
        <div class="row">
            <div class="col-md-3 col-md-offset-4">
                <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/createUser.html">
                    <div class="form-group">
                        <input type="text" class="form-control" name="login" placeholder="Login">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="password" placeholder="Password">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="firstName" placeholder="First name">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="lastName" placeholder="Last name">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" name="email" placeholder="Email">
                    </div>
                    <div class="form-group">
                        <button class="btn btn-primary btn-block" id="create-user">Create user</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div id="terms">
        <div id="terms-content" style="width: 300px; display: block;">

        </div>
        <%--<button class="button green" id="accept-terms" style="margin-top: 30px; display: inline;">Accept terms</button>--%>
        <%--<button class="button red" id="cancel-terms" style="margin-top: 30px; display: ">Cancel</button>--%>
    </div>
</body>
</html>
