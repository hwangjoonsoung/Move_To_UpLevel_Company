package me.mtuc.conference.institute.login.repository;

import me.mtuc.conference.institute.login.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Token, Long> {

    @Query("select t from Token t where t.userId = :user_id order by t.dateOfCreate desc limit 1")
    Optional<Token> findTokenByUserId(@Param("user_id") Long userId);

}
