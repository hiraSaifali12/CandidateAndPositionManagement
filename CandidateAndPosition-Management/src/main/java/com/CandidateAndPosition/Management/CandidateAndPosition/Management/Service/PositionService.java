package com.CandidateAndPosition.Management.CandidateAndPosition.Management.Service;

import com.CandidateAndPosition.Management.CandidateAndPosition.Management.Entity.Position;
import com.CandidateAndPosition.Management.CandidateAndPosition.Management.Repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    public Position createPosition(Position position) {
        if (position.getPositionName() == null || position.getPositionName().isBlank()) {
            throw new IllegalArgumentException("Position name is required.");
        }

        if (position.getPositionName().length() > 50) {
            throw new IllegalArgumentException("Position name must be max 50 characters.");
        }

        if (positionRepository.existsByPositionName(position.getPositionName())) {
            throw new IllegalArgumentException("Position name must be unique.");
        }

        return positionRepository.save(position);
    }

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }
}
