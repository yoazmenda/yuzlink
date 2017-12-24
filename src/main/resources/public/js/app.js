$(document).ready(function () {


    $('#textBox').on('input change', function () {
        $('#button').attr('disabled', $(this).val() == '');
    });

    $('.menu-item').click(function () {
        var activeElement = this;
        requestName = $(this).attr('name');
        $('.content').load(requestName + ".html", function (responseTxt, statusTxt, xhr) {
                if (statusTxt == "success") {
                    $(".menu-item").removeClass('active');
                    $(activeElement).addClass('active');
                }
            }
        )
    });

    $('.content').load(
        "home.html"
    )


});