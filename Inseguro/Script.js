// Elementos Main.html
var numerosEmpleadosRH;
var numerosEmpleadosFNZ;
var numeroEmpleado;
var apellidoMaterno;
var apellidoPaterno;
var nombre;
var departamentoSelectForm;
var tablaFinanzas;
var tablaRH;
var divFinanzas;
var divRecursosHumanos;
var IP = "10.50.31.55"
var API_IP = "http://"+IP+":8084/seguridad/api/"

var ñam

function inicializar() {
    numerosEmpleadosRH = document.getElementById("numerosEmpleadosRH");
    numerosEmpleadosFNZ = document.getElementById("numerosEmpleadosFNZ");
    numeroEmpleado = document.getElementById("numeroEmpleado");
    apellidoMaterno = document.getElementById("apellidoMaterno");
    apellidoPaterno = document.getElementById("apellidoPaterno");
    nombre = document.getElementById("nombre");
    departamentoSelectForm = document.getElementById("departamentoSelectForm");
    tablaFnz = document.getElementById("tablaFnz");
    tablaRH = document.getElementById("tablaRH");
    divFinanzas = document.getElementById("Finanzas");
    divRecursosHumanos = document.getElementById("Recursos Humanos");
    obtenerEmpleados()
}

async function onLogin() {
    try {
        nombreUsuario = document.getElementById("nombreUsuario").value;
        contraseña = document.getElementById("contraseña").value;
        informacion = document.getElementById("informacion");

        const response = await axios.post(
            API_IP+"autenticacion/loginInseguro",
            `nombreUsuario=${nombreUsuario}&contraseña=${contraseña}`,
            {
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                },
            }
        );
        informacion.textContent = response.data.mensaje;
        if (response.data.error == false) {
            location.href="Main.html";
        }

    } catch (error) {
        console.error(error);
        throw new Error(error);
    }
}

async function cambiarContraseña(nombreUsuario, contraseña) {
    const response = await axios.post(
        API_IP+"autenticacion/cambiarContraseña",
        `nombreUsuario=${nombreUsuario}&contraseña=${contraseña}`,
        {
            headers: {
                "Content-Type": "application/x-www-form-urlencoded",
            },
        }
    );
    if (response.data.error == false) {
        alert("Contraseña cambiada")
    }
}

async function obtenerEmpleados() {
    try {

        var defaultRH = document.createElement('option');
        defaultRH.value = 0;
        defaultRH.text = "Registrar empleado";

        var defaultFNZ = document.createElement('option');
        defaultFNZ.value = 0;
        defaultFNZ.text = "Registrar empleado";

        numerosEmpleadosRH.add(defaultRH);
        numerosEmpleadosFNZ.add(defaultFNZ);

        const response = await axios.get(
            API_IP+ "empleado/obtenerEmpleados",
            { responseType: 'text' }
        );

        const datosComoString = response.data;
        const data = JSON.parse(datosComoString);

        data.empleado.forEach(element => {
            var nuevaFila = document.createElement('tr');
            var celdaNombre = document.createElement('td');
            var celdaApellidoPaterno = document.createElement('td');
            var celdaApellidoMaterno = document.createElement('td');
            var celdaNumeroEmpleado = document.createElement('td');
            var celdaRedesSociales = document.createElement('td');
            var iconRedesSociales =  document.createElement('img');

            celdaNombre.textContent = element.nombre;
            celdaApellidoPaterno.textContent = element.apellidoPaterno;
            celdaApellidoMaterno.textContent = element.apellidoMaterno;
            celdaNumeroEmpleado.textContent = element.numeroEmpleado;

            iconRedesSociales.src= "https://upload.wikimedia.org/wikipedia/commons/thumb/5/51/Facebook_f_logo_%282019%29.svg/2048px-Facebook_f_logo_%282019%29.svg.png"
            iconRedesSociales.style.width= "50px"
            iconRedesSociales.style.height="50px"
            iconRedesSociales.style.marginLeft= "10px"
            iconRedesSociales.setAttribute("onclick", 'window.location.href = "https://www.facebook.com/"') 
            
            celdaRedesSociales.appendChild(iconRedesSociales);

            nuevaFila.appendChild(celdaNombre);
            nuevaFila.appendChild(celdaApellidoPaterno);
            nuevaFila.appendChild(celdaApellidoMaterno);
            nuevaFila.appendChild(celdaNumeroEmpleado);
            nuevaFila.appendChild(celdaRedesSociales);

            if (element.departamento == "Recursos Humanos") {
                tablaRH.appendChild(nuevaFila);

                var empleado = document.createElement('option');
                empleado.value = element.numeroEmpleado;
                empleado.text = element.numeroEmpleado;
                numerosEmpleadosRH.add(empleado);
            } else {
                tablaFnz.appendChild(nuevaFila);
                var empleado = document.createElement('option');
                empleado.value = element.numeroEmpleado;
                empleado.text = element.numeroEmpleado;
                numerosEmpleadosFNZ.add(empleado);
            }

        });

    } catch (error) {
        console.error(error);
        throw new Error(error);
    }
}

function cambiarPagina(url) {
    window.location.href = url
}

async function cargarDatosEmpleado(departamento) {
    var selectValor;
    if (departamento == 0) {
        selectValor = numerosEmpleadosRH.value
    } else {
        selectValor = numerosEmpleadosFNZ.value
    }

    if (selectValor != 0) {

        const response = await axios.get(
            API_IP+"empleado/obtenerEmpleado/" + selectValor,
            { responseType: 'text' }
        );

        const datosComoString = response.data;
        const data = JSON.parse(datosComoString);
        nombre.value = data.empleado[0].nombre
        apellidoPaterno.value = data.empleado[0].apellidoPaterno
        apellidoMaterno.value = data.empleado[0].apellidoMaterno
        numeroEmpleado.value = data.empleado[0].numeroEmpleado
        numeroEmpleado.readOnly = true
        departamentoSelectForm.value = data.empleado[0].departamento
    } else {
        nombre.value = ""
        apellidoPaterno.value = ""
        apellidoMaterno.value = ""
        numeroEmpleado.value = ""
        numeroEmpleado.readOnly = false
        departamentoSelectForm.value = ""
    }
}

async function guardarEmpleado(departamento) {
    var selectValor;
    if (departamento == 0) {
        selectValor = numerosEmpleadosRH.value
    } else {
        selectValor = numerosEmpleadosFNZ.value
    }

    if (selectValor != 0) {
        const response = await axios.put(
            API_IP+ "empleado/editar",
            {
                "apellidoMaterno": apellidoMaterno.value,
                "apellidoPaterno": apellidoPaterno.value,
                "departamento": departamentoSelectForm.value,
                "nombre": nombre.value,
                "numeroEmpleado": numeroEmpleado.value
            },
            {
                headers: {
                    "Content-Type": "application/json",
                },
            }
        );
        location.reload()
    } else {
        const response = await axios.post(
            API_IP+"empleado/registrar",
            {
                "apellidoMaterno": apellidoMaterno.value,
                "apellidoPaterno": apellidoPaterno.value,
                "departamento": departamentoSelectForm.value,
                "nombre": nombre.value,
                "numeroEmpleado": numeroEmpleado.value
            },
            {
                headers: {
                    "Content-Type": "application/json",
                },
            }
        );
        location.reload()
    }
}

async function eliminarEmpleado(departamento) {
    var selectValor;
    if (departamento == 0) {
        selectValor = numerosEmpleadosRH.value
    } else {
        selectValor = numerosEmpleadosFNZ.value
    }

    if (selectValor != 0) {
        var confirmacion = confirm("¿Deseas eliminar este empleado?");
        if (confirmacion) {
            const response = await axios.delete(
                API_IP+"empleado/eliminarEmpleado/" + numeroEmpleado.value);
        }
        location.reload()
    }
}