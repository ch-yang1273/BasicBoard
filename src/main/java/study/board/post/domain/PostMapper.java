package study.board.post.domain;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {

    void save(Post post);

    Optional<Post> findById(Long id);

    List<Post> findAll();
}
