package com.it3920.shop.entity;

import com.it3920.shop.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
//    @Id
//    @JsonSerialize(using = ToStringSerializer.class)
//    private ObjectId id;
    @Id
    @Pattern(regexp = "^[a-zA-z0-9]{4,}$", message = "username must be alphanumeric character and at least 6 character")
    private String username;

    @NotBlank(message = "password is required")
    @Size(min=5, message = "password must be at least 5 charactor")
    private String password;

    @NotBlank
    @Size(min=10, max=12)
    private String phoneNumber;
    private long score;
    private String fullName;
    private String role;
    private boolean isVerifiedPhoneNumber;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public void updateInfo(UserDto userDto){
        this.fullName = userDto.getFullName();
    }
}
