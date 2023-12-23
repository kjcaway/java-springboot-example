package me.javaexample.javademo.api.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.javaexample.javademo.api.member.repository.TblMember;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDto {

    private String name;
    private String email;
    private String category;
    private String categoryName;

    public MemberDto(TblMember member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.category = member.getCategory();
        this.categoryName = member.getCategoryName();
    }

    public void setDefaultCategory() {
        this.category = "M001"; // 학생 카테고리
    }
}
