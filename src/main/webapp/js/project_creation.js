var projectCreationInputs;

var projectPatterns;

window.addEventListener("load", function (event) {

    const nameRegex = new RegExp(document.getElementById('regex-name').textContent);

    const descriptionRegex = new RegExp(document.getElementById('regex-description').textContent);

    projectCreationInputs = document.querySelectorAll('.form-control');

    projectPatterns = {
        name: nameRegex,
        description: descriptionRegex
    };

    projectCreationInputs.forEach((input) => {
        input.addEventListener('keyup', (e) => {
            validate(e.target, projectPatterns[e.target.attributes.id.value]);
        });
    });

});

function validateProjectForm() {
    return validateInputs(projectCreationInputs, projectPatterns);
}