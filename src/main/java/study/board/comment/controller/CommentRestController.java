package study.board.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.board.comment.service.CommentService;

@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
@RestController
public class CommentRestController {

    private final CommentService commentService;

    public void save() {

    }
}
