function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('.editor_block__image img')
                .attr('src', e.target.result)
        };

        reader.readAsDataURL(input.files[0]);
    }
}

$(document).ready(function(){
    $("#view").click(function(){

        $('.car_block__brand span').text($('#brand_editor').val() + " " + $('#model_editor').val());
        $('.car_block__img img').attr('src', $('.editor_block__image img').attr('src'))
        $('#color').text($('#ce option:selected').text());
        $('#mileage').text($('#me').val());
        $('#gearbox').text($('#ge option:selected').text());
        $('#year').text($('#ye').val());
        $('#engine').text($('#ee option:selected').text());
        $('#class').text($('#cce option:selected').text());
        $('#price').text($('#pre').val());

        var data = CKEDITOR.instances['content_editor'].getData();
        $('.news_block__header span').text($("#header_editor").val());
        $('.news_block__content span').html(data);
        $('.news_block__img img').attr('src', $('.editor_block__image img').attr('src'))
    });
});