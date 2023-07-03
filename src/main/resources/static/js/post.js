$(function() {

    // submit-post process
    $('#submit-post').click(function () {
        const section = $('section');
        const board = section.data('board');

        const title = $('#title').val();
        const content = $('#content').val();

        $.ajax({
            type: 'POST',
            url: '/api/v1/post/submit/' + board,
            contentType: 'application/json',
            data: JSON.stringify({
                title: title,
                content: content
            })
        }).done(function() {
            window.location.href = '/board?board=' + board;
        })
    });

    // edit-post process
    $('#edit-post').click(function () {
        const section = $('section');
        const postId = section.data('post');

        const title = $('#title').val();
        const content = $('#content').val();

        $.ajax({
            type: 'POST',
            url: '/api/v1/post/edit/' + postId,
            contentType: 'application/json',
            data: JSON.stringify({
                title: title,
                content: content
            })
        }).done(function() {
            window.location.href = '/';
        }).fail(function() {
            window.location.href = '/?error=true';
        })
    });

    // delete-post process
    $('#delete-post').click(function () {
        const section = $('section');
        const postId = section.data('post');

        $.ajax({
            type: 'POST',
            url: '/api/v1/post/delete/' + postId,
            contentType: 'application/json'
        }).done(function() {
            window.location.href = '/';
        }).fail(function() {
            window.location.href = '/?error=true';
        })
    });
});