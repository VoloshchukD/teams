$('.element').each(function () {
    var badge = $(this).find('.badge');
    var span = badge.find('span')
    var block = $(this).find('.block');
    var billId = block.find('.id').val();
    var updatedText = block.find('.update').val();
    $(this).find('.accept')
    $(this).find('.accept').click(function () {
        webix.ajax().post("http://localhost:8080/async-controller?command=accept-payment",
            {
                "bill-id": billId
            })
            .then((response) => response.json())
            .then((data) => {
                span.attr('class', 'blue');
                span.html(updatedText)
                $(this).hide();
            })
    });
})

var ajax = webix.ajax().headers({
    'Content-type': 'application/json'
})

var projectId;

window.addEventListener("load", function (event) {
    var url_string = window.location.href;
    var url = new URL(url_string);
    projectId = url.searchParams.get("project-id");



});

$('.element').each(function () {

    var billId = $(this).find('.billId').val();

    $(this).find('.edit').click(function () {

        ajax.get("http://localhost:8080/async-controller?command=load-tasks-information",
            {'project-id': projectId})
            .then((response) => response.json())
            .then((data) => {
                var text = '';
                var amountDue = 0;
                for (let i = 0; i < data.length; i++) {
                    var workerSalary = data[i].hours * data[i].salary;
                    text += data[i].name + ' - ' + workerSalary + '\n';
                    amountDue += workerSalary;
                }
                $('#modal').find('#updateBillId').val(billId)
                $('#modal').find('#forUpdateProjectId').val(projectId)
                $('#modal').find('#amount').val(amountDue);
                $('#modal').find('#information').val(text);
            })

    });

    var editForm = $(this).find('.editing');
    editForm.find('.forDeleteProjectId').val(projectId)
})






