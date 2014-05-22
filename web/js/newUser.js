$(document).ready(function() {
    $("#create-user").click(function() {
        $("#terms").dialog("open");
    });

    $("#terms").dialog({
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
            "Accept terms": function() {
                $.ajax({
                    url: "createUser.html",
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
                        window.location = "authorization.html";
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
