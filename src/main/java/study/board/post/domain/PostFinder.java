package study.board.post.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.board.account.domain.AccountFinder;
import study.board.post.repository.PostContentMapper;
import study.board.post.repository.PostLikeMapper;
import study.board.post.repository.PostMapper;
import study.board.post.repository.SelectPostLikeDto;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class PostFinder {

    private final PostMapper postMapper;
    private final PostContentMapper postContentMapper;
    private final PostLikeMapper postLikeMapper;

    private final AccountFinder accountFinder;

    public Post findPostByPostId(Long postId) {
        return postMapper.findById(postId).orElseThrow(
                () -> new NoSuchElementException("Could not find entity with postId=" + postId)
        );
    }

    public List<Post> findListByBoardId(Long boardId) {
        return postMapper.findListByBoardId(boardId);
    }

    public PostContent findPostContentById(Long id) {
        return postContentMapper.findById(id).orElseThrow(
                () -> new NoSuchElementException("Could not find entity with id=" + id)
        );
    }

    public PostInfo findPostInfoByPostId(Long postId, Long accessorId) {
        Post post = findPostByPostId(postId);
        String authorName = accountFinder.findNicknameByIdOrUnknown(post.getAuthorId());
        Long postLikeCount = getPostLikeCount(postId);
        Boolean isLiked = isPostLikedByUser(postId, accessorId);

        return PostInfo.of(post, authorName, postLikeCount, isLiked);
    }

    private Boolean isPostLikedByUser(Long postId, Long likerId) {
        SelectPostLikeDto dto = new SelectPostLikeDto(postId, likerId);
        return postLikeMapper.findPostLike(dto).isPresent();
    }

    private Long getPostLikeCount(Long postId) {
        return postLikeMapper.countPostLike(postId);
    }

    public void validatePostExistenceAndNotDeleted(Long postId) {
        if (!postMapper.existPostWithNotDeleted(postId)) {
            throw new NoSuchElementException("Could not find entity with postIdd=" + postId);
        }
    }
}
