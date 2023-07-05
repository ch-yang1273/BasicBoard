package study.board.comment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.board.comment.domain.Comment;

@NoArgsConstructor
@Getter
public class CommentResp {

    private Long id;
    private String content;

    private String authorName;
    private Long parentCommentId; // Comment fk (대댓글)

    @Builder
    public CommentResp(Long id, String content, String authorName, Long parentCommentId) {
        this.id = id;
        this.content = content;
        this.authorName = authorName;
        this.parentCommentId = parentCommentId;
    }

    public static CommentResp of(Comment comment, String authorName) {
        return CommentResp.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .authorName(authorName)
                .parentCommentId(comment.getParentCommentId())
                .build();
    }
}
