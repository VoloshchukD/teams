var ajax = webix.ajax().headers({
    'Content-type': 'application/json'
})

var technicalTaskId;
var projectId;

window.addEventListener("load", function (event){
    var url_string = window.location.href;
    var url = new URL(url_string);

    technicalTaskId = url.searchParams.get("technical-task-id");
    projectId = url.searchParams.get("project-id");

});

$('.requirement').each(function () {
    var seekEmployeesButton = $(this).find('.seek');
    var requirementId = $(this).find('.identifier').val();

    var salary = $(this).find('.salary').html();
    var experience = $(this).find('.experience').html();
    var primary = $(this).find('.primary').html();

    seekEmployeesButton.click(function () {
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
                        '                                    <h5 class="font-weight-bold mb-0 d-block">' + data[i].experience + '</h5><small class="text-muted"><i\n' +
                        '                                        class="fa fa-picture-o mr-1 text-primary"></i>Experience</small>\n' +
                        '                                </li>\n' +
                        '                                <li class="list-inline-item text-center">\n' +
                        '                                    <h5 class="font-weight-bold mb-0 d-block">' + data[i].salary + '</h5><small class="text-muted"><i\n' +
                        '                                        class="fa fa-user-circle-o mr-1 text-primary"></i>Salary</small>\n' +
                        '                                </li>\n' +
                        '                            </ul>\n' +
                        '                        </div>\n' +
                        '                        <div class="pb-1 px-2 d-flex justify-content-center">\n' +
                        '                            <ul class="list-inline mb-0">\n' +
                        '                                <li class="text-center">\n' +
                        '                                    <small class="text-muted"><i class="fa fa-picture-o mr-1 text-primary"></i>' + data[i].skills + '</small>\n' +
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
                                "user-id": data[i].user-id,
                                "project-id": projectId
                            })
                            .then((response) => response.json())
                            .then((responseData) => {
                                });


                    });
                }
            })

    });


});