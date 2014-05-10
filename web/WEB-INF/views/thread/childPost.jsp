<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="postShift" value="${param.postShift}"/>
<c:set var="children" value="${posts.get(param.postId)}"/>

<c:if test="${not empty children}">
    <c:forEach items="${children}" var="post">
        <c:set var="user" value="${userById.get(post.userId)}"/>

        <div class="post" style="left: ${postShift}px;">
            <input type="hidden" class="post-id" value="${post.id}">
            <input type="hidden" class="user-id" value="${post.userId}">
            <span class="post-author">${user.login}</span>
            <span class="post-info">${post.dateCreatedFormatted}</span><br>
            <span class="post-message">${post.message}</span><br>
            <button class="reply-post">Reply</button>
            <button class="complain-post">Complain</button>
        </div>
        <hr>

        <jsp:include page="childPost.jsp">
            <jsp:param name="postShift" value="${postShift + 50}"/>
            <jsp:param name="postId" value="${post.id}"/>
        </jsp:include>
    </c:forEach>
</c:if>