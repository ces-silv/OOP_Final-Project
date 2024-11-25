package org.cash.surveysuam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@ToString @EqualsAndHashCode
@Getter @Setter
@AllArgsConstructor
public class ResponseApi {

    @JsonProperty("success")
    private boolean successOrNot;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private List<StudentData> studentDataList;
}
