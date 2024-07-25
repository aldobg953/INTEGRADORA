document.addEventListener("DOMContentLoaded", function() {
    const form = document.getElementById("myForm");

    // Configuración de campos para validar
    const sections = [
        {
            fields: [
                { id: "nombre", errorId: "nombreError", minLength: 8, maxLength: 80, required: true },
                { id: "carrera", errorId: "universidadErrorE", required: true },
                { id: "descripcion_breve", errorId: "descBreveErrorE", minLength: 8, maxLength: 200, required: true },
                { id: "informacion", errorId: "infoErrorE", minLength: 8, maxLength: 550, required: true },
                { id: "RoadMap", errorId: "roadErrorE", minLength: 8, maxLength: 150, required: true },
                { id: "horario", errorId: "horarioEspecialidadE", minLength: 8, maxLength: 150, required: true },
                { id: "slctHorario", errorId: "horarioErrorE", type: "select", required: true },
                { id: "txtCosto", errorId: "costoErrorE", required: true },
                { id: "slctModalidad", errorId: "selectModalidadError", type: "select", required: true },
                { id: "slctPeriodoEscolar", errorId: "peroidoErrorE", type: "select", required: true },
                { id: "horario", errorId: "horarioEspecialidadE", minLength: 8, maxLength: 150, required: true }
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