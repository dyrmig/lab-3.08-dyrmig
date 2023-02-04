package com.dmitri.lab308.repository;

import com.dmitri.lab308.enums.Status;
import com.dmitri.lab308.model.Chapter;
import com.dmitri.lab308.model.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ChapterRepositoryTest {
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private MemberRepository memberRepository;
    private Member member1, member2, member3;
    private Chapter chapter1;
    private List<Member> memberList;
    @BeforeEach
    void setUp() {
        member1 = new Member("Lolo", Status.ACTIVE, "26/01/2024");
        member2 = new Member("Lala", Status.ACTIVE, "26/01/2024");
        member3 = new Member("Pipi", Status.ACTIVE, "26/01/2024");
        chapter1 = new Chapter("Chapter1", "District1");

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        memberList = new ArrayList<Member>();
        memberList.add(member1);
        memberList.add(member2);
        memberList.add(member3);

        chapter1.setPresident(member1);
        chapter1.setMembers(memberList);
        chapterRepository.save(chapter1);

        member1.setChapter(chapter1);
        member2.setChapter(chapter1);
        member3.setChapter(chapter1);
        //hacemos save de nuevo para actualizar los miembros
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
    }

    @AfterEach
    void tearDown() {
        //quitamos toda relacion de chapter antes de proceder a borrar todo
        chapter1.setPresident(null);
        chapterRepository.save(chapter1);
        memberRepository.deleteAll();
        chapterRepository.deleteAll();
    }
    @Test
    void add_chapter_addChapter(){
        Optional<Chapter> chapterOptional = chapterRepository.findById(chapter1.getId());
        assertEquals("Chapter1", chapterOptional.get().getName());
        assertEquals("Lolo", chapterOptional.get().getPresident().getName());
    }
    @Test
    void add_members_addmembersToChapter(){
        Optional<Chapter> chapterOptional = chapterRepository.findByIdWithMembers(chapter1.getId());
        assertTrue(chapterOptional.isPresent());
        assertEquals("Chapter1", chapterOptional.get().getName());
        //assertEquals("Lolo", chapterOptional.get().getPresident().getName());
        assertEquals(3, chapterOptional.get().getMembers().size());
    }
}