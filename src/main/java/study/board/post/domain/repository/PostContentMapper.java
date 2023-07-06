package study.board.post.domain.repository;

import org.apache.ibatis.annotations.Mapper;
import study.board.post.domain.PostContent;

import java.util.Optional;

@Mapper
public interface PostContentMapper {

    void save(PostContent postContent);

    Optional<PostContent> findById(Long id);

    void edit(EditPostContentDto dto);
}
