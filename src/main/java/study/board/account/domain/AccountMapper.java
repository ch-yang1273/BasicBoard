package study.board.account.domain;

import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface AccountMapper {

    void save(Account account);

    Optional<Account> findById(Long id);
}
