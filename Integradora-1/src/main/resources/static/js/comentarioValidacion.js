document.addEventListener('DOMContentLoaded', function() {
    const stars = document.querySelectorAll('#star-rating .star');
    const calificacionInput = document.getElementById('calificacion');
    const comentarioTextarea = document.getElementById('comentarioNuevo');
    const errorMessagesDiv = document.getElementById('error-messages');
    const enviarButton = document.getElementById('enviarButton');
    let calificacion = 0;

    stars.forEach(star => {
        star.addEventListener('click', function() {
            calificacion = this.dataset.value;
            calificacionInput.value = calificacion;
            updateStarRating();
            removeErrorStyles(); // Elimina estilos de error al cambiar la calificación
        });
    });

    function updateStarRating() {
        stars.forEach(star => {
            if (star.dataset.value <= calificacion) {
                star.classList.add('text-yellow-500');
            } else {
                star.classList.remove('text-yellow-500');
            }
        });
    }

    function setErrorStyles() {
        comentarioTextarea.classList.add('error-border');
        enviarButton.classList.add('error-border');
        stars.forEach(star => star.classList.add('error-star'));
    }

    function removeErrorStyles() {
        comentarioTextarea.classList.remove('error-border');
        enviarButton.classList.remove('error-border');
        stars.forEach(star => star.classList.remove('error-star'));
    }

    function validateForm() {
        let errors = [];
        const comentario = comentarioTextarea.value.trim();

        if (calificacion === 0) {
            errors.push('Por favor, selecciona una calificación.');
        }
        if (comentario.length < 3 || comentario.length > 350) {
            errors.push('El comentario debe tener entre 3 y 350 caracteres.');
        } else if (comentario === '') {
            errors.push('El comentario no puede estar vacío.');
        }

        if (errors.length > 0) {
            errorMessagesDiv.innerHTML = errors.join('<br>');
            setErrorStyles();
        } else {
            errorMessagesDiv.innerHTML = '';
            removeErrorStyles();
        }
    }

    comentarioTextarea.addEventListener('input', validateForm);
    stars.forEach(star => star.addEventListener('click', validateForm));
    
    document.getElementById('comentarioForm').addEventListener('submit', function(event) {
        let errors = [];
        const comentario = comentarioTextarea.value.trim();

        if (calificacion === 0) {
            errors.push('Por favor, selecciona una calificación.');
        }
        if (comentario.length < 3 || comentario.length > 350) {
            errors.push('El comentario debe tener entre 3 y 350 caracteres.');
        } else if (comentario === '') {
            errors.push('El comentario no puede estar vacío.');
        }

        if (errors.length > 0) {
            errorMessagesDiv.innerHTML = errors.join('<br>');
            event.preventDefault();
            setErrorStyles();
        } else {
            removeErrorStyles();
        }
    });
});


console.log("comentario");