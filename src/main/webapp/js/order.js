$(document).ready(function (){
    function calculate() {
        let firstDate = new Date($("#start").val()).getTime();
        let secondDate = new Date($("#end").val()).getTime();

        let days = (secondDate - firstDate) / (1000 * 3600 * 24);
        let price = 0;
        if (days != NaN && days >= 0) {
            price = round((days + 1) * parseFloat($('.car_block__price span').text()), 2);
            $('.total_price span').text(price);
            $('#total_price').val(price);
        } else {
            $('.total_price span').text(price);
            $('#total_price').val(price);
        }
    }

    $('#start').change(function () {
        calculate();
    })

    $('#end').change(function (){
        calculate();
    })

    function round (number, decimalPlaces) {
        var n = Math.pow(10, decimalPlaces);
        return Math.round(number * n) / n;
    }
})

function validateDate() {
    let firstDate = new Date($("#start").val()).getTime();
    let secondDate = new Date($("#end").val()).getTime();

    let days = (secondDate - firstDate) / (1000 * 3600 * 24);

    if (days < 0) {
        alert("Wrong dates");
        return false;
    }
    return true;
}