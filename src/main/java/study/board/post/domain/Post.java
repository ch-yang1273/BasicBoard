package study.board.post.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
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
}
