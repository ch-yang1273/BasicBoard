$(function() {

    // signup process
    $('#btn-signup-proc').click(function () {
        console.log("btn-signup-proc.click")
        const email = $('#email').val();
        const password = $('#password').val();

        $.ajax({
            type: 'POST',
            url: '/api/v1/account/signup',
            contentType: 'application/json',
            data: JSON.stringify({
                email: email,
                password: password
            })
        }).done(function() {
            // redirect to the /login page.
            window.location.href = '/login';
        }).fail(function() {
            // redirect to the /signup page.
            window.location.href = '/signup';
        })
    });
});