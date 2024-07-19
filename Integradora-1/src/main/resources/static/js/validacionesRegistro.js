function validateForm() {
    // Limpiar mensajes de error previos
    document.querySelectorAll('.error-message').forEach(element => element.textContent = '');

    // Validar nombre 
    // El nombre tiene que tener un espacio, eso significa que el usuario esta obligado a ingresar su nombre y su Apellido ademas de tener una longitud minima de 8 caracteres
    const nombre = document.getElementById('nombre').value;
    const nombrePattern = /\S+\s+\S+.*/;
    if (!nombrePattern.test(nombre) || nombre.length <= 8) {
        document.getElementById('nombre-error').textContent = "El nombre debe tener más de 8 caracteres";
        return false;
    }

    // Validar correo electrónico
    // El correo tiene que tener los caracteres necesarios y su respectivo dominio.
    const email = document.getElementById('email').value;
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        document.getElementById('email-error').textContent = "Ingrese un correo electrónico válido";
        return false;
    }

    // Validar contraseña
    const password = document.getElementById('password').value;
    if (password.length < 6) {
        document.getElementById('password-error').textContent = "La contraseña debe tener al menos 6 caracteres";
        return false;
    }

    // Validar confirmación de contraseña
    const confirmPassword = document.getElementById('confirm-password').value;
    if (password !== confirmPassword) {
        document.getElementById('confirm-password-error').textContent = "Las contraseñas no coinciden";
        return false;
    }

    return true;
}

document.addEventListener("DOMContentLoaded", function() {
    const emailInput = document.getElementById('email');
    emailInput.addEventListener('invalid', function(event) {
        event.preventDefault();
        document.getElementById('email-error').textContent = "Ingrese un correo electrónico válido";
    });
});

document.addEventListener("DOMContentLoaded", function() {
    const emailUsedDiv = document.getElementById('emailUsed');
    if (emailUsedDiv) {
        setTimeout(() => {
            emailUsedDiv.style.display = 'none';
        }, 5000); // Oculta la alerta después de 5 segundos
    }
});

// function validateForm() {
//     let isValid = true;
//     // Limpiar mensajes de error previos
//     document.querySelectorAll('.error-message').forEach(element => element.textContent = '');

//     // Validar nombre
//     const nombre = document.getElementById('nombre').value;
//     const nombrePattern = /\S+\s+\S+.*/;
//     if (!nombrePattern.test(nombre) || nombre.length <= 6) {
//         document.getElementById('nombre-error').textContent = "Ingrese Nombre y Apellido*, Minimo 6 caracteres. ";
//         isValid = false;
//         // if (nombre.length <= 6) {
//         //     document.getElementById('nombre-error').textContent = "El nombre tiene que ser mayor a 6 letras"
//         //     isValid = false;
//         // }
//     } 

//     // Validar correo electrónico
//     const email = document.getElementById('email').value;
//     const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
//     if (!emailPattern.test(email)) {
//         document.getElementById('email-error').textContent = "Por favor, ingrese un correo electrónico válido";
//         isValid = false; 
        
//     }

//     // Validar contraseña
//     const password = document.getElementById('password').value;
//     if (password.length < 6) {
//         document.getElementById('password-error').textContent = "La contraseña debe tener al menos 6 caracteres";
//         isValid = false;
//     }

//     // Validar confirmación de contraseña
//     const confirmPassword = document.getElementById('confirm-password').value;
//     if (password !== confirmPassword) {
//         document.getElementById('confirm-password-error').textContent = "Las contraseñas no coinciden";
//         isValid = false;
//     }

//     return isValid;
// }

        window.onload = function() {
            setTimeout(function() {
                alert("Hola ");
            }, 3000); 
        };


        