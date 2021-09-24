package com.it3920.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @NotBlank(message = "Thiếu tên màu")
    private String color;
    @NotNull(message = "Thiếu thông tin về kích thước và số lượng")
    private List<SizeAndQuantity> list;
}
