
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



const strength = {
    0: "Worst",
    1: "Bad",
    2: "Weak",
    3: "Good",
    4: "Strong"
}

const password = document.getElementById('password');
const meter = document.getElementById('password-strength-meter');
const text = document.getElementById('password-strength-text');

password.addEventListener('input', updateStrength);

function updateStrength() {
    const val = password.value;
    const result = zxcvbn(val);

    // Update the password strength meter
    meter.value = result.score;

    // Update the text indicator
    if (val !== "") {
        text.innerHTML = strength[result.score]
    } else {
        text.innerHTML = "";
    }
}




function mySearchFunction() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("search");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
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



















