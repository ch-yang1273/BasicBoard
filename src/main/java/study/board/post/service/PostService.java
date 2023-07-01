package study.board.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.post.dto.PostTitleResp;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    @Transactional(readOnly = true)
    public List<PostTitleResp> getPostList(String boardName) {
        PostTitleResp resp = PostTitleResp.builder()
                .title("title")
                .contentId(1L)
                .authorName("author")
                .postCreateTime(LocalDateTime.now())
                .likeCount(10L)
                .build();
        return List.of(resp);
    }
}
