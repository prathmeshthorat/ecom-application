package com.ecom.application.user_profile_service.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Getter
@Setter
public class PrimarySequence {

    @Id
    private String id;

    private long seq;

}
