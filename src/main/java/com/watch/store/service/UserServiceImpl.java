package com.watch.store.service;

import com.watch.store.dto.request.ShippingInformationRequestDTO;
import com.watch.store.dto.request.UserRequestDTO;
import com.watch.store.dto.response.UserResponseDTO;
import com.watch.store.exception.ResourceNotFoundException;
import com.watch.store.exception.UserAlreadyExistsException;
import com.watch.store.mapper.ShippingInformationMapper;
import com.watch.store.mapper.UserMapper;
import com.watch.store.model.Role;
import com.watch.store.model.ShippingInformation;
import com.watch.store.model.User;
import com.watch.store.repository.RoleRepository;
import com.watch.store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ShippingInformationMapper shippingInformationMapper;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByEmail(userRequestDTO.getEmail())){
            throw new UserAlreadyExistsException(userRequestDTO.getEmail() + " already exists");
        }
        User user = userMapper.UserRequestDTOToUser(userRequestDTO);
        Role role = roleRepository.findById(userRequestDTO.getRoleID())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + userRequestDTO.getRoleID()));
        role.assignRoleToUser(user);
        return userMapper.userToUserResponseDTO(userRepository.save(user));
    }

    @Override
    public UserResponseDTO getUserByID(int userID) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userID));
        return userMapper.userToUserResponseDTO(user);
    }

    @Override
    public void changePassword(int userID, String newPassword) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userID));
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public void changeAvatar(int userID, String newAvatar) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userID));
        user.setAvatar(newAvatar);
        userRepository.save(user);
    }

    @Override
    public void changeEmail(int userID, String newEmail) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userID));
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    @Override
    public void changeShippingInformation(int userID, ShippingInformationRequestDTO shippingInformationRequestDTO) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userID));
        ShippingInformation shippingInformationOfUser = user.getShippingInformation();
        if (shippingInformationOfUser == null) {
            ShippingInformation shippingInformation = shippingInformationMapper.shippingInformationRequestDTOToShippingInformation(shippingInformationRequestDTO);
            user.setShippingInformation(shippingInformation);
            userRepository.save(user);
        } else {
            shippingInformationMapper.updateShippingInformationFromShippingInformationRequestDTO(shippingInformationRequestDTO, shippingInformationOfUser);
            user.setShippingInformation(shippingInformationOfUser);
            userRepository.save(user);
        }
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOs = new ArrayList<>();
        for (User user : users) {
            userResponseDTOs.add(userMapper.userToUserResponseDTO(user));
        }
        return userResponseDTOs;
    }

    @Override
    public void deleteUser(int userID) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userID));
        Role role = user.getRole();
            role.removeUserFromRole(user);
        userRepository.delete(user);
    }
}
