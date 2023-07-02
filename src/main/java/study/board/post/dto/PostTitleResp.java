package study.board.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.board.post.domain.Post;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class PostTitleResp {

    private Long postId;
    private String title;
    private String authorName;
    private LocalDateTime postCreateTime;
    private Long likeCount;

    @Builder
    public PostTitleResp(Long postId, String title, String authorName, LocalDateTime postCreateTime, Long likeCount) {
        this.postId = postId;
        this.title = title;
        this.authorName = authorName;
        this.postCreateTime = postCreateTime;
        this.likeCount = likeCount;
    }

    public static PostTitleResp of(Post post, String authorName, Long likeCount) {
        return PostTitleResp.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .authorName(authorName)
                .postCreateTime(post.getCreateTime())
                .likeCount(likeCount)
                .build();
    }
}
