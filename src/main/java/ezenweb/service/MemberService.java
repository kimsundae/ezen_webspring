package ezenweb.service;

import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {

    // ------------------------------------------ //
        // 1. UserDetailsService 구현체
        // 2. 시큐리티 인증 처리해주는 메소드 구현 [ loaduserByUsername ]
        // 3. loadUserByUsername 메소드는 무조건 UserDaetails객체를 반환해야함
        // 4. UserDetails객체를 이용한 패스워드 검증과 사용자 권한을 확인하는 동작(메서드)
     // passwordEncoder
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username = " + username);

        // 인증절차 684
        // 1. 사용자의 아이디만으로 사용자 정보를 로딩[불러오기]
        // 2. 로딩[불러오기]된 사용자의 정보를 이용해서 패스워드를 검증

        return null;
    }


    // ------------------------------------------ //

    // Controller -> Service -> Repository
    // Controller <- Service <- Repository
    @Autowired
    private MemberEntityRepository memberEntityRepositoryEntity;
    @Autowired
    private HttpServletRequest request;
    // 1. [C] 회원가입
    public boolean postMember( MemberDto memberDto ){

        // --- 암호화 --- //
            // - 입력받은 비밀번호 [ memberDto.getMpassword() ]를 암호화해서 다시 memberDto에 저장
        memberDto.setMpassword( passwordEncoder.encode(memberDto.getMpassword()));


        MemberEntity memberEntity = memberEntityRepositoryEntity.save(memberDto.toEntity());
        // 2. insert된 엔티티 확인 후 성공/실패 유무
            // 3. 만약에 회원번호가 0보다 크면 ( auto_increment 적용 됨. )
        if( memberEntity.getMno() >= 1 ) { return true;}
        return false;
    }

    /* 세션 이전
    // 2. [R] 회원정보 호출
    public MemberDto getMember( int mno ){
        System.out.println("mno = " + mno);
        // 1. mno[회원번호pk]를 이용한 회원 엔티티 찾기
        Optional<MemberEntity> entity = memberEntityRepositoryEntity.findById( mno );
        // 2. optional 클래스로 검색한 반환값 확인
        if( entity.isPresent() ){ // 3. 만약에 optinal 클래스 안에 엔티티가 들어있으면
            // 4. optinal 클래스에서 엔티티 꺼내고 Dto로 반환
            return entity.get().toDto();
        }
        return null;
    }
     */
    //세션 적용 회원정보 호출 메서드
    @Transactional
    public MemberDto getMember(){
        // 1.
        Object session = request.getSession().getAttribute("loginDto");
        // 2. 세션 검증
        if( session != null ) {
            return (MemberDto) session;
        }
        return null;
    }
    // 3. [U] 회원정보 수정
    @PutMapping("")
    @Transactional
    public boolean putMember( MemberDto memberDto ){
        System.out.println("memberDto = " + memberDto);
        Optional<MemberEntity> entity = memberEntityRepositoryEntity.findById( memberDto.getMno() );
        if(entity.isPresent()){
            MemberEntity result = entity.get();
            result.setMname(memberDto.getMname());
            result.setMpassword(memberDto.getMpassword());
            result.setMphone(memberDto.getMphone());
            // 세션수정
            request.getSession().setAttribute("loginDto",result.toDto() );
        }
        return true;
    }
    // 4. [D] 회원탈퇴
    @DeleteMapping("")
    public boolean deleteMember( int mno ){
        System.out.println("mno = " + mno);
        memberEntityRepositoryEntity.deleteById(mno);
        // 4. 삭제성공시
        // 로그아웃 함수
        request.getSession().setAttribute("loginDto",null);
        return true;
    }
    // 7. [R] 이메일 중복찾기
    public boolean existsByMemail( String memail ){
        return memberEntityRepositoryEntity.existsByMemail(memail);
    }

    // ---------- 과제5 -------------- //
    // 아이디 찾기
    public String findId( String name, String phoneNumber ){
        List<MemberEntity> list = memberEntityRepositoryEntity.findAll();
        List<MemberDto> memberDtoList = new ArrayList<>();
        list.forEach( entity -> {
            memberDtoList.add(MemberDto.builder()
                    .mno(entity.getMno())
                    .memail(entity.getMemail())
                    .mname(entity.getMname())
                    .mpassword(entity.getMpassword())
                    .mphone(entity.getMphone())
                    .mrole(entity.getMrole()).build());
        });

        for( MemberDto memberDto : memberDtoList){
            if( memberDto.getMname().equals(name) && memberDto.getMphone().equals(phoneNumber)){
                return memberDto.getMemail();
            }
        }
        return null;
    }
    // 비밀번호 찾기
    public String findPw( String email, String phoneNumber ){
        List<MemberEntity> list = memberEntityRepositoryEntity.findAll();
        List<MemberDto> memberDtoList = new ArrayList<>();
        list.forEach( entity -> {
            memberDtoList.add(MemberDto.builder()
                    .mno(entity.getMno())
                    .memail(entity.getMemail())
                    .mname(entity.getMname())
                    .mpassword(entity.getMpassword())
                    .mphone(entity.getMphone())
                    .mrole(entity.getMrole()).build());
        });

        for( MemberDto memberDto : memberDtoList){
            if( memberDto.getMemail().equals(email) && memberDto.getMphone().equals(phoneNumber)){
                return memberDto.getMpassword();
            }
        }
        return null;
    }
    // 로그인
    public MemberDto login( MemberDto inputMemberDto ){
        List<MemberEntity> list = memberEntityRepositoryEntity.findAll();
        List<MemberDto> memberDtoList = new ArrayList<>();
        list.forEach( entity -> {
            memberDtoList.add(MemberDto.builder()
                    .mno(entity.getMno())
                    .memail(entity.getMemail())
                    .mname(entity.getMname())
                    .mpassword(entity.getMpassword())
                    .mphone(entity.getMphone())
                    .mrole(entity.getMrole()).build());
        });
        // 이메일과 비밀번호 찾으면 true 반환
        for( MemberDto memberDto : memberDtoList){
            if( memberDto.getMemail().equals(inputMemberDto.getMemail()) && memberDto.getMpassword().equals(inputMemberDto.getMpassword())){
                return memberDto;
            }
        }
        return null;
    }


}
