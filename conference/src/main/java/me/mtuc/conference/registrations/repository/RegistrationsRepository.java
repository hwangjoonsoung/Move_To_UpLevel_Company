package me.mtuc.conference.registrations.repository;

import me.mtuc.conference.registrations.domain.Registrations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationsRepository extends JpaRepository<Registrations , Long> {

}
