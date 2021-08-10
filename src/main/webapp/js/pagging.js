
paginate();

function paginate() {
    $('.frame').each(function () {

        var currentFrame = $(this);

        var currentPage;
        var unitPerPage;
        var totalPages;
        var totalUnits = currentFrame.find(".elements").children(".element").length;

        function preparePages() {
            unitPerPage = $("input[name='unitPerPage']:checked").val();
            totalPages = Math.ceil(totalUnits / unitPerPage)
            currentPage = 1;
            currentFrame.find('.elements .element').slice(0).show();
            currentFrame.find('.elements .element').slice(unitPerPage).hide();
            check_navigation_display(currentFrame);
        }

        preparePages();

        $('#radio').change(function () {
            preparePages();
        });

        var pagging = currentFrame.find('.pagging');

        pagging.find('.next').click(function () {
            next(currentFrame);
        });

        pagging.find('.prev').click(function () {
            previous(currentFrame);
        });

        pagging.find('.num1').click(function () {
            previous(currentFrame);
        });

        pagging.find('.num3').click(function () {
            next(currentFrame);
        });

        pagging.find('.first').click(function () {
            var first = currentFrame.find('.elements').children('.element:first');
            first.show()
            first.nextAll().hide();
            first.nextAll(':lt(' + (unitPerPage - 1) + ')').show();
            currentPage = 1;
            check_navigation_display(currentFrame);
        });

        pagging.find('.last').click(function () {
            var last = currentFrame.find('.elements').children('.element:last');
            var index = totalUnits - unitPerPage * (totalPages - 1);
            last.show();
            last.prevAll().hide();
            last.prevAll(':lt(' + (index - 1) + ')').show();
            currentPage = totalPages;
            check_navigation_display(currentFrame);
        });

        function previous(el) {
            var first = $(el).find('.elements').children('.element:visible:first');
            first.prevAll(':lt(' + (unitPerPage) + ')').show();
            first.prev().nextAll().hide()
            currentPage = currentPage - 1;
            check_navigation_display(el);
        }

        function next(el) {
            var last = $(el).find('.elements').children('.element:visible:last');
            last.nextAll(':lt(' + (unitPerPage) + ')').show();
            last.next().prevAll().hide();
            currentPage = currentPage + 1;
            check_navigation_display(el);
        }

        function check_navigation_display(el) {
            var pagging = el.find('.pagging');

            pagging.find('.num1').html(currentPage - 1);
            pagging.find('.num2').html(currentPage);
            pagging.find('.num3').html(currentPage + 1);

            pagging.children('.num1').hide();
            pagging.children('.num2').hide();
            pagging.children('.num3').hide();
            pagging.children('.first').hide();
            pagging.children('.last').hide();

            if (pagging.find('.num1').html() >= 1) {
                pagging.children('.num1').show();
            }
            if (pagging.find('.num2').html() == currentPage && totalPages != 0) {
                pagging.children('.num2').show();
            }
            if (pagging.find('num3').html() <= totalPages) {
                pagging.children('.num3').show();
            }

            if ($(el).find('.elements').children('.element').first().is(':visible')) {
                pagging.find('.prev').hide();
                pagging.find('.first').hide();
            } else {
                pagging.find('.prev').show();
                pagging.find('.first').show();
            }

            if ($(el).find('.elements').children('.element').last().is(':visible')) {
                pagging.find('.next').hide();
                pagging.find('.last').hide();
            } else {
                pagging.find('.next').show();
                pagging.find('.last').show();
            }
        }

    });
}



