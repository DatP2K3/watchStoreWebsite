package com.watch.store.controller;

import com.watch.store.dto.request.InfoUserRequestDTO;
import com.watch.store.dto.request.ShippingInformationRequestDTO;
import com.watch.store.dto.request.UserRequestDTO;
import com.watch.store.dto.response.UserResponseDTO;
import com.watch.store.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/users")
    ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);
        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping("/users/{userID}")
    ResponseEntity<UserResponseDTO> getUser(@PathVariable int userID) {
        UserResponseDTO userResponseDTO = userService.getUserByID(userID);
        return ResponseEntity.ok(userResponseDTO);
    }

    @GetMapping("/users")
    ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> userResponseDTOs = userService.getAllUsers();
        return ResponseEntity.ok(userResponseDTOs);
    }

    @PutMapping("/users/{userID}/email")
    ResponseEntity<Void> updateEmail(@PathVariable int userID, @Valid @RequestBody InfoUserRequestDTO infoUserRequestDTO) {
        userService.changeEmail(userID, infoUserRequestDTO.getEmail());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{userID}/password")
    ResponseEntity<Void> updatePassword(@PathVariable int userID,  @Valid @RequestBody InfoUserRequestDTO infoUserRequestDTO) {
        userService.changePassword(userID, passwordEncoder.encode(infoUserRequestDTO.getPassword()));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{userID}/avatar")
    ResponseEntity<Void> updateAvatar(@PathVariable int userID,  @Valid @RequestBody InfoUserRequestDTO infoUserRequestDTOr) {
        userService.changeAvatar(userID, infoUserRequestDTOr.getAvatar());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/users/{userID}/shippingInformation")
    ResponseEntity<Void> updateShippingInformation(@PathVariable int userID, @RequestBody ShippingInformationRequestDTO shippingInformationRequestDTO) {
        userService.changeShippingInformation(userID, shippingInformationRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{userID}")
    ResponseEntity<Void> deleteUser(@PathVariable int userID) {
        userService.deleteUser(userID);
        return ResponseEntity.ok().build();
    }
}
