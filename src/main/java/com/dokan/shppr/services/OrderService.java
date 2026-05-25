package com.dokan.shppr.services;

import com.dokan.shppr.dto.OrderItemsRequest;
import com.dokan.shppr.dto.OrderRequest;
import com.dokan.shppr.entities.Order;
import com.dokan.shppr.entities.OrderItems;
import com.dokan.shppr.entities.Products;
import com.dokan.shppr.repository.OrderItemsRepo;
import com.dokan.shppr.repository.OrderRepo;
import com.dokan.shppr.repository.ProductRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepo orderRepo;
    private final OrderItemsRepo orderItemsRepo;
    private final ProductRepo productRepo;

    @Transactional
    public Order createOrder(OrderRequest request) {
        Order order = Order.builder()
                .customer_name(request.getCustomerName())
                .customer_email(request.getCustomerEmail())
                .customer_status(request.getCustomerStatus())
                .total_price(BigDecimal.ZERO)
                .build();

        order = orderRepo.save(order);

        List<OrderItems> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemsRequest itemRequest : request.getOrderItems()) {
            Products product = productRepo.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found by id: " + itemRequest.getProductId()));

            if (product.getStock_quantity() < itemRequest.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            BigDecimal itemPrice = product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity()));

            OrderItems orderItem = OrderItems.builder()
                    .order(order)
                    .products(product)
                    .quantity(itemRequest.getQuantity())
                    .price(itemPrice)
                    .build();

            items.add(orderItem);
            total = total.add(itemPrice);

            product.setStock_quantity(product.getStock_quantity() - itemRequest.getQuantity());
            productRepo.save(product);
        }

        orderItemsRepo.saveAll(items);

        order.setTotal_price(total);
        order.setOrder_items(items);
        return orderRepo.save(order);
    }

    public List<Order> getOrders() {
        return orderRepo.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found by id: " + id));
    }
}

