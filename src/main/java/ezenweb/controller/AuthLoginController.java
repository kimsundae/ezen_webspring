package ezenweb.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthLoginController implements
        AuthenticationSuccessHandler,
        AuthenticationFailureHandler {


    @Override // 로그인 성공했을 때
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;utf-8");
        response.getWriter().print(true);
    }
    @Override // 로그인 실패했을 때
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println( "exception: " + exception.getMessage() );
        response.setContentType("application/json;utf-8");
        response.getWriter().print(false);
    }


}
