
console.log('El archivo "script.js" se cargó.');

//window.addEventListener('load', function () {
document.addEventListener('DOMContentLoaded', function() {

console.log('DOM Cargado');


// Obtener referencias a los elementos del DOM
const nombreInput = document.getElementById('nombre');
const apellidoInput = document.getElementById('apellido');
const dniInput = document.getElementById('dni');
const fechaAltaInput = document.getElementById('fechaIngreso');
const calleInput = document.getElementById('calle');
const numeroInput = document.getElementById('numero');
const ciudadInput = document.getElementById('localidad');
const ciudadInput = document.getElementById('provincia');

const nombreOInput = document.getElementById('matriculaO');
const apellidoOInput = document.getElementById('nombreO');
const matriculaInput = document.getElementById('apellidoO');

const pacienteIdInput = document.getElementById('pacienteId');
const odontologoIdInput = document.getElementById('odontologoId');
const fechaTurnoInput = document.getElementById('fechaHoraTurno');

const idPInput = document.getElementById('idP');
const idOInput = document.getElementById('idO');
const idTInput = document.getElementById('idT');

const listarPButton = document.getElementById('listarP');
const agregarPButton = document.getElementById('agregarP');
const buscarPButton = document.getElementById('buscarP');

const listarOButton = document.getElementById('listarO');
const agregarOButton = document.getElementById('agregarO');
const buscarOButton = document.getElementById('buscarO');

const listarTButton = document.getElementById('listarT');
const agregarTButton = document.getElementById('agregarT');
const buscarTButton = document.getElementById('buscarT');

// Event listeners para los botones
listarPButton.addEventListener('click', listarPacientes);
agregarPButton.addEventListener('click', agregarPaciente);
buscarPButton.addEventListener('click', function(event) {
  event.preventDefault(); // Evitar comportamiento predeterminado del botón
  buscarPaciente();
});


listarOButton.addEventListener('click', listarOdontologos);
agregarOButton.addEventListener('click', agregarOdontologo);
buscarOButton.addEventListener('click', buscarOdontologo);

listarTButton.addEventListener('click', listarTurnos);
agregarTButton.addEventListener('click', agregarTurno);
buscarTButton.addEventListener('click', buscarTurno);


// FUNCIONES GENERALES aplicables a todas las entities.

// Funciones para enviar los datos vía POST utilizando Fetch
function enviarDatos(url, datos) {
  fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  })
  .then(response => {
    if (response.ok) {
      console.log('Datos enviados correctamente');
      // Aquí puedes realizar alguna acción adicional después de enviar los datos
    } else {
      throw new Error('Error al enviar los datos');
    }
  })
  .catch(error => {
    console.error('Error:', error);
  });
}

function listar(url, id) {
  fetch(url + id)
    .then(response => {
      if (response.ok) {
        console.log('Datos recibidos correctamente');
        return response.json(); // Convertir la respuesta a JSON
      } else {
        //throw new Error('Error al realizar la solicitud');
        console.log("ERROR " + response.status);
        document.getElementById('contenedorRespuesta').innerHTML = "<div>ERROR</div>";
        alert("ERROR " + response.status);
      }
    })
    .then(data => {
      // Procesar los datos y generar contenido HTML dinámicamente
      const html = generarHTML(data);
      // Agregar el contenido HTML al elemento deseado en el DOM
      document.getElementById('contenedorRespuesta').innerHTML = html;
    })
    .catch(error => {
      console.error('Error:', error);
      document.getElementById('contenedorRespuesta').innerHTML = "<div>ERROR</div>";

    });
}

// Función para generar contenido HTML dinámicamente a partir de los datos
function generarHTML(data) {
  let html = '';

  if (Array.isArray(data)) {

  data.forEach(obj => {
    for (let key in obj) {
      console.log(obj[key]);
      if (typeof obj[key] === "object") { // Utilizar typeof para verificar el tipo de objeto
        const objeto = obj[key];
        for (let key2 in objeto) {
          html += `<div>${key2}: ${objeto[key2]}</div>`;
        }
      } else {
        html += `<div>${key}: ${obj[key]}</div>`;
      }
    }
  });
  } else {


      for (let key in data) {

        if (typeof data[key] === "object") { // Utilizar typeof para verificar el tipo de objeto
          const objeto = data[key];
          for (let key2 in objeto) {
            html += `<div>${key2}: ${objeto[key2]}</div>`;
          }
        } else {
          html += `<div>${key}: ${data[key]}</div>`;
        }
      }


  }

  return html;
}


// FUNCIONES ESPECIFICAS ligadas a los botones.

function agregarPaciente() {
  // Obtener los valores de los campos de entrada
  let paciente = {
    nombre: nombreInput.value,
    apellido: apellidoInput.value,
    dni: dniInput.value,
    fechaIngreso: fechaIngresoInput.value,
    domicilio: {
        calle: calleInput.value,
        numero: numeroInput.value,
        localidad: localidadInput.value
        provincia: provinciaInput.value
        }
  }

  // Enviar solicitud POST al handler de agregar paciente
  enviarDatos('http://localhost:8080/pacientes/agregar', paciente);
}

function listarPacientes(){
  listar("http://localhost:8080/pacientes","");
}

function buscarPaciente(){

    let id = idP.value;
    listar("http://localhost:8080/pacientes/", id);
}

function agregarOdontologo() {
  // Obtener los valores de los campos de entrada
  let odontologo = {
    matricula: matriculaInput.value
    nombre: nombreOInput.value,
    apellido: apellidoOInput.value,

  }

  // Enviar solicitud POST al handler de agregar paciente
  enviarDatos('http://localhost:8080/odontologos/agregar', odontologo);
}

function listarOdontologos(){
    listar("http://localhost:8080/odontologos","");
}

function buscarOdontologo(){

    let id = idO.value;
    listar("http://localhost:8080/odontologos/", id);
}

function agregarTurno() {
  // Obtener los valores de los campos de entrada
  let turno = {
    paciente: pacienteIdInput.value,
    odontologo: odontologoIdInput.value,
    fechaHoraTurno: fechaHoraTurnoInput.value
  }
    console.log("Fecha Turno: " + fechaHoraTurno)
  // Enviar solicitud POST al handler de agregar paciente
  enviarDatos('http://localhost:8080/turnos/nuevo', turno);
}

function listarTurnos(){
    listar("http://localhost:8080/turnos","");
}

function buscarTurno(){

    let id = idT.value;
    listar("http://localhost:8080/turnos/", id);
}


/// cierre
});