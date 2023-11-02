package ezenweb.controller;

import ezenweb.model.dto.MemberDto;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
// IOC(Inversion Of Control ) : 제어역전 ( 객체 관리를 스프링에게 위임 )
// DI( Dependency injection ) : 의존성 주입 [ 스프링이 객체를 관리 ]
@RestController
@RequestMapping("/member")
//@CrossOrigin("http://192.168.17.138:3000") // 교차 리소스
public class MemberController {
    @Autowired
    private HttpServletRequest request;
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
    /* 세션 적용 이전
    // 2. [R] 회원정보 호출
    @GetMapping("")
    public MemberDto doGet( @RequestParam int mno ){
        MemberDto result = memberService.getMember( mno );
        return result;
    }
     */
    // 세션 적용 회원정보 호출
    @GetMapping("")
    public MemberDto getMember(){
        return memberService.getMember();
    }
    // 7. [R] 이메일 중복
    @GetMapping("/findMemail")
    public boolean getFindMemail( @RequestParam String memail ){
        System.out.println("memail = " + memail);
        return memberService.existsByMemail(memail);
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
        System.out.println("mno = " + mno);
        return memberService.deleteMember( mno );
    }
    // ------------- 과제5 ---------------- //
    @GetMapping("/findId")
    public String findId( @RequestParam String name , String phoneNumber ){
        String result = memberService.findId( name, phoneNumber );
        return result == null ? "일치하는 정보가 없습니다." : result;
    }
    @GetMapping("/findPw")
    public String findPw( @RequestParam String email , String phoneNumber){
        String result = memberService.findPw( email, phoneNumber);
        return result == null ? "일치하는 정보가 없습니다." : result;
    }
    // 로그인 유무 상태 저장하는 곳 => request 객체도 스프링 컨테이너에 등록 상태
    //@Autowired
    //private HttpServletRequest request;

    // 로그인
    @PostMapping("/login")
    public String login( @RequestBody MemberDto memberDto , HttpSession session ){
        MemberDto loginDto = memberService.login( memberDto );
        if( loginDto != null ){
            session.setAttribute("loginDto" , loginDto );
            return "로그인 되었습니다.";
        }
        else
            return "로그인 실패되었습니다.";
    }
/*    // 로그아웃
    @GetMapping("/logout")
    public boolean logout( HttpSession session ){
        if( ((MemberDto)session.getAttribute("loginDto")) != null ){
            session.setAttribute("loginDto", null);
            return true;
        }else
            return false;

    }*/

}
