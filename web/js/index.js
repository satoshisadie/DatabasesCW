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
        this.click(function() {
            var threadId = $(this).parent(".thread").children(".thread-id").val();
            window.location = "./followThread?threadId=" + threadId;
        });
    });

    $(".thread-subject").each(function() {
        $(this).click(function() {
            var threadId = $(this).parent(".thread").children(".thread-id").val();
            window.location = "./thread.html?id=" + threadId;
        });
    });

    $("#create-thread").click(function() {
       $("#thread-form").dialog("open");
    });

    $("#thread-form").dialog({
        autoOpen: false,
        height: 400,
        width: 500,
        modal: true,
        buttons: {
            "Ok": function() {
                $.ajax({
                    url: "createThread.html",
                    type: "POST",
                    data: {
                        subject: $("#subject").val(),
                        initialPost: $("#initialPost").val()
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
