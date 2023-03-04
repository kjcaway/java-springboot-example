package me.javaexample.javademo.api.member;

import me.javaexample.javademo.api.IntegrationTest;
import me.javaexample.javademo.api.member.dto.MemberDto;
import me.javaexample.javademo.api.member.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class MemberControllerTest extends IntegrationTest {

    @Autowired
    MemberService memberService;

    @Test
    void getMembers() {
        System.out.println("StartTest!");
        List<MemberDto> mList = memberService.getMembers();

        Assertions.assertEquals(mList.size(), 2);
    }
}