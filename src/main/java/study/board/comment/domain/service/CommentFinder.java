package study.board.comment.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.board.comment.domain.Comment;
import study.board.comment.domain.repository.CommentMapper;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CommentFinder {

    private final CommentMapper commentMapper;

    public List<Comment> findListByPostId(Long postId) {
        return commentMapper.findListByPostId(postId);
    }
}
