package me.javaexample.javademo.api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.javaexample.javademo.api.member.repository.TblMember;

@Getter
@AllArgsConstructor
public class MemberDto {
    private String name;
    private String email;
    private String category;

    public MemberDto(TblMember member){
        this.name = member.getName();
        this.email = member.getEmail();
        this.category = member.getCategory();
    }
    
    public void setDefaultCategory(){
        this.category = "M001"; // 학생 카테고리
    }
}
