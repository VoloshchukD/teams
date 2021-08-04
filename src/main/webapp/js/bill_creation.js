var ajax = webix.ajax().headers({
    'Content-type': 'application/json'
})

$("#project").change(function () {
    ajax.get("http://localhost:8080/async-controller?async-command=load-tasks-information",
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
});