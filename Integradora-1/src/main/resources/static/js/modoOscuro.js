document.addEventListener('DOMContentLoaded', () => {
    const toggle = document.getElementById('toggle-dark-mode');
    const confirmButton = document.getElementById('modoOscuroConfirmacion');


    const isDarkMode = localStorage.getItem('dark-mode') === 'true';
    if (isDarkMode) {
        document.body.classList.add('dark');
        toggle.checked = true;
    } else {
        document.body.classList.remove('dark');
        toggle.checked = false;
    }

    toggle.addEventListener('change', () => {
        if (toggle.checked) {
            document.body.classList.add('dark');
            localStorage.setItem('dark-mode', 'true');
        } else {
            document.body.classList.remove('dark');
            localStorage.setItem('dark-mode', 'false');
        }
        
        // Simula un clic en el botón
        if (confirmButton) {
            confirmButton.click();
        }
    });
});

