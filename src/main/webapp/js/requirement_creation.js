window.addEventListener("load", function (event) {
    var url_string = window.location.href;
    var url = new URL(url_string);
    var technicalTaskId = url.searchParams.get("technical-task-id");
    if (technicalTaskId != null) {
        $('#task option[value=' + technicalTaskId + ']').prop('selected', true);
        loadRequirements();
    }
});

const experienceRegex = new RegExp(document.getElementById('regex-experience').textContent);
const salaryRegex = new RegExp(document.getElementById('regex-salary').textContent);
const commentRegex = new RegExp(document.getElementById('regex-comment').textContent);
const qualificationRegex = new RegExp(document.getElementById('regex-qualification').textContent);
const primaryRegex = new RegExp(document.getElementById('regex-primary').textContent);

var ajax = webix.ajax().headers({
    'Content-type': 'application/json'
})

$("#task").change(function () {
    loadRequirements();
});

function loadRequirements() {
    var taskId = $("#task").val();
    $('#updateModal').find('#forUpdateTechnicalTaskId').val(taskId);
    if (taskId != 'null') {
        $('.table').find(".saved").remove();
        ajax.get("http://localhost:8080/async-controller?command=employee-requirements",
            {"technical-task-id": taskId})
            .then((response) => response.json())
            .then((data) => {
                for (let i = 0; i < data.length; i++) {
                    var html = '<tr class="saved table-primary">' +
                        '<td class="experience" ></td>' +
                        '<td class="salary" ></td>' +
                        '<td class="qualification" ></td>' +
                        '<td class="primary" ></td>' +
                        '<td class="comment" ></td>' +
                        '<td>' +
                        '<button type="button" class="edit btn btn-secondary" data-toggle="modal" data-target="#updateModal" ><i class="fa fa-pencil-square-o" aria-hidden="true"></i></i></button>' +
                        '</td>' +
                        '<td>' +
                        '<button type="button" class="delete btn btn-danger"><i class="fa fa-trash" aria-hidden="true"></i></button>' +
                        '</td>' +
                        '</tr>';
                    $('.table tr:eq(' + (i + 1) + ')').before(html);

                    var element = $('.table').find('.saved:last');
                    element.find('.qualification').text(data[i].qualification);
                    element.find('.salary').text(data[i].salary);
                    element.find('.experience').text(data[i].experience);
                    element.find('.primary').text(data[i].primary);
                    element.find('.comment').text(data[i].comment);

                    element.find('.edit').click(function () {
                            $('#updateModal').find('#updateRequirementId').val(data[i].id);
                            $('#updateModal').find('#updateExperience').val(data[i].experience);
                            $('#updateModal').find('#updateSalary').val(data[i].salary);
                            $('#updateModal').find('#updateQualification').val(data[i].qualification);
                            $('#updateModal').find('#updatePrimary').val(data[i].primary);
                            $('#updateModal').find('#updateComment').val(data[i].comment);
                        });

                    element.find('.delete').click(function () {
                            webix.ajax().post("http://localhost:8080/async-controller?command=delete-requirement",
                                {
                                    "id": data[i].id
                                })
                                .then((response) => response.json())
                                .then((data) => {
                                    element.remove();
                                })
                        });
                }
            })
    }

}

$('#add').click(function () {
    var html = '<tr class="requirement">' +
        '<td class="experience" ><input type="text" class="input1 align form-control" /></td>' +
        '<td class="salary" ><input type="text" class="input2 align form-control" /></td>' +
        '<td class="qualification" ><input type="text" class="input3 form-control" /></td>' +
        '<td class="primary" ><input type="text" class="input4 form-control" /></td>' +
        '<td class="comment" > <textarea class="input5 form-control" aria-label="With textarea" style="height: 10px;" ></textarea></td>' +
        '<td>' +
        '<button type="button" class="save btn btn-primary"><i class="fa fa-floppy-o" aria-hidden="true"></i></button>' +
        '</td>' +
        '<td>' +
        '<button type="button" class="drop btn btn-danger"><i class="fa fa-minus-square" aria-hidden="true"></i></button>' +
        '</td>' +
        '</tr>';
    $('.table').append(html);

    var addedRequirement = $('.table').find('.requirement:last');
    addedRequirement.find('.save').click(function () {

        var experience = addedRequirement.find('.experience .input1').val();
        var salary = addedRequirement.find('.salary .input2').val();
        var qualification = addedRequirement.find('.qualification .input3').val();
        var primary = addedRequirement.find('.primary .input4').val();
        var comment = addedRequirement.find('.comment .input5').val();

        if (experienceRegex.test(experience)
            && salaryRegex.test(salary)
            && qualificationRegex.test(qualification)
            && primaryRegex.test(primary)
            && commentRegex.test(comment)) {
            webix.ajax().post("http://localhost:8080/async-controller?command=add-requirement",
                {
                    "experience": experience,
                    "salary": salary,
                    "qualification": qualification,
                    "primary": primary,
                    "comment": comment,
                    "technical-task-id": $("#task").val()
                })
                .then((response) => response.json())
                .then((data) => {
                    loadRequirements();
                })
            addedRequirement.remove();
        } else {
            addedRequirement.attr('class', 'requirement table-danger');
        }

    });
    addedRequirement.find('.drop').click(function () {
        addedRequirement.remove();
    });

});

$('.requirement').each(function () {

    var saveButton = $(this).find('.save');
    var dropButton = $(this).find('.drop');


    saveButton.click(function () {
        loadRequirements()
        requirement.remove();
    });

    dropButton.click(function () {
        requirement.remove();
    });

});

var requirementUpdationInputs;
var requirementUpdationPatterns;

window.addEventListener("load", function (event) {

    requirementUpdationInputs = document.querySelectorAll('.update-form');

    requirementUpdationPatterns = {
        updateExperience: experienceRegex,
        updateSalary: salaryRegex,
        updateQualification: commentRegex,
        updatePrimary: qualificationRegex,
        updateComment: primaryRegex
    };

    requirementUpdationInputs.forEach((input) => {
        input.addEventListener('keyup', (e) => {
            validate(e.target, requirementUpdationPatterns[e.target.attributes.id.value]);
        });
    });

});

function validateRequirementForm() {
    return validateInputs(requirementUpdationInputs, requirementUpdationPatterns);
}