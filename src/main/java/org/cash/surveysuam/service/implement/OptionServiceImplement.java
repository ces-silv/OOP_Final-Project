package org.cash.surveysuam.service.implement;

import org.cash.surveysuam.model.survey.Option;
import org.cash.surveysuam.repository.OptionRepository;
import org.cash.surveysuam.service.interfaces.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImplement implements OptionService {

    @Autowired
    private OptionRepository optionRepository;

    @Override
    public List<Option> getOptionsById(List<Long> ids) {
        return optionRepository.findAllById(ids);
    }
}
