package org.cash.surveysuam.service.interfaces;

import java.util.UUID;

public interface ParticipationService {

    boolean hasParticipatedOrNot(UUID surveyId, String participationToken);

    void registerParticipation(UUID surveyId, String participationToken);

}
