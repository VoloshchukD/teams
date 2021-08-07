var ajax = webix.ajax().headers({
    'Content-type': 'application/json'
})

function sleep(time) {
    return new Promise((resolve) => setTimeout(resolve, time));
}

window.addEventListener("load", function (event) {
    loadEditData();
});

var editInputs;

var editPatterns;

window.addEventListener("load", function (event) {

    const forenameRegex = new RegExp(document.getElementById('regex-forename').textContent);

    const surnameRegex = new RegExp(document.getElementById('regex-surname').textContent);

    const companyRegex = new RegExp(document.getElementById('regex-company').textContent);

    const positionRegex = new RegExp(document.getElementById('regex-position').textContent);

    const experienceRegex = new RegExp(document.getElementById('regex-experience').textContent);

    const salaryRegex = new RegExp(document.getElementById('regex-salary').textContent);

    const primaryRegex = new RegExp(document.getElementById('regex-primary').textContent);

    const skillsRegex = new RegExp(document.getElementById('regex-skills').textContent);

    editInputs = document.querySelectorAll('.form-control');

    editPatterns = {
        forename: forenameRegex,
        surname: surnameRegex,
        company: companyRegex,
        position: positionRegex,
        experience: experienceRegex,
        salary: salaryRegex,
        primary: primaryRegex,
        skills: skillsRegex
    };

    editInputs.forEach((input) => {
        input.addEventListener('keyup', (e) => {
            validate(e.target, editPatterns[e.target.attributes.id.value]);
        });
    });

    $("#status-checker").on('change', function() {
        var newStatus;
        if ($(this).is(':checked')) {
            newStatus = 'NOT_BUSY';
        }
        else {
            newStatus = 'BUSY';
        }
        webix.ajax().post("http://localhost:8080/async-controller?async-command=update-user-status",
            {'status': newStatus})
    });

    $("#delete-account-button").on("click", function(){
        $('#modal').hide();
        $('body').removeClass('modal-open');
        $('.modal-backdrop').remove();
    });

});

function validateEditForm() {
    return validateInputs(editInputs, editPatterns);
}

function submitEditForm() {
    if (validateEditForm()) {
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
                loadEditData();
            })
    }
}

function loadEditData() {
    ajax.get("http://localhost:8080/async-controller?async-command=edit")
        .then((response) => response.json())
        .then((data) => {
            document.getElementById("forename").value = data.forename;
            document.getElementById("surname").value = data.surname;
            document.getElementById("company").value = data.company;
            document.getElementById("position").value = data.position;
            document.getElementById("experience").value = data.experience;
            document.getElementById("salary").value = data.salary;
            document.getElementById("primary").value = data.primary;
            document.getElementById("skills").value = data.skills;
            if (data.status == 'NOT_BUSY') {
                $("#status-checker").prop('checked', true);
            } else if (data.status == 'BUSY') {
                $("#status-checker").prop('checked', false);
            }
        })
}

webix.ready(function () {

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




