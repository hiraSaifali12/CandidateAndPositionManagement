package com.CandidateAndPosition.Management.CandidateAndPosition.Management.Repository;

import com.CandidateAndPosition.Management.CandidateAndPosition.Management.Entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    boolean existsByEmail(String email);
}