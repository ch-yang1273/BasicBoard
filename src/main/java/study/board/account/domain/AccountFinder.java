package study.board.account.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AccountFinder {

    private final AccountMapper accountMapper;

    public Account findByEmail(String email) {
        return accountMapper.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Unknown Email =" + email)
        );
    }
}
