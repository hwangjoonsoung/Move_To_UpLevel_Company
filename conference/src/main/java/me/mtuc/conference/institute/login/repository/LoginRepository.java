package me.mtuc.conference.institute.login.repository;

import io.lettuce.core.dynamic.annotation.Param;
import me.mtuc.conference.institute.login.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Token, Long> {

    @Query("select t from Token t where t.dateOfcreate < now() and t.dateOfExpried > now() and user_id = :user_id")
    Optional<Token> findTokenByUserId(@Param("user_id") long userId);

}
