//Lee los archivos y agrega el nombre al boton de upload
var loader = function(e){
    var file = e.target.files;
    let show= "<span>Selected file: </span>"+file[0].name;

    let output = document.getElementById("selector");
    output.innerHTML = show;
    output.classList.add("active"); 
    
};

//add event Listener for input

let fileInput = document.getElementById("file");
fileInput.addEventListener("change", loader);

//when input text on fields background color changes
function changeColor(){
    let campo = document.getElementById("idAgrupador");
    campo.style.backgroundColor = "#4f4e4a";
}
