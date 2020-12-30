
const alertDangers = document.getElementsByClassName("alert-danger");
const successMessages = document.getElementsByClassName("alert-success");

if(alertDangers !== null) {
    for(let i = 0; i < alertDangers.length; i++) {
        alertDangers[i].addEventListener("click", p => alertDangers[i].remove())
    }
}

if(successMessages !== null) {
    for(let i = 0; i < successMessages.length; i++) {
        successMessages[i].addEventListener("click", p => successMessages[i].remove())
    }
}


















/*
const alerDangerP = document.getElementById("alert-danger");

if(alerDangerP !== null) {
    alerDangerP.addEventListener("click", p => {
        alerDangerP.remove();
    })
}
const alertDanger2p = document.getElementById("alert-danger1");

if(alertDanger2p !== null) {
    alertDanger2p.addEventListener("click", p => alertDanger2p.remove());
}
*/



















