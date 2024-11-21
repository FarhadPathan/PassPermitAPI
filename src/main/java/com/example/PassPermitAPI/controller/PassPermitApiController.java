package com.example.PassPermitAPI.controller;

import com.example.PassPermitAPI.model.PassPermit;
import com.example.PassPermitAPI.service.PassPermitService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/passpermits")
public class PassPermitApiController {

    private final PassPermitService service;
    public PassPermitApiController(PassPermitService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<PassPermit> createPermit(@RequestBody @Valid PassPermit passPermit) {
        return new ResponseEntity<>(service.createPassPermit(passPermit), HttpStatus.CREATED);
    }
    @GetMapping
    public List<PassPermit> getAllPermits() {
        return service.getAllPermits();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PassPermit> getPermitById(@PathVariable Long id) {
        return service.getPassPermitById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/onlyActives")
    public List<PassPermit> getActivePermits() {
        return service.getActivePermits();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassPermit> updatePassPermit(
            @PathVariable Long id,
            @RequestBody PassPermit passPermit) {
        try {
            PassPermit updatedPermit = service.updatePassPermit(id, passPermit);
            return ResponseEntity.ok(updatedPermit);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermit(@PathVariable Long id) {
        service.deletePassPermit(id);
        return ResponseEntity.noContent().build();
    }
}


