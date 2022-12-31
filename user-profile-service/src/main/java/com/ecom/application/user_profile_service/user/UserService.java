package com.ecom.application.user_profile_service.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecom.application.user_profile_service.util.NotFoundException;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findAll() {
        final List<User> users = userRepository.findAll(Sort.by("customerId"));
        return users.stream()
                .map((user) -> mapToDTO(user, new UserDTO()))
                .collect(Collectors.toList());
    }

    public UserDTO get(final Long customerId) {
        return userRepository.findById(customerId)
                .map(user -> mapToDTO(user, new UserDTO()))
                .orElseThrow(() -> new NotFoundException());
    }

    public Long create(final UserDTO userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        return userRepository.save(user).getCustomerId();
    }

    public void update(final Long customerId, final UserDTO userDTO) {
        final User user = userRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException());
        mapToEntity(userDTO, user);
        userRepository.save(user);
    }

    public void delete(final Long customerId) {
        userRepository.deleteById(customerId);
    }

    private UserDTO mapToDTO(final User user, final UserDTO userDTO) {
        userDTO.setCustomerId(user.getCustomerId());
        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setMiddleName(user.getMiddleName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setGovernmentId(user.getGovernmentId());
        userDTO.setIsEnabled(user.getIsEnabled());
        userDTO.setIsEmailVerified(user.getIsEmailVerified());
        userDTO.setBirthDate(user.getBirthDate());
        userDTO.setUserType(user.getUserType());
        userDTO.setGender(user.getGender());
        userDTO.setTitle(user.getTitle());
        return userDTO;
    }

    private User mapToEntity(final UserDTO userDTO, final User user) {
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setMiddleName(userDTO.getMiddleName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setGovernmentId(userDTO.getGovernmentId());
        user.setIsEnabled(userDTO.getIsEnabled());
        user.setIsEmailVerified(userDTO.getIsEmailVerified());
        user.setBirthDate(userDTO.getBirthDate());
        user.setUserType(userDTO.getUserType());
        user.setGender(userDTO.getGender());
        user.setTitle(userDTO.getTitle());
        return user;
    }

    public boolean emailExists(final String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

}
