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
    webix.ajax().post("http://localhost:8080/controller?command=create-technical-task",
        {
            "deadline": $$("deadline").getValue(),
            "name": document.getElementById("name").value,
            "overview": document.getElementById("overview").value
        })
        .then((response) => response.json())
        .then((data) => {
            window.location.href = "http://localhost:8080/controller?command=to-create-requirement";
        })
}

