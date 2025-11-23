package me.mtuc.conference.institute.login.repository;

import me.mtuc.conference.institute.login.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Token, Long> {

}
