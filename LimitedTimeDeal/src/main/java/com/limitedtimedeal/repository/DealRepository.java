package com.limitedtimedeal.repository;

import com.limitedtimedeal.model.Deal;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface DealRepository extends JpaRepository<Deal, Long> {


    @Modifying
    @Transactional
    @Query("UPDATE Deal d SET d.isValid = false WHERE d.isValid = true AND d.endTime < :currentTime")
    int deactivateExpiredDeals(@Param("currentTime") LocalDateTime currentTime); // Or @Param("currentTime") Date currentTime


}
