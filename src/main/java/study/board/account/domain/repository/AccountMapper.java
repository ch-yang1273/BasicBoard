package study.board.account.domain.repository;

import org.apache.ibatis.annotations.Mapper;
import study.board.account.domain.Account;

import java.util.Optional;

@Mapper
public interface AccountMapper {

    void save(Account account);

    Optional<Account> findById(Long id);

    Optional<Account> findByEmail(String email);
}
