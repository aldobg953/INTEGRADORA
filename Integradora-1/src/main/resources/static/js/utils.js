function ocultarAlerta(){
    const alerta = event.target.parentElement.parentElement;
    alerta.classList.add("hidden");
}

function confirmDelete(id){
    const input_id = document.getElementById("input_id_carrera");
    const alerta = document.getElementById("popup-modal");
    input_id.value = id;
    alerta.classList.remove("hidden");
}

function ocultarModal(){
    const alerta = document.getElementById("popup-modal");
    alerta.classList.add("hidden");
}

function confirmDeleteEspecialidad(id){
    const input_id = document.getElementById("input_id_especialidad");
    const alerta = document.getElementById("popup-modal");
    input_id.value = id;
    alerta.classList.remove("hidden");
}

function confirmDeleteUniversidad(id){
    const input_id = document.getElementById("input_id_universidad");
    const alerta = document.getElementById("popup-modal");
    input_id.value = id;
    alerta.classList.remove("hidden");
}
