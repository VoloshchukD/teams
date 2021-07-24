var ajax = webix.ajax().headers({
    'Content-type': 'application/json'
})

window.addEventListener("load", function (event) {
    alert('as')
    loadEditData();
});

function submitUpdateForm() {

    ajax().post("http://localhost:8080/async-controller?async-command=update-user-detail").then(function(data){

    });


}

function loadEditData() {
    ajax.get("http://localhost:8080/async-controller?async-command=edit")
        .then((response) => response.json())
        .then((data) => {
            document.getElementById("first-name").value = data.firstname;
            document.getElementById("last-name").value = data.lastname;
            document.getElementById("company").value = data.company;
            document.getElementById("position").value = data.position;
            document.getElementById("experience").value = data.experience;
            document.getElementById("salary").value = data.salary;
            document.getElementById("primary-skill").value = data.primary;
            document.getElementById("skills-description").value = data.skills;
        })
}

webix.ready(function () {

    // $('#user_image_header').error(function() {
    //     sleep(10000).then(() => {
    //             $$("user_image").config.image = document.getElementById("userImageUrl").value;
    //             $$("user_image").refresh();
    //             $("#user_image_header").attr("src", document.getElementById("userImageUrl").value);
    //             }
    //         )
    //     });


    function sleep(time) {
        return new Promise((resolve) => setTimeout(resolve, time));
    }

    // webix.attachEvent("onLoadError", function(xhr, view){
    //     alert('as');
    // });

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
                        // onAfterRender: function () {
                        //     // alert('a');
                        //     $$("user_image").refresh();
                        //     $("#user_image_header").attr("src", document.getElementById("userImageUrl").value);
                        // },
                        onAfterFileAdd: function () {
                            sleep(5000).then(() => {
                                ajax.get("http://localhost:8080/controller?async-command=avatar")
                                    .then((response) => response.json())
                                    .then((data) => {
                                        alert(data.avatar)
                                        $$("user_image").config.image = "http://localhost:8080" + data.avatar;
                                        $$("user_image").refresh();
                                        $("#user_image_header").attr("src", "http://localhost:8080" + data.avatar);
                                        //             $$("user_image").config.image = document.getElementById("userImageUrl").value;
                                        //             $$("user_image").refresh();
                                        //             $("#user_image_header").attr("src", document.getElementById("userImageUrl").value);

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


