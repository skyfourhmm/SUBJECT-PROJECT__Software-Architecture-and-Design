package com.hotelmanagementsystem.booking_service.DAO;

import com.hotelmanagementsystem.booking_service.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
