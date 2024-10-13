package com.yw.userAPI.controller.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    @NotEmpty(message = "The user name is required.")
    @Column(nullable = false, unique = true)
    private String userName;

    @NotEmpty(message = "The first name is required.")
    private String firstName;

    @NotEmpty(message = "The last name is required.")
    private String lastName;

    @NotEmpty(message = "The email is required.")
    @Email(message = "The email address is invalid.", flags = { Pattern.Flag.CASE_INSENSITIVE })
    @Column(nullable = false, unique = true)
    private String email;

    @NotEmpty(message = "The phone number is required.")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be valid and contain 10 to 15 digits")
    private String phoneNumber;
}
