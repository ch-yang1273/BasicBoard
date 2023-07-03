package study.board.post.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EditPostContentDto {

    private Long id;
    private String content;
}
