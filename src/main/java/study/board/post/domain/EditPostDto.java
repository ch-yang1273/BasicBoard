package study.board.post.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EditPostDto {

    private Long id;
    private String title;
    private int viewCount;
    private LocalDateTime modifiedTime;

    @Builder
    public EditPostDto(Long id, String title, int viewCount, LocalDateTime modifiedTime) {
        this.id = id;
        this.title = title;
        this.viewCount = viewCount;
        this.modifiedTime = modifiedTime;
    }
}
