package study.board.post.domain;

public class Post {

    private Long id;
    private String title;

    private Long contentId; // PostContent fk

    private Long authorId; // Account fk
    private Long boardId; // Board fk

    private int viewCount;
    private boolean isDeleted;
}
