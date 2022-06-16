package com.coders.laundry.controller;

import com.coders.laundry.domain.entity.CategoryEntity;
import com.coders.laundry.domain.entity.MemberEntity;
import com.coders.laundry.domain.entity.PostEntity;
import com.coders.laundry.repository.BoardRepository;
import com.coders.laundry.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/board")
    public String board(Model model) {
        // 카테고리 목록 조회
        List<CategoryEntity> categories = boardService.findAllCategory();
        model.addAttribute("categories", categories);
        // 게시판 별 인기글 조회
        List<PostEntity> posts = boardService.findHotPost();
        model.addAttribute("posts", posts);

        return "board";
    }
}
