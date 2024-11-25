package org.cash.surveysuam.service;

import org.cash.surveysuam.dto.LoginRequest;
import org.cash.surveysuam.dto.StudentData;

import java.util.List;

public interface StudentService {
    List<StudentData> authenticate(LoginRequest loginRequest);
}
