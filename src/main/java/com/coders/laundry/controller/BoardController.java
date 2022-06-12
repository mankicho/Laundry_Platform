package com.coders.laundry.controller;

import com.coders.laundry.domain.entity.MemberEntity;
import com.coders.laundry.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board")
    public String board(Model model) {
        // todo. operate
        return "board";
    }
}
