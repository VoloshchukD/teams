$('.element').each(function () {

    var nameValue = $(this).find('.name').html();
    var descriptionValue = $(this).find('.description').html();
    var updateProjectId =  $(this).find('.identifier').val();

    $(this).find('.edit').click(function () {
        $('#modal').find('#updateProjectId').val(updateProjectId)
        $('#modal').find('#name').val(nameValue);
        $('#modal').find('#description').val(descriptionValue);
    });

})

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

function validateUpdateForm() {
    return validateInputs(projectCreationInputs, projectPatterns);
}

