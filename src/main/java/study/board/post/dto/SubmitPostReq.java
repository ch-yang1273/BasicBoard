package study.board.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SubmitPostReq {

    private String title;
    private String content;
}
