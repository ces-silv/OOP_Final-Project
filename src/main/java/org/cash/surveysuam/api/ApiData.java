package org.cash.surveysuam.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.cash.surveysuam.dto.LoginRequest;
import org.cash.surveysuam.dto.ResponseApi;
import org.cash.surveysuam.dto.StudentData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiData {

    @Value("${uam.api.url}")
    private String baseUrl;

    public List<StudentData> authenticateStudent(String cif, String pass) {
        try {
            RestTemplate restTemplate = new RestTemplate();

            // 1. Primer llamado: autenticación y obtención del token
            String loginUrl = baseUrl + "login";
            LoginRequest request = new LoginRequest(cif, pass);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            headers.add("X-Uam-Secure-Api", "version-1.0");

            HttpEntity<LoginRequest> entity = new HttpEntity<>(request, headers);

            System.out.println("URL: " + loginUrl);
            System.out.println("Headers: " + headers);
            System.out.println("Cuerpo JSON: " + new ObjectMapper().writeValueAsString(request));


            ResponseEntity<String> loginResponse = restTemplate.exchange(loginUrl, HttpMethod.POST, entity, String.class);

            if (!loginResponse.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Error Trying to Log-In | " + loginResponse.getStatusCode() + " | " + loginResponse.getBody());
            }

            String token = loginResponse.getBody();
            String authToken = "Bearer " + token;

            // 2. Segundo llamado: obtención de los datos del estudiante
            String dataUrl = baseUrl + "GetStudentInformation?cif=" + cif;

            HttpHeaders dataHeaders = new HttpHeaders();
            dataHeaders.add("Accept", "application/json");
            dataHeaders.add("Content-Type", "application/json");
            dataHeaders.add("X-Uam-Secure-Api", "version-1.0");
            dataHeaders.add("Authorization", authToken);

            HttpEntity<Void> dataEntity = new HttpEntity<>(dataHeaders);

            ResponseEntity<ResponseApi> dataResponse = restTemplate.exchange(dataUrl, HttpMethod.GET, dataEntity, ResponseApi.class);

            if (!dataResponse.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Error al obtener datos del estudiante: " + dataResponse.getStatusCode());
            }

            ResponseApi apiResponse = dataResponse.getBody();

            if (apiResponse != null && apiResponse.isSuccessOrNot() && !apiResponse.getStudentDataList().isEmpty()) {
                return apiResponse.getStudentDataList(); // Devolver toda la lista de datos
            } else {
                throw new RuntimeException("Error al obtener datos del estudiante: " + (apiResponse != null ? apiResponse.getMessage() : "Respuesta vacía"));
            }

        } catch (Exception e) {
            throw new RuntimeException("Acceso inválido. Por favor, inténtelo otra vez ");
        }
    }
}
