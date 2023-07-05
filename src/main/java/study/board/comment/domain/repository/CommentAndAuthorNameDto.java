package study.board.comment.domain.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CommentAndAuthorNameDto {

    private Long id;
    private String content;

    private String authorName;
    private Long parentCommentId;

    private LocalDateTime createTime;
}
