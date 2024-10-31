package com.watch.store.mapper;

import com.watch.store.dto.request.UserRequestDTO;
import com.watch.store.dto.response.UserResponseDTO;
import com.watch.store.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "role.id", target = "roleID")
    UserResponseDTO userToUserResponseDTO(User user);
    User UserRequestDTOToUser(UserRequestDTO userRequestDTO);
}
