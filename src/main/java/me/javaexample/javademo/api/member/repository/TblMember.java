package me.javaexample.javademo.api.member.repository;

import com.querydsl.core.Tuple;
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

    @Transient
    private String categoryName;

    public TblMember(MemberDto dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.category = dto.getCategory();
    }

    public TblMember(Tuple tuple) {
        this.id = tuple.get(0, Long.class);
        this.name = tuple.get(1, String.class);
        this.email = tuple.get(2, String.class);
        this.category = tuple.get(3, String.class);
        this.categoryName = tuple.get(4, String.class);
    }
}
