package study.board.post.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SelectPostLikeDto {

    Long postId;
    Long likerId;
}
