var ajax = webix.ajax().headers({
    'Content-type': 'application/json'
})

webix.ready(function () {
    webix.ui({
        id: "editFormAndTable",
        container: "date",
        view: "form",
        css: "webix_primary",
        align: "center",
        type: "wide",
        borderless: true,
        rows: [
            {
                id: "deadline",
                view: "datepicker",
                timepicker: true,
                name: "end",
                stringResult: true,
                format: "%d %M %Y at %H:%i"
            }
        ],
    });
});

function submitCreationForm() {
    $('#deadlineHelp').hide();
    if (validateCreationForm()) {
        webix.ajax().post("http://localhost:8080/controller?command=create-technical-task",
            {
                "deadline": $$("deadline").getValue(),
                "name": document.getElementById("name").value,
                "overview": document.getElementById("overview").value
            })
            .then((response) => {
                window.location.href = "http://localhost:8080/controller?command=to-create-requirement";
            })
    }
}

var technicalTaskCreationInputs;

var technicalTaskCreationPatterns;

window.addEventListener("load", function (event) {

    const nameRegex = new RegExp(document.getElementById('regex-name').textContent);

    const overviewRegex = new RegExp(document.getElementById('regex-overview').textContent);

    technicalTaskCreationInputs = document.querySelectorAll('.form-control');

    technicalTaskCreationPatterns = {
        name: nameRegex,
        overview: overviewRegex
    };

    technicalTaskCreationInputs.forEach((input) => {
        input.addEventListener('keyup', (e) => {
            validate(e.target, technicalTaskCreationPatterns[e.target.attributes.id.value]);
        });
    });


});

function validateCreationForm() {
    var today = new Date();
    var deadlineValue = $$("deadline").getValue();
    var deadline = new Date(deadlineValue);
    if (deadline < today) {
        $('#deadlineHelp').show();
    }
    return validateInputs(technicalTaskCreationInputs, technicalTaskCreationPatterns)
        && (deadline > today);
}

