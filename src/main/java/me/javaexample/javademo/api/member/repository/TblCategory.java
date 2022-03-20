package me.javaexample.javademo.api.member.repository;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "tbl_category")
public class TblCategory {
    @Id
    @Column(name = "cateogry_id")
    private String categoryId;

    @Column
    private String type;

    @Column
    private String name;

}
