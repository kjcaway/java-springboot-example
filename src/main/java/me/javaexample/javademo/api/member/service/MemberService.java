package me.javaexample.javademo.api.member.service;

import com.mysql.cj.util.StringUtils;
import lombok.RequiredArgsConstructor;
import me.javaexample.javademo.api.member.dto.MemberDetailDto;
import me.javaexample.javademo.api.member.dto.MemberDto;
import me.javaexample.javademo.api.member.repository.*;
import me.javaexample.javademo.exception.CustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final TblMemberRepository memberRepository;
    private final TblMemberDetailRepository memberDetailRepository;
    private final TblMemberQueryRepository memberQueryRepository;
    private final EntityManager em;

    public List<MemberDto> getMembers() {
        List<MemberDto> result = memberRepository.findAll()
            .stream()
            .map(MemberDto::new)
            .collect(Collectors.toList());
        return result;
    }

    public List<MemberDto> getMembersByCategory(String category) {
        List<MemberDto> result = memberQueryRepository.findMemberByCategory(category)
            .stream()
            .map(MemberDto::new)
            .collect(Collectors.toList());
        return result;
    }

    public MemberDetailDto getMember(Long id) {
        try {
            TblMember member = memberRepository.findById(id)
                .orElseThrow(CustomException::new);
            TblMemberDetail memberDetail = memberDetailRepository.findFirstByMemberId(id);
            return new MemberDetailDto(member, memberDetail);

        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    public Map<String, ?> getMembersPaging(Pageable pageable) {
        Page<TblMember> page = memberQueryRepository.findMemberByPage(pageable);
        List<MemberDto> result = page
            .stream()
            .map(MemberDto::new)
            .collect(Collectors.toList());

        return Map.of(
            "list", result,
            "totalcount", page.getTotalElements()
        );
    }

    public List<MemberDto> getMembersNamedQuery(String keyword) {
        List<MemberDto> result = em.createNamedQuery("TblMember.selectByKeyword", TblMember.class)
            .setParameter("keyword", keyword)
            .getResultStream()
            .map(MemberDto::new)
            .collect(Collectors.toList());
        return result;
    }

    @Transactional
    public void insertMember(MemberDto memberDto) {
        if (StringUtils.isNullOrEmpty(memberDto.getCategory())) {
            memberDto.setDefaultCategory();
        }
        memberRepository.save(new TblMember(memberDto));
    }
}
