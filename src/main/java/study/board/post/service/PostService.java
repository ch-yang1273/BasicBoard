package study.board.post.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.account.domain.AccountFinder;
import study.board.account.dto.UserProfile;
import study.board.board.domain.Board;
import study.board.board.domain.BoardFinder;
import study.board.post.domain.*;
import study.board.post.dto.EntirePostResp;
import study.board.post.dto.PostTitleResp;
import study.board.post.repository.PostContentMapper;
import study.board.post.repository.PostMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final PostMapper postMapper;
    private final PostContentMapper postContentMapper;

    private final AccountFinder accountFinder;
    private final PostFinder postFinder;
    private final PostEditor postEditor;
    private final BoardFinder boardFinder;

    @Transactional
    public void savePost(UserProfile profile, String boardName, String title, String contents) {
        Board board = boardFinder.findByName(boardName);

        // PostContent 저장
        PostContent postContent = new PostContent(contents);
        postContentMapper.save(postContent);

        // Post 저장
        Post post = Post.builder()
                .title(title)
                .contentId(postContent.getId())
                .authorId(profile.getId())
                .boardId(board.getId())
                .createTime(LocalDateTime.now())
                .build();
        postMapper.save(post);
    }

    @Transactional(readOnly = true)
    public List<PostTitleResp> getPostList(String boardName) {
        Board board = boardFinder.findByName(boardName);
        List<Post> postList = postFinder.findListByBoardId(board.getId());

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
