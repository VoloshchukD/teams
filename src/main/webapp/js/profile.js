webix.ready(function () {

    var ajax = webix.ajax().headers({
        'Content-type': 'application/json'
    })

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
                            css:"webix_transparent"
                        },
                        {
                            id: "choosePhotoInput",
                            view: "uploader",
                            hidden: false,
                            formData: {},
                            value:  document.getElementById("uploadButtonName").value,
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


