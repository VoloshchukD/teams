var ajax = webix.ajax().headers({
    'Content-type': 'application/json'
})

var projectId;

window.addEventListener("load", function (event){
    var url_string = window.location.href;
    var url = new URL(url_string);
    projectId = url.searchParams.get("project-id");
    if (projectId != null) {
        $('#projects option[value=' + projectId + ']').prop('selected', true);
        loadProjectRequirements();
    }
});

$("#projects").change(function () {
    projectId = $("#projects").val();
    loadProjectRequirements();
});

function loadProjectRequirements() {
    $('#requirements').find("tr:gt(0)").remove();
    ajax.get("http://localhost:8080/async-controller?async-command=load-project-requirements",
        {
            "project-id": projectId
        })
        .then((response) => response.json())
        .then((data) => {
                for (let i = 0; i < data.length; i++) {
                    var html = '<tr class="requirement">' +
                        '<th scope="row">' + (i+1) + '</th>' +
                        '<td>' + data[i].experience + '</td>' +
                        '<td>' + data[i].salary + '</td>' +
                        '<td>' + data[i].qualification + '</td>' +
                        '<td>' + data[i].primary + '</td>' +
                        '<td>' + data[i].comment + '</td>' +
                        '<td>' +
                        '<button type="button" class="seek btn btn-primary"><i class="fa fa-search" aria-hidden="true"></i></button>' +
                        '</td>'
                        '</tr>';
                    $('#requirements').append(html);

                    var requirement = $('#requirements').find('.requirement:last')
                    var seekEmployeesButton = requirement.find('.seek:last');

                    seekEmployeesButton.click(function () {

                        var salary = data[i].salary;
                        var experience = data[i].experience;
                        var primary = data[i].primary;

                        $('.employee').each(function () {
                            $(this).remove();
                        })
                        $('#form3').show();
                        ajax.get("http://localhost:8080/async-controller?async-command=seek-employees",
                            {
                                "salary": salary,
                                "experience": experience,
                                "primary": primary
                            })
                            .then((response) => response.json())
                            .then((data) => {
                                for (let i = 0; i < data.length; i++) {
                                    var html = '';
                                    html += '<div class="employee col-4 card rounded shadow-sm border-0">\n' +
                                        '                    <div class="card-body p-0">\n' +
                                        '                        <div class="bg-primary px-5 py-4 text-center card-img-top"><img\n' +
                                        '                                src="'+ data[i].avatar +'" alt="..."\n' +
                                        '                                width="100" class="rounded-circle mb-2 img-thumbnail d-block mx-auto">\n' +
                                        '                            <h5 class="text-white mb-0">' + data[i].forename + ' ' + data[i].surname + '</h5>\n' +
                                        '                            <p class="small text-white mb-0">' + data[i].primary + '</p>\n' +
                                        '                        </div>\n' +
                                        '                        <div class="p-3 d-flex justify-content-center">\n' +
                                        '                            <ul class="list-inline mb-0">\n' +
                                        '                                <li class="list-inline-item text-center">\n' +
                                        '                                    <h5 class="font-weight-bold mb-0 d-block">' + data[i].experience + '</h5><small class="text-muted">Experience</small>\n' +
                                        '                                </li>\n' +
                                        '                                <li class="list-inline-item text-center">\n' +
                                        '                                    <h5 class="font-weight-bold mb-0 d-block">' + data[i].salary + '</h5><small class="text-muted">Salary</small>\n' +
                                        '                                </li>\n' +
                                        '                            </ul>\n' +
                                        '                        </div>\n' +
                                        '                        <div class="pb-1 px-2 d-flex justify-content-center">\n' +
                                        '                            <ul class="list-inline mb-0">\n' +
                                        '                                <li class="text-center">\n' +
                                        '                                    <small class="text-muted">' + data[i].skills + '</small>\n' +
                                        '                                </li>\n' +
                                        '                            </ul>\n' +
                                        '                        </div>\n' +
                                        '                        <hr>\n' +
                                        '                        <div class="d-flex justify-content-center mb-2">\n' +
                                        '                            <button type="button" class="add btn btn-primary"><i class="fa fa-plus"></i></button>\n' +
                                        '                        </div>\n' +
                                        '                    </div>\n' +
                                        '                </div>'

                                    $('.employees').append(html);
                                    $('.employees').find('.add:last').click(function () {
                                        $('.employee').each(function () {
                                            $(this).remove();
                                        })
                                        $('#form3').hide();
                                        ajax.get("http://localhost:8080/async-controller?async-command=add-employee",
                                            {
                                                "user-id": data[i].id,
                                                "project-id": projectId
                                            })
                                            .then((response) => response.json())
                                            .then((responseData) => {

                                            });

                                    });
                                }
                            })
                    });

                }

            }
        )
}