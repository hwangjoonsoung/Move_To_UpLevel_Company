package me.mtuc.conference.registrations.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import me.mtuc.conference.registrations.domain.Registrations;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RegistrationsRepository {

    private final EntityManager entityManager;


    public void newRepository(Registrations registrations) {
        entityManager.persist(registrations);
    }
}
