package study.board.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.board.account.dto.SignupReq;
import study.board.account.repository.AccountMapper;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountMapper accountMapper;

    public void signup(SignupReq dto) {
        accountMapper.save(dto.toEntity());
    }
}
