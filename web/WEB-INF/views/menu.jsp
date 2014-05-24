<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="nav nav-pills nav-justified">
    <li id="forums"><a href="#">Forums</a></li>
    <c:if test="${not empty sessionScope.user}">
        <li><a href="#" id="forum-rules">Forum rules</a></li>
    </c:if>
    <c:if test="${not empty sessionScope.administrator}">
        <li><a href="#" id="administrator-page">Administrator page</a></li>
    </c:if>
    <c:if test="${not empty sessionScope.moderator}">
        <li><a href="#" id="moderator-page">Moderator page</a></li>
    </c:if>
    <li><a href="#" id="view-profile">Profile</a></li>
    <li><a href="#" id="logout">Log out</a></li>
</ul>
