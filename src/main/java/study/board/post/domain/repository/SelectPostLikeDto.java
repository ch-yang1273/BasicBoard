package study.board.post.domain.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SelectPostLikeDto {

    Long postId;
    Long likerId;
}
