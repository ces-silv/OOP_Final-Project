package org.cash.surveysuam.model.survey;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
public class Participation {

    @Id @GeneratedValue
    private UUID id;

    @ManyToOne @JoinColumn(name = "survey_id")
    private Survey survey;

    private String participationToken;

    private LocalDateTime respondedAt;

    public Participation() {
        this.respondedAt = LocalDateTime.now();
    }

}
