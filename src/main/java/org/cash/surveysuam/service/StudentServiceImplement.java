package org.cash.surveysuam.service;

import org.cash.surveysuam.api.ApiData;
import org.cash.surveysuam.dto.LoginRequest;
import org.cash.surveysuam.dto.StudentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImplement implements StudentService {

    private final ApiData apiData;

    @Autowired
    public StudentServiceImplement(ApiData apiData) {
        this.apiData = apiData;
    }

    @Override
    public List<StudentData> authenticate(LoginRequest loginRequest) {
        String cif = loginRequest.getCif();
        String password = loginRequest.getPassword();
        return apiData.authenticateStudent(cif, password);
    }
}
