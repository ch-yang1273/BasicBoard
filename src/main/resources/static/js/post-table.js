$(function () {

    $(document).ready(function () {
        const section = $('section');
        const board = section.data('board');

        $.ajax({
            type: 'GET',
            url: '/api/v1/post/list/' + board,
            contentType: 'application/json',
            success: function (data) {
                const table = $('#boardTable');
                $.each(data, function (index, item) {
                    const row = $('<tr></tr>');
                    const date = new Date(item.postCreateTime)
                    const link = '/post/view/' + item.postId;
                    const titleLink = '<a href="' + link + '">' + item.title + '</a>';

                    const iconClass = item.isLike ? 'fa-solid' : 'fa-regular';
                    const icon = $('<i></i>').addClass('fa-heart text-danger').addClass(iconClass);
                    row.append($('<td></td>').append(icon));

                    row.append('<td class="text-start">' + titleLink + '</td>');
                    row.append('<td>' + item.authorName + '</td>');
                    row.append('<td>' + date.toLocaleDateString() + '</td>');
                    row.append('<td>' + item.likeCount + '</td>');
                    // 생성한 테이블 행을 테이블에 추가
                    table.append(row);
                });
            }
        })
    })
});