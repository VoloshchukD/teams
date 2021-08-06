$('.element').each(function () {
    var badge = $(this).find('.badge');
    var span = badge.find('span')
    var block = $(this).find('.block');
    var billId = block.find('.id').val();
    var updatedText = block.find('.update').val();
    $(this).find('.accept')
    $(this).find('.accept').click(function () {
        webix.ajax().post("http://localhost:8080/async-controller?async-command=accept-payment",
            {
                "bill-id":billId
            })
            .then((response) => response.json())
            .then((data) => {
                span.attr('class', 'blue');
                span.html(updatedText)
                $(this).hide();
            })
    });

})



