package study.board.post.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PostLike {

    private Long id;
    private Long postId; // post fk
    private Long likerId; // Account fk

    private LocalDateTime createTime;

    public PostLike(Long postId, Long likerId, LocalDateTime createTime) {
        this.postId = postId;
        this.likerId = likerId;
        this.createTime = createTime;
    }
}
