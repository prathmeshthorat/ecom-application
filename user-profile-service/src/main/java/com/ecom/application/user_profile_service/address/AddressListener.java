package com.ecom.application.user_profile_service.address;

import com.ecom.application.user_profile_service.config.PrimarySequenceService;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class AddressListener extends AbstractMongoEventListener<Address> {

    private final PrimarySequenceService primarySequenceService;

    public AddressListener(final PrimarySequenceService primarySequenceService) {
        this.primarySequenceService = primarySequenceService;
    }

    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Address> event) {
        if (event.getSource().getAddressId() == null) {
            event.getSource().setAddressId(primarySequenceService.getNextValue());
        }
    }

}
