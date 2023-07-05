package study.board.comment.domain.repository;

import org.apache.ibatis.annotations.Mapper;
import study.board.comment.domain.Comment;

import java.util.List;

@Mapper
public interface CommentMapper {

    void save(Comment comment);

    List<Comment> findListByPostId(Long postId);

    List<CommentAndAuthorNameDto> findListCommentAndAuthorNameByPostId(Long postId);
}
