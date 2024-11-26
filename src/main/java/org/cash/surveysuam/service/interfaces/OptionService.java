package org.cash.surveysuam.service.interfaces;

import org.cash.surveysuam.model.survey.Option;

import java.util.List;

public interface OptionService {

    List<Option> getOptionsById(List<Long> ids);

}
