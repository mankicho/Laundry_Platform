package com.coders.laundry.service;

import com.coders.laundry.domain.entity.CategoryEntity;
import com.coders.laundry.domain.entity.PostEntity;
import com.coders.laundry.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;

public class BoardServiceTest {

    @InjectMocks
    private BoardService boardService;
    @Mock
    private BoardRepository boardRepository;


    @Test
    @DisplayName("커뮤니티 메인 진입시 카테고리 목록과 인기글 확인")
    void init(){
        CategoryEntity category1 = CategoryEntity.builder()
                .categoryId(1)
                .categoryName("자유")
                .build();
        CategoryEntity category2 = CategoryEntity.builder()
                .categoryId(2)
                .categoryName("로운")
                .build();
        CategoryEntity category3 = CategoryEntity.builder()
                .categoryId(3)
                .categoryName("세상")
                .build();

        PostEntity post1 = PostEntity.builder()
                .postId(1)
                .categoryId(1)
                .writer(1)
                .title("1번핫글")
                .contents("1번핫내용")
                .createDate(LocalDate.now())
                .likeNum(100)
                .build();
        PostEntity post2 = PostEntity.builder()
                .postId(2)
                .categoryId(1)
                .writer(1)
                .title("1번그냥글")
                .contents("1번그냥내용")
                .createDate(LocalDate.now())
                .likeNum(0)
                .build();
        PostEntity post3 = PostEntity.builder()
                .postId(3)
                .categoryId(1)
                .writer(1)
                .title("1번예전핫글")
                .contents("1번예전내용")
                .createDate(LocalDate.now().minusMonths(1))
                .likeNum(150)
                .build();
        PostEntity post4 = PostEntity.builder()
                .postId(4)
                .categoryId(2)
                .writer(1)
                .title("2번글")
                .contents("2번내용")
                .createDate(LocalDate.now())
                .likeNum(10)
                .build();
        PostEntity post5 = PostEntity.builder()
                .postId(5)
                .categoryId(3)
                .writer(1)
                .title("3번옛날글")
                .contents("3번내용")
                .createDate(LocalDate.now().minusWeeks(3))
                .likeNum(100)
                .build();

        // todo. something
    }
}
