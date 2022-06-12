package com.coders.laundry.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReplyEntity {
    private int replyId;
    private int commentId;
    private int writer;
    private String contents;
    private LocalDateTime createDate;
    private int likeNum;
    private LocalDateTime updateDate;
    private LocalDateTime deleteDate;
}
