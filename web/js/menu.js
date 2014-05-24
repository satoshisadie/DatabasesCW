$(document).ready(function() {
    $("#forums").click(function() {
        window.location = "./forums.html";
    });

    $("#administrator-page").click(function() {
        window.location = "./administrator.html";
    });

    $("#moderator-page").click(function() {
        window.location = "./moderator.html";
    });

    $("#view-profile").click(function() {
        window.location = "./viewProfile.html";
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
});

