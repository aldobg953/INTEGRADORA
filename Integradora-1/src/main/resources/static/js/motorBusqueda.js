document.addEventListener('click', function(event) {
    var searchInput = document.getElementById('search-bar');
    var resultsDiv = document.getElementById('results');
    
    // Verifica si el clic fue dentro del input o del div de resultados
    if (!searchInput.contains(event.target) && !resultsDiv.contains(event.target)) {
        resultsDiv.classList.add('hidden'); // Ocultar el div de resultados
    }
});

document.getElementById('search-bar').addEventListener('input', function() {
    var searchTerm = this.value;
    var lang = document.body.getAttribute('data-lang'); // Asegúrate de que este dato esté disponible

    fetch(`/api/motor?query=${encodeURIComponent(searchTerm)}&lang=${encodeURIComponent(lang)}`)
        .then(response => response.json())
        .then(data => {
            var resultsDiv = document.getElementById('results');
            resultsDiv.innerHTML = ''; // Limpiar resultados previos
            
            if (data.length > 0) {
                resultsDiv.classList.remove('hidden'); // Mostrar el div si hay resultados
                
                data.forEach(item => {
                    var a = document.createElement('a');
                    a.textContent = item.nombre;
                    
                    // Configurar la URL con el parámetro lang
                    var baseUrl;
                    switch(item.tipo) {
                        case 'carrera':
                            baseUrl = `/carreras/carrera/${item.id}`;
                            break;
                        case 'area':
                            baseUrl = `/carreras/area/${item.id}`;
                            break;
                        case 'universidad':
                            baseUrl = `/universidades/universidad/${item.id}`;
                            break;
                    }
                    
                    a.href = `${baseUrl}?lang=${lang}`;
                    a.classList.add('block', 'px-4', 'py-2', 'hover:bg-gray-100', 'dark:hover:bg-gray-600');
                    resultsDiv.appendChild(a);
                });
            } else {
                resultsDiv.classList.add('hidden'); // Ocultar el div si no hay resultados
            }
        })
        .catch(error => console.error('Error:', error));
});