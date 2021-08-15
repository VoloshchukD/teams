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

var billCreationInputs;

var billCreationPatterns;

window.addEventListener("load", function (event) {

    const amountRegex = new RegExp(document.getElementById('regex-amount').textContent);

    const informationRegex = new RegExp(document.getElementById('regex-information').textContent);

    billCreationInputs = document.querySelectorAll('.form-control');

    billCreationPatterns = {
        amount: amountRegex,
        information: informationRegex
    };

    billCreationInputs.forEach((input) => {
        input.addEventListener('keyup', (e) => {
            validate(e.target, billCreationPatterns[e.target.attributes.id.value]);
        });
    });

});

function validateBillForm() {
    return validateInputs(billCreationInputs, billCreationPatterns);
}