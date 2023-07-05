package study.board.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.board.account.dto.UserProfile;
import study.board.comment.domain.Comment;
import study.board.comment.domain.repository.CommentAndAuthorNameDto;
import study.board.comment.domain.repository.CommentMapper;
import study.board.comment.domain.service.CommentFinder;
import study.board.comment.dto.SubmitCommentReq;
import study.board.post.domain.PostFinder;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentMapper commentMapper;
    private final CommentFinder commentFinder;
    private final PostFinder postFinder;

    public void submitComment(UserProfile userProfile, Long postId, SubmitCommentReq dto) {
        postFinder.validatePostExistenceAndNotDeleted(postId);

        Comment comment = Comment.builder()
                .content(dto.getContent())
                .postId(postId)
                .authorId(userProfile.getId())
                .parentCommentId(null)
                .createTime(LocalDateTime.now())
                .build();
        commentMapper.save(comment);
    }

    public void submitChildComment(UserProfile userProfile, Long parentCommentId, SubmitCommentReq dto) {
        Comment parentComment = commentFinder.findByIdAndNotDeleted(parentCommentId);
        postFinder.validatePostExistenceAndNotDeleted(parentComment.getPostId());

        Comment comment = Comment.builder()
                .content(dto.getContent())
                .postId(parentComment.getPostId())
                .authorId(userProfile.getId())
                .parentCommentId(parentCommentId)
                .createTime(LocalDateTime.now())
                .build();
        commentMapper.save(comment);
    }

    public List<CommentAndAuthorNameDto> getComment(Long postId) {
        return commentFinder.findListCommentAndAuthorNameDtoByPostId(postId);
    }
}
