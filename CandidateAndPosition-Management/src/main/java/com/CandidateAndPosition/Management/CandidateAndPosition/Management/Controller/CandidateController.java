package com.CandidateAndPosition.Management.CandidateAndPosition.Management.Controller;

import com.CandidateAndPosition.Management.CandidateAndPosition.Management.DTO.CandidateRequest;
import com.CandidateAndPosition.Management.CandidateAndPosition.Management.Entity.Candidate;
import com.CandidateAndPosition.Management.CandidateAndPosition.Management.Service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

        @Autowired
        private CandidateService candidateService;

        @PostMapping
        public ResponseEntity<?> createCandidate(@RequestBody CandidateRequest request) {
            try {
                Candidate savedCandidate = candidateService.createCandidate(request);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedCandidate);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }

        @PatchMapping("/{id}")
        public ResponseEntity<?> updateCandidate(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
            try {
                Candidate updatedCandidate = candidateService.updateCandidate(id, updates);
                return ResponseEntity.ok(updatedCandidate);
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        }
    }


