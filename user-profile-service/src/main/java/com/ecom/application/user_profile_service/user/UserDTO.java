package com.ecom.application.user_profile_service.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Long customerId;

    @NotNull
    @Size(max = 16)
    private String password;

    @NotNull
    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String middleName;

    @NotNull
    @Size(max = 255)
    private String lastName;

    @NotNull
    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String phoneNumber;

    @Size(max = 255)
    private String governmentId;

    @NotNull
    private Boolean isEnabled;

    @NotNull
    private Boolean isEmailVerified;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private UserType userType;

    @NotNull
    private Gender gender;

    @NotNull
    private Title title;

}
