package study.board.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.board.post.dto.PostTitleResp;
import study.board.post.dto.SubmitPostReq;
import study.board.post.service.PostService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
@RestController
public class PostRestController {

    private final PostService postService;

    @GetMapping("/{boardName}")
    public ResponseEntity<List<PostTitleResp>> getPostList(@PathVariable String boardName) {
        return ResponseEntity.ok().body(postService.getPostList(boardName));
    }

    @PostMapping("/{boardName}/submit")
    public ResponseEntity<Void> submitPost(@PathVariable String boardName, @RequestBody SubmitPostReq request) {
        postService.savePost(boardName, request.getTitle(), request.getContents());
        return ResponseEntity.ok().build();
    }
}
