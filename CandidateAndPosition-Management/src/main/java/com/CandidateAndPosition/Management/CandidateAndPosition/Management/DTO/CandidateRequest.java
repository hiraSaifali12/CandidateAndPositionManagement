package com.CandidateAndPosition.Management.CandidateAndPosition.Management.DTO;

import java.time.LocalDate;
import java.util.List;

public class CandidateRequest {
    public String name;
    public String email;
    public LocalDate dob;
    public List<Long> positionIds;
}
