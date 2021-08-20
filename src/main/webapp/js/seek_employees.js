var ajax = webix.ajax().headers({
    'Content-type': 'application/json'
})

var projectId;

window.addEventListener("load", function (event) {
    var url_string = window.location.href;
    var url = new URL(url_string);
    projectId = url.searchParams.get("project-id");
    if (projectId != null) {
        $('#projects option[value=' + projectId + ']').prop('selected', true);
        loadProjectRequirements();
    }
});

$("#projects").change(function () {
    if ($("#projects").val() != "null") {
        projectId = $("#projects").val();
        loadProjectRequirements();
    }
});

const cardHeader = '<div class="employee element col-4 card rounded shadow-sm border-0">\n' +
    '<div class="card-body p-0"><div class="bg-primary px-5 py-4 text-center card-img-top"><img src="';
const cardHeader2 = '" alt="..." width="100" class="rounded-circle mb-2 img-thumbnail d-block mx-auto">\n' +
    '<h5 class="text-white mb-0 names">';
const space = ' ';
const cardHeader3 = '</h5><p class="small text-white mb-0 primary">'
const cardHeader4 = '</p></div><div class="p-3 d-flex justify-content-center">\n' +
    '<ul class="list-inline mb-0"><li class="list-inline-item text-center">\n' +
    '<h5 class="font-weight-bold mb-0 d-block experience">'
const cardHeader5 = '</h5><small class="text-muted"><i class="fa fa-graduation-cap" aria-hidden="true">' +
    '</i></small></li><li class="list-inline-item text-center">' +
    '<h5 class="font-weight-bold mb-0 d-block mr-3 pr-3 salary">';
const cardHeader6 = '</h5><small class="text-muted"><i class="fa fa-usd" aria-hidden="true"></i></small>\n' +
    '</li></ul></div><div class="pb-1 px-2 d-flex justify-content-center">\n' +
    '<ul class="list-inline mb-0"><li class="text-center"><small class="text-muted skills">';
const cardFooter = '</small></li></ul></div><hr><div class="d-flex justify-content-center mb-2">'

const deleteButton = '<button type="button" class="delete btn btn-danger">' +
    '<i class="fa fa-ban" aria-hidden="true"></i></button>';

const addButton = '<button type="button" class="add btn btn-primary"><i class="fa fa-plus"></i></button>';

const closePart = '</div></div></div>';

function createHtmlCard(data, withDelete) {
    var card = cardHeader + data.avatar + cardHeader2 + cardHeader3
        + cardHeader4 + cardHeader5 + cardHeader6 + cardFooter;
    if (withDelete) {
        card += deleteButton;
    } else {
        card += addButton;
    }
    card += closePart;
    return card;
}

function fillHtmlCard(card, data) {
    card.find('.names').text(data.forename + space + data.surname);
    card.find('.salary').text(data.salary);
    card.find('.experience').text(data.experience);
    card.find('.primary').text(data.primary);
    card.find('.skills').text(data.skills);
}

$("#users").click(function () {
    $('#form3').show();
    $('.employee').each(function () {
        $(this).remove();
    })
    ajax.get("http://localhost:8080/async-controller?command=load-project-users&user-role=all",
        {"project-id": projectId}).then((response) => response.json())
        .then((data) => {
            configNoneLabel(data);
            for (let i = 0; i < data.length; i++) {
                var html = createHtmlCard(data[i], true);
                $('.employees').append(html);
                var employee = $('.employees').find('.employee:last');
                fillHtmlCard(employee, data[i]);

                employee.find('.delete').click(function () {
                    ajax.get("http://localhost:8080/async-controller?command=delete-employee",
                        {
                            "user-id": data[i].id,
                            "project-id": projectId
                        })
                        .then((response) => {
                            employee.remove();
                        });
                });
            }
            paginate();
        })
})

$("#search").click(function () {
    $('.employee').each(function () {
        $(this).remove();
    })
    $('#form3').show();
    ajax.get("http://localhost:8080/async-controller?command=search-employees",
        {
            "primary": $("#values").val(),
            "project-id": projectId
        })
        .then((response) => response.json())
        .then((data) => {

            configNoneLabel(data);

            for (let i = 0; i < data.length; i++) {
                var html = createHtmlCard(data[i], false);
                $('.employees').append(html);
                var employee = $('.employees').find('.employee:last');
                fillHtmlCard(employee, data[i]);

                $('.employees').find('.add:last').click(function () {
                    $('.employee').each(function () {
                        $(this).remove();
                    })
                    $('#form3').hide();
                    webix.ajax().post(
                        "http://localhost:8080/async-controller" +
                        "?command=add-employee",
                        {
                            "user-id": data[i].id,
                            "project-id": projectId
                        })
                });
            }

            paginate();

        })

})

const requirementHeader = '<tr class="requirement"><th scope="row">';

const requirementBody = '</th>' +
    '<td class="experience" ></td>' +
    '<td class="salary" ></td>' +
    '<td class="qualification" ></td>' +
    '<td class="primary" ></td>' +
    '<td class="comment" ></td>' +
    '<td>' +
    '<button type="button" class="seek btn btn-primary">' +
    '<i class="fa fa-search" aria-hidden="true"></i></button>' +
    '</td>'
'</tr>';

function loadProjectRequirements() {
    $('#requirements').find("tr:gt(0)").remove();
    ajax.get(
        "http://localhost:8080/async-controller?command=load-project-requirements",
        {
            "project-id": projectId
        })
        .then((response) => response.json())
        .then((data) => {
                for (let i = 0; i < data.length; i++) {
                    var html = requirementHeader + (i + 1) + requirementBody;
                    $('#requirements').append(html);

                    var requirement = $('#requirements').find('.requirement:last');
                    requirement.find('.qualification').text(data[i].qualification);
                    requirement.find('.salary').text(data[i].salary);
                    requirement.find('.experience').text(data[i].experience);
                    requirement.find('.primary').text(data[i].primary);
                    requirement.find('.comment').text(data[i].comment);
                    var seekEmployeesButton = requirement.find('.seek');

                    seekEmployeesButton.click(function () {
                        $('.employee').each(function () {
                            $(this).remove();
                        })
                        $('#form3').show();
                        ajax.get("http://localhost:8080/async-controller?command=seek-employees",
                            {
                                "salary": data[i].salary,
                                "experience": data[i].experience,
                                "primary": data[i].primary,
                                "project-id": projectId
                            })
                            .then((response) => response.json())
                            .then((data) => {
                                configNoneLabel(data);
                                for (let i = 0; i < data.length; i++) {
                                    var html = createHtmlCard(data[i], false);
                                    $('.employees').append(html);
                                    var employee = $('.employees').find('.employee:last');
                                    fillHtmlCard(employee, data[i]);

                                    $('.employees').find('.add:last').click(function () {
                                        $('.employee').each(function () {
                                            $(this).remove();
                                        })
                                        $('#form3').hide();
                                        webix.ajax().post(
                                            "http://localhost:8080/async-controller" +
                                            "?command=add-employee",
                                            {
                                                "user-id": data[i].id,
                                                "project-id": projectId
                                            })
                                    });
                                }
                            })
                    });
                }
            }
        )
}

function configNoneLabel(data) {
    if (data.length == 0) {
        $('#none').show();
    } else {
        $('#none').hide();
    }
}