package com.watch.store.dto.response;

import com.watch.store.model.Role;
import com.watch.store.model.ShippingInformation;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.HashSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDTO {
    int id;
    String email;
    String password;
    String avatar;
    int RoleID;
    ShippingInformation shippingInformation;
}
