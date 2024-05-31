//  Inicializacion Swiper


document.addEventListener("DOMContentLoaded", function() {
    // Fetch data from the 'ProductInfo' endpoint
    fetch('ProductInfo')
        .then(response => {
            // Check if the response is ok (status code 200-299)
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json(); // Parse the JSON from the response
        })
        .then(data => {
            // Check if the data contains the expected properties
            if (data.productName && data.price1 && data.price2) {
                // Update the HTML elements with the fetched data
                document.getElementById("product-name").innerText = data.productName;
                document.getElementById("price-1").innerText = data.price1 + " €";
                document.getElementById("price-2").innerText = data.price2 + " €";
            } else if (data.error) {
                console.error('Error from server: ' + data.error);
            } else {
                console.error('Unexpected data format:', data);
            }
        })
        .catch(error => {
            // Log any errors to the console
            console.error('Error:', error);
        });
});


function cerrarSesion() {
    window.location.href = "index.html"; // Redirige a index.html
}

var swiper = new Swiper(".mySwiper-1", {
    slidesPerView: 1,
    spaceBetween: 30,
    loop: true, 
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
    },
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    }
});

var swiper = new Swiper(".mySwiper-2", {
    slidesPerView: 3,
    spaceBetween: 30,
    loop: true, 
    navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev",
    },
    breakpoints: {
        0: {
            slidesPerView: 1
        }, 
        520: {
            slidesPerView: 2
        },
        950: {
            slidesPerView: 3
        }
    }
});

// Carrito
const carrito = document.getElementById('carrito');
const elementos1 = document.getElementById('lista-1');
const elementos2 = document.getElementById('lista-2');
const elementos3 = document.getElementById('lista-3');
const lista = document.querySelector('#lista-carrito tbody');
const vaciarCarritoBtn = document.getElementById('vaciar-carrito');

cargarEventListeners();

function cargarEventListeners() {
    elementos1.addEventListener('click', comprarElemento);
    elementos2.addEventListener('click', comprarElemento);
    elementos3.addEventListener('click', comprarElemento);
    carrito.addEventListener('click', eliminarElemento);
    vaciarCarritoBtn.addEventListener('click', vaciarCarrito);
}

function comprarElemento(e) {
    e.preventDefault();
    if (e.target.classList.contains('agregar-carrito')) {
        const elemento = e.target.parentElement.parentElement;
        leerDatosElementos(elemento);
    }
}

function leerDatosElementos(elemento) {
    const infoElemento = {
        imagen: elemento.querySelector('img').src,
        titulo: elemento.querySelector('h3').textContent,
        precio: elemento.querySelector('.precio').textContent,
        id: elemento.querySelector('a').getAttribute('data-id')
    }

    insertarCarrito(infoElemento); 
}

function insertarCarrito(elemento) { 
    const row = document.createElement('tr'); // estamos dentro de una tabla
    row.innerHTML = `
        <td>
            <img src="${elemento.imagen}" width="100">
        </td>
        <td>
            ${elemento.titulo}
        </td>
        <td> 
            ${elemento.precio}
        </td>
        <td>
            <a href="#" class="borrar" data-id="${elemento.id}">X</a> 
        </td>
    `;

    lista.appendChild(row);
}

function eliminarElemento(e) {
    e.preventDefault();
    let elemento,
        elementoId;

    if (e.target.classList.contains('borrar')) {
        e.target.parentElement.parentElement.remove();
        elemento = e.target.parentElement.parentElement;
        elementoId = elemento.querySelector('a').getAttribute('data-id');
    }
}

function vaciarCarrito() {
    while (lista.firstChild) {
        lista.removeChild(lista.firstChild);
    }
    return false;
}

/*<script>
    document.addEventListener("DOMContentLoaded", function() {
        var infoBtn = document.getElementById("infoBtn");
        var overlay = document.getElementById("overlay");
        var closeBtn = document.getElementById("closeBtn");

        infoBtn.addEventListener("click", function(event) {
            event.preventDefault(); // Previene el comportamiento por defecto del enlace
            overlay.style.display = "flex"; // Muestra el overlay
        });

        closeBtn.addEventListener("click", function() {
            overlay.style.display = "none"; // Oculta el overlay
        });

        // Cerrar el overlay si se hace clic fuera del contenido
        window.addEventListener("click", function(event) {
            if (event.target == overlay) {
                overlay.style.display = "none";
            }
        });
    });
</script>*/


