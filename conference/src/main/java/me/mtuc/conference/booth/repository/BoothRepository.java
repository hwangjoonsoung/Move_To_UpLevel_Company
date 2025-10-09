package me.mtuc.conference.booth.repository;

import me.mtuc.conference.booth.entity.Booths;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoothRepository extends JpaRepository<Booths, Long> {
}
