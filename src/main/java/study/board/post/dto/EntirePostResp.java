package study.board.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EntirePostResp {

    PostTitleResp postInfo;
    String content;

    public EntirePostResp(PostTitleResp postInfo, String content) {
        this.postInfo = postInfo;
        this.content = content;
    }
}
