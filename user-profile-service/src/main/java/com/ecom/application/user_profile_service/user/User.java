package com.ecom.application.user_profile_service.user;

import com.ecom.application.user_profile_service.address.Address;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


@Document
@Getter
@Setter
public class User {

    @Id
    private Long customerId;

    @NotNull
    @Size(max = 255)
    private String password;

    @NotNull
    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String middleName;

    @NotNull
    @Size(max = 255)
    private String lastName;

    @Indexed(unique = true)
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

    @DocumentReference(lazy = true, lookup = "{ 'address' : ?#{#self._id} }")
    @ReadOnlyProperty
    private Set<Address> addressAddresss;

    @CreatedDate
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    private OffsetDateTime lastUpdated;

    @Version
    private Integer version;

}
