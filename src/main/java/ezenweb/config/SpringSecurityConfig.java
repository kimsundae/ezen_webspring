package ezenweb.config;

import ezenweb.controller.AuthLoginController;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MemberService memberService;
    @Autowired
    private AuthLoginController authLoginController;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

        // 0. 인증(로그인)된 인가(권한/허가) 통해 페이지 접근 제한 UserDetails 내 authorities 권한 일치
        http.authorizeHttpRequests() // 1. 인증된 권한에 따른 HTTP 요청 제한.
                .antMatchers("/info").hasRole("USER") // 인증된 권한 중에 ROLE_USER 이면 해당 HTTP 허용
                .antMatchers("/board/write").hasRole("USER")
                .antMatchers("/**").permitAll(); // 모든 페이지 권한 모두 허용
        // 1. 인증(로그인 커스텀)
        http.formLogin()                        // 1. 시큐리티 로그인 사용 [ form 전송]
        .loginPage("/login")                    // 2. 시큐리티 로그인으로 사용할 View페이지의 http 주소
        .loginProcessingUrl("/member/login")    // 3. 시큐리티 로그인(인증)처리 요청시 사용할 HTTP 주소
                // 시큐리티 사용하기 전에 MemberController 해서 정의한 로그인/로그아웃 함수를 없애기
                // HTTP '/member/login' Post요청시 --> MemberService의 load
      //  .defaultSuccessUrl("/")                 // 4. 로그인 성공시 이동할 HTTP 주소
      //  .failureUrl("/login")   // 5. 만약에 로그인 실패시 이동할 HTTP 주소
        .usernameParameter("memail")                // 6. 로그인시 입력받은 아이디의 변수명 정의
        .passwordParameter("mpassword")            // 7. 로그인 시 입력받은 비밀번호의 변수명 정의
                .successHandler(authLoginController) // 로그인 성공했을 때 해당 클래스 매핑
                        .failureHandler(authLoginController); // 로그인 실패했을 때 해당 클래스 매핑


        // 2. 로그아웃 커스텀 [ 시큐리티 사용 전에 Controller, Service에 구현한 logout 관련 메소드 제거]
        http.logout()                   // 1. 로그인(인증) 로그아웃 처리
                .logoutRequestMatcher( new AntPathRequestMatcher("/member/logout")) // 2. 로그아웃 처리할 HTTP 주소 정의
                        .logoutSuccessUrl("/") // 3. 로그아웃 성공했을 때 이동할 HTTP 주소
                                .invalidateHttpSession(true);    // 4. 로그아웃 할 때 http 세션 모두 초기화[ true : 초기화o / false : 초기화x ]
        // 3. CSRF 커스텀
        http.csrf().disable(); // --- 모든 HTTP POST/PUT 에서 csrf 사용안함
        // 특정 HTTP에서만 CSRF 사용안함 [ POST, PUT ]
        //http.csrf().ignoringAntMatchers("/member/post"); // -- controller 매핑 주소
        ///http.csrf().ignoringAntMatchers("/member/login"); // -- 매핑주소

        // 4. Oauth2 커스텀
        http.oauth2Login()
                .loginPage("/login")   // oauth2 로그인할 view 페이지
                .userInfoEndpoint().userService(memberService); // 로그인을 성공한 oauth2 유저 정보를 받을 서비스 선택
    }

    // p.689 configure(WebSecurity web) : 웹 시큐리티 보안 담당하는 메서드
    @Override
    public void configure(AuthenticationManagerBuilder auth ) throws Exception {
        //super.configure(web);
        auth.userDetailsService( memberService ).passwordEncoder( new BCryptPasswordEncoder() );
        // auth.userDetailsService( userDetailsService 구현체 ).passwordEncoder
    }
}
// -- 시큐리티 관련 메소드 커스텀 하기
// 1. 해당 클래스에 상속받기
// 2. 커스텀 할 메소드 오버라이딩 하기