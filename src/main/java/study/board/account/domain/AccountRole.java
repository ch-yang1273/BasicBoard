package study.board.account.domain;

public enum AccountRole {

    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN")
    ;

    private final String value;

    AccountRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
