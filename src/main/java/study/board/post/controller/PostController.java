package study.board.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import study.board.post.dto.EntirePostResp;
import study.board.post.service.PostService;

@RequiredArgsConstructor
@RequestMapping("/post")
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping("/{postId}")
    public String getEntirePost(Model model, @PathVariable Long postId) {
        EntirePostResp entirePost = postService.getEntirePost(postId);
        model.addAttribute("post", entirePost);
        return "/post/post";
    }
}