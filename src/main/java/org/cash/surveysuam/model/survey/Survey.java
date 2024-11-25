package org.cash.surveysuam.model.survey;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Survey {

    @Id
    private UUID id;

    private String title;
    private String description;

    private List<Question> questions = new ArrayList<>();
    private LocalDateTime createdAt;

}
