package study.board.post.repository;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EditPostDto {

    private final Long id;
    private final String title;
    private final LocalDateTime modifiedTime;

    @Builder
    public EditPostDto(Long id, String title, LocalDateTime modifiedTime) {
        this.id = id;
        this.title = title;
        this.modifiedTime = modifiedTime;
    }
}
