package com.coders.laundry.service;

import com.coders.laundry.domain.entity.MemberEntity;
import com.coders.laundry.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private MemberRepository memberRepository;

    MemberService(MemberRepository memberRepository){
        this.memberRepository= memberRepository;
    }

    public MemberEntity getMember(int id){
        return memberRepository.selectMember(id);
    }
}
