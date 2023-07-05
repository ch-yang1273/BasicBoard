package study.board.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.board.account.dto.UserProfile;
import study.board.comment.domain.repository.CommentAndAuthorNameDto;
import study.board.comment.dto.SubmitCommentReq;
import study.board.comment.service.CommentService;
import study.board.common.authentication.LoginUser;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
@RestController
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping("/submit/{postId}")
    public ResponseEntity<Void> submitComment(@LoginUser UserProfile userProfile,
                              @PathVariable Long postId,
                              @RequestBody SubmitCommentReq dto) {
        commentService.save(userProfile, postId, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list/{postId}")
    public ResponseEntity<List<CommentAndAuthorNameDto>>  getComment(@PathVariable Long postId) {
        return ResponseEntity.ok().body(commentService.getComment(postId));
    }
}
