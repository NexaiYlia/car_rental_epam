function submitForm(form) {
    $.post($(form).attr('action'),$(form).serializeArray());
    // It is necessary for the form information to be sent and only then close the window
    // Sleep a guarantee that the form information will be received on the server.
    sleepFor(1000);
    window.close();
    return false;
}

function sleepFor( sleepDuration ){
    var now = new Date().getTime();
    while(new Date().getTime() < now + sleepDuration){ /* do nothing */ }
}