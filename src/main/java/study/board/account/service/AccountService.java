package study.board.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import study.board.account.domain.Account;
import study.board.account.dto.SignupReq;
import study.board.account.repository.AccountMapper;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final PasswordEncoder passwordEncoder;

    private final AccountMapper accountMapper;

    public void signup(SignupReq dto) {
        Account account = dto.toEntity(passwordEncoder);
        accountMapper.save(account);
    }
}
