package me.mtuc.conference.common.repository;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import me.mtuc.conference.common.entity.FeeItems;
import org.springframework.stereotype.Repository;

import java.rmi.server.RMIClassLoader;

@RequiredArgsConstructor
@Repository
public class CommonRepository {

    private final EntityManager em;

    public FeeItems findFeeItem(Long id) {
        return em.find(FeeItems.class, id);
    }
}
