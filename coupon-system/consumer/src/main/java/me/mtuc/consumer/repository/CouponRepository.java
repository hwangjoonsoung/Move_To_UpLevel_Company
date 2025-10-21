package me.mtuc.consumer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<me.mtuc.consumer.domain.Coupon, Long> {
}
