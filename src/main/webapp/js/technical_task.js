var ajax = webix.ajax().headers({
    'Content-type': 'application/json'
})

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
        ajax.get("http://localhost:8080/async-controller?async-command=employee-requirements",
            {"technical-task-id": technicalTaskId})
            .then((response) => response.json())
            .then((data) => {
                for (let i = 0; i < data.length; i++) {
                    var html = '';
                    html += '<tr><th scope="row">' + (i + 1) + '</th><td>'
                    html += data[i].experience + '</td>' + '<td>' + data[i].salary + '</td><td>'
                    html += data[i].qualification + '</td><td>' + data[i].primary + '</td><td>' + data[i].comment + '</td></tr>';
                    $(table).append(html);
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