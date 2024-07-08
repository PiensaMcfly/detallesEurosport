
    function mostrarFormulario(formulario) {
        document.getElementById('formulario-cliente').style.display = 'none';
        document.getElementById('formulario-auto').style.display = 'none';
        document.getElementById('formulario-cotizacion').style.display = 'none';
        document.getElementById(formulario).style.display = 'block';
    }

    function enviarFormulario(formulario, siguienteFormulario) {
        var formData = new FormData(document.getElementById(formulario));
        fetch('/' + formulario.split('-')[1] + '/save', {
            method: 'POST',
            body: formData
        })
        .then(response => {
            if (response.ok) {
                if (siguienteFormulario) {
                    mostrarFormulario(siguienteFormulario);
                } else {
                    alert('Datos guardados exitosamente');
                }
            } else {
                console.error('Error en la respuesta del servidor:', response);
            }
        })
        .catch(error => console.error('Error:', error));
    }
