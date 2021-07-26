var signInInputs;

var signInPatterns;

window.addEventListener("load", function (event) {

    const emailRegex = new RegExp(document.getElementById('regex-email').textContent);

    const passwordRegex = new RegExp(document.getElementById('regex-password').textContent);

    signInInputs = document.querySelectorAll('.form-control');

    signInPatterns = {
        email: emailRegex,
        password: passwordRegex
    };

    signInInputs.forEach((input) => {
        input.addEventListener('keyup', (e) => {
            validate(e.target, signInPatterns[e.target.attributes.id.value]);
        });
    });

});

function validateSignInForm() {
    return validateInputs(signInInputs, signInPatterns);
}