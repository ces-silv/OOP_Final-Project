package org.cash.surveysuam.model.survey;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
public class Survey {

    @Id @GeneratedValue // A UUID as the id for the Survey (Automatically Generated)
    private UUID id;

    private String title;
    private String description;

    // CascadeType.ALL - Operations like persist or remove executed at the Survey will also have effect at the List of Questions
    // So, if we Delete the Survey the List of Questions will also be Deleted
    // orphanRemoval - If the Question is deleted from the list, will also be deleted from the database
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();
    private LocalDateTime createdAt; // Just the time :P

    private String faculty;

    // When a New Survey its created with no args then "createdAt" will be equal to the LocalTime and LocalDate
    public Survey() {
        this.createdAt = LocalDateTime.now();
    }

}
