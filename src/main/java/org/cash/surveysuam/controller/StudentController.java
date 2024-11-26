package org.cash.surveysuam.controller;

import org.cash.surveysuam.dto.LoginRequest;
import org.cash.surveysuam.dto.StudentData;
import org.cash.surveysuam.service.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/login")
    public ResponseEntity<List<StudentData>> authenticate(@RequestBody LoginRequest loginRequest) {
        List<StudentData> studentData = studentService.authenticate(loginRequest);
        return ResponseEntity.ok(studentData);
    }

}
