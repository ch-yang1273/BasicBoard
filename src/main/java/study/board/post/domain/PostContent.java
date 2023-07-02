package study.board.post.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostContent {

    private Long id;
    private String content;

    public PostContent(String content) {
        this.content = content;
    }
}
