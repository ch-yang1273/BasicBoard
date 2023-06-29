package study.board.account.repository;

import org.apache.ibatis.annotations.Mapper;
import study.board.account.domain.Account;

import java.util.Optional;

@Mapper
public interface AccountMapper {

    Long save(Account account);

    Optional<Account> findById(Long id);
}
