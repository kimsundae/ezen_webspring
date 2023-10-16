package ezenweb.controller;

import ezenweb.model.dto.MemberDto;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {
    // Controller -> Service 요청
    // Controller <- Service 응답
    @Autowired
    private MemberService memberService;
    @PostMapping("")
    // 1. [C] 회원가입
    public boolean doPost(@RequestBody MemberDto memberDto ){
        boolean result = memberService.postMember( memberDto );
        return result;
    }
    // 2. [R] 회원정보 호출
    @GetMapping("")
    public MemberDto doGet( @RequestParam int mno ){
        MemberDto result = memberService.getMember( mno );
        return result;
    }
    // 3. [U] 회원정보 수정
    @PutMapping("")
    public boolean doPut(@RequestBody MemberDto memberDto){
        boolean result = memberService.putMember( memberDto );
        return result;
    }
    // 4. [D] 회원탈퇴
    @DeleteMapping("")
    public boolean doDelete( @RequestParam int mno ){
        boolean result = memberService.deleteMember( mno );
        return result;
    }
}
