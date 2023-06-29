package study.board.comment.domain;

import lombok.AllArgsConstructor;
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
}
