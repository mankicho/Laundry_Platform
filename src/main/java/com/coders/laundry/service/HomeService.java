package com.coders.laundry.service;

import com.coders.laundry.domain.entity.MemberEntity;
import com.coders.laundry.dto.MemberDTO;
import com.coders.laundry.repository.HomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {

  private final HomeRepository homeRepository;
  private final TokenService tokenService;
  private final PasswordEncryptionService passwordEncryptionService;

  public List<MemberEntity> findAllTeamMemberList() {
    return homeRepository.selectAllTeamMemberList();
  }

  public MemberDTO create(MemberDTO memberDTO) {
    // 1. password 암호화
    MemberEntity memberEntity = memberDTO.getMemberEntity();
    String encrypted = passwordEncryptionService.encrypt(memberEntity.getPassword());
    memberEntity.setPassword(encrypted);

    // 2. 멤버 생성
    int key = homeRepository.create(memberEntity);
    if (key < 0) {
      throw new RuntimeException("에러발생");
    }

    memberEntity = homeRepository.selectById(key);

    // 3. 토큰 발급
    String token = tokenService.issue();
    memberDTO.setToken(token);
    memberDTO.setMemberEntity(memberEntity);

    return memberDTO;
  }

  public MemberEntity createAndFetch(MemberDTO memberDTO) {
    MemberEntity memberEntity = memberDTO.getMemberEntity();

    int key = homeRepository.create(memberEntity);

    return homeRepository.selectById(key);
  }
}
