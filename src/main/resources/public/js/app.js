$(document).ready(function () {

    function ContentRequest(filename, fileLoaded) {
        this.filename = filename;
        this.fileLoaded = fileLoaded;
        this.make = function () {
            var activeElement = document.getElementById(name);
            $.ajax(this.filename)
                .done(function (data) {
                    $('.content').html(data);
                    $(".menu-item").removeClass('active');
                    $(activeElement).addClass('active');
                })
                .done(function (){fileLoaded();});

        }
    }

    requests = {};

    requests['home'] = new ContentRequest('templates/home.html', function () {
        console.log("home loaded...")
    });
    requests['urls'] = new ContentRequest('templates/urls.html', function () {
        console.log("urls loaded...")
    });
    requests['about'] = new ContentRequest('templates/about.html', function () {
        console.log("about loaded...")
    });
    requests['home'].make();

    //menu click
    $('.menu-item').click(function () {
        requests[$(this).attr('name')].make();
    });
});