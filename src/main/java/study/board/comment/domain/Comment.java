package study.board.comment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Comment {

    private Long id;
    private String content;

    private Long postId; // Post fk
    private Long authorId;  // Account fk
    private Long parentCommentId; // Comment fk (대댓글)

    private boolean isDeleted;

    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;

    public Comment(Long id, String content, Long postId, Long authorId, Long parentCommentId,
                   LocalDateTime createTime, LocalDateTime modifiedTime) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.authorId = authorId;
        this.parentCommentId = parentCommentId;
        this.isDeleted = false;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }

    @Builder
    public Comment(String content, Long postId, Long authorId, Long parentCommentId, LocalDateTime createTime) {
        this.content = content;
        this.postId = postId;
        this.authorId = authorId;
        this.parentCommentId = parentCommentId;
        this.isDeleted = false;
        this.createTime = createTime;
    }
}
