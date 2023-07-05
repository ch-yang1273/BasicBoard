package study.board.comment.dto;

public class CommentResp {

    private Long id;
    private String content;

    private Long postId; // Post fk
    private Long authorName;  // Account fk
    private Long parentCommentId; // Comment fk (대댓글)
}
