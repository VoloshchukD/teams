var inputs;

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

});

function validateSignUpForm() {
    return validateInputs(inputs, patterns);
}



