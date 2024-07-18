function changeLang(selectElement) {
    var selectedLang = selectElement.value;
    var currentUrl = window.location.href;
    
    // Reemplazar o añadir el parámetro lang en la URL actual
    var updatedUrl;
    if (currentUrl.includes('?')) {
        updatedUrl = currentUrl.replace(/(\?lang=)[^\&]+/, '$1' + selectedLang);
    } else {
        updatedUrl = currentUrl + '?lang=' + selectedLang;
    }
    
    // Redirigir a la nueva URL
    window.location.href = updatedUrl;
}