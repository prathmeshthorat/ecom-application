package com.ecom.application.user_profile_service.address;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecom.application.user_profile_service.user.User;
import com.ecom.application.user_profile_service.user.UserRepository;
import com.ecom.application.user_profile_service.util.NotFoundException;

import com.ecom.application.common.model.AddressDTO;


@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressService(final AddressRepository addressRepository,
            final UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    public List<AddressDTO> findAll() {
        final List<Address> addresss = addressRepository.findAll(Sort.by("addressId"));
        return addresss.stream()
                .map((address) -> mapToDTO(address, new AddressDTO()))
                .collect(Collectors.toList());
    }

    public AddressDTO get(final Long addressId) {
        return addressRepository.findById(addressId)
                .map(address -> mapToDTO(address, new AddressDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final AddressDTO addressDTO) {
        final Address address = new Address();
        mapToEntity(addressDTO, address);
        return addressRepository.save(address).getAddressId();
    }

    public void update(final Long addressId, final AddressDTO addressDTO) {
        final Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(addressDTO, address);
        addressRepository.save(address);
    }

    public void delete(final Long addressId) {
        addressRepository.deleteById(addressId);
    }

    private AddressDTO mapToDTO(final Address address, final AddressDTO addressDTO) {
        addressDTO.setAddressId(address.getAddressId());
        addressDTO.setName(address.getName());
        addressDTO.setFirstName(address.getFirstName());
        addressDTO.setLastName(address.getLastName());
        addressDTO.setEmail(address.getEmail());
        addressDTO.setPhoneNumber(address.getPhoneNumber());
        addressDTO.setType(address.getType());
        addressDTO.setTitle(address.getTitle());
        addressDTO.setIsDefault(address.getIsDefault());
        addressDTO.setUser(address.getUser() == null ? null : address.getUser().getCustomerId());
        return addressDTO;
    }

    private Address mapToEntity(final AddressDTO addressDTO, final Address address) {
        address.setName(addressDTO.getName());
        address.setFirstName(addressDTO.getFirstName());
        address.setLastName(addressDTO.getLastName());
        address.setEmail(addressDTO.getEmail());
        address.setPhoneNumber(addressDTO.getPhoneNumber());
        address.setType(addressDTO.getType());
        address.setTitle(addressDTO.getTitle());
        address.setIsDefault(addressDTO.getIsDefault());
        final User user = addressDTO.getUser() == null ? null : userRepository.findById(addressDTO.getUser())
                .orElseThrow(() -> new NotFoundException("address not found"));
        address.setUser(user);
        return address;
    }

}
