package com.it3920.shop.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @NotNull
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId productId;

    private String productName;
    @Positive
    private int size;
    @NotBlank
    private String color;
    @Positive
    private int amount;
    @Min(value=100)
    private int price;
}
