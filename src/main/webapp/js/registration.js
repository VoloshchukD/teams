var inputs;

var textArea;

var patterns;

window.addEventListener("load", function (event) {

    const emailRegex = new RegExp(document.getElementById('regex-email').textContent);

    const passwordRegex = new RegExp(document.getElementById('regex-password').textContent);

    const forenameRegex = new RegExp(document.getElementById('regex-forename').textContent);

    const surnameRegex = new RegExp(document.getElementById('regex-surname').textContent);

    const companyRegex = new RegExp(document.getElementById('regex-company').textContent);

    const positionRegex = new RegExp(document.getElementById('regex-position').textContent);

    const experienceRegex = new RegExp(document.getElementById('regex-experience').textContent);

    const salaryRegex = new RegExp(document.getElementById('regex-salary').textContent);

    const primaryRegex = new RegExp(document.getElementById('regex-primary').textContent);

    const skillsRegex = new RegExp(document.getElementById('regex-skills').textContent);

    inputs = document.querySelectorAll('.form-control');

    patterns = {
        email: emailRegex,
        password: passwordRegex,
        forename: forenameRegex,
        surname: surnameRegex,
        company: companyRegex,
        position: positionRegex,
        experience: experienceRegex,
        salary: salaryRegex,
        primary: primaryRegex,
        skills: skillsRegex
    };

    inputs.forEach((input) => {
        input.addEventListener('keyup', (e) => {
            validate(e.target, patterns[e.target.attributes.id.value]);
        });
    });

    function validate(field, regex) {
        if (regex.test(field.value)) {
            field.className = 'form-control valid';
        } else {
            field.className = 'form-control invalid';
        }
    }

});

function validateForm() {
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



