package org.cash.surveysuam.api;

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

    @Value("${uam.api.url}") //Injecting Api URL
    private String baseUrl; // Now baseUrl will have the value of the injected Api URL

    // Main Method to Authenticate Students Credentials and get Students Data
    public List<StudentData> authenticateStudent(String cif, String pass) {
        try {
            //Creating a new instance of RestTemplate, usefully to make HTTP calls
            RestTemplate restTemplate = new RestTemplate();

            // The url has changed, now we are going to be located at the login
            String loginUrl = baseUrl + "login";
            // Creating a new request with user credentials
            LoginRequest request = new LoginRequest(cif, pass);

            // Creating headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json"); // Request body is JSON
            headers.add("X-Uam-Secure-Api", "version-1.0"); // Custom Header | Required by the API, we don't know why, but it doesn't work without it

            HttpEntity<LoginRequest> entity = new HttpEntity<>(request, headers); // Combine the request headers and body into an HttpEntity object

            ResponseEntity<String> loginResponse = restTemplate.exchange(loginUrl, HttpMethod.POST, entity, String.class); // Will send the request (As POST) to the login endpoint

            if (!loginResponse.getStatusCode().is2xxSuccessful()) { // If the response isn't Successful then throw an Exception with some details
                throw new RuntimeException("Error Trying to Log-In | " + loginResponse.getStatusCode() + " | " + loginResponse.getBody());
            } // if the response is successful then we can continue :D

            // The response will give us a Token so we have to prepared to get it
            String token = loginResponse.getBody(); // Receiving the token
            String authToken = "Bearer " + token; // Preparing the token in Bearer format, will be necessary to the next request
            // Bearer means that the client of that Token is authorized of making a protected request

            // Now we will get the data of the user
            // The url has changed again, now we are going to be located at GetStudentInformation + cif
            String dataUrl = baseUrl + "GetStudentInformation?cif=" + cif;

            // Creating headers
            HttpHeaders dataHeaders = new HttpHeaders();
            dataHeaders.add("Accept", "application/json");       // Response body is JSON
            dataHeaders.add("Content-Type", "application/json"); // Request body is JSON
            dataHeaders.add("X-Uam-Secure-Api", "version-1.0");  // Custom Header
            dataHeaders.add("Authorization", authToken);                    // Adding the token

            // Creating a new HttpEntity with the new headers (A GET Request doesn't have a body, that's why its void)
            HttpEntity<Void> dataEntity = new HttpEntity<>(dataHeaders);

            // Send the request as GET
            ResponseEntity<ResponseApi> dataResponse = restTemplate.exchange(dataUrl, HttpMethod.GET, dataEntity, ResponseApi.class);

            // If the response isn't Successful then throw an Exception with some details
            if (!dataResponse.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Error trying to get Student Data: " + dataResponse.getStatusCode());
            } // if the response is successful then we can continue and almost finish :D

            // GET the body of the response (user data)
            ResponseApi apiResponse = dataResponse.getBody();

            // If the response was successful and was data then
            if (apiResponse != null && apiResponse.isSuccessOrNot() && !apiResponse.getStudentDataList().isEmpty()) {
                return apiResponse.getStudentDataList(); // Return the StudentDataList
            } else {
                throw new RuntimeException("Error getting Student Data: " + (apiResponse != null ? apiResponse.getMessage() : "Empty Response"));
            } // else we throw an exception, the api response wasn't successful or the Response was empty

        } catch (Exception e) { // if there was an error not handled, we handle here (kinda general)
            throw new RuntimeException("Not Valid Access - Please Try Again | " + e.getMessage());
        }
    }
}