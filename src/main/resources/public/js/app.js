$(document).ready(function () {


    $('#textBox').on('input change', function () {
        $('#button').attr('disabled', $(this).val() == '');
    });

    $('.menu-item').click(function () {
        var activeElement = this;
        var requestName = $(this).attr('name');

        $.ajax('templates/' + requestName + '.html')
            .done(function (data) {
                $('.content').html(data);
                $(".menu-item").removeClass('active');
                $(activeElement).addClass('active');
            });
    });

    $('.content').load(
        "./templates/home.html"
    )


});