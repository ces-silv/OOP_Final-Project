package org.cash.surveysuam.repository;

import jakarta.annotation.Nonnull;
import org.cash.surveysuam.model.database.Respondido;
import org.cash.surveysuam.model.database.RespondidoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespondidoRepository extends JpaRepository<Respondido, RespondidoId> {
    boolean existsById(@Nonnull RespondidoId respondidoId);
}
