package study.board.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.board.account.dto.UserProfile;
import study.board.comment.domain.Comment;
import study.board.comment.domain.repository.CommentAndAuthorNameDto;
import study.board.comment.domain.repository.CommentMapper;
import study.board.comment.domain.service.CommentFinder;
import study.board.comment.dto.SubmitCommentReq;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentMapper commentMapper;
    private final CommentFinder commentFinder;

    public void save(UserProfile userProfile, Long postId, SubmitCommentReq dto) {
        Comment comment = Comment.builder()
                .content(dto.getContent())
                .postId(postId)
                .authorId(userProfile.getId())
                .parentCommentId(null)
                .createTime(LocalDateTime.now())
                .build();
        commentMapper.save(comment);
    }

    public List<CommentAndAuthorNameDto> getComment(Long postId) {
        return commentFinder.findListCommentAndAuthorNameDtoByPostId(postId);
    }
}
