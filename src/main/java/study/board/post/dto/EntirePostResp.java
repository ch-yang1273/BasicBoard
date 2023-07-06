package study.board.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import study.board.post.domain.service.PostInfo;

@NoArgsConstructor
@Getter
public class EntirePostResp {

    PostInfo postInfo;
    String content;

    public EntirePostResp(PostInfo postInfo, String content) {
        this.postInfo = postInfo;
        this.content = content;
    }
}
