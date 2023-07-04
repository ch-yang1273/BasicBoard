package study.board.account.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import study.board.account.repository.AccountMapper;

@RequiredArgsConstructor
@Component
public class AccountFinder {

    private final AccountMapper accountMapper;

    public Account findById(Long id) {
        return accountMapper.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("Unknown Account id =" + id.toString())
        );
    }

    public String findNicknameByIdOrUnknown(Long id) {
        Account account = accountMapper.findById(id).orElse(Account.unknownAccount());
        return account.getNickname();
    }

    public Account findByEmail(String email) {
        return accountMapper.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Unknown Email =" + email)
        );
    }
}
