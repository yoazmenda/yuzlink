$(document).ready(function(){
    $('#textBox').on('input change', function (){
        $('#button').attr('disabled', $(this).val() == '');
    })
});