
document.getElementById("logoutButton").addEventListener("click", function() {
    window.location.href = "index.html"; // Reemplaza "login.html" con la URL de tu página de inicio de sesión
});

document.getElementById("codiConf").addEventListener("input", function() {
    // Limitar la longitud de la entrada a 6 caracteres
    if (this.value.length > 6) {
        this.value = this.value.slice(0, 6);
    }
});



document.addEventListener("DOMContentLoaded", function () {
    fetch('GetSessionMessage') // Servlet to get the session message
        .then(response => response.json())
        .then(data => {
            const messageDiv = document.getElementById("mensaje");
            const mensajeTexto = document.getElementById("mensaje-texto");
            const cerrarBoton = document.getElementById("cerrar-mensaje");
            const overlay = document.getElementById("overlay");
            const containerDiv = document.getElementById("Contendor");

            if (data.message) {
                let icon = '';
                if (data.tipoMensaje === "success") {
                    icon = '<ion-icon name="checkmark-circle-outline"></ion-icon>'; 
                    mensajeTexto.style.color = 'green';
                } else if (data.tipoMensaje === "error") {
                    icon = '<ion-icon name="close-circle-outline"></ion-icon>'; 
                    mensajeTexto.style.color = 'red';
                }
                
                mensajeTexto.innerHTML = `${icon} ${data.message}`;
                containerDiv.classList.add('active');
                overlay.classList.add('active');
            } else {
                messageDiv.style.display = 'none';
                overlay.classList.remove('active');
                containerDiv.classList.remove('active');
            }

            cerrarBoton.addEventListener("click", function () {
                messageDiv.style.display = 'none';
                overlay.classList.remove('active');
                containerDiv.classList.remove('active');
            });
        });
});
