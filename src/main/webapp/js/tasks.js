$('#create-task-button').click(function () {
    $('#modal').hide();
    $('body').removeClass('modal-open');
    $('.modal-backdrop').remove();
})

$('#create-task-modal-button').click(function () {

})

$('.card').each(function () {
    initCardButtons($(this));



    var trackButton = $(this).find('.track');
    var hours = $(this).find('.hours');
    var panel = $(this).find('.panel');
    var hoursVal = $(this).find('.val').html();

    $(this).find('.input').val(hoursVal);
    trackButton.click(function () {

        hours.hide();
        panel.show();
    })

    var saveButton = $(this).find('.save');
    saveButton.click(function () {
        hours.show();
        panel.hide();
    })

})



function initCardButtons(card){

    var backButton = card.find('.back');
    var forwardButton = card.find('.forward');
    var curentDiv = card.parent().attr("id");

    if (curentDiv == 'col3') {
        forwardButton.hide();
        backButton.show();
    } else if (curentDiv == 'col1') {
        backButton.hide();
        forwardButton.show();
    } else if (curentDiv == 'col2') {
        backButton.show();
        forwardButton.show();
    }

    backButton.click(function () {
        var targetDiv;
        if (curentDiv == 'col3') {
            targetDiv = '#col2';
        } else if (curentDiv == 'col2') {
            targetDiv = '#col1';
        }


        jQuery(card).detach().appendTo(targetDiv)
        initCardButtons(card)
    })

    forwardButton.click(function () {
        var targetDiv;
        if (curentDiv == 'col1') {
            targetDiv = '#col2';
        } else if (curentDiv == 'col2') {
            targetDiv = '#col3';
        }

        jQuery(card).detach().appendTo(targetDiv)
        initCardButtons(card)
    })

}