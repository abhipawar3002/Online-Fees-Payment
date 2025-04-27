package com.system.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.project.entities.Payment;
import com.system.project.entities.Student;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Page<Payment> findByStudent(Student student, Pageable pageable);
    
    Page<Payment> findByStudentAndStatus(Student student, String status, Pageable pageable);
    
    Page<Payment> findByStudentAndTransactionIdContainingIgnoreCase(Student student, String transactionId, Pageable pageable);
    
    Page<Payment> findByStudentAndStatusAndTransactionIdContainingIgnoreCase(
        Student student, String status, String transactionId, Pageable pageable);
}