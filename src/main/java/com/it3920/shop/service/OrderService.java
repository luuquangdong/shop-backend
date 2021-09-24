package com.it3920.shop.service;

import com.it3920.shop.entity.Order;
import com.it3920.shop.entity.OrderItem;
import com.it3920.shop.entity.Product;
import com.it3920.shop.exception.InvalidException;
import com.it3920.shop.exception.NotFoundException;
import com.it3920.shop.repository.OrderRepository;
import com.it3920.shop.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order createOrder(Order order){
        List<Product> products = new ArrayList<>();

        long totalPrice = 0;

        // kiểm tra còn hàng và giảm số lượng tương ứng với order
//        order.getOrderItems().forEach(orderItem -> {
//            Optional<Product> productOpt = productRepository.findById(orderItem.getProductId());
//            Product product = productOpt.orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm", 1012));
//            if(!product.available(orderItem))
//                throw new InvalidException("Sản phẩm đã hết hàng", 1013);
//            product.makeOrder(orderItem);
//            products.add(product);
//
//            totalPrice += orderItem.getPrice()*orderItem.getAmount();
//        });

        for(OrderItem orderItem : order.getOrderItems()) {
            Optional<Product> productOpt = productRepository.findById(orderItem.getProductId());
            Product product = productOpt.orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm", 1012));
            if(!product.available(orderItem))
                throw new InvalidException("Sản phẩm đã hết hàng", 1013);
            product.makeOrder(orderItem);
            products.add(product);

            totalPrice += orderItem.getPrice()*orderItem.getAmount();
        };

        order.setId(null);
        order.setState("NOT_VERIFY");
        order.setTotalPrice(totalPrice);
        Order savedOrder = orderRepository.save(order);
        productRepository.saveAll(products);



        return savedOrder;
    }

    public Order update(Order order){
        orderRepository.findById(order.getId()).orElseThrow(() -> new NotFoundException("Order không tồn tại", 1021));
        return orderRepository.save(order);
    }

    public Order cancel(String id){
        Order order = orderRepository.findById(new ObjectId(id)).orElseThrow(() -> new NotFoundException("Order không tồn tại", 1021));
        List<Product> products = new ArrayList<>();

        order.getOrderItems().forEach(orderItem -> {
            Optional<Product> productOpt = productRepository.findById(orderItem.getProductId());
            Product product = productOpt.orElseThrow(() -> new NotFoundException("Không tìm thấy sản phẩm", 1012));

            product.cancelOrder(orderItem);
            products.add(product);
        });

        productRepository.saveAll(products);

        order.setState("CANCEL");
        order = orderRepository.save(order);

        return order;
    }
}
