$(document).ready(function() {
    $(".forum-subject").each(function() {
        $(this).click(function() {
            var forumId = $(this).parents(".forum").children(".forum-id").val();
            window.location = "./viewForum.html?id=" + forumId;
        });
    });

    $(".follow-topic").each(function() {
        $(this).click(function() {
            $.ajax({
                url: "followTopic.html",
                type: "POST",
                data: {
                    topicId: $(this).parents(".topic").children(".topic-id").val()
                },
                success: function() {
                    location.reload();
                }
            });
        });
    });

    $(".cancel-following").each(function() {
        $(this).click(function() {
            $.ajax({
                url: "cancelFollowing.html",
                type: "POST",
                data: {
                    topicId: $(this).parents(".topic").children(".topic-id").val()
                },
                success: function() {
                    location.reload();
                }
            });
        });
    });

    $(".topic-subject").each(function() {
        $(this).click(function() {
            var topicId = $(this).parents(".topic").children(".topic-id").val();
            window.location = "./viewTopic.html?id=" + topicId;
        });
    });

    $("#create-topic").click(function() {
        $("#topic-form").modal("show");
    });

    $("#submit-topic-form").click(function() {
        $.ajax({
            url: "createTopic.html",
            type: "POST",
            data: {
                forumId: $("#forumId").val(),
                subject: $("#subject").val(),
                initialPost: $("#initial-post").val(),
                tags: function() {
                    var tags = [];
                    $("#topic-tags").find(":selected").each(function(i, selected) {
                        tags[i] = $(selected).val();
                    });
                    return JSON.stringify(tags);
                }()
            },
            success: function() {
                location.reload();
            }
        });
        $("#topic-form").modal("hide");
    });
});
