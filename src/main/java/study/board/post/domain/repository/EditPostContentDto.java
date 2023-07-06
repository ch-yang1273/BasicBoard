package study.board.post.domain.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EditPostContentDto {

    private Long id;
    private String content;
}
