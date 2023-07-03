$(function () {

    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            url: '/api/v1/post/list/' + '${board}',
            contentType: 'application/json',
            success: function (data) {
                const table = $('#boardTable');
                $.each(data, function (index, item) {
                    const row = $('<tr></tr>');
                    const date = new Date(item.postCreateTime)
                    const link = '/post/' + item.postId;
                    const titleLink = '<a href="' + link + '">' + item.title + '</a>';
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