package com.system.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.project.entities.Payment;
import com.system.project.entities.Student;
import com.system.project.repository.StudentRepository;

import java.util.Optional;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }
    
    public Double getTotalPaidAmount(Student student) {
        return student.getPayments().stream()
                .filter(p -> "SUCCESS".equals(p.getStatus()))
                .mapToDouble(Payment::getAmount)
                .sum();
    }
}