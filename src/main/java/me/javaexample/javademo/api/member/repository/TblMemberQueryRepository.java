package me.javaexample.javademo.api.member.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TblMemberQueryRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<TblMember> findMemberByCategory(String category) {
        QTblMember qTblMember = QTblMember.tblMember;
        QTblCategory qTblCategory = QTblCategory.tblCategory;

        List<Tuple> list = jpaQueryFactory.select(qTblMember.id, qTblMember.name, qTblMember.email,
                qTblMember.category, qTblCategory.name)
            .from(qTblMember)
            .innerJoin(qTblCategory)
            .on(qTblCategory.categoryId.eq(qTblMember.category))
            .where(qTblMember.category.eq(category))
            .fetch();

        return list.stream()
            .map(TblMember::new)
            .collect(Collectors.toList());
    }


    public Page<TblMember> findMemberByPage(Pageable pageable) {
        QTblMember qTblMember = QTblMember.tblMember;
        QTblCategory qTblCategory = QTblCategory.tblCategory;

        QueryResults<Tuple> result = jpaQueryFactory.select(qTblMember.id, qTblMember.name,
                qTblMember.email, qTblMember.category, qTblCategory.name)
            .from(qTblMember)
            .innerJoin(qTblCategory)
            .on(qTblCategory.categoryId.eq(qTblMember.category))
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qTblMember.id.desc())
            .fetchResults();

        List<TblMember> list = result.getResults()
            .stream()
            .map(TblMember::new)
            .collect(Collectors.toList());

        return new PageImpl<>(list, pageable, result.getTotal());
    }
}
