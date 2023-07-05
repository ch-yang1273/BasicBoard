package study.board.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.board.comment.domain.service.CommentFinder;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentFinder commentFinder;
}
