package com.ecom.application.user_profile_service.address;

import com.ecom.application.user_profile_service.user.Title;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddressDTO {

    private Long addressId;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    private String firstName;

    @NotNull
    @Size(max = 255)
    private String lastName;

    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String phoneNumber;

    @NotNull
    private AddressType type;

    private Title title;

    @NotNull
    private Boolean isDefault;

    private Long user;

}
