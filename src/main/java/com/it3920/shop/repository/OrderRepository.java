package com.it3920.shop.repository;

import com.it3920.shop.entity.Order;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, ObjectId> {
    @Query("{'orderItems.productId' : ?0}")
    List<Order> findOrdersGotProductId(ObjectId productId);
}
