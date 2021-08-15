$('.element').each(function () {

    var nameValue = $(this).find('.name').html();
    var descriptionValue = $(this).find('.description').html();
    var updateProjectId =  $(this).find('.identifier').val();

    $(this).find('.edit').click(function () {
        $('#modal').find('#updateProjectId').val(updateProjectId)
        $('#modal').find('#name').val(nameValue);
        $('#modal').find('#description').val(descriptionValue);
    });

})

