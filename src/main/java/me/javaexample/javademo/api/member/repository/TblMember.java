package me.javaexample.javademo.api.member.repository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.javaexample.javademo.api.member.dto.MemberDto;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_member")
public class TblMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String email;

    @Column()
    private String category;

    public TblMember(MemberDto dto){
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.category = dto.getCategory();
    }
}
