package com.coders.laundry.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostEntity {
    private int postId;
    private int categoryId;
    private int writer;
    private String title;
    private String contents;
    private LocalDateTime createDate;
    private int likeNum;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;
}
