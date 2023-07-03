package study.board.common.authentication;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import study.board.account.dto.UserProfile;

@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //"@LoginUser" UserProfile user
        boolean hasLoginUserAnnotation = parameter.hasParameterAnnotation(LoginUser.class);
        //@LoginUser "UserProfile" user
        boolean hasUserProfileType = UserProfile.class.isAssignableFrom(parameter.getParameterType());

        return hasLoginUserAnnotation && hasUserProfileType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        UserProfile userProfile = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userProfile == null) {
            throw new IllegalArgumentException("Authorization server error");
        }
        return userProfile;
    }
}
