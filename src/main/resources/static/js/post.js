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

    function updateLikeCount(postId) {
        $.ajax({
            type: 'GET',
            url: '/api/v1/post/info/' + postId
        }).done(function(data) {
            // 성공적으로 PostInfo를 가져온 경우
            $('#like-count').text(data.likeCount);
        });
    }

    // like-post process
    $('#like-post').click(function() {
        const section = $('section');
        const postId = section.data('post');

        const likeImg = $('#like-img');
        const isLiked = likeImg.hasClass('fa-solid');

        // 좋아요 상태에 따라 like 값을 설정
        const likeValue = isLiked ? 'N' : 'Y';

        // 좋아요 요청 전송
        $.ajax({
            type: 'POST',
            url: '/api/v1/post/like/' + postId,
            contentType: 'application/json',
            data: JSON.stringify({
                like: likeValue
            })
        }).always(function() {
            // 요청 완료 시 아이콘 변경
            if (isLiked) {
                likeImg.removeClass('fa-solid');
                likeImg.addClass('fa-regular');
            } else {
                likeImg.removeClass('fa-regular');
                likeImg.addClass('fa-solid');
            }

            updateLikeCount(postId);
        });
    });
});