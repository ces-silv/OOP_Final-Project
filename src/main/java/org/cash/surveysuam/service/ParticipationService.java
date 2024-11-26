package org.cash.surveysuam.service;

import java.util.UUID;

public interface ParticipationService {

    boolean hasParticipatedOrNot(UUID surveyId, String participationToken);

    void registerParticipation(UUID surveyId, String participationToken);

}
