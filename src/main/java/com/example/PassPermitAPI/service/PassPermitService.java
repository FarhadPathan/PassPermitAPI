package com.example.PassPermitAPI.service;

import com.example.PassPermitAPI.model.PassPermit;
import com.example.PassPermitAPI.repository.PermitRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PassPermitService {
    private final PermitRepository permitRepository;

    public List<PassPermit> getAllPermits() {
        return permitRepository.findAll();
    }
    public List<PassPermit> getActivePermits() {
        return permitRepository.findByIsActiveTrue();
    }

    public PassPermitService(PermitRepository permitRepository) {
        this.permitRepository = permitRepository;
    }

    public Optional<PassPermit> getPassPermitById(Long id) {
        return permitRepository.findById(id);
    }
    public PassPermit createPassPermit(PassPermit passPermit) {
        return permitRepository.save(passPermit);
    }

    public PassPermit updatePassPermit(Long id, PassPermit passPermit) {

        Optional<PassPermit> existingPermitOpt = permitRepository.findById(id);
        if (existingPermitOpt.isPresent()) {
            PassPermit existingPermit = existingPermitOpt.get();
            if (passPermit.getVehicleNumber() != null) {
                existingPermit.setVehicleNumber(passPermit.getVehicleNumber());
            }
            if (passPermit.getVehicleDetails() != null) {
                existingPermit.setVehicleDetails(passPermit.getVehicleDetails());
            }
            if (passPermit.getOwnerIdNumber() != null) {
                existingPermit.setOwnerIdNumber(passPermit.getOwnerIdNumber());
            }
            if (passPermit.getOwnerName() != null) {
                existingPermit.setOwnerName(passPermit.getOwnerName());
            }

            if (passPermit.isActive() != null) {
                existingPermit.setActive(passPermit.isActive());
            }

            return permitRepository.save(existingPermit);
        } else {

            throw new IllegalArgumentException("PassPermit with ID " + id + " does not exist.");
        }
    }

    public void deletePassPermit(Long id) {
        permitRepository.deleteById(id);
    }

}
