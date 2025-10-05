package me.mtuc.conference.common.repository;

import me.mtuc.conference.common.entity.FeeItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonRepository extends JpaRepository<FeeItems , Long> {

}
