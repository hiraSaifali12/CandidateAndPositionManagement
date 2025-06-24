package com.CandidateAndPosition.Management.CandidateAndPosition.Management.Repository;

import com.CandidateAndPosition.Management.CandidateAndPosition.Management.Entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
        boolean existsByPositionName(String positionName);
}

