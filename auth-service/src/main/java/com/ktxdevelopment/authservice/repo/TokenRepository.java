package com.ktxdevelopment.authservice.repo;

import com.ktxdevelopment.authservice.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
      select t from token t inner join userEntity u\s
      on t.userEntity.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(String id);

    Optional<Token> findByToken(String token);

}
