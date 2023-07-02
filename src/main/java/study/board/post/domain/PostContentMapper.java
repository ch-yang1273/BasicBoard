package study.board.post.domain;

import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface PostContentMapper {

    void save(PostContent postContent);

    Optional<PostContent> findById(Long id);
}
