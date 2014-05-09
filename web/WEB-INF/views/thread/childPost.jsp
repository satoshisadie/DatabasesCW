<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="postShift" value="${param.postShift}"/>
<c:set var="children" value="${posts.get(param.postId)}"/>

<c:if test="${not empty children}">
    <c:forEach items="${children}" var="post">
        <div class="post" style="left: ${postShift}px;">
            <input type="hidden" value="${post.id}" class="postId">
            <input type="hidden" value="${post.userId}" class="userId">
            Subject ${post.subject}<br/>
            Message ${post.message}<br/>
            Created ${post.dateCreated}<br/>
            Created by <br/>
            <button class="replyPost">Reply</button>
        </div>

        <jsp:include page="childPost.jsp">
            <jsp:param name="postShift" value="${postShift + 50}"/>
            <jsp:param name="postId" value="${post.id}"/>
        </jsp:include>
    </c:forEach>
</c:if>