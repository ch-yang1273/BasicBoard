package study.board.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import study.board.account.domain.Account;

@AllArgsConstructor
@Getter
public class UserProfile {

    private Long id;
    private String email;
    private String nickname;

    public static UserProfile of(Account account) {
        return new UserProfile(account.getId(), account.getEmail(), account.getNickname());
    }
}
