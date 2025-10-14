package me.mtuc.conference.booth.repository;

import me.mtuc.conference.booth.entity.Booth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoothRepository extends JpaRepository<Booth, Long> {

    @Modifying
    @Query("delete from Staff s where s.booth = :id")
    int deleteStaffWithEntity(@Param("id") Long id);

    @Modifying
    @Query(value = "delete from staff where booth_id = :id",nativeQuery = true)
    int deleteStaffNativeQuery(@Param("id") Long id);
}
