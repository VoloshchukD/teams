var ajax = webix.ajax().headers({
    'Content-type': 'application/json'
})

const requirementRowHeader = '<tr class="requirement" ><th scope="row">';

const requirementRowBody = '</th><td class="experience" ></td><td class="salary" ></td>' +
    '<td class="qualification" ></td><td class="primary" ></td><td class="comment" ></td></tr>';

$('.requirements').each(function () {

    var details = $(this).find('.details');
    var hidden = $(this).find('.hide');
    var table = $(this).find('.table');
    var tableDiv = $(this).find('.list');
    var technicalTaskId = $(this).find('.identifier').val();

    details.click(function () {
        $(tableDiv).show();
        $(details).hide();
        $(hidden).show();
        ajax.get("http://localhost:8080/async-controller?command=employee-requirements",
            {"technical-task-id": technicalTaskId})
            .then((response) => response.json())
            .then((data) => {
                for (let i = 0; i < data.length; i++) {
                    var html = requirementRowHeader + (i + 1);
                    html += requirementRowBody;
                    $(table).append(html);

                    var element = $('.table').find('.requirement:last');
                    element.find('.qualification').text(data[i].qualification);
                    element.find('.salary').text(data[i].salary);
                    element.find('.experience').text(data[i].experience);
                    element.find('.primary').text(data[i].primary);
                    element.find('.comment').text(data[i].comment);
                }
            })
    });

    hidden.click(function () {
        $(details).show();
        $(hidden).hide();
        $(table).find("tr:gt(0)").remove();
        $(tableDiv).hide();
    });

});

$('.element').each(function () {

    var nameValue = $(this).find('.name').html();
    var overviewValue = $(this).find('.overview').html();
    var updateTaskId =  $(this).find('.identifier').val();

    $(this).find('.edit').click(function () {
        $('#modal').find('#updateTaskId').val(updateTaskId)
        $('#modal').find('#name').val(nameValue);
        $('#modal').find('#overview').val(overviewValue);
    });

})

var technicalTaskUpdationInputs;

var technicalTaskUpdationPatterns;

window.addEventListener("load", function (event) {

    const nameRegex = new RegExp(document.getElementById('regex-name').textContent);

    const overviewRegex = new RegExp(document.getElementById('regex-overview').textContent);

    technicalTaskUpdationInputs = document.querySelectorAll('.form-control');

    technicalTaskUpdationPatterns = {
        name: nameRegex,
        overview: overviewRegex
    };

    technicalTaskUpdationInputs.forEach((input) => {
        input.addEventListener('keyup', (e) => {
            validate(e.target, technicalTaskUpdationPatterns[e.target.attributes.id.value]);
        });
    });


});

function validateTaskForm() {
    return validateInputs(technicalTaskUpdationInputs, technicalTaskUpdationPatterns);
}