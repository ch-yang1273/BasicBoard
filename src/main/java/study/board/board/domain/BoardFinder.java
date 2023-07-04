package study.board.board.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.board.board.repository.BoardMapper;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class BoardFinder {

    private final BoardMapper boardMapper;

    public Board findByName(String name) {
        return boardMapper.findByName(name).orElseThrow(
                () -> new NoSuchElementException("Could not find entity with name=" + name)
        );
    }
}
