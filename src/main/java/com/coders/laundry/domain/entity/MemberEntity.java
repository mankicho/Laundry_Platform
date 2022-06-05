package com.coders.laundry.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberEntity {
    private int memberId;
    private String phoneNum;
    private String password;
    private String nickname;
    private LocalDateTime birthday;
    private String gender;
    private boolean autoLoginYn;
    private LocalDateTime joinDate;
}
