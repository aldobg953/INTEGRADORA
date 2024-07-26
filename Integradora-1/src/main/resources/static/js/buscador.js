
$(document).ready(function(){
    $("#search").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $(".tarjeta").filter(function() {
            var tarjetaTitle = $(this).find("h2").text().toLowerCase();
            $(this).toggle(tarjetaTitle.indexOf(value) > -1);
        });
    });
});
