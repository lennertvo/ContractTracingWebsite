document.addEventListener("blur", checkField, true);

document.addEventListener("submit", finalValidation, false);
const textMeter = document.getElementById("password-strength-text")


function finalValidation(event) {
    let fields = event.target.elements;
    let error, hasErrors;
    for (let i = 0; i < fields.length; i++) {
        error = hasError(fields[i]);
        if (error) {
            showError(fields[i], error);
            if (!hasErrors) {
                hasErrors = fields[i];
            }
        }
    }
    if (hasErrors) {
        event.preventDefault();
        hasErrors.focus();
    }
}

function checkField(event) {
    let error = hasError(event.target);
    if (error) {
        showError(event.target, error);
    } else {
        removeError(event.target);
    }
}

function hasError(field) {
    if (field.disabled || field.type === "file" || field.type === "submit") {
        return;
    }
    let validity = field.validity;
    if (validity === null || validity.valid) {
        return;
    }
    if (validity.valueMissing) {

        return "Please fill out a value"
    }
    if (validity.typeMismatch) {
        if (field.id === "email") {
            return "Give a valid e-mail"
        }
        if (field.id === "phoneNumber") {
            return "Give a valid phonenumber"
        }
        return "Please use the correct input type";
    }
    if (validity.patternMismatch) {
        if (field.type === "text") {
            if (field.id === "useridLogIn" || field.id === "userid") {
                return "Your userid must contain at least 4 characters";
            }
            if (field.id === "email") {
                return "Give a valid e-mail";
            }

        }

    }

    //return "Please complete the form correct";
}

function removeError(field) {
    let id = field.id;
    let message = document.getElementById("errorFor-" + id);
    if (message != null && message.classList != null) {

        message.innerText = "";
        message.classList.add("hidden");
    }

}

function showError(field, error) {
    let id = field.id;
    if (!id) {
        return;
    }
    let message = document.getElementById("errorFor-" + id);
    message.classList.remove("hidden");
    message.innerHTML = error;
}