package study.board.post.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Post {

    private Long id;
    private String title;

    private Long contentId; // PostContent fk

    private Long authorId; // Account fk
    private Long boardId; // Board fk

    private int viewCount;
    private boolean isDeleted;

    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;

    public Post(Long id, String title, Long contentId, Long authorId, Long boardId, int viewCount,
                boolean isDeleted, LocalDateTime createTime, LocalDateTime modifiedTime) {
        this.id = id;
        this.title = title;
        this.contentId = contentId;
        this.authorId = authorId;
        this.boardId = boardId;
        this.viewCount = viewCount;
        this.isDeleted = isDeleted;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }

    @Builder
    public Post(String title, Long contentId, Long authorId, Long boardId,
                LocalDateTime createTime) {
        this.title = title;
        this.contentId = contentId;
        this.authorId = authorId;
        this.boardId = boardId;
        this.viewCount = 0;
        this.isDeleted = false;
        this.createTime = createTime;
        this.modifiedTime = this.createTime;
    }
}
