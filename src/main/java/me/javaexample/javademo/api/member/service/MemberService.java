package me.javaexample.javademo.api.member.service;

import com.mysql.cj.util.StringUtils;
import lombok.RequiredArgsConstructor;
import me.javaexample.javademo.api.member.dto.MemberDto;
import me.javaexample.javademo.api.member.repository.TblMember;
import me.javaexample.javademo.api.member.repository.TblMemberQueryRepository;
import me.javaexample.javademo.api.member.repository.TblMemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final TblMemberRepository memberRepository;
    private final TblMemberQueryRepository memberQueryRepository;

    public List<MemberDto> getMembers(){
        List<MemberDto> result = memberRepository.findAll()
                .stream()
                .map(MemberDto::new)
                .collect(Collectors.toList());
        return result;
    }

    public List<MemberDto> getMembersByCategory(String category){
        List<MemberDto> result = memberQueryRepository.findMemberByCategory(category)
                .stream()
                .map(MemberDto::new)
                .collect(Collectors.toList());
        return result;
    }

    public Map<String, ?> getMembersPaging(Pageable pageable){
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

    @Transactional
    public void insertMember(MemberDto memberDto){
        if(StringUtils.isNullOrEmpty(memberDto.getCategory())){
            memberDto.setDefaultCategory();
        }
        memberRepository.save(new TblMember(memberDto));
    }
}
