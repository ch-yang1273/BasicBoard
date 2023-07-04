package study.board.post.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.board.post.repository.PostContentMapper;
import study.board.post.repository.PostMapper;

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

    public List<Post> findAllPostList() {
        return postMapper.findAll();
    }

    public List<Post> findListByBoardId(Long boardId) {
        return postMapper.findListByBoardId(boardId);
    }

    public PostContent findPostContentById(Long id) {
        return postContentMapper.findById(id).orElseThrow(
                () -> new NoSuchElementException("Could not find entity with id=" + id)
        );
    }
}
