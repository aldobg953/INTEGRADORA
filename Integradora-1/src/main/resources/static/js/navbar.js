document.addEventListener('DOMContentLoaded', function () {
    var userMenuButton = document.getElementById('user-menu-button');
    var userDropdown = document.getElementById('user-dropdown');

    userMenuButton.addEventListener('click', function (event) {
        event.stopPropagation();
        userDropdown.classList.toggle('hidden');
    });

    userDropdown.addEventListener('click', function (event) {
        event.stopPropagation(); // Evita que el clic en el menú desplegable cierre el menú
    });

    document.addEventListener('click', function (event) {
        if (!userMenuButton.contains(event.target) && !userDropdown.contains(event.target)) {
            userDropdown.classList.add('hidden');
        }
    });
});