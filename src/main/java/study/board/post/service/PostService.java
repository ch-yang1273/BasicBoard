package study.board.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.account.domain.Account;
import study.board.account.domain.AccountFinder;
import study.board.account.domain.AccountMapper;
import study.board.post.domain.Post;
import study.board.post.domain.PostContent;
import study.board.post.domain.PostContentMapper;
import study.board.post.domain.PostMapper;
import study.board.post.dto.PostTitleResp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final AccountFinder accountFinder;
    private final PostMapper postMapper;
    private final PostContentMapper postContentMapper;

    @Transactional(readOnly = true)
    public List<PostTitleResp> getPostList(String boardName) {
        log.info("getPostList ={}", boardName);
        List<Post> postList = postMapper.findAll();

        List<PostTitleResp> resultList = new ArrayList<>();
        for (Post post : postList) {
            String nickname = accountFinder.findNicknameByIdOrUnknown(post.getId());
            resultList.add(PostTitleResp.of(post, nickname, 10L));
        }

        return resultList;
    }

    @Transactional
    public void savePost(String board, String title, String contents) {

        // PostContent 저장
        PostContent postContent = new PostContent(contents);
        postContentMapper.save(postContent);

        // Post 저장
        Post post = Post.builder()
                .title(title)
                .contentId(postContent.getId())
                .authorId(1L)
                .boardId(1L)
                .createTime(LocalDateTime.now())
                .build();
        postMapper.save(post);

        log.info("Save Post. id={}", post.getId());
        log.info("Save PostContent. id={}", postContent.getId());
    }
}
