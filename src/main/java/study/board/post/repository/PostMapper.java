package study.board.post.repository;

import org.apache.ibatis.annotations.Mapper;
import study.board.post.domain.Post;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {

    void save(Post post);

    void editTitle(EditPostDto dto);

    void softDelete(DeletePostDto dto);

    Optional<Post> findById(Long id);

    List<Post> findListByBoardId(Long boardId);

    void increaseViewCount(Long postId);

    boolean existPostWithNotDeleted(Long postId);
}
