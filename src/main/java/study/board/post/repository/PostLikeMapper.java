package study.board.post.repository;

import org.apache.ibatis.annotations.Mapper;
import study.board.post.domain.PostLike;

import java.util.Optional;

@Mapper
public interface PostLikeMapper {

    void save(PostLike postLike);

    void delete(DeletePostLikeDto dto);

    Optional<PostLike> findPostLike(SelectPostLikeDto dto);

    Long countPostLike(Long postId);
}
