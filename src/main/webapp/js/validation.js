function validate(field, regex) {
    if (regex.test(field.value)) {
        field.className = 'form-control valid';
    } else {
        field.className = 'form-control invalid';
    }
}

function validateInputs(inputs, patterns) {
    let valid = true;
    inputs.forEach((input) => {
        let regex = patterns[input.attributes.id.value];
        if (regex.test(input.value) == false) {
            input.className = 'form-control invalid';
            if (valid == true) {
                document.getElementById(input.attributes.id.value).focus();
            }
            valid = false;
        }
    });
    return valid;
}