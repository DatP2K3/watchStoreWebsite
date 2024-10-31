package com.watch.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShippingInformationRequestDTO {
    int id;
    @NotBlank(message = "Full name cannot be blank")
    String fullName;
    @NotBlank(message = "Phone cannot be blank")
    @Pattern(regexp = "^(\\+84|0)\\d{9}$", message = "Phone number must be in the form of +84xxxxxxxxx or 0xxxxxxxxx")
    String phone;
    @NotBlank(message = "City cannot be blank")
    String city;
    @NotBlank(message = "District cannot be blank")
    String district;
    @NotBlank(message = "Ward cannot be blank")
    String ward;
    @NotBlank(message = "Detailed address cannot be blank")
    String detailedAddress;
}
