package study.board.post.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import study.board.post.domain.Post;
import study.board.post.domain.PostLike;
import study.board.post.domain.repository.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class PostEditor {

    private final PostFinder postFinder;
    private final PostMapper postMapper;
    private final PostContentMapper postContentMapper;
    private final PostLikeMapper postLikeMapper;

    public void validatePostEditPermission(Long accessorId, Long authorId) {
        if (!accessorId.equals(authorId)) {
            throw new AccessDeniedException("수정 권한이 없습니다");
        }
    }

    public void editPost(Long accessorId, Long postId, String title, String content) {
        Post post = postFinder.findPostByPostId(postId);
        validatePostEditPermission(accessorId, post.getAuthorId());

        EditPostDto postDto = EditPostDto.builder()
                .id(postId)
                .title(title)
                .modifiedTime(LocalDateTime.now())
                .build();

        EditPostContentDto postContentDto = new EditPostContentDto(post.getContentId(), content);

        postMapper.editTitle(postDto);
        postContentMapper.edit(postContentDto);
    }

    public void softDelete(Long accessorId, Long postId) {
        Post post = postFinder.findPostByPostId(postId);
        validatePostEditPermission(accessorId, post.getAuthorId());

        DeletePostDto dto = new DeletePostDto(postId, LocalDateTime.now());
        postMapper.softDelete(dto);
    }

    public void likePost(Long likerId, Long postId) {
        Post post = postFinder.findPostByPostId(postId);
        PostLike postLike = new PostLike(post.getId(), likerId, LocalDateTime.now());
        postLikeMapper.save(postLike);
    }

    public void unlikePost(Long likerId, Long postId) {
        Post post = postFinder.findPostByPostId(postId);
        DeletePostLikeDto dto = new DeletePostLikeDto(post.getId(), likerId);
        postLikeMapper.delete(dto);
    }

    public void increasePostViewCount(Long postId) {
        postMapper.increaseViewCount(postId);
    }
}
