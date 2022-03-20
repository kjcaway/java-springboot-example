package me.javaexample.javademo.api.member.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TblMemberQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<TblMember> findMemberByCategory(String category){
        QTblMember qTblMember = QTblMember.tblMember;
        QTblCategory qTblCategory = QTblCategory.tblCategory;

        List<Tuple> list =  jpaQueryFactory.select(qTblMember.id, qTblMember.name, qTblMember.email, qTblMember.category, qTblCategory.name)
                .from(qTblMember)
                .innerJoin(qTblCategory)
                .on(qTblCategory.categoryId.eq(qTblMember.category))
                .where(qTblMember.category.eq(category))
                .fetch();

        return list.stream()
                .map(TblMember::new)
                .collect(Collectors.toList());

//        return jpaQueryFactory.selectFrom(qTblMember)
//                .innerJoin(qTblCategory)
//                .on(qTblCategory.categoryId.eq(qTblMember.category))
//                .where(qTblMember.category.eq(category))
//                .fetch();
    }

}
