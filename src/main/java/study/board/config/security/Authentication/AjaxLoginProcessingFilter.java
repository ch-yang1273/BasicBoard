package study.board.config.security.Authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.util.StringUtils;
import study.board.account.dto.LoginReq;
import study.board.config.security.dto.EmailPasswordAuthenticationToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper mapper = new ObjectMapper();

    public AjaxLoginProcessingFilter(AuthenticationManager auth, String loginProcessingUrl) {
        // 이 필터가 동작하는 URL
        super(new AntPathRequestMatcher(loginProcessingUrl));

        // AuthenticationManager 등록
        super.setAuthenticationManager(auth);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        checkContentTypeJson(request);

        LoginReq dto;
        try {
            dto = mapper.readValue(request.getReader(), LoginReq.class);
        } catch (IOException e) {
            throw new AuthenticationServiceException("Failed to parse authentication request body", e);
        }

        if (StringUtils.isEmpty(dto.getEmail()) || StringUtils.isEmpty(dto.getPassword())) {
            throw new AuthenticationServiceException("Email or Password is empty");
        }

        log.info("Authenticate EmailPasswordAuthenticationToken");
        EmailPasswordAuthenticationToken token = new EmailPasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        return getAuthenticationManager().authenticate(token);
    }

    private void checkContentTypeJson(HttpServletRequest request) {
        if (!request.getHeader("Content-Type").equals("application/json")) {
            throw new AuthenticationServiceException("Invalid Content-Type. Expected application/json");
        }
    }
}
