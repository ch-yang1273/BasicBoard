package study.board.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import study.board.config.security.Authentication.AjaxLoginProcessingFilter;
import study.board.config.security.Authentication.AuthenticationBeans;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationBeans authenticationBeans;

    // 정적 파일에 대한 검사 Skip
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        AjaxLoginProcessingFilter ajaxLoginProcessingFilter = authenticationBeans.ajaxLoginProcessingFilter(http);

        http
                .csrf().disable()
                // URL 인가 설정
                .authorizeRequests()
                .antMatchers("/", "/login", "/signup").permitAll()
                .antMatchers("/forbidden").permitAll()
                .antMatchers("/api/v1/account/signup").permitAll()
                .anyRequest().authenticated()
                .and()
                // AJAX 로그인 인증 필터 추가
                .addFilterBefore(ajaxLoginProcessingFilter, UsernamePasswordAuthenticationFilter.class)
                // 인가 에러 처리
                .exceptionHandling()
                .accessDeniedPage("/forbidden")
                .and()
                // 로그인
                .formLogin()
                .loginPage("/login")
                .and()
                // 로그아웃
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/v1/logout-proc", "GET"))
                .deleteCookies()
                ;

        return http.build();
    }
}
