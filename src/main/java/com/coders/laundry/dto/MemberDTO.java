package com.coders.laundry.dto;

import com.coders.laundry.domain.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {

  private MemberEntity memberEntity;

  private String token;

}
