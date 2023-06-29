package study.board.comment.domain;

public class Comment {

    private Long id;
    private String content;

    private Long postId; // Post fk
    private Long authorId;  // Account fk
    private Long parentCommentId; // Comment fk (대댓글)

    private boolean isDeleted;
}
