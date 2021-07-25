var ajax = webix.ajax().headers({
    'Content-type': 'application/json'
})

window.addEventListener("load", function (event) {
    loadEditData();
});

function submitEditForm() {
    let userDataToEdit = {
        forename: document.getElementById("forename").value,
        surname: document.getElementById("surname").value,
        company: document.getElementById("company").value,
        position: document.getElementById("position").value,
        experience: document.getElementById("experience").value,
        salary: document.getElementById("salary").value,
        primary: document.getElementById("primary").value,
        skills: document.getElementById("skills").value
    };
    let json = JSON.stringify(userDataToEdit);
    webix.ajax().post("http://localhost:8080/async-controller?async-command=update-user-detail", {'jsonString': json})
        .then((response) => response.json())
        .then((data) => {
            webix.message({type: '"success', text: 'Данные изменены'});
            loadEditData();
        })
}

function loadEditData() {
    ajax.get("http://localhost:8080/async-controller?async-command=edit")
        .then((response) => response.json())
        .then((data) => {
            document.getElementById("forename").value = data.firstname;
            document.getElementById("surname").value = data.lastname;
            document.getElementById("company").value = data.company;
            document.getElementById("position").value = data.position;
            document.getElementById("experience").value = data.experience;
            document.getElementById("salary").value = data.salary;
            document.getElementById("primary").value = data.primary;
            document.getElementById("skills").value = data.skills;
        })
}

webix.ready(function () {

    function sleep(time) {
        return new Promise((resolve) => setTimeout(resolve, time));
    }

    webix.ui({
        id: "editFormAndTable",
        container: "box",
        view: "form",
        css: "webix_primary",
        align: "center",
        margin: 1,
        rows: [{
            align: "center",

            rows: [
                {
                    id: "user_image",
                    view: "button",
                    type: "image",
                    align: "center",
                    image: document.getElementById("userImageUrl").value,
                    width: 415,
                    height: 415,
                    css: "webix_transparent"
                },
                {
                    id: "choosePhotoInput",
                    view: "uploader",
                    hidden: false,
                    formData: {},
                    value: document.getElementById("uploadButtonName").value,
                    multiple: false,
                    autosend: true,
                    name: "file",
                    upload: "http://localhost:8080/upload",
                    css: "webix_transparent",
                    on: {
                        onAfterFileAdd: function () {
                            sleep(5000).then(() => {
                                ajax.get("http://localhost:8080/async-controller?async-command=load-avatar")
                                    .then((response) => response.json())
                                    .then((data) => {
                                        $$("user_image").config.image = "http://localhost:8080" + data.avatar;
                                        $$("user_image").refresh();
                                        $("#user_image_header").attr("src", "http://localhost:8080" + data.avatar);
                                    })
                            })

                        },
                    }
                },
            ]
        }
        ],
    });
});


