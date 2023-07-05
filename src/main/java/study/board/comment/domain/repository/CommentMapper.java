package study.board.comment.domain.repository;

import org.apache.ibatis.annotations.Mapper;
import study.board.comment.domain.Comment;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommentMapper {

    void save(Comment comment);

    List<CommentAndAuthorNameDto> findListCommentAndAuthorNameByPostId(Long postId);

    Optional<Comment> findByIdAndNotDeleted(Long id);
}
