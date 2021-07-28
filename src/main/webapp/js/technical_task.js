window.addEventListener("load", function (event) {

    alert($("#tt").children().length);
});

// function hideLayout() {
//     alert($("#tt").children().length);
// }

function showLayout() {
    $(".aaa").show();
    $(".view-tasks").hide();
    $(".create-task").show();
}