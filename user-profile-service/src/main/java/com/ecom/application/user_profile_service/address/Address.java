package com.ecom.application.user_profile_service.address;

import java.time.OffsetDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.ecom.application.user_profile_service.user.Title;
import com.ecom.application.user_profile_service.user.User;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Document
@Getter
@Setter
public class Address {

    @Id
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

    @DocumentReference(lazy = false)
    private User user;

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
