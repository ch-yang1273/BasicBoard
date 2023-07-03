package study.board.config.security.dto;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import study.board.account.domain.Account;
import study.board.account.dto.UserProfile;

import java.util.Collection;

@Getter
public class CustomUserDetails extends User {

    private final UserProfile userProfile;

    public CustomUserDetails(Account account, Collection<? extends GrantedAuthority> authorities) {
        super(account.getEmail(), account.getPassword(), authorities);
        this.userProfile = UserProfile.of(account);
    }
}
