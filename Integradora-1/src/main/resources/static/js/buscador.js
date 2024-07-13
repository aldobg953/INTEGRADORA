
$(document).ready(function(){
    $("#search").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $(".w-72").filter(function() {
            var universityName = $(this).find("h5").text().toLowerCase();
            $(this).toggle(universityName.indexOf(value) > -1);
        });
    });
});
