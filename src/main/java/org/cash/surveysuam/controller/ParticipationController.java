package org.cash.surveysuam.controller;

import org.cash.surveysuam.service.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController @RequestMapping("/surv/participation")
public class ParticipationController {
    @Autowired
    private ParticipationService participationService;

    @GetMapping("/{surveyId}")
    public ResponseEntity<Boolean> hasParticipated(
            @PathVariable UUID surveyId,
            @RequestHeader("Participation-Token") String participationToken
    ) {
        boolean participated = participationService.hasParticipatedOrNot(surveyId, participationToken);
        return ResponseEntity.ok(participated);
    }
}
