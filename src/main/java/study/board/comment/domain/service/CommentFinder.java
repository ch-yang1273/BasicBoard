package study.board.comment.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import study.board.comment.domain.Comment;
import study.board.comment.domain.repository.CommentAndAuthorNameDto;
import study.board.comment.domain.repository.CommentMapper;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Component
public class CommentFinder {

    private final CommentMapper commentMapper;

    public List<CommentAndAuthorNameDto> findListCommentAndAuthorNameDtoByPostId(Long postId) {
        List<CommentAndAuthorNameDto> dtoList = commentMapper.findListCommentAndAuthorNameByPostId(postId);
        log.info("list size={}", dtoList.size());
        return dtoList;
    }

    public Comment findByIdAndNotDeleted(Long commentId) {
        return commentMapper.findByIdAndNotDeleted(commentId).orElseThrow(
                () -> new NoSuchElementException("Could not find entity with id=" + commentId)
        );
    }
}
