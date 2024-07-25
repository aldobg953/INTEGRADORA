
$(document).ready(function(){
    $("#search").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $(".tarjeta").filter(function() {
            var universityName = $(this).find("h2").text().toLowerCase();
            $(this).toggle(universityName.indexOf(value) > -1);
        });
    });
});
