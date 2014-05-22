<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="postShift" value="${param.postShift}"/>
<c:set var="children" value="${posts.get(param.postId)}"/>

<c:if test="${not empty children}">
    <c:forEach items="${children}" var="post">
        <c:set var="user" value="${userById.get(post.userId)}"/>

        <div class="post" style="left: ${postShift}px; width: ${800 - postShift}px;">
            <input type="hidden" class="post-id" value="${post.id}">
            <input type="hidden" class="user-id" value="${post.userId}">
            <div class="post-header" style="clear: both">
                <span class="post-author">${user.login}</span>
                <span class="post-info">${post.dateCreatedFormatted}</span>
                <c:if test="${thread.active}">
                    <div class="post-actions" style="float: right; margin-top: 5px;">
                        <span class="ui-icon ui-icon-arrowreturnthick-1-w reply-post" style="display: inline-block;"
                              data-toggle="tooltip" data-placement="left" title="Reply"></span>
                        <span class="ui-icon ui-icon-alert complain-post" style="display: inline-block; margin-left: 20px;"
                              data-toggle="tooltip" data-placement="right" title="Complain"></span>
                    </div>
                </c:if>
            </div><br>
            <span class="post-message">${post.message}</span><br>
        </div>

        <jsp:include page="childPost.jsp">
            <jsp:param name="postShift" value="${postShift + 70}"/>
            <jsp:param name="postId" value="${post.id}"/>
        </jsp:include>
    </c:forEach>
</c:if>