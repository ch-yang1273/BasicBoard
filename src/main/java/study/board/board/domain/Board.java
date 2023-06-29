package study.board.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Board {

    private Long id;
    private String name;

    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;
}
