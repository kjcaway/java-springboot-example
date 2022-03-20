package me.javaexample.javademo.api.member.service;

import com.mysql.cj.util.StringUtils;
import lombok.RequiredArgsConstructor;
import me.javaexample.javademo.api.member.dto.MemberDto;
import me.javaexample.javademo.api.member.repository.TblMember;
import me.javaexample.javademo.api.member.repository.TblMemberQueryRepository;
import me.javaexample.javademo.api.member.repository.TblMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional
    public void insertMember(MemberDto memberDto){
        if(StringUtils.isNullOrEmpty(memberDto.getCategory())){
            memberDto.setDefaultCategory();
        }
        memberRepository.save(new TblMember(memberDto));
    }
}
