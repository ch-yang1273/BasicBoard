package study.board.post.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class PostInfo {

    private Long postId;
    private String title;
    private String authorName;
    private LocalDateTime postCreateTime;
    private Long viewCount;
    private Long likeCount;
    private Boolean isLike;

    @Builder
    public PostInfo(Long postId, String title, String authorName, LocalDateTime postCreateTime,
                    Long viewCount, Long likeCount, Boolean isLike) {
        this.postId = postId;
        this.title = title;
        this.authorName = authorName;
        this.postCreateTime = postCreateTime;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.isLike = isLike;
    }

    static PostInfo of(Post post, String authorName, Long likeCount, Boolean isLike) {
        return PostInfo.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .authorName(authorName)
                .postCreateTime(post.getCreateTime())
                .viewCount(post.getViewCount())
                .likeCount(likeCount)
                .isLike(isLike)
                .build();
    }
}
