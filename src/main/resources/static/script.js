
console.log('El archivo "script.js" se cargó.');

//window.addEventListener('load', function () {
document.addEventListener('DOMContentLoaded', function() {

console.log('DOM Cargado');


// Obtener referencias a los elementos del DOM
const nombreInput = document.getElementById('nombre');
const apellidoInput = document.getElementById('apellido');
const dniInput = document.getElementById('dni');
const fechaIngresoInput = document.getElementById('fechaIngreso');
const calleInput = document.getElementById('calle');
const numeroInput = document.getElementById('numero');
const localidadInput = document.getElementById('localidad');
const provinciaInput = document.getElementById('provincia');

const matriculaOInput = document.getElementById('matriculaO');
const nombreOInput = document.getElementById('nombreO');
const apellidoOInput = document.getElementById('apellidoO');

const pacienteIdInput = document.getElementById('pacienteId');
const odontologoIdInput = document.getElementById('odontologoId');
const fechaHoraTurnoInput = document.getElementById('fechaHoraTurno');

const idPInput = document.getElementById('idP');
const idOInput = document.getElementById('idO');
const idTInput = document.getElementById('idT');

const listarPButton = document.getElementById('listarP');
const agregarPButton = document.getElementById('agregarP');
const buscarPButton = document.getElementById('buscarP');
const eliminarPButton = document.getElementById('eliminarP');

const listarOButton = document.getElementById('listarO');
const agregarOButton = document.getElementById('agregarO');
const buscarOButton = document.getElementById('buscarO');
const eliminarOButton = document.getElementById('eliminarO');

const listarTButton = document.getElementById('listarT');
const agregarTButton = document.getElementById('agregarT');
const buscarTButton = document.getElementById('buscarT');
const eliminarTButton = document.getElementById('eliminarT');
// Event listeners para los botones
listarPButton.addEventListener('click', listarPacientes);
agregarPButton.addEventListener('click', registrarPaciente);
buscarPButton.addEventListener('click', buscarPacientePorId);
eliminarPButton.addEventListener('click', eliminarPacientePorId);


listarOButton.addEventListener('click', listarOdontologos);
agregarOButton.addEventListener('click', registrarOdontologo);
buscarOButton.addEventListener('click', buscarOdontologoPorId);
eliminarOButton.addEventListener('click', eliminarOdontologoPorId);

listarTButton.addEventListener('click', listarTodos);
agregarTButton.addEventListener('click', guardarTurno);
buscarTButton.addEventListener('click', buscarTurnoPorId);
eliminarTButton.addEventListener('click', eliminarTurnoPorId);



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
      console.error('Error en la respuesta:', error.status);
      console.error('Contenido del error:', error.statusText);
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

function eliminarPorId(url, id) {
  fetch(url + id, {
    method: 'DELETE'
  })
    .then(response => {
      if (response.ok) {
        console.log('Elemento eliminado correctamente');
        document.getElementById('contenedorRespuesta').innerHTML = "<div>Elemento eliminado correctamente</div>";
      } else {
        console.log("ERROR " + response.status);
        document.getElementById('contenedorRespuesta').innerHTML = "<div>ERROR</div>";
        alert("ERROR " + response.status);
      }
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

function registrarPaciente() {
  // Obtener los valores de los campos de entrada
  let paciente = {
    nombre: nombreInput.value,
    apellido: apellidoInput.value,
    dni: dniInput.value,
    fechaIngreso: fechaIngresoInput.value,
    domicilio: {
        calle: calleInput.value,
        numero: numeroInput.value,
        localidad: localidadInput.value,
        provincia: provinciaInput.value,
        }
  }

  // Enviar solicitud POST al handler de agregar paciente
  enviarDatos('http://localhost:8080/pacientes/registrar', paciente);
}

function listarPacientes(){
  listar("http://localhost:8080/pacientes","");
}

function buscarPacientePorId(){

    let id = idP.value;
    listar("http://localhost:8080/pacientes/", id);
}
function eliminarPacientePorId(){

    let id3 = idP.value;
    eliminarPorId("http://localhost:8080/pacientes/eliminar/", id3);
}

function registrarOdontologo() {
  // Obtener los valores de los campos de entrada
  let odontologo = {
    matricula: matriculaOInput.value,
    nombre: nombreOInput.value,
    apellido: apellidoOInput.value,

  }

  // Enviar solicitud POST al handler de agregar paciente
  enviarDatos('http://localhost:8080/odontologos/registrar', odontologo);
}

function listarOdontologos(){
    listar("http://localhost:8080/odontologos","");
}

function buscarOdontologoPorId(){

    let id = idO.value;
    listar("http://localhost:8080/odontologos/", id);
}
function eliminarOdontologoPorId(){

    let id2 = idO.value;
    eliminarPorId("http://localhost:8080/odontologos/eliminar/", id2);
}

function guardarTurno() {
  // Obtener los valores de los campos de entrada
  let turno = {
    paciente: { id: pacienteIdInput.value },
    odontologo: { id: odontologoIdInput.value },
    fechaHoraTurno: fechaHoraTurnoInput.value,
  };
    console.log("Fecha Turno: " + fechaHoraTurno);
  // Enviar solicitud POST al handler de agregar paciente
  enviarDatos('http://localhost:8080/turnos/registrar', turno);
}

function listarTodos(){
    listar("http://localhost:8080/turnos","");
}

function buscarTurnoPorId(){

    let id = idT.value;
    listar("http://localhost:8080/turnos/", id);
}

function eliminarTurnoPorId(){

    let id = idT.value;
    eliminarPorId("http://localhost:8080/turnos/eliminar/", id);
}


/// cierre
});