document.addEventListener('DOMContentLoaded', function() {
    const header = document.querySelector('.header');
    const navbar = document.querySelector('.navbar');
    const navbarLogo = document.querySelector('.navbar-logo');

    // Crear el botón del menú hamburguesa
    const hamburger = document.createElement('div');
    hamburger.classList.add('hamburger-menu');
    hamburger.innerHTML = '<span></span><span></span><span></span>';

    // Insertar el botón después del logo
    navbarLogo.appendChild(hamburger);

    hamburger.addEventListener('click', function() {
        header.classList.toggle('expanded');
        hamburger.classList.toggle('active');
        
        // Ajustar la altura máxima del header para la animación
        if (header.classList.contains('expanded')) {
            const navbarLinks = document.querySelector('.navbar-links');
            header.style.height = `${5 + navbarLinks.scrollHeight / 16}rem`;
        } else {
            header.style.height = '6rem';
        }
    });

    // Función para manejar el cambio de tamaño de la ventana
    function handleResize() {
        if (window.innerWidth > 768) {
            header.classList.remove('expanded');
            hamburger.classList.remove('active');
            header.style.height = '6rem';
        }
    }

    // Agregar event listener para el cambio de tamaño de la ventana
    window.addEventListener('resize', handleResize);
});