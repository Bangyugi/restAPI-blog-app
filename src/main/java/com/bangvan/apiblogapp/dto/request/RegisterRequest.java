package com.bangvan.apiblogapp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotNull(message = "Username is required!")
    private String username;
    @NotNull(message = "Password is required!")
    private String password;
    @NotNull(message = "Email is required!")
    @Email(message = "Email invalid!")
    private String email;
    @NotNull(message = "First name is required!")
    private String firstName;
    private String middleName;
    @NotNull(message = "Last name is required!")
    private String lastName;
}
