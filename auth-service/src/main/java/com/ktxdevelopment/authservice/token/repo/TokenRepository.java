package com.ktxdevelopment.authservice.token.repo;

import com.ktxdevelopment.authservice.token.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
      select t from token t inner join user u\s
      on t.user.userId = u.userId\s
      where u.userId = :userId and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(String userId);

    Optional<Token> findByToken(String token);

}
