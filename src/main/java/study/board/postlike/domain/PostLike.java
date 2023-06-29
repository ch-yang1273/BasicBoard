package study.board.postlike.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PostLike {

    private Long id;
    private Long postId;
    private Long likerId; // Account fk

    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;
}
