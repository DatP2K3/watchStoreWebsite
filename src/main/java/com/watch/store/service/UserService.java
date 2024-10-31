package com.watch.store.service;

import com.watch.store.dto.request.ShippingInformationRequestDTO;
import com.watch.store.dto.request.UserRequestDTO;
import com.watch.store.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO getUserByID(int userID);
    void changePassword(int userId, String newPassword);
    void changeAvatar(int userId, String newAvatar);
    void changeEmail(int userId, String newEmail);
    void changeShippingInformation(int userID, ShippingInformationRequestDTO shippingInformationRequestDTO);
    List<UserResponseDTO> getAllUsers();
    void deleteUser(int userId);
}
