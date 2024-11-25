package org.cash.surveysuam.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @EqualsAndHashCode
@Getter @Setter
@AllArgsConstructor
public class LoginRequest {
    private String cif; // CIF
    private String password; // Password
}
