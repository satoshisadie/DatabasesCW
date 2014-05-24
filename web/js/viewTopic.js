$(document).ready(function() {
    $("#write-post").click(function() {
        $("#post-id").val("");
        $("#post-form").modal("show");
    });

    $(".reply-post").each(function() {
        $(this).tooltip();
        $(this).click(function() {
            $("#post-id").val($(this).parent().children(".post-id").val());
            $("#post-form").modal("show");
        });
    });

//    $("#post-form").dialog({
//        autoOpen: false,
//        height: "auto",
//        width: 500,
//        modal: true,
//        position: {
//            my: "center",
//            at: "center",
//            of: window
//        },
//        buttons: {
//            "Ok": function() {
//                $.ajax({
//                    url: "./createPost.html",
//                    type: "POST",
//                    data: {
//                        threadId: $("#thread-id").val(),
//                        postId: $("#post-id").val(),
//                        message: $("#message").val()
//                    },
//                    success: function() {
//                        location.reload();
//                    }
//                });
//                $(this).dialog("close");
//            },
//            "Cancel": function() {
//                $(this).dialog("close");
//            }
//        }
//    });

    $(".complain-post").each(function() {
        $(this).tooltip();
        $(this).click(function() {
            $("#post-id").val($(this).parent().children(".post-id").val());
//            $("#complain-form").dialog("open");
            $("#complain-form").modal("show");
        });
    });

//    $("#complain-form").dialog({
//        autoOpen: false,
//        height: "auto",
//        width: 350,
//        modal: true,
//        position: {
//            my: "center",
//            at: "center",
//            of: window
//        },
//        buttons: {
//            "Complain": function() {
//                $.ajax({
//                    url: "./complainPost.html",
//                    type: "POST",
//                    data: {
//                        postId: $("#post-id").val(),
//                        ruleId: $("#violated-rule").find(":selected").val(),
//                        violationComment: $("#violation-comment").val()
//                    },
//                    success: function() {
//                        alert("Post was complained");
//                    }
//                });
//                $(this).dialog("close");
//            },
//            "Cancel": function() {
//                $(this).dialog("close");
//            }
//        }
//    });
});
