document.getElementById('download-pdf').addEventListener('click', function() {
    var element = document.getElementById('content'); // Elemento que quieres convertir a PDF
    var opt = {
        margin:       10,
        filename:     'informacion_cliente.pdf',
        image:        { type: 'jpeg', quality: 0.98 },
        html2canvas:  { scale: 2 },
        jsPDF:        { unit: 'pt', format: 'letter', orientation: 'portrait' }
    };

    // Espera 1 segundo para asegurar que todo el contenido esté cargado
    setTimeout(function() {
        // Crea el PDF
        html2pdf().from(element).set(opt).save();
    }, 1000); // Espera 1 segundo (1000 milisegundos)
});