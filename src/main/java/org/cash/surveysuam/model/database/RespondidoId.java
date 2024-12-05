package org.cash.surveysuam.model.database;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable @Getter
@Setter @NoArgsConstructor @AllArgsConstructor
@ToString @EqualsAndHashCode
public class RespondidoId implements Serializable {
    private String IdRespondido;
    private String ClaseId;
    private int Grupo;
}
