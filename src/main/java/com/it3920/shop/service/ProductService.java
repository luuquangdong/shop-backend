package com.it3920.shop.service;

import com.it3920.shop.entity.Order;
import com.it3920.shop.entity.Product;
import com.it3920.shop.exception.InvalidException;
import com.it3920.shop.exception.NotFoundException;
import com.it3920.shop.repository.OrderRepository;
import com.it3920.shop.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProduct(String id){
        ObjectId objId = new ObjectId(id);
        Optional<Product> productOpt = productRepository.findById(objId);
        return productOpt.orElse(null);
    }

    public Product create(Product product){
        product.setId(null);
        product.preprocess();
        return productRepository.save(product);
    }

    public Product update(Product product, String id){
        ObjectId objId = new ObjectId(id);
        if(product.getId().compareTo(objId) != 0)
            throw new InvalidException("Id không hợp lệ", 1010);
        productRepository.findById(objId).orElseThrow(() -> new NotFoundException("Product không tồn tại", 1011));
        return productRepository.save(product);
    }

    public Product delete(String id){
        ObjectId objId = new ObjectId(id);
        Product product = productRepository.findById(objId)
            .orElseThrow(() -> new NotFoundException("Product không tồn tại", 1011));

        List<Order> orders = orderRepository.findOrdersGotProductId(objId);
        if(orders.size() > 0)
            throw new InvalidException("Không thể xóa sản phẩm do sản phẩm đã có liên kết với đơn hàng", 1012);

        productRepository.deleteById(objId);
        return product;
    }
}
