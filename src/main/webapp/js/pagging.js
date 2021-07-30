
var currentPage = 1;
var unitPerPage = 4;
var totalUnits = $(".elements").children(".element").length;
var totalPages = Math.ceil(totalUnits/unitPerPage);


$('.pagging-frame').each(function () {

    $(this).find('.elements .element').slice(unitPerPage).hide();

    check_navigation_display($(this));

    $(this).find('.next').click(function () {
        next($(this));
    });

    $(this).find('.prev').click(function () {
        previous($(this));
    });

    $(this).find('.num1').click(function () {
        previous($(this));
    });

    $(this).find('.num3').click(function () {
        next($(this));
    });

    $(this).find('.first').click(function () {
        var first = $(this).siblings('.elements').children('.element:first');
        first.show()
        first.nextAll().hide();
        first.nextAll(':lt('+(unitPerPage-1)+')').show();
        currentPage = 1;
        check_navigation_display($(this).closest('.pagging-frame'));
    });

    $(this).find('.last').click(function () {
        var last = $(this).siblings('.elements').children('.element:last');
        var index = totalUnits - unitPerPage*(totalPages-1);
        last.show();
        last.prevAll().hide();
        last.prevAll(':lt('+(index-1)+')').show();
        currentPage = totalPages;
        check_navigation_display($(this).closest('.pagging-frame'));
    });



});

function previous(el) {
    var first = $(el).siblings('.elements').children('.element:visible:first');
    first.prevAll(':lt('+(unitPerPage)+')').show();
    first.prev().nextAll().hide()
    currentPage = currentPage - 1;
    check_navigation_display($(el).closest('.pagging-frame'));
}

function next(el) {
    var last = $(el).siblings('.elements').children('.element:visible:last');
    last.nextAll(':lt('+(unitPerPage)+')').show();
    last.next().prevAll().hide();
    currentPage = currentPage + 1;
    check_navigation_display($(el).closest('.pagging-frame'));
}

function check_navigation_display(el) {
    document.getElementById('num1').innerHTML = currentPage-1;
    document.getElementById('num2').innerHTML = currentPage;
    document.getElementById('num3').innerHTML = currentPage+1;

    $(el).children('.num1').hide();
    $(el).children('.num2').hide();
    $(el).children('.num3').hide();
    $(el).children('.first').hide();
    $(el).children('.last').hide();

    if (document.getElementById('num1').innerHTML >= 1) {
        $(el).children('.num1').show();
    }
    if (document.getElementById('num2').innerHTML == currentPage && totalPages != 0) {
        $(el).children('.num2').show();
    }
    if (document.getElementById('num3').innerHTML <= totalPages) {
        $(el).children('.num3').show();
    }

    if ($(el).find('.elements').children('.element').first().is(':visible')) {
        $(el).children('.prev').hide();
        $(el).children('.first').hide();
    } else {
        $(el).children('.prev').show();
        $(el).children('.first').show();
    }

    if ($(el).find('.elements').children('.element').last().is(':visible')) {
        $(el).children('.next').hide();
        $(el).children('.last').hide();
    } else {
        $(el).children('.next').show();
        $(el).children('.last').show();
    }
}