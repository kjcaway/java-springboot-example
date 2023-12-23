package me.javaexample.javademo.api.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.javaexample.javademo.api.member.repository.TblMember;
import me.javaexample.javademo.api.member.repository.TblMemberDetail;
import me.javaexample.javademo.util.JsonUtils;

import java.util.Map;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberDetailDto {

    private Long id;
    private String name;
    private String email;
    private String category;
    private String categoryName;
    private String version;
    private Map<?, ?> detailInfo;

    public MemberDetailDto(TblMember member, TblMemberDetail detail) throws Exception {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
        this.category = member.getCategory();
        this.categoryName = member.getCategoryName();
        this.version = detail.getVersion();
        this.detailInfo = JsonUtils.convertToMap(detail.getDetailInfo());
    }
}
