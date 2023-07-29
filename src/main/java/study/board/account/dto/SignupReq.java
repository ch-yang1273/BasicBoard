package study.board.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import study.board.account.domain.Account;
import study.board.account.domain.AccountRole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
public class SignupReq {

    @Email(message = "이메일 형식을 확인해주세요.")
    @NotEmpty(message = "이메일은 필수 입력 항목입니다.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력 항목입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private String password;

    public Account toEntity(PasswordEncoder encoder) {
        String[] split = email.split("@");

        return Account.builder()
                .email(email)
                .password(encoder.encode(password))
                .nickname(split[0])
                .role(AccountRole.ROLE_USER)
                .build();
    }
}
