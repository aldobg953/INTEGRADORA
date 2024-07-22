document.addEventListener('DOMContentLoaded', () => {
    const select = document.getElementById('idioma');
    const updateButton = document.getElementById('actualizarIdioma');

    select.addEventListener('change', () => {
        if (updateButton) {
            updateButton.click(); 
        }
    });
});

console.log("idioma");