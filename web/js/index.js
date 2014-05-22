$(document).ready(function() {
    $("#administrator-page").click(function() {
//            var threadId = $(this).parent().children(".thread-id").val();
//            window.location = "./followThread?threadId=" + threadId;
    });

    $("#logout").click(function() {
        $.ajax({
            url: "logout.html",
            method: "POST",
            success: function() {
                location.reload();
            }
        })
    });

    $(".follow-thread").each(function() {
        $(this).click(function() {
            $.ajax({
                url: "followThread.html",
                type: "POST",
                data: {
                    threadId: $(this).parents(".thread").children(".thread-id").val()
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
                    threadId: $(this).parents(".thread").children(".thread-id").val()
                },
                success: function() {
                    location.reload();
                }
            });
        });
    });

    $(".thread-subject").each(function() {
        $(this).click(function() {
            var threadId = $(this).parents(".thread").children(".thread-id").val();
            window.location = "./thread.html?id=" + threadId;
        });
    });

    $("#create-thread").click(function() {
       $("#thread-form").dialog("open");
    });

    $("#thread-form").dialog({
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
                    url: "createThread.html",
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
