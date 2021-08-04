var projectId;

window.addEventListener("load", function (event){
    var url_string = window.location.href;
    var url = new URL(url_string);
    projectId = url.searchParams.get("project-id");
});

var ajax = webix.ajax().headers({
    'Content-type': 'application/json'
})

$('#create-task-button').click(function () {
    webix.ajax().post("http://localhost:8080/async-controller?async-command=add-task",
        {
            "name": document.getElementById("name").value,
            "details": document.getElementById("details").value,
            "hours": document.getElementById("hours").value,
            "project-id": projectId,
            "user-id": $("#developer").val()
        })
        .then((response) => response.json())
        .then((data) => {
            var adress = "http://localhost:8080/controller?command=to-tasks&project-id=" + projectId;
            window.location.href = adress;
        })
    $('#modal').hide();
    $('body').removeClass('modal-open');
    $('.modal-backdrop').remove();
})

$('#create-task-modal-button').click(function () {
    ajax.get("http://localhost:8080/async-controller?async-command=load-project-users",
        {"project-id": projectId}).then((response) => response.json())
        .then((data) => {
            for (let i = 0; i < data.length; i++) {
                $('#developer').append($('<option>', {
                    value: data[i].id,
                    text: data[i].forename + ' ' + data[i].surname
                }));
            }
        })
})

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
        webix.ajax().post("http://localhost:8080/async-controller?async-command=update-task-hours",
            {
                "hours": hoursInput.val(),
                "task-id": taskId
            })
            .then((response) => response.json())
            .then((data) => {
                var adress = "http://localhost:8080/controller?command=to-tasks&project-id=" + projectId;
                window.location.href = adress;
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
            status = 'ToDo';
        } else if (target == '#col2') {
            status = 'InProgress';
        } else if (target == '#col3') {
            status = 'Done';
        }

        webix.ajax().post("http://localhost:8080/async-controller?async-command=update-task-status",
            {
                "status": status,
                "task-id": taskId
            })
            .then((response) => response.json())
            .then((data) => {

            })
    }

}