package org.cash.surveysuam.repository;

import org.cash.surveysuam.model.survey.ResponseContext;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResponseContextRepository extends JpaRepository<ResponseContext, UUID> {
}
