package ezenweb.service;

import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
public class MemberService implements UserDetailsService, // 일반 회원 서비스 : loadUserByUsername 메소드 구현
        OAuth2UserService<OAuth2UserRequest, OAuth2User> // Oauth2 회원 서비스 : loadUser 메소드 구현[ 로그인된 회원정보를 받는 메서드 ]
{
    // ======================== 2. Oauth2 회원 ============================== //
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        // 1. 로그인을 성공한 oauth2 계정의 정보 호출
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser( userRequest );  //
        System.out.println("oAuth2User = " + oAuth2User);
        return null;
    }
    
    
    // --------------------- 1.일반회원 --------------------- //
        // 1. UserDetailsService 구현체
        // 2. 시큐리티 인증 처리해주는 메소드 구현 [ loaduserByUsername ]
        // 3. loadUserByUsername 메소드는 무조건 UserDaetails객체를 반환해야함
        // 4. UserDetails객체를 이용한 패스워드 검증과 사용자 권한을 확인하는 동작(메서드)
     // passwordEncoder
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // 9
    public MemberDto getMember(){
        // ! : 시큐리티 사용하기 전에는 서블릿 세션을 이용한 로그인 상태 저장
        // 시큐리티 사용할 때는 일단 서블릿 세션이 아니고 시큐리티 저장소 이용!!!
        System.out.println("시큐리티에 저장된 세션 정보 저장소 : " + SecurityContextHolder.getContext());
        System.out.println( "시큐리티에 저장된 세션 정보 저장소 저장된 인증 : "
                +SecurityContextHolder.getContext().getAuthentication());
        System.out.println( "시큐리티에 저장된 세션 정보 저장소 저장된 인증 호출: "
                +SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        // 인증에 성공한 정보 호출 [ 세션 호출 ]
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println( o.toString() );
        // 1. 만약에 인증결과가 실패했을 때/없을 때 anonymousUser
        if( o.equals("anonymousUser")){ return null; } // 로그인 안함
        // 2. 인증결과에 저장된 UserDetails로 타입 반환
        UserDetails userDetails = (UserDetails)o;
        // 로그인 상태에 필요한 데이터 구성
        MemberEntity memberEntity = memberEntityRepositoryEntity.findByMemail(userDetails.getUsername());
        // 3. UserDetails의 정보를 memberDto에 담아서 반환
        return MemberDto.builder().memail(userDetails.getUsername()).mno(memberEntity.getMno()).build();
    }
    @Override
    public UserDetails loadUserByUsername(String memail) throws UsernameNotFoundException {
        // * login페이지에서 form을 통해 전송된 아이디 받고( 패스워드 없음 )
        System.out.println("username = " + memail);

        // 인증절차 순서684
        // 1. 사용자의 아이디만으로 사용자 정보[엔티티]를 로딩[불러오기]
        MemberEntity memberEntity = memberEntityRepositoryEntity.findByMemail(memail);
        //없는 아이디이면 // throw : 예외처리 던지기 // new UsernameNotFoundException() : username 없을 때 사용하는 예외 클래스
        if( memberEntity == null ){ throw new UsernameNotFoundException("없는 아이디입니다.");}
        // 2. 로딩[불러오기]된 사용자의 정보를 이용해서 패스워드를 검증
            // 2-1 있는 아이디이면
        UserDetails userDetails = User.builder()
                .username(memberEntity.getMemail())
                .password(memberEntity.getMpassword())
                .authorities("ROLE_USER").build();
        return userDetails;
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

        System.out.println(memberDto);
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
    /*//세션 적용 회원정보 호출 메서드
    @Transactional
    public MemberDto getMember(){
        // 1. 세션 호출
        Object session = request.getSession().getAttribute("loginDto");
        // 2. 세션 검증
        if( session != null ) {
            return (MemberDto) session;
        }
        return null;
    }*/
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
