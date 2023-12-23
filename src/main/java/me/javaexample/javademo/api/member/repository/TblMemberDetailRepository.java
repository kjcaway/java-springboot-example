package me.javaexample.javademo.api.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblMemberDetailRepository extends JpaRepository<TblMemberDetail, Long> {

    TblMemberDetail findFirstByMemberId(Long id);
}
