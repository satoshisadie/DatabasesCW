$(document).ready(function() {
    $(".followThread").each(function() {
        this.click = function() {
            window.location = ""
        };
    });

    $(".openThread").each(function() {
        $(this).click(function() {
            var id = $(this).parent().children(".id").val();
            window.location = "./thread.html?id=" + id;
        });
    });
});
