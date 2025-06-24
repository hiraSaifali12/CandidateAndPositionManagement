package com.CandidateAndPosition.Management.CandidateAndPosition.Management.Service;

import com.CandidateAndPosition.Management.CandidateAndPosition.Management.DTO.CandidateRequest;
import com.CandidateAndPosition.Management.CandidateAndPosition.Management.Entity.Candidate;
import com.CandidateAndPosition.Management.CandidateAndPosition.Management.Entity.Position;
import com.CandidateAndPosition.Management.CandidateAndPosition.Management.Repository.CandidateRepository;
import com.CandidateAndPosition.Management.CandidateAndPosition.Management.Repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;

@Service
public class CandidateService {


        @Autowired
        private CandidateRepository candidateRepository;

        @Autowired
        private PositionRepository positionRepository;

        public Candidate createCandidate(CandidateRequest request) {
            // Validate name
            if (request.name == null || request.name.isBlank()) {
                throw new IllegalArgumentException("Name is required.");
            }
            if (request.name.length() > 50) {
                throw new IllegalArgumentException("Name must be max 50 characters.");
            }

            // Validate email
            if (!request.email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
                throw new IllegalArgumentException("Invalid email format.");
            }
            if (candidateRepository.existsByEmail(request.email)) {
                throw new IllegalArgumentException("Email must be unique.");
            }

            // Validate dob
            if (request.dob == null || Period.between(request.dob, LocalDate.now()).getYears() < 18) {
                throw new IllegalArgumentException("Candidate must be at least 18 years old.");
            }

            // Validate position IDs
            List<Position> positions = positionRepository.findAllById(request.positionIds);
            if (positions.size() != request.positionIds.size()) {
                throw new IllegalArgumentException("One or more position IDs are invalid.");
            }

            // Saving candidate
            Candidate candidate = new Candidate();
            candidate.setName(request.name);
            candidate.setEmail(request.email);
            candidate.setDob(request.dob);
            candidate.setPositions(positions);

            return candidateRepository.save(candidate);
        }
    public Candidate updateCandidate(Long id, Map<String, Object> updates) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        if (updates.containsKey("name")) {
            String name = updates.get("name").toString();
            if (!name.isBlank() && name.length() <= 50) {
                candidate.setName(name);
            } else {
                throw new IllegalArgumentException("Invalid name.");
            }
        }

        if (updates.containsKey("email")) {
            String email = updates.get("email").toString();
            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
                throw new IllegalArgumentException("Invalid email format.");
            }
            if (!email.equals(candidate.getEmail()) && candidateRepository.existsByEmail(email)) {
                throw new IllegalArgumentException("Email must be unique.");
            }
            candidate.setEmail(email);
        }

        if (updates.containsKey("dob")) {
            LocalDate dob = LocalDate.parse(updates.get("dob").toString());
            if (Period.between(dob, LocalDate.now()).getYears() < 18) {
                throw new IllegalArgumentException("Candidate must be at least 18 years old.");
            }
            candidate.setDob(dob);
        }

        if (updates.containsKey("positionIds")) {
            List<Integer> positionIds = (List<Integer>) updates.get("positionIds");
            List<Position> positions = positionRepository.findAllById(
                    positionIds.stream().map(Long::valueOf).toList());

            if (positions.size() != positionIds.size()) {
                throw new IllegalArgumentException("One or more position IDs are invalid.");
            }

            candidate.setPositions(positions);
        }

        return candidateRepository.save(candidate);
    }
    }


