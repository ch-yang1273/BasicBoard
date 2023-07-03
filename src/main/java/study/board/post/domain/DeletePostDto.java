package study.board.post.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class DeletePostDto {

    private Long id;
    private LocalDateTime modifiedTime;
}
