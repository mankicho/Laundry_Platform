package com.coders.laundry.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.coders.laundry.domain.entity.MemberEntity;
import com.coders.laundry.dto.MemberDTO;
import com.coders.laundry.repository.HomeRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HomeServiceTest {

    @InjectMocks
    private HomeService homeService;
    @Mock
    private HomeRepository homeRepository;
    @Mock
    private TokenService tokenService;
    @Mock
    private PasswordEncryptionService passwordEncryptionService;


    @Test
    @DisplayName("멤버 생성할때 암호화, 토큰발급이 잘되었는지 체크")
    void create() {
        MemberEntity entity = MemberEntity.builder()
            .phoneNum("testPhoneNumber")
            .password("testPassword")
            .birthday(LocalDate.now())
            .gender("testGender")
            .nickname("testNickname")
            .build();

        MemberEntity expectedEntity = MemberEntity.builder()
            .phoneNum("testPhoneNumber")
            .password("encrypted")
            .autoLoginYn(false)
            .joinDate(LocalDateTime.now())
            .birthday(LocalDate.now())
            .gender("testGender")
            .nickname("testNickname")
            .build();

        MemberDTO input = MemberDTO.builder()
            .memberEntity(entity)
            .token(null)
            .build();

        MemberDTO expected = MemberDTO.builder()
            .memberEntity(expectedEntity)
            .token(tokenService.issue())
            .build();

        when(tokenService.issue()).thenReturn("issue");
        when(passwordEncryptionService.encrypt(entity.getPassword())).thenReturn("encrypted");
        when(homeRepository.create(entity)).thenReturn(1);
        when(homeRepository.selectById(1)).thenReturn(expectedEntity);

        MemberDTO resultDTO = homeService.create(input);

        assertNotNull(resultDTO);
        assertNotNull(resultDTO.getToken());
        assertEquals(
            expected.getMemberEntity().getPassword(),
            resultDTO.getMemberEntity().getPassword()
        );

    }
}
