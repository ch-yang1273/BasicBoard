package study.board.post.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import study.board.post.repository.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class PostEditor {

    private final PostFinder postFinder;
    private final PostMapper postMapper;
    private final PostContentMapper postContentMapper;

    public void validatePostEditPermission(Long accessorId, Long authorId) {
        if (!accessorId.equals(authorId)) {
            throw new AccessDeniedException("수정 권한이 없습니다");
        }
    }

    public void editPost(Long accessorId, Long postId, String title, String content) {
        Post post = postFinder.findPostById(postId);
        validatePostEditPermission(accessorId, post.getAuthorId());

        EditPostDto postDto = EditPostDto.builder()
                .id(postId)
                .title(title)
                .viewCount(post.getViewCount())
                .modifiedTime(LocalDateTime.now())
                .build();

        EditPostContentDto postContentDto = new EditPostContentDto(post.getContentId(), content);

        postMapper.edit(postDto);
        postContentMapper.edit(postContentDto);
    }

    public void softDelete(Long accessorId, Long postId) {
        Post post = postFinder.findPostById(postId);
        validatePostEditPermission(accessorId, post.getAuthorId());

        DeletePostDto dto = new DeletePostDto(postId, LocalDateTime.now());
        postMapper.softDelete(dto);
    }
}
