package study.board.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.board.account.dto.UserProfile;
import study.board.common.authentication.LoginUser;
import study.board.post.dto.EntirePostResp;
import study.board.post.dto.PostTitleResp;
import study.board.post.dto.SubmitPostReq;
import study.board.post.service.PostService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
@RestController
public class PostRestController {

    private final PostService postService;

    @GetMapping("/list/{boardName}")
    public ResponseEntity<List<PostTitleResp>> getPostList(@PathVariable String boardName) {
        return ResponseEntity.ok().body(postService.getPostList(boardName));
    }

    @PostMapping("/submit/{boardName}")
    public ResponseEntity<Void> submitPost(@LoginUser UserProfile userProfile, @PathVariable String boardName, @RequestBody SubmitPostReq dto) {
        postService.savePost(userProfile, boardName, dto.getTitle(), dto.getContents());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<EntirePostResp> getEntirePost(@PathVariable Long postId) {
        return ResponseEntity.ok().body(postService.getEntirePost(postId));
    }
}
