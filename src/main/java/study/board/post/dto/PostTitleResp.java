package study.board.post.dto;

import java.time.LocalDateTime;

public class PostTitleResp {

    private String title;
    private Long contentId; // PostContent fk
    private String authorName;
    private LocalDateTime postCreateTime;
    private Long likeCount;
}
