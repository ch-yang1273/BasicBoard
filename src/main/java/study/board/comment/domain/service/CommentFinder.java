package study.board.comment.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import study.board.comment.domain.Comment;
import study.board.comment.domain.repository.CommentAndAuthorNameDto;
import study.board.comment.domain.repository.CommentMapper;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class CommentFinder {

    private final CommentMapper commentMapper;

    public List<Comment> findListByPostId(Long postId) {
        return commentMapper.findListByPostId(postId);
    }

    public List<CommentAndAuthorNameDto> findListCommentAndAuthorNameDtoByPostId(Long postId) {
        List<CommentAndAuthorNameDto> dtoList = commentMapper.findListCommentAndAuthorNameByPostId(postId);
        log.info("list size={}", dtoList.size());
        return dtoList;
    }
}
