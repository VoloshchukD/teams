var projectId;
var technicalTaskId;

window.addEventListener("load", function (event){
    var url_string = window.location.href;
    var url = new URL(url_string);
    projectId = url.searchParams.get("project-id");
    technicalTaskId = url.searchParams.get("technical-task-id");

    $('.card').each(function () {
        var editForm = $(this).find('.editing');
        editForm.find('.forDeleteProjectId').val(projectId);
    });
    loadProjectUsers();
});

var ajax = webix.ajax().headers({
    'Content-type': 'application/json'
})

$('#create-task-button').click(function () {
    if(validateCreateTaskForm()) {
        webix.ajax().post("http://localhost:8080/async-controller?command=add-task",
            {
                "name": document.getElementById("createName").value,
                "details": document.getElementById("createDetails").value,
                "hours": document.getElementById("createHours").value,
                "project-id": projectId,
                "id": $("#developer").val()
            })
            .then(()  => {
                redirectBack();
            })
        $('#modal').hide();
        $('body').removeClass('modal-open');
        $('.modal-backdrop').remove();
    }
})

$('#finish-project-modal-button').click(function () {
    $('#updateProjectId').val(projectId);
    $('#updateTechnicalTaskId').val(technicalTaskId);
})

function loadProjectUsers() {
    ajax.get("http://localhost:8080/async-controller?command=load-project-users&user-role=developer",
        {"project-id": projectId}).then((response) => response.json())
        .then((data) => {
            $('#developer').empty();
            $('#updateDeveloper').empty();
            for (let i = 0; i < data.length; i++) {
                fill($('#developer'), data, i);
                fill($('#updateDeveloper'), data, i);
            }
        })
}

function fill(holder, data, i) {
    holder.append($('<option>', {
        value: data[i].id,
        text: data[i].forename + ' ' + data[i].surname
    }));
}

$('.card').each(function () {
    initCardButtons($(this));

    var trackButton = $(this).find('.track');
    var hours = $(this).find('.hours');
    var panel = $(this).find('.panel');
    var hoursVal = $(this).find('.val').html();
    var taskId = $(this).find('.identifier').val();


    $(this).find('.input').val(hoursVal);
    trackButton.click(function () {
        hours.hide();
        panel.show();
    })

    var saveButton = $(this).find('.save');
    var hoursInput = $(this).find('.hour');
    saveButton.click(function () {
        webix.ajax().post("http://localhost:8080/async-controller?command=update-task-hours",
            {
                "hours": hoursInput.val(),
                "task-id": taskId
            })
            .then(() => {
                redirectBack();
            })
        hours.show();
        panel.hide();
    })

})

function initCardButtons(card){

    var backButton = card.find('.back');
    var forwardButton = card.find('.forward');
    var currentDiv = card.parent().attr("id");
    var taskId = card.find('.identifier').val();

    if (currentDiv == 'col3') {
        forwardButton.hide();
        backButton.show();
    } else if (currentDiv == 'col1') {
        backButton.hide();
        forwardButton.show();
    } else if (currentDiv == 'col2') {
        backButton.show();
        forwardButton.show();
    }

    backButton.click(function () {
        var targetDiv;
        if (currentDiv == 'col3') {
            targetDiv = '#col2';
        } else if (currentDiv == 'col2') {
            targetDiv = '#col1';
        }

        updateTaskStatus(taskId, targetDiv);
        jQuery(card).detach().appendTo(targetDiv)
        initCardButtons(card)
    })

    forwardButton.click(function () {
        var targetDiv;
        if (currentDiv == 'col1') {
            targetDiv = '#col2';
        } else if (currentDiv == 'col2') {
            targetDiv = '#col3';
        }

        updateTaskStatus(taskId, targetDiv);
        jQuery(card).detach().appendTo(targetDiv)
        initCardButtons(card)
    })

    function updateTaskStatus(taskId, target) {
        var status ;
        if (target == '#col1') {
            status = 'TO_DO';
        } else if (target == '#col2') {
            status = 'IN_PROGRESS';
        } else if (target == '#col3') {
            status = 'DONE';
        }

        webix.ajax().post("http://localhost:8080/async-controller?command=update-task-status",
            {
                "status": status,
                "task-id": taskId
            })
    }

}

$('.card').each(function () {
    var card = $(this);

    $(this).find('.edit').click(function () {
        var nameValue = card.find('.name').html();
        var detailsValue = card.find('.details').html();
        var updateTaskId =  card.find('.identifier').val();
        var plannedTime = card.find('.planned').html();

        $('#updateModal').find('#updateTaskId').val(updateTaskId);
        $('#updateModal').find('#updateName').val(nameValue);
        $('#updateModal').find('#updateDetails').val(detailsValue);
        $('#updateModal').find('#updateHours').val(plannedTime);
        $('#updateModal').find('#forUpdateProjectId').val(projectId);
        $('#updateModal').find('#forUpdateTechnicalTaskId').val(technicalTaskId);
    });

    $(this).find('.delete').click(function () {
        var deleteTaskId =  card.find('.identifier').val();
        webix.ajax().post("http://localhost:8080/async-controller?command=delete-task",
            {
                "task-id": deleteTaskId
            })
            .then(()  => {
                redirectBack();
            })
    });

})

function redirectBack(){
    var adress = "http://localhost:8080/controller?command=to-tasks&project-id=" + projectId
        + "&technical-task-id=" + technicalTaskId;
    window.location.href = adress;
}

var createInputs;
var updateInputs;

var createPatterns;
var updatePatterns;

const nameRegex = new RegExp(document.getElementById('regex-name').textContent);
const detailsRegex = new RegExp(document.getElementById('regex-details').textContent);
const hoursRegex = new RegExp(document.getElementById('regex-hours').textContent);

window.addEventListener("load", function (event) {

    updateInputs = document.querySelectorAll('.update-form');
    createInputs = document.querySelectorAll('.create-form');

    createPatterns= {
        createName: nameRegex,
        createDetails: detailsRegex,
        createHours: hoursRegex
    };

    updatePatterns = {
        updateName: nameRegex,
        updateDetails: detailsRegex,
        updateHours: hoursRegex
    };

    createInputs.forEach((input) => {
        input.addEventListener('keyup', (e) => {
            validate(e.target, createPatterns[e.target.attributes.id.value]);
        });
    });
    updateInputs.forEach((input) => {
        input.addEventListener('keyup', (e) => {
            validate(e.target, updatePatterns[e.target.attributes.id.value]);
        });
    });

});

function validateCreateTaskForm() {
    return validateInputs(createInputs, createPatterns);
}

function validateUpdateTaskForm() {
    return validateInputs(updateInputs, updatePatterns);
}



