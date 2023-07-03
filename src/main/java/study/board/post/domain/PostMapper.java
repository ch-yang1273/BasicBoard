package study.board.post.domain;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {

    void save(Post post);

    void edit(EditPostDto dto);

    void softDelete(DeletePostDto dto);

    Optional<Post> findById(Long id);

    List<Post> findAll();
}
