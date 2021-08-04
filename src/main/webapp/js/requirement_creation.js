var ajax = webix.ajax().headers({
    'Content-type': 'application/json'
})

function sleep(time) {
    return new Promise((resolve) => setTimeout(resolve, time));
}

$("#task").change(function () {
    loadRequirements()
});

function loadRequirements() {
    var taskId = $("#task").val();
    if (taskId != 'null') {
        $('.table').find(".saved").remove();
        ajax.get("http://localhost:8080/async-controller?async-command=employee-requirements",
            {"technical-task-id": taskId})
            .then((response) => response.json())
            .then((data) => {
                for (let i = 0; i < data.length; i++) {
                    var html = '<tr class="saved table-primary">' +
                        '<td>' + data[i].experience + '</td>' +
                        '<td>' + data[i].salary + '</td>' +
                        '<td>' + data[i].qualification + '</td>' +
                        '<td>' + data[i].primary + '</td>' +
                        '<td>' + data[i].comment + '</td>' +
                        '<td>' +
                        '<button type="button" class="edit btn btn-secondary">'+'<fmt:message bundle="${loc}" key="local.requirement.create.edit-button"/>'+'</button>' +
                        '</td>' +
                        '<td>' +
                        '<button type="button" class="delete btn btn-danger">Delete</button>' +
                        '</td>' +
                        '</tr>';
                    $('.table tr:eq('+(i + 1)+')').before(html);
                    var savedRequirement = $('.table').find('.saved:last');
                    savedRequirement.find('.edit').click(function () {
                        savedRequirement.remove();
                    });
                    savedRequirement.find('.delete').click(function () {
                        savedRequirement.remove();
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
        '<button type="button" class="save btn btn-primary">Save</button>' +
        '</td>' +
        '<td>' +
        '<button type="button" class="drop btn btn-danger">Drop</button>' +
        '</td>' +
        '</tr>';
    $('.table').append(html);

    var addedRequirement = $('.table').find('.requirement:last');
    addedRequirement.find('.save').click(function () {

        var experience = addedRequirement.find('.experience');
        var salary = addedRequirement.find('.salary');
        var qualification = addedRequirement.find('.qualification');
        var primary = addedRequirement.find('.primary');
        var comment = addedRequirement.find('.comment');

        webix.ajax().post("http://localhost:8080/async-controller?async-command=add-requirement",
            {
                "experience": experience.find('.input1').val(),
                "salary": salary.find('.input2').val(),
                "qualification": qualification.find('.input3').val(),
                "primary": primary.find('.input4').val(),
                "comment": comment.find('.input5').val(),
                "technical-task-id": $("#task").val()
            })
            .then((response) => response.json())
            .then((data) => {
                loadRequirements();
            })
        addedRequirement.remove();

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