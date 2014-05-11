$(document).ready(function() {
    $("#write-post").click(function() {
        $("#post-id").val("");
        $("#post-form").dialog("open");
    });

    $(".reply-post").each(function() {
        $(this).click(function() {
            $("#post-id").val($(this).parent().children(".post-id").val());
            $("#post-form").dialog("open");
        });
    });

    $(".complain-post").each(function() {
        $(this).click(function() {
            var threadId = $(document.body).children("#thread-id").val();
            var repliedTo = $(this).parent().children(".post-id").val();
            window.location = "./writePost.html?threadId=" + threadId + "&repliedTo=" + repliedTo;
        });
    });

    $("#post-form").dialog({
        autoOpen: false,
        height: 250,
        width: 500,
        modal: true,
        buttons: {
            "Ok": function() {
                $.ajax({
                    url: "createPost.html",
                    type: "POST",
                    data: {
                        threadId: $("#thread-id").val(),
                        postId: $("#post-id").val(),
                        message: $("#message").val()
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
