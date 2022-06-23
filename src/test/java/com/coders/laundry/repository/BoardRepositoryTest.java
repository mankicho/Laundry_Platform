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
    void categoryList(){
        List<CategoryEntity> result = boardRepository.categoryList();
        assertNotNull(result);
    }

    @Test
    void init(){
        // insert
        // Arrange
        PostEntity expected = PostEntity.builder()
                .categoryId(1)
                .writer(1)
                .title("first post")
                .contents("first contents")
                .build();

        // Act
        int result = boardRepository.postEnroll(expected);

        // Assert
        assertEquals(1, result);

        // selectById
        // Act
        PostEntity selected = boardRepository.selectById(1);
        // 아직 데이터가 없어 새로 생성되는 데이터의 id는 1로 시작된다.
        // builder로 생성한 객체는 default값이 null로 표기되어 get메서드가 적용되지 않아서 위와같이 작성

        // Assert
        assertEquals(1, selected.getPostId());
        assertEquals(1,selected.getCategoryId());
        assertEquals(1, selected.getWriter());
        assertEquals("first post",selected.getTitle());
        assertEquals("first contents", selected.getContents());

        // update
        // Arrange
        PostEntity post = boardRepository.selectById(1);
        post.setTitle("update post");
        post.setContents("update contents");

        // Act
        result = boardRepository.postUpdate(post);

        // Assert
        assertEquals(1, result);

        // delete
        // Act
        result = boardRepository.postDelete(1);

        // Assert
        assertEquals(1, result);
        PostEntity deleted = boardRepository.selectById(1);
        assertNull(deleted);
    }
}
