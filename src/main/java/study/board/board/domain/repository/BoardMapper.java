package study.board.board.domain.repository;

import org.apache.ibatis.annotations.Mapper;
import study.board.board.domain.Board;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {

    List<Board> findAll();

    Optional<Board> findByName(String name);
}
