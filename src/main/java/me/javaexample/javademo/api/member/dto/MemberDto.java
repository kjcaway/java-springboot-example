package me.javaexample.javademo.api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.javaexample.javademo.api.member.repository.TblMember;

@Getter
@AllArgsConstructor
public class MemberDto {
    private String name;
    private String email;

    public MemberDto(TblMember member){
        this.name = member.getName();
        this.email = member.getEmail();
    }
}
