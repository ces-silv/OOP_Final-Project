package org.cash.surveysuam.model.database;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable @Getter
@Setter @NoArgsConstructor @AllArgsConstructor
@ToString @EqualsAndHashCode
public class RespondidoId implements Serializable { // "Serializable" allows us to be sure that the object can be serialized (stored in the database for example) or deserialized
    // "RespondidoId" it's a Compound Key for "Respondido"
    private String IdRespondido;
    private String ClaseId;
    private int Grupo;
}
