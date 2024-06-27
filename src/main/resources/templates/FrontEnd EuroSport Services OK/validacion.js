document.getElementById('reservaForm').addEventListener('submit', function(event) {
    // Previene el envío del formulario
    event.preventDefault();

    // DOM
    const nombre = document.getElementById('nombre').value.trim();
    const dni = document.getElementById('dni').value.trim();
    const email = document.getElementById('email').value.trim();
    const telefono = document.getElementById('telefono').value.trim();
    const tipoHabitacion = document.getElementById('tipo_habitacion').value;
    const formasPago = document.querySelectorAll('input[name="forma_pago"]:checked');

    // Validación del nombre
    if (nombre === "") {
        alert('Por favor, ingrese su nombre completo.');
        return;
    }

    // Validación del DNI (ejemplo para DNI español con 8 números y 1 letra)
    const dniPattern = /^\d{8}[]$/;
    if (!dniPattern.test(dni)) {
        alert('Por favor, ingrese un DNI válido.');
        return;
    }

    // Validación del correo electrónico
    const emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!emailPattern.test(email)) {
        alert('Por favor, ingrese un correo electrónico válido.');
        return;
    }

    // Validación del teléfono
    const telefonoPattern = /^\d{9,15}$/; // Ajusta el patrón según el formato de teléfono esperado
    if (!telefonoPattern.test(telefono)) {
        alert('Por favor, ingrese un número de teléfono válido.');
        return;
    }

    // Validación del tipo de habitación
    if (tipoHabitacion === "") {
        alert('Por favor, seleccione un tipo de habitación.');
        return;
    }

    // Validación de formas de pago
    if (formasPago.length === 0) {
        alert('Por favor, seleccione al menos una forma de pago.');
        return;
    }

    // Si todas las validaciones pasan, se puede enviar el formulario
    alert('Formulario enviado con éxito.');
    document.getElementById('reservaForm').submit();
});
