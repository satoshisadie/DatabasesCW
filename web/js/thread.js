$(document).ready(function() {
    $(".writePost").each(function() {
        $(this).click(function() {
            var threadId = $(window).children("#threadId").val();
            window.location = "./writePost.html?threadId=" + threadId;
        });
    });

    $(".replyPost").each(function() {
        $(this).click(function() {
            var threadId = $(window).children("#threadId").val();
            var repliedTo = $(this).parent().children(".postId").val();
            window.location = "./writePost.html?threadId=" + threadId + "&repliedTo=" + repliedTo;
        });
    });
});
