package com.ecom.application.user_profile_service.user;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, Long> {

    boolean existsByEmailIgnoreCase(String email);

}
