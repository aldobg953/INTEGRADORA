function changeLang(selectElement) {
    var selectedLang = selectElement.value;
    var currentUrl = window.location.href;
    
    var updatedUrl;
    if (currentUrl.includes('?')) {
        updatedUrl = currentUrl.replace(/(\?lang=)[^\&]+/, '$1' + selectedLang);
    } else {
        updatedUrl = currentUrl + '?lang=' + selectedLang;
    }
    
    window.location.href = updatedUrl;
}