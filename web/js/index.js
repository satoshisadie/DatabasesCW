$(document).ready(function() {
    $(".followThread").each(function() {
        this.click = function() {
            var threadId = $(this).parent().children(".threadId").val();
            window.location = "./followThread?threadId=" + threadId;
        };
    });

    $(".viewThread").each(function() {
        $(this).click(function() {
            var threadId = $(this).parent().children(".threadId").val();
            window.location = "./thread.html?id=" + threadId;
        });
    });
});
