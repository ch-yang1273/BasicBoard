package study.board.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.post.dto.PostTitleResp;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    @Transactional(readOnly = true)
    public List<PostTitleResp> getPostList(String boardName) {
        return List.of(new PostTitleResp());
    }
}
