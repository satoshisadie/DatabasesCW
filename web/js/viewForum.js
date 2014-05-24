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
                    threadId: $(this).parents(".topic").children(".topic-id").val()
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
                    threadId: $(this).parents(".topic").children(".topic-id").val()
                },
                success: function() {
                    location.reload();
                }
            });
        });
    });

    $(".topic-subject").each(function() {
        $(this).click(function() {
            var threadId = $(this).parents(".topic").children(".topic-id").val();
            window.location = "./viewTopic.html?id=" + threadId;
        });
    });

    $("#create-topic").click(function() {
        $("#topic-form").dialog("open");
    });

    $("#topic-form").dialog({
        autoOpen: false,
        height: "auto",
        width: 500,
        modal: true,
        position: {
            my: "center",
            at: "center",
            of: window
        },
        buttons: {
            "Ok": function() {
                $.ajax({
                    url: "createTopic.html",
                    type: "POST",
                    data: {
                        subject: $("#subject").val(),
                        initialPost: $("#initial-post").val(),
                        tags: function() {
                            var tags = [];
                            $("#post-tags").find(":selected").each(function(i, selected) {
                                tags[i] = $(selected).val();
                            });
                            return JSON.stringify(tags);
                        }()
                    },
                    success: function() {
                        location.reload();
                    }
                });
                $(this).dialog("close");
            },
            "Cancel": function() {
                $(this).dialog("close");
            }
        }
    });
});
