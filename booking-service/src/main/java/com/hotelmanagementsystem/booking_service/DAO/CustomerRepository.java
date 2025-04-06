package com.hotelmanagementsystem.booking_service.DAO;
import com.hotelmanagementsystem.booking_service.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
