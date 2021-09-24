package com.it3920.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Pattern(regexp = "^[a-zA-z0-9]{4,}$", message = "username must be alphanumeric character and at least 6 character")
    private String username;
    @NotBlank
    @Size(min=10, max=12)
    private String phoneNumber;
    private long score;
    private String fullName;
    private String role;
    private boolean isVerifiedPhoneNumber;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
