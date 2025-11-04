package me.mtuc.conference.conf.booth.repository;

import me.mtuc.conference.conf.booth.entity.Booth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoothRepository extends JpaRepository<Booth, Long> {

    @Modifying
    @Query("update Staff s set s.isDeleted = true where s.booth = :booth and s.isDeleted = false")
    void deleteStaffWithEntity(@Param("booth") Booth booth);

}
