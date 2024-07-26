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
    var lang = document.body.getAttribute('data-lang');
    
    // Obtener las traducciones
    var translations = document.getElementById('translations');
    var typeTranslations = {
        'carrera': translations.getAttribute('data-carrera'),
        'area': translations.getAttribute('data-area'),
        'universidad': translations.getAttribute('data-universidad')
    };

    fetch(`/api/motor?query=${encodeURIComponent(searchTerm)}&lang=${encodeURIComponent(lang)}`)
        .then(response => response.json())
        .then(data => {
            var resultsDiv = document.getElementById('results');
            resultsDiv.innerHTML = '';
            
            if (data.length > 0) {
                resultsDiv.classList.remove('hidden');
                
                data.forEach(item => {
                    var a = document.createElement('a');
                    
                    // Crear el span para el tipo
                    var typeSpan = document.createElement('span');
                    typeSpan.classList.add('result-type');
                    
                    // Usar la traducción correspondiente
                    typeSpan.textContent = typeTranslations[item.tipo] || item.tipo;
                    typeSpan.classList.add('type-' + item.tipo);
                    
                    // Crear un contenedor para el nombre y el tipo
                    var contentDiv = document.createElement('div');
                    contentDiv.classList.add('result-content');
                    
                    var nameSpan = document.createElement('span');
                    nameSpan.textContent = item.nombre;
                    nameSpan.classList.add('result-name');
                    
                    contentDiv.appendChild(nameSpan);
                    contentDiv.appendChild(typeSpan);
                    
                    a.appendChild(contentDiv);
                    
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
                    a.classList.add('show-results', 'dark:text-white', 'dark:bg-gray-800');
                    resultsDiv.appendChild(a);
                });
            } else {
                resultsDiv.classList.add('hidden');
            }
        })
        .catch(error => console.error('Error:', error));
});