package study.board.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.board.account.dto.UserProfile;
import study.board.common.authentication.LoginUser;
import study.board.post.dto.EntirePostResp;
import study.board.post.dto.LikePostReq;
import study.board.post.domain.PostInfo;
import study.board.post.dto.SubmitPostReq;
import study.board.post.service.PostService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
@RestController
public class PostRestController {

    private final PostService postService;

    @GetMapping("/list/{boardName}")
    public ResponseEntity<List<PostInfo>> getPostList(@LoginUser UserProfile userProfile, @PathVariable String boardName) {
        return ResponseEntity.ok().body(postService.getPostList(userProfile, boardName));
    }

    @GetMapping("/info/{postId}")
    public ResponseEntity<PostInfo> getPostInfo(@LoginUser UserProfile userProfile, @PathVariable Long postId) {
        return ResponseEntity.ok().body(postService.getPostInfo(userProfile, postId));
    }

    @PostMapping("/submit/{boardName}")
    public ResponseEntity<Void> submitPost(@LoginUser UserProfile userProfile,
                                           @PathVariable String boardName,
                                           @RequestBody SubmitPostReq dto) {
        postService.savePost(userProfile, boardName, dto.getTitle(), dto.getContent());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/edit/{postId}")
    public ResponseEntity<Void> editPost(@LoginUser UserProfile userProfile,
                                         @PathVariable Long postId,
                                         @RequestBody SubmitPostReq dto) {
        postService.editPost(userProfile, postId, dto.getTitle(), dto.getContent());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete/{postId}")
    public ResponseEntity<Void> deletePost(@LoginUser UserProfile userProfile, @PathVariable Long postId) {
        postService.softDelete(userProfile, postId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/like/{postId}")
    public ResponseEntity<Void> likePost(@LoginUser UserProfile userProfile,
                                         @PathVariable Long postId,
                                         @RequestBody LikePostReq dto) {
        postService.likePost(userProfile, postId, dto.getLike());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{postId}")
    public ResponseEntity<EntirePostResp> getEntirePost(@LoginUser UserProfile userProfile, @PathVariable Long postId) {
        return ResponseEntity.ok().body(postService.getEntirePost(userProfile, postId));
    }
}
