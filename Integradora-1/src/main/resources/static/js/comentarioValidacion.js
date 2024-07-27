document.addEventListener('DOMContentLoaded', function() {
    const stars = document.querySelectorAll('#star-rating .estrella');
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
            removeErrorStyles();
        });
    });

    function updateStarRating() {
        stars.forEach(star => {
            if (star.dataset.value <= calificacion) {
                star.classList.add('selected');
            } else {
                star.classList.remove('selected');
            }
        });
    }

    function setErrorStyles() {
        comentarioTextarea.style.border = '2px solid red';
        enviarButton.style.backgroundColor = '#9795a3';
    }

    function removeErrorStyles() {
        comentarioTextarea.style.border = '';
        enviarButton.style.backgroundColor = '';
    }

    function validateForm() {
        let errors = [];
        const comentario = comentarioTextarea.value.trim();

        if (calificacion === 0) {
            errors.push(/*[[#{errorSeleccionaCalificacion}]]*/'Por favor, selecciona una calificación.');
        }
        if (comentario.length < 3 || comentario.length > 350) {
            errors.push(/*[[#{errorLongitudComentario}]]*/'El comentario debe tener entre 3 y 350 caracteres.');
        } else if (comentario === '') {
            errors.push(/*[[#{errorComentarioVacio}]]*/'El comentario no puede estar vacío.');
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
        validateForm();
        if (errorMessagesDiv.innerHTML !== '') {
            event.preventDefault();
        }
    });
});