package me.mtuc.conference.institute.user.repository;

import me.mtuc.conference.institute.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = :email and u.password = :password")
    Optional<User> loginUser(@Param("email") String email, @Param("password") String password);

    @Query("select u from User  u where u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);

}
