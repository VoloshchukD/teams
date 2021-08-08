window.addEventListener("load", function (event) {
    var url_string = window.location.href;
    var url = new URL(url_string);
    var projectId = url.searchParams.get("project-id");
    if (projectId != null) {
        $('#project option[value=' + projectId + ']').prop('selected', true);
        loadBillData();
    }
});

$("#project").change(function () {
    loadBillData();
});

var ajax = webix.ajax().headers({
    'Content-type': 'application/json'
})

function loadBillData() {
    ajax.get("http://localhost:8080/async-controller?command=load-tasks-information",
        {"project-id": $("#project").val()})
        .then((response) => response.json())
        .then((data) => {
            var text = '';
            var amountDue = 0;
            for (let i = 0; i < data.length; i++) {
                var workerSalary = data[i].hours * data[i].salary;
                text += data[i].name + ' - ' + workerSalary + '\n';
                amountDue += workerSalary;
            }
            $("#amount").val(amountDue);
            $("#information").val(text);
        })
}