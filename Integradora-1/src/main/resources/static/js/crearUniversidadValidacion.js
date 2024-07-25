document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("myForm");

    // Configuración de campos para validar
    const sections = [
        {
            fields: [
                { id: "nombre_completo", errorId: "nombreErrorV", minLength: 8, maxLength: 50, required: true },
                { id: "nombre_abreviado", errorId: "nombreAbreviadoErrorV", minLength:3, maxLength:20, required: true},
                { id: "pagina_web", errorId: "linkErrorV",minLength: 8, maxLength: 50, required: true },
                { id: "correo", errorId: "correoErrorV", minLength: 8, maxLength: 50, required: true},
                { id: "telefono", errorId: "telefonoErrorV",minLength: 8, maxLength: 50, required: true },
                { id: "message", errorId: "messageErrorV", minLength: 15, maxLength: 300, required: true },
                { id: "direccion", errorId: "direccionErrorV", minLength: 8, maxLength: 50, required: true },
                { id: "direccionGoogle", errorId: "direccionGoogleErrorV", minLength: 8, maxLength: 50, required: true  },
                { id: "file_input", errorId: "logoErrorV", required: true  },
                { id: "", errorId: "logoErrorV", required: true  },
                { id: "file_input_help", errorId: "logoErrorV", required: true  },

            ]
        },
        // Añadir más secciones según sea necesario
    ];

    function validateTextField(field, errorElement, minLength, maxLength) {
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

    function validateIntegerField(field, errorElement, min) {
        const value = parseInt(field.value);
        if (isNaN(value) || value < min) {
            field.classList.add("input-error");
            field.classList.remove("input-valid");
            errorElement.style.display = "block";
            errorElement.textContent = `El valor debe ser al menos ${min}`;
            return false;
        } else {
            field.classList.add("input-valid");
            field.classList.remove("input-error");
            errorElement.style.display = "none";
            errorElement.textContent = "";
            return true;
        }
    }

    function validateFloatField(field, errorElement, min) {
        const value = parseFloat(field.value);
        if (isNaN(value) || value < min) {
            field.classList.add("input-error");
            field.classList.remove("input-valid");
            errorElement.style.display = "block";
            errorElement.textContent = `El valor debe ser al menos ${min.toFixed(2)}`;
            return false;
        } else {
            field.classList.add("input-valid");
            field.classList.remove("input-error");
            errorElement.style.display = "none";
            errorElement.textContent = "";
            return true;
        }
    }

    function validateSelectField(field, errorElement) {
        if (field.value === "" || field.value === "0" ) {
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

    function validateForm() {
        let isValid = true;
        sections.forEach(section => {
            section.fields.forEach(({ id, errorId, minLength, maxLength, type, min, required }) => {
                const field = document.getElementById(id);
                const errorElement = document.getElementById(errorId);
                if (required && field.value.trim() === "") {
                    field.classList.add("input-error");
                    field.classList.remove("input-valid");
                    errorElement.style.display = "block";
                    errorElement.textContent = "Este campo no puede estar vacío";
                    isValid = false;
                } else if (type === "integer") {
                    if (!validateIntegerField(field, errorElement, min)) {
                        isValid = false;
                    }
                } else if (type === "float") {
                    if (!validateFloatField(field, errorElement, min)) {
                        isValid = false;
                    }
                } else if (type === "select") {
                    if (!validateSelectField(field, errorElement)) {
                        isValid = false;
                    }
                } else {
                    if (!validateTextField(field, errorElement, minLength, maxLength)) {
                        isValid = false;
                    }
                }
            });
        });
        return isValid;
    }

    sections.forEach(section => {
        section.fields.forEach(({ id, errorId, minLength, maxLength, type, min, required }) => {
            const field = document.getElementById(id);
            const errorElement = document.getElementById(errorId);
            if (type === "integer") {
                field.addEventListener("input", () => validateIntegerField(field, errorElement, min));
            } else if (type === "float") {
                field.addEventListener("input", () => validateFloatField(field, errorElement, min));
            } else if (type === "select") {
                field.addEventListener("change", () => validateSelectField(field, errorElement));
            } else {
                field.addEventListener("input", () => validateTextField(field, errorElement, minLength, maxLength));
            }
        });
    });

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


console.log("hola");