package com.bangvan.apiblogapp.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotNull(message = "First name is required!")
    private String firstName;
    private String middleName;
    @NotNull(message = "Last name is required!")
    private String lastName;
    private String phone;
    private String imageUrl;
    private String facebook;
    private String Youtube;
    private String instagram;
    private String spotify;
}
