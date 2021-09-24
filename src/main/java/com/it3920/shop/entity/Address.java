package com.it3920.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @NotBlank
    private String province;
    @NotBlank
    private String district;
    @NotBlank
    private String ward;
    @NotBlank
    private String detail;
}
