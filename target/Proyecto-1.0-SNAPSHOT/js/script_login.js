const container = document.querySelector(".container");
const btnSignIn = document.getElementById("btn-sign-in");
const btnSignUp = document.getElementById("btn-sign-up");

btnSignIn.addEventListener("click", () => {
    container.classList.remove("toggle");
});
btnSignUp.addEventListener("click", () => {
    container.classList.add("toggle");
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
                    icon = '<ion-icon name="checkmark-circle-outline"></ion-icon>'; // Ícono de verificación
                    mensajeTexto.style.color = 'green';
                } else if (data.tipoMensaje === "error") {
                    icon = '<ion-icon name="close-circle-outline"></ion-icon>'; // Ícono de error
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


document.getElementById("forgotLink").addEventListener("click", function(event) {
    event.preventDefault(); 
    window.location.href = "Recuperar.html"; // Reemplaza con la ruta correcta a la página de recuperación de contraseña
});




