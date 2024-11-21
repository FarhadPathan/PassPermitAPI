package com.example.PassPermitAPI.repository;

import com.example.PassPermitAPI.model.PassPermit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermitRepository extends JpaRepository<PassPermit, Long> {
    List<PassPermit> findByIsActiveTrue();
}
