package study.board.post.domain.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class DeletePostDto {

    private Long id;
    private LocalDateTime modifiedTime;
}
