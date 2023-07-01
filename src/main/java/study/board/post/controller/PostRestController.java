package study.board.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.board.post.dto.PostTitleResp;
import study.board.post.service.PostService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
@RestController
public class PostRestController {

    private final PostService postService;

    @GetMapping("/boardName")
    public ResponseEntity<List<PostTitleResp>> getPostList(@PathVariable String boardName) {
        return ResponseEntity.ok().body(postService.getPostList(boardName));
    }
}
