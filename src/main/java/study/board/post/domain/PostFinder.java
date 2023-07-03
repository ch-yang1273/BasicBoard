package study.board.post.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class PostFinder {

    private final PostMapper postMapper;
    private final PostContentMapper postContentMapper;

    public Post findPostById(Long id) {
        return postMapper.findById(id).orElseThrow(
                () -> new NoSuchElementException("Could not find entity with id=" + id)
        );
    }

    public List<Post> findPostList() {
        return postMapper.findAll();
    }

    public PostContent findPostContentById(Long id) {
        return postContentMapper.findById(id).orElseThrow(
                () -> new NoSuchElementException("Could not find entity with id=" + id)
        );
    }
}
