package com.driver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.driver.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
