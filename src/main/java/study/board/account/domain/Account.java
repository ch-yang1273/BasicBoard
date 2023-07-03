package study.board.account.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Account {

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private AccountRole role;

    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;

    public Account(Long id, String email, String password, String nickname, AccountRole role,
                   LocalDateTime createTime, LocalDateTime modifiedTime) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }

    @Builder
    public Account(String email, String password, String nickname, AccountRole role) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = role;

        this.createTime = LocalDateTime.now();
        this.modifiedTime = createTime;
    }

    public static Account unknownAccount() {
        return Account.builder()
                .nickname("Unknown")
                .build();
    }
}
