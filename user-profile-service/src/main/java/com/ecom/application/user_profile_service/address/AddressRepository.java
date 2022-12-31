package com.ecom.application.user_profile_service.address;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface AddressRepository extends MongoRepository<Address, Long> {
}
