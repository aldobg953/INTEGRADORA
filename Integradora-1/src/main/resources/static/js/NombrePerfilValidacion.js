document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("myForm1");
    const fields = [
        { id: "newNombre", errorId: "nuevoNombreError", minLength: 3, maxLength: 50, required: true },
        { id: "newApellidoP", errorId: "nuevoApellidoError", minLength: 4, maxLength: 50, required: true }
        
    ];

    function validateField(field, errorElement, minLength, maxLength) {
        if (field.value.trim() === "") {
            field.classList.add("input-error");
            field.classList.remove("input-valid");
            errorElement.style.display = "block";
            errorElement.textContent = "Este campo no puede estar vacío";
            return false;
        } else if (field.value.length < minLength || field.value.length > maxLength) {
            field.classList.add("input-error");
            field.classList.remove("input-valid");
            errorElement.style.display = "block";
            errorElement.textContent = `Este campo debe tener entre ${minLength} y ${maxLength} caracteres`;
            return false;
        } else {
            field.classList.add("input-valid");
            field.classList.remove("input-error");
            errorElement.style.display = "none";
            errorElement.textContent = "";
            return true;
        }
    }

    function validateSelect(field, errorElement) {
        if (field.value === "" || field.value === "0") {
            field.classList.add("input-error");
            field.classList.remove("input-valid");
            errorElement.style.display = "block";
            errorElement.textContent = "Por favor, selecciona una opción válida";
            return false;
        } else {
            field.classList.add("input-valid");
            field.classList.remove("input-error");
            errorElement.style.display = "none";
            errorElement.textContent = "";
            return true;
        }
    }

    function validateIntegerInput() {
        const field = document.getElementById("numero_periodos");
        const errorElement = document.getElementById("numeroError");
        const value = parseInt(field.value);
        if (isNaN(value) || value < 1) {
            field.classList.add("input-error");
            field.classList.remove("input-valid");
            errorElement.style.display = "block";
            errorElement.textContent = "El valor debe ser al menos 1";
            return false;
        } else {
            field.classList.add("input-valid");
            field.classList.remove("input-error");
            errorElement.style.display = "none";
            errorElement.textContent = "";
            return true;
        }
    }

    function validateFloatInput() {
        const field = document.getElementById("txtCosto");
        const errorElement = document.getElementById("costoError");
        const value = parseFloat(field.value);
        if (isNaN(value) || value < 1) {
            field.classList.add("input-error");
            field.classList.remove("input-valid");
            errorElement.style.display = "block";
            errorElement.textContent = "El valor debe ser al menos 1.00";
            return false;
        } else {
            field.classList.add("input-valid");
            field.classList.remove("input-error");
            errorElement.style.display = "none";
            errorElement.textContent = "";
            return true;
        }
    }

    function validateForm() {
        let isValid = true;
        fields.forEach(({ id, errorId, minLength, maxLength, required }) => {
            const field = document.getElementById(id);
            const errorElement = document.getElementById(errorId);
            if (field.tagName === "SELECT") {
                if (!validateSelect(field, errorElement)) {
                    isValid = false;
                }
            } else if (field.type === "number") {
                if (field.id === "numero_periodos") {
                    if (!validateIntegerInput(field, errorElement)) {
                        isValid = false;
                    }
                } else if (field.id === "txtCosto") {
                    if (!validateFloatInput(field, errorElement)) {
                        isValid = false;
                    }
                }
            } else {
                if (!validateField(field, errorElement, minLength, maxLength)) {
                    isValid = false;
                }
            }
        });
        return isValid;
    }

    fields.forEach(({ id, errorId, minLength, maxLength }) => {
        const field = document.getElementById(id);
        const errorElement = document.getElementById(errorId);
        if (field.tagName === "SELECT") {
            field.addEventListener("change", () => validateSelect(field, errorElement));
        } else if (field.type === "number") {
            if (field.id === "numero_periodos") {
                field.addEventListener("input", validateIntegerInput);
            } else if (field.id === "txtCosto") {
                field.addEventListener("input", validateFloatInput);
            }
        } else {
            field.addEventListener("input", () => validateField(field, errorElement, minLength, maxLength));
        }
    });

    document.getElementById("newConfirmacionCorreo").addEventListener("input", validateEmailConfirmation);

    form.addEventListener("submit", function(event) {
        const isFormValid = validateForm();
        const formError = document.getElementById("formError");
        if (!isFormValid) {
            formError.style.display = "block";
            formError.textContent = "Por favor, complete todos los campos correctamente.";
            event.preventDefault();
        } else {
            formError.style.display = "none";
            formError.textContent = "";
        }
    });
});
