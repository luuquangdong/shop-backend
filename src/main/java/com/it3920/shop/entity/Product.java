package com.it3920.shop.entity;

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

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Document
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @NotBlank(message = "Tên sản phẩm không được trống")
    private String name;

    @Min(value = 100, message = "Giá trị tiền không hợp lệ")
    private int price;

    @NotBlank(message = "Thương hiệu không được trống")
    private String brand;

    private long totalQuantity;

    private String shortDescription;
    private String details;

    @NotNull(message = "Thiếu danh sách sản phẩm")
    private List<Item> items;

    private List<String> images;

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public void preprocess(){
        int sum = 0;
        for(Item item : this.items){
            for(SizeAndQuantity saq : item.getList()){
                sum += saq.getQuantity();
            }
        }
        this.totalQuantity = sum;
    }

    public boolean available(OrderItem orderItem){
        SizeAndQuantity saq = getSizeAndQuantity(orderItem.getColor(), orderItem.getSize());
        if(saq == null) return false;
        return saq.getQuantity() >= orderItem.getAmount();
    }

    public void makeOrder(OrderItem orderItem){
        SizeAndQuantity saq = getSizeAndQuantity(orderItem.getColor(), orderItem.getSize());
        saq.setQuantity(saq.getQuantity() - orderItem.getAmount());
    }

    public void cancelOrder(OrderItem orderItem){
        SizeAndQuantity saq = getSizeAndQuantity(orderItem.getColor(), orderItem.getSize());
        saq.setQuantity(saq.getQuantity() + orderItem.getAmount());
    }

    public SizeAndQuantity getSizeAndQuantity(String color, int size) {
        // tìm item với màu tương ứng
        Optional<Item> itemOpt = items.stream()
                .filter(it -> it.getColor().equalsIgnoreCase(color))
                .findAny();
        if(itemOpt.isEmpty()) return null;
        // tìm SizeAndAuantity với size tương ứng
        Optional<SizeAndQuantity> saqOpt = itemOpt.get().getList().stream()
                .filter(saq -> saq.getSize() == size)
                .findAny();

        return saqOpt.orElse(null);
    }

}
