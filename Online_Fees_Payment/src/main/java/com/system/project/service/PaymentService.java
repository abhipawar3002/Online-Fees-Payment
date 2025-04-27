package com.system.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.system.project.entities.Payment;
import com.system.project.entities.Student;
import com.system.project.repository.PaymentRepository;

@Service
public class PaymentService {
    
    @Autowired
    private PaymentRepository paymentRepository;
    
    public Page<Payment> getPaymentsByStudent(Student student, String searchQuery, String statusFilter, Pageable pageable) {
        if (searchQuery != null && !searchQuery.isEmpty() && statusFilter != null && !statusFilter.isEmpty()) {
            return paymentRepository.findByStudentAndStatusAndTransactionIdContainingIgnoreCase(
                student, statusFilter, searchQuery, pageable);
        } else if (searchQuery != null && !searchQuery.isEmpty()) {
            return paymentRepository.findByStudentAndTransactionIdContainingIgnoreCase(
                student, searchQuery, pageable);
        } else if (statusFilter != null && !statusFilter.isEmpty()) {
            return paymentRepository.findByStudentAndStatus(student, statusFilter, pageable);
        } else {
            return paymentRepository.findByStudent(student, pageable);
        }
    }
    
    public Payment updatePaymentStatus(Long paymentId, String status) {
        Payment payment = paymentRepository.findById(paymentId).orElseThrow(
            () -> new RuntimeException("Payment not found with id: " + paymentId));
        payment.setStatus(status);
        return paymentRepository.save(payment);
    }
}