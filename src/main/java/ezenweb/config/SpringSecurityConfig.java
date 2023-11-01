package ezenweb.config;

import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.formLogin()                        // 1. 시큐리티 로그인 사용 [ form 전송]
        .loginPage("/login")                    // 2. 시큐리티 로그인으로 사용할 View페이지의 http 주소
        .loginProcessingUrl("/member/login")    // 3. 시큐리티 로그인(인증)처리 요청시 사용할 HTTP 주소
                // 시큐리티 사용하기 전에 MemberController 해서 정의한 로그인/로그아웃 함수를 없애기
                // HTTP '/member/login' Post요청시 --> MemberService의 load
        .defaultSuccessUrl("/")                 // 4. 로그인 성공시 이동할 HTTP 주소
        .failureUrl("/login")   // 5. 만약에 로그인 실패시 이동할 HTTP 주소
        .usernameParameter("memail")                // 6. 로그인시 입력받은 아이디의 변수명 정의
        .passwordParameter("mpassword");            // 7. 로그인 시 입력받은 비밀번호의 변수명 정의
        http.csrf().disable(); // --- 모든 HTTP POST/PUT 에서 csrf 사용안함
        // 특정 HTTP에서만 CSRF 사용안함 [ POST, PUT ]
        //http.csrf().ignoringAntMatchers("/member/post"); // -- controller 매핑 주소

    }

    // configure(WebSecurity web) : 웹 시큐리티 보안 담당하는 메서드
    @Autowired
    private MemberService memberService;
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