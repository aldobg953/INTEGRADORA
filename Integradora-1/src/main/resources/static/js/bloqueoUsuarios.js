function bloquearUsuario() {
    var inputIdUsuarioEdit = document.getElementById("input_id_usuarioB").value;

    const url = `/api/usuarios/${inputIdUsuarioEdit}/bloquear`;
    const alerta = document.getElementById("popup-modal");
    alerta.classList.add("hidden");
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al bloquear usuario');
        }
        return response.text();
    })
    .then(data => {
        alert('Usuario bloqueado exitosamente');
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error al bloquear usuario: ' + error.message);
    });
}
function desbloquearUsuario() {
    var inputIdUsuarioEdit = document.getElementById("input_id_usuarioB").value;

    const url = `/api/usuarios/${inputIdUsuarioEdit}/desbloquear`;
    const alerta = document.getElementById("popup-modal2");
    alerta.classList.add("hidden");
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al bloquear usuario');
        }
        return response.text(); 
    })
    .then(data => {
        alert('Usuario desbloqueado exitosamente');
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error al desbloquear usuario: ' + error.message);
    });
}