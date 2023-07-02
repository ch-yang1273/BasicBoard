package study.board.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.post.dto.PostTitleResp;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
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

    @Transactional
    public void savePost(String board, String title, String contents) {
        log.info("board={}", board);
        log.info("title={}", title);
    }
}
