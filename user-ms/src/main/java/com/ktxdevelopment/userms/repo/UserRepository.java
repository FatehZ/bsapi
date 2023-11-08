package com.ktxdevelopment.userms.repo;


import com.ktxdevelopment.userms.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {


    @Query("SELECT u.email FROM user u WHERE u.id IN :userIds")
    List<String> findEmailsByUserIds(@Param("userIds") List<String> userIds);
}
