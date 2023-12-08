package me.javaexample.javademo.api.member.repository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_member_detail")
public class TblMemberDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long memberId;

    @Column(nullable = false, length = 100)
    private String version;

    @Column(nullable = false, length = 100)
    private String detailInfo;
}
