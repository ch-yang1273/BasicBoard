package study.board.post.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.board.account.dto.UserProfile;
import study.board.common.authentication.LoginUser;
import study.board.post.dto.EntirePostResp;
import study.board.post.service.PostService;

@RequiredArgsConstructor
@RequestMapping("/post")
@Controller
public class PostController {

    private final PostService postService;

    @GetMapping("/view/{postId}")
    public String getEntirePost(Model model, @PathVariable Long postId) {
        EntirePostResp entirePost = postService.getEntirePost(postId);
        model.addAttribute("post", entirePost);
        return "/post/post";
    }

    @GetMapping("/edit/{postId}")
    public String editPost(Model model, @LoginUser UserProfile userProfile, @PathVariable Long postId) {
        postService.validatePostEditPermission(userProfile, postId);

        EntirePostResp entirePost = postService.getEntirePost(postId);
        model.addAttribute("post", entirePost);
        return "/post/edit-post";
    }

    @GetMapping("/write")
    public String writePost(Model model, @RequestParam("board") String boardName) {
        model.addAttribute("board", boardName);
        return "/post/write-post";
    }
}
