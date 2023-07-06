$(function () {

    // 댓글 추가
    $(document).ready(function () {
        const section = $('section');
        const postId = section.data('post');

        $.ajax({
            type: 'GET',
            url: '/api/v1/comment/list/' + postId,
            contentType: 'application/json',
        }).done(function (data) {
            // 총 댓글 개수
            $('#comment-count').text(data.length);

            // 댓글 아이디를 키로, 댓글 데이터를 값으로 하는 맵 생성
            const commentMap = new Map();
            data.forEach(comment => {
                commentMap.set(comment.id, comment);
                comment.children = [];
            });

            // 댓글 데이터를 트리 구조로 재구성
            const rootComments = [];
            data.forEach(comment => {
                if (comment.parentCommentId === null || comment.parentCommentId === 0) {
                    rootComments.push(comment);
                } else {
                    const parentComment = commentMap.get(comment.parentCommentId);
                    if (parentComment) {
                        parentComment.children.push(comment);
                    }
                }
            });

            // 댓글 하나에 대한 HTML 코드 생성 함수
            function createCommentHTML(comment) {
                let html =
                    `
                <li class="list-group-item">
                    <p class="mb-0">
                        <span class="align-middle">${comment.authorName}</span>
                        <span class="align-middle text-secondary">${comment.createTime}</span>
                        <button data-comment="${comment.id}" class="btn btn-link reply-button" style="text-decoration: none" 
                        data-bs-toggle="modal" data-bs-target="#child-comment-modal">답글+</button>
                    </p>
                    <p class="ms-2">${comment.content}</p>
                `;
                // 자식 댓글이 있는 경우, 자식 댓글들에 대한 HTML 코드를 생성하여 <ul> 태그 안에 넣음
                if (comment.children.length > 0) {
                    html += `<ul class="list-group ms-2">`;
                    comment.children.forEach(childComment => {
                        html += createCommentHTML(childComment);
                    });
                    html += `</ul>`;
                }
                html += `</li>`;
                return html;
            }

            // 댓글 트리를 순회하면서 HTML 코드 생성
            let html = '';
            rootComments.forEach(comment => {
                html += createCommentHTML(comment);
            });

            // 생성된 HTML 코드를 페이지에 적용
            $('#comment-list').html(html);
        }).fail(function (jqXHR, textStatus, errorThrown) {
            // 오류 처리
            console.log("AJAX request failed: " + textStatus);
        });
    });

    /** 답글 추가 버튼 클릭 이벤트
     * .click() 또는 .on('click')는 페이지 로드 시 이미 존재하는 요소에만 바인딩됩니다.
     *  이후에 동적으로 추가된 요소에는 바인딩되지 않습니다.
     *
    $('.reply-button').click(function () {
        console.log(".reply-button click");
    });
    */
    // 동적으로 추가된 "답글 추가 버튼" 클릭 이벤트
    $(document).on('click', '.reply-button', function() {
        const commentId = $(this).data('comment');
        console.log(".reply-button click id=" + commentId);

        // 모달 창 버튼의 "data-parent" 속성 값을 "commentId"로 수정
        $('#submit-child-comment').data('parent', commentId);
        $('#child-comment-content').val('');
    });

    $('#submit-child-comment').click(function () {
        const section = $('section');
        const postId = section.data('post');

        const parentCommentId = $('#submit-child-comment').data('parent');
        const content = $('#child-comment-content').val();

        $.ajax({
            type: 'POST',
            url: '/api/v1/comment/submit/child/' + parentCommentId,
            contentType: 'application/json',
            data: JSON.stringify({
                content: content
            })
        }).always(function () {
            window.location.href = '/post/view/' + postId;
        });
    });

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
        }).done(function () {
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
        }).done(function () {
            window.location.href = '/';
        }).fail(function () {
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
        }).done(function () {
            window.location.href = '/';
        }).fail(function () {
            window.location.href = '/?error=true';
        })
    });

    function updateLikeCount(postId) {
        $.ajax({
            type: 'GET',
            url: '/api/v1/post/info/' + postId
        }).done(function (data) {
            // 성공적으로 PostInfo를 가져온 경우
            $('#like-count').text(data.likeCount);
        });
    }

    // like-post process
    $('#like-post').click(function () {
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
        }).always(function () {
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

    $('#add-comment').click(function () {
        const section = $('section');
        const postId = section.data('post');

        const content = $('#comment-content').val();

        $.ajax({
            type: 'POST',
            url: '/api/v1/comment/submit/' + postId,
            contentType: 'application/json',
            data: JSON.stringify({
                content: content
            })
        }).always(function () {
            window.location.href = '/post/view/' + postId;
        });
    });
});