package com.driver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.driver.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
}
