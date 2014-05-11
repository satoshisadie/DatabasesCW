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

    $("#post-form").dialog({
        autoOpen: false,
        height: 250,
        width: 500,
        modal: true,
        buttons: {
            "Ok": function() {
                $.ajax({
                    url: "./createPost.html",
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

    $(".complain-post").each(function() {
        $(this).click(function() {
            $("#post-id").val($(this).parent().children(".post-id").val());
            $("#complain-form").dialog("open");
        });
    });

    $("#complain-form").dialog({
        autoOpen: false,
        height: 230,
        width: 350,
        modal: true,
        buttons: {
            "Complain": function() {
                $.ajax({
                    url: "./complainPost.html",
                    type: "POST",
                    data: {
                        postId: $("#post-id").val(),
                        ruleId: $("#violated-rule").find(":selected").val()
                    },
                    success: function() {
                        alert("Post was complained");
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
