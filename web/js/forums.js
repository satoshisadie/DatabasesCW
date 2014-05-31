$(document).ready(function() {
    $(".forum-subject").each(function() {
        $(this).click(function() {
            var forumId = $(this).parents(".forum").children(".forum-id").val();
            window.location = "./viewForum.html?id=" + forumId;
        });
    });

    $(".tag").click(function() {
        var tagId = $(this).attr("data-id");
        window.location = "./search.html?tagId=" + tagId;
    });
});
