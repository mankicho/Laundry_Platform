package com.coders.laundry.repository;

import com.coders.laundry.domain.entity.CategoryEntity;
import com.coders.laundry.domain.entity.PostEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("dev")
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    void categoryList() {
        List<CategoryEntity> result = boardRepository.categoryList();
        assertNotNull(result);
    }

    @Test
    void selectHotPosts() {
        // todo.
    }

    @Test
    void selectSpecificCategoryPosts() {
        // todo.
    }

    @Test
    void writePost() {
        // Arrange
        PostEntity expected = PostEntity.builder()
                .categoryId(1)
                .writer(1)
                .title("first post")
                .contents("first contents")
                .build();

        // Act
        int result = boardRepository.writePost(expected);

        // Assert
        assertEquals(1, result);

        int generatedPostId = expected.getPostId();
        PostEntity actual = boardRepository.selectById(generatedPostId);
        assertEquals(expected, actual);
    }

    @Test
    void selectById() {
        // Arrange
        PostEntity post = PostEntity.builder()
                .categoryId(1)
                .writer(1)
                .title("first post")
                .contents("first contents")
                .build();
        int writeCount = boardRepository.writePost(post);
        assertEquals(1, writeCount);
        int generatedPostId = post.getPostId();

        // Act
        PostEntity selected = boardRepository.selectById(generatedPostId);

        // Assert
        assertEquals(1, selected.getPostId());
        assertEquals(1, selected.getCategoryId());
        assertEquals(1, selected.getWriter());
        assertEquals("first post", selected.getTitle());
        assertEquals("first contents", selected.getContents());
    }

    @Test
    void updatePost() {
        // Arrange
        PostEntity post = PostEntity.builder()
                .categoryId(1)
                .writer(1)
                .title("first post")
                .contents("first contents")
                .build();
        post.setTitle("update title");
        post.setContents("update contents");

        // Act
        int result = boardRepository.updatePost(post);

        // Assert
        assertEquals(1, result);
        assertEquals("update title", boardRepository.selectById(1).getTitle());
        assertEquals("update contents", boardRepository.selectById(1).getContents());
    }

    @Test
    void deletePost() {
        // Arrange
        PostEntity post = PostEntity.builder()
                .categoryId(1)
                .writer(1)
                .title("temp post")
                .contents("temp contents")
                .build();
        int writeCount = boardRepository.writePost(post);
        assertEquals(1, writeCount);

        int generatedPostId = post.getPostId();

        // Act
        int result = boardRepository.deletePost(generatedPostId);

        // Assert
        assertEquals(1, result);
        PostEntity deleted = boardRepository.selectById(generatedPostId);
        assertNull(deleted);
    }
}
