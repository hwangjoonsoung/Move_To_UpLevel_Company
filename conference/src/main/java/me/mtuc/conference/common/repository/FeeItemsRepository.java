package me.mtuc.conference.common.repository;

import me.mtuc.conference.common.entity.FeeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeItemsRepository extends JpaRepository<FeeItem, Long> {

}
