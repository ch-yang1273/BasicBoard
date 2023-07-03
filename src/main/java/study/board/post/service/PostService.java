package study.board.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.account.domain.AccountFinder;
import study.board.account.dto.UserProfile;
import study.board.post.domain.*;
import study.board.post.dto.EntirePostResp;
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

    private final PostFinder postFinder;
    private final PostEditor postEditor;

    @Transactional
    public void savePost(UserProfile profile, String board, String title, String contents) {

        // PostContent 저장
        PostContent postContent = new PostContent(contents);
        postContentMapper.save(postContent);

        // Post 저장
        Post post = Post.builder()
                .title(title)
                .contentId(postContent.getId())
                .authorId(profile.getId())
                .boardId(1L)
                .createTime(LocalDateTime.now())
                .build();
        postMapper.save(post);

        log.info("Save Post. id={}", post.getId());
        log.info("Save PostContent. id={}", postContent.getId());
    }

    @Transactional(readOnly = true)
    public List<PostTitleResp> getPostList(String boardName) {
        log.info("getPostList ={}", boardName);
        List<Post> postList = postFinder.findPostList();

        List<PostTitleResp> resultList = new ArrayList<>();
        for (Post post : postList) {
            String nickname = accountFinder.findNicknameByIdOrUnknown(post.getAuthorId());
            resultList.add(PostTitleResp.of(post, nickname, 10L));
        }

        return resultList;
    }

    @Transactional
    public EntirePostResp getEntirePost(Long postId) {
        Post post = postFinder.findPostById(postId);
        PostContent postContent = postFinder.findPostContentById(post.getContentId());
        String authorNickname = accountFinder.findNicknameByIdOrUnknown(post.getAuthorId());
        Long likeCount = 10L;

        PostTitleResp postTitleResp = PostTitleResp.of(post, authorNickname, likeCount);
        return new EntirePostResp(postTitleResp, postContent.getContent());
    }

    @Transactional(readOnly = true)
    public void validatePostEditPermission(UserProfile userProfile, Long postId) {
        Post post = postFinder.findPostById(postId);
        postEditor.validatePostEditPermission(userProfile.getId(), post.getAuthorId());
    }

    @Transactional
    public void editPost(UserProfile userProfile, Long postId, String title, String content) {
        postEditor.editPost(userProfile.getId(), postId, title, content);
    }

    @Transactional
    public void softDelete(UserProfile userProfile, Long postId) {
        postEditor.softDelete(userProfile.getId(), postId);
    }
}
