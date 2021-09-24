package com.it3920.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;
    @NotBlank
    private String customerName;
    @NotBlank
    private String phoneNumber;
    private String state;
    private String paymentMethod;
    @CreatedDate
    private LocalDateTime createdDate;
    private LocalDateTime shipDate;
    private LocalDateTime receivedDate;
    private long totalPrice;
    @NotNull
    private Address address;
    @NotNull
    private List<OrderItem> orderItems;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @JsonIgnore
    public List<ObjectId> getListProductId(){
        return orderItems.stream()
                .map(oi -> oi.getProductId())
                .collect(Collectors.toList());
    }
}
