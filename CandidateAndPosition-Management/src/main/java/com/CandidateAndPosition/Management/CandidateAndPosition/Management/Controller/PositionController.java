package com.CandidateAndPosition.Management.CandidateAndPosition.Management.Controller;

import com.CandidateAndPosition.Management.CandidateAndPosition.Management.Entity.Position;
import com.CandidateAndPosition.Management.CandidateAndPosition.Management.Service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/positions")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @PostMapping
    public ResponseEntity<?> createPosition(@RequestBody Position position) {
        try {
            Position savedPosition = positionService.createPosition(position);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPosition);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Position>> getAllPositions() {
        return ResponseEntity.ok(positionService.getAllPositions());
    }
}
