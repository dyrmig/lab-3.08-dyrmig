package com.dmitri.lab308.repository;

import com.dmitri.lab308.enums.Status;
import com.dmitri.lab308.model.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    private Member member1;
    @BeforeEach
    void setUp() {
        member1 = new Member("Lolo", Status.ACTIVE, "26/01/2024");
        memberRepository.save(member1);
    }

    @AfterEach
    void tearDown() {
        memberRepository.deleteAll();
    }

    @Test
    void add_member_addmember(){
        Optional<Member> memberOptional = memberRepository.findById(member1.getId());
        assertEquals("Lolo", memberOptional.get().getName());
    }
}