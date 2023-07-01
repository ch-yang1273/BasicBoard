package study.board.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class PostTitleResp {

    private String title;
    private Long contentId; // PostContent fk
    private String authorName;
    private LocalDateTime postCreateTime;
    private Long likeCount;

    @Builder
    public PostTitleResp(String title, Long contentId, String authorName, LocalDateTime postCreateTime, Long likeCount) {
        this.title = title;
        this.contentId = contentId;
        this.authorName = authorName;
        this.postCreateTime = postCreateTime;
        this.likeCount = likeCount;
    }
}
