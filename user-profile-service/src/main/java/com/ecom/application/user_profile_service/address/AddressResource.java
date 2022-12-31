package com.ecom.application.user_profile_service.address;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;


@RestController
@RequestMapping(value = "/addresss", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressResource {

    private final AddressService addressService;

    public AddressResource(final AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresss() {
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable final Long addressId) {
        return ResponseEntity.ok(addressService.get(addressId));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createAddress(@RequestBody @Valid final AddressDTO addressDTO) {
        return new ResponseEntity<>(addressService.create(addressDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<Void> updateAddress(@PathVariable final Long addressId,
            @RequestBody @Valid final AddressDTO addressDTO) {
        addressService.update(addressId, addressDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{addressId}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteAddress(@PathVariable final Long addressId) {
        addressService.delete(addressId);
        return ResponseEntity.noContent().build();
    }

}
