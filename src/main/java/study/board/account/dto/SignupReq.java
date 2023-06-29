package study.board.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import study.board.account.domain.Account;
import study.board.account.domain.AccountRole;

@NoArgsConstructor
@Getter
public class SignupReq {

    private String email;
    private String password;

    public Account toEntity() {
        String[] split = email.split("@");

        return Account.builder()
                .email(email)
                .password(password)
                .nickname(split[0])
                .role(AccountRole.ROLE_USER)
                .build();
    }
}
