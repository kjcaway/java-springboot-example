package me.javaexample.javademo.api.member;

import lombok.RequiredArgsConstructor;
import me.javaexample.javademo.api.base.ApiResult;
import me.javaexample.javademo.api.member.dto.MemberDto;
import me.javaexample.javademo.api.member.service.MemberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("${demo.api}/member")
public class MemberController {
    private static final Logger logger = LogManager.getLogger(MemberController.class);

    private final MemberService memberService;

    @GetMapping("")
    public ApiResult<?> getMembers() {
        List<MemberDto> result = memberService.getMembers();
        return ApiResult.ok(result);
    }

    @PostMapping("")
    public ApiResult<?> setMember(@RequestBody MemberDto memberDto) {
        memberService.insertMember(memberDto);
        return ApiResult.ok();
    }

    @GetMapping("/search")
    public ApiResult<?> getMembersByCategory(@RequestParam String category) {
        List<MemberDto> result = memberService.getMembersByCategory(category);
        return ApiResult.ok(result);
    }

    @GetMapping("/paging")
    public ApiResult<?> getMembersPaging(Pageable pageable,
                                    @RequestParam(required = false, defaultValue = "0") int page,
                                    @RequestParam(required = false, defaultValue = "10") int size) {
        Map<?, ?> result = memberService.getMembersPaging(pageable);
        return ApiResult.ok(result);
    }
}
