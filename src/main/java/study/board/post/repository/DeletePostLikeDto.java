package study.board.post.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeletePostLikeDto {

    private Long postId;
    private Long likerId;
}
