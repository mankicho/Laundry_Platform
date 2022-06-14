package com.coders.laundry.domain.entity;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import lombok.NoArgsConstructor;

@Data
@Builder
public class MemberEntity {
    private int memberId;
    private String phoneNum;
    private String password;
    private String nickname;
    private LocalDate birthday;
    private String gender;
    private boolean autoLoginYn;
    private LocalDateTime joinDate;
}
