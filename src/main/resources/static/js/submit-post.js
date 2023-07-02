$(function() {

    // signup process
    $('#submit-post').click(function () {
        const section = $('section');
        const board = section.data('board');

        const title = $('#title').val();
        const contents = $('#contents').val();

        console.log(title)
        console.log(contents)

        $.ajax({
            type: 'POST',
            url: '/api/v1/post/' + board + "/submit",
            contentType: 'application/json',
            data: JSON.stringify({
                title: title,
                contents: contents
            })
        }).done(function() {
            window.location.href = '/board?board=' + board;
        })
    });
});